import asyncio
import os
import sys

here = os.path.dirname(__file__)
module_path = os.path.join(here)
root = os.path.dirname(module_path)
if module_path not in sys.path:
    sys.path.append(module_path)
if root not in sys.path:
    sys.path.append(root)

import platform
import tempfile
import subprocess
from typing import List, Tuple

import edge_tts

from scripts.script_parser import get_parser
from util import VOICES, parse_args


def play_audio(path: str) -> None:
    """
    Play an audio file using a safe, argument-list subprocess call.

    On Windows this hides the ffplay console window. Uses ffplay on
    Linux/Windows and afplay on macOS.
    """
    system = platform.system()
    if system == "Windows":
        cmd = ["ffplay", "-nodisp", "-autoexit", path]
        creationflags = subprocess.CREATE_NO_WINDOW
    elif system == "Darwin":
        cmd = ["afplay", path]
        creationflags = 0
    else:
        cmd = ["ffplay", "-nodisp", "-autoexit", path]
        creationflags = 0

    try:
        subprocess.run(
            cmd,
            stdout=subprocess.DEVNULL,
            stderr=subprocess.DEVNULL,
            check=False,
            creationflags=creationflags,
        )
    except FileNotFoundError:
        print("Playback command not found. Install FFmpeg (ffplay) or ensure afplay is available on macOS.")


# Generate and play audio for a line in real-time
async def speak_text(speaker: str, text: str, speed: str = "-20%") -> None:
    """

    :param speaker:
    :param text:
    :param speed: "+0%", can be "-10%"
    :return:
    """
    voice = VOICES.get(speaker)
    print(f"{speaker}: {text}")

    # Create temporary audio file
    with tempfile.NamedTemporaryFile(suffix=".mp3", delete=False) as temp_audio:
        temp_audio_path = temp_audio.name

    # Generate speech
    communicate = edge_tts.Communicate(text, voice, rate=speed)
    await communicate.save(temp_audio_path)

    # Play audio
    play_audio(temp_audio_path)

    # Clean up temporary file
    os.remove(temp_audio_path)


# Read and speak the conversation
async def read_conversation(script: List[Tuple[str, str]], speed: str = "-10%") -> None:
    for speaker, text, _ in script:
        await speak_text(speaker, text, speed)
        await asyncio.sleep(0.5)  # Small pause between lines


# Run script
if __name__ == "__main__":
    args = parse_args()
    parser = get_parser(args.mode)
    script = parser.normalize(filename=args.file_path)
    asyncio.run(read_conversation(script))

import asyncio
import os
import sys
from typing import List, Tuple

here = os.path.dirname(__file__)
module_path = os.path.join(here)
root = os.path.dirname(module_path)
if module_path not in sys.path:
    sys.path.append(module_path)
if root not in sys.path:
    sys.path.append(root)

import edge_tts
from pydub import AudioSegment

from scripts.script_parser import get_parser
from util import VOICES, OUTPUT_AUDIO_FILE, TEMP_AUDIO_DIRECTORY, parse_args


# Generate audio for a line
# async def generate_audio(speaker: str, text: str, output_file: str) -> None:
async def generate_audio(speaker: str, text: str, output_file: str, speed: str = "-10%") -> None:
    voice = VOICES.get(speaker)  # Default male
    communicate = edge_tts.Communicate(text, voice, rate=speed)
    await communicate.save(output_file)


def remove_mp3_files(directory: str) -> None:
    if not directory:
        directory = "."
    for filename in os.listdir(directory):
        if filename.endswith(".mp3"):
            file_path = os.path.join(directory, filename)
            os.remove(file_path)
            print(f"Removed {file_path}")


# Generate and merge conversation
async def save_conversation(script: List[Tuple[str, str]], output_file: str = OUTPUT_AUDIO_FILE, speed: str = "-10%") -> None:
    # Remove existing mp3 file if any
    remove_mp3_files(os.path.dirname(output_file))

    temp_folder = TEMP_AUDIO_DIRECTORY
    os.makedirs(temp_folder, exist_ok=True)

    audio_files: List[str] = []

    # Generate audio files
    print(f"Total {len(script)} audio files will be generated.")
    for idx, (speaker, text) in enumerate(script):
        temp_file = os.path.join(temp_folder, f"{idx:02d}_{speaker}.mp3")
        await generate_audio(speaker, text, temp_file, speed=speed)
        audio_files.append(temp_file)
        print(f"Generated {idx} files")

    # Merge all audio into one file
    combined = AudioSegment.empty()
    print(f"Combining {len(audio_files)} audio files...")
    for file in audio_files:
        audio = AudioSegment.from_file(file)
        combined += audio + AudioSegment.silent(duration=1000)

    # Save final conversation
    combined.export(output_file, format="mp3")
    # remove temp files and folder
    for file in audio_files:
        os.remove(file)
    os.rmdir(temp_folder)

    print(f"✅ Conversation saved as {output_file}")


# Run script
if __name__ == "__main__":
    additional_args = {
        "short": "-o",
        "full": "--output",
        "required": False,
        "help": "Path to store the map3 file, default output.mp3",
    }

    args = parse_args(additional_args)
    parser = get_parser(args.mode)
    script = parser.normalize(args.file_path)
    for idx, (speaker, text) in enumerate(script):
        print(f"{speaker}: {text}")
    output = args.output if args.output else OUTPUT_AUDIO_FILE
    asyncio.run(save_conversation(script, output_file=output))
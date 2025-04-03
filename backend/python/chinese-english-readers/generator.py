import asyncio
import os
import shutil
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
from util import VOICES, OUTPUT_AUDIO_FILE, TEMP_AUDIO_DIRECTORY, parse_args, CHN_LANGUAGE_CODE


# Generate audio for a line
# async def generate_audio(speaker: str, text: str, output_file: str) -> None:
async def generate_audio(speaker: str, text: str, output_file: str, speed: str = "-10%") -> None:
    voice = VOICES.get(speaker)  # Default male
    communicate = edge_tts.Communicate(text, voice, rate=speed)
    await communicate.save(output_file)



def remove_directory(directory: str) -> None:
    if os.path.exists(directory):
        shutil.rmtree(directory)
        print(f"Removed directory {directory}")
    else:
        print(f"Directory {directory} does not exist.")

def remove_file(file_path: str) -> None:
    if os.path.exists(file_path):
        os.remove(file_path)
        print(f"Removed file {file_path}")
    else:
        print(f"File {file_path} does not exist.")

# Generate and merge conversation
async def save_conversation(script: List[Tuple[str, str]], *,
                            output_file: str = OUTPUT_AUDIO_FILE,
                            speed: str = "-10%",
                            times: int=1) -> None:
    # Remove existing mp3 file if any
    remove_directory(TEMP_AUDIO_DIRECTORY)
    remove_file(OUTPUT_AUDIO_FILE)
    temp_folder = TEMP_AUDIO_DIRECTORY
    os.makedirs(temp_folder, exist_ok=True)

    audio_files: List[str] = []

    # Generate audio files
    print(f"Total {len(script)} audio files will be generated.")
    for idx, (speaker, text, lang) in enumerate(script):
        file_name = f"{idx:02d}_{speaker}_{lang}.mp3"
        temp_file = os.path.join(temp_folder, file_name)
        for _ in range(3):
            if await _generate_audio(speaker, text, temp_file, speed):
                break
        audio_files.append(temp_file)
        print(f"Generated {file_name} files")

    # Merge all audio into one file
    combined = AudioSegment.empty()
    print(f"Combining {len(audio_files)} audio files...")
    for file in audio_files:
        audio = AudioSegment.from_file(file)
        combined += audio + AudioSegment.silent(duration=1000)
        if CHN_LANGUAGE_CODE in file:
            for _ in range(times-1):
                combined += audio + AudioSegment.silent(duration=1000)

    # Save final conversation
    combined.export(output_file, format="mp3")
    # remove temp files and folder
    remove_directory(TEMP_AUDIO_DIRECTORY)

    print(f"✅ Conversation saved as {output_file}")


async def _generate_audio(speaker, text, temp_file, speed):
    try:
        await generate_audio(speaker, text, temp_file, speed=speed)
        return True
    except Exception as e:
        print(f"Error occurred while generating audio: {e}")
        return False

# Run script
if __name__ == "__main__":
    additional_args = [{
        "short": "-o",
        "full": "--output",
        "required": False,
        "help": "Path to store the map3 file, default output.mp3",
    },
    {
        "short": "-t",
        "full": "--times",
        "required": False,
        "help": "Read the Chinese script for the given times",
    }]

    args = parse_args(additional_args)
    parser = get_parser(args.mode)
    script = parser.normalize(filename=args.file_path)
    times = args.times if args.times else 1
    for idx, (speaker, text, _) in enumerate(script):
        print(f"{speaker}: {text}")
    output = args.output if args.output else OUTPUT_AUDIO_FILE
    asyncio.run(save_conversation(script, output_file=output, times=int(times)))
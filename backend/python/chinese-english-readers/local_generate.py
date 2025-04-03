import asyncio
import shutil

from scripts.convert_txt_pdf import convert_txt_to_pdf
from scripts.read_notes import get_local_notes, copy_file_to_remote_folder, copy_file
from scripts.script_parser import get_parser
from util import *
from generator import save_conversation

TMP_FOLDER = "tmp"


async def flow(user, workdir, times=1):
    # check if the folder exists before removal
    if os.path.exists(TMP_FOLDER):
        shutil.rmtree(TMP_FOLDER)

    notes_folder = os.path.join(workdir, get_path(user))

    # # 1. get the target notes
    files = get_local_notes(notes_folder, last_x_days=7000)
    if not files:
        return

    # 2. create a tmp folder
    os.makedirs(TMP_FOLDER, exist_ok=True)
    # 3. copy the target notes to the tmp folder
    for file in files:
        copy_file(notes_folder, file['name'], TMP_FOLDER)
        # 4. convert the txt file to pdf

        filename = file['name']
        tmp_filename = f"{TMP_FOLDER}/{filename}"
        filename_without_suffix = filename.split('.')[0]
        pdf_file = f"{filename_without_suffix}.pdf"
        audio_file = f"{filename_without_suffix}.mp3"
        tmp_audio_file = f"{TMP_FOLDER}/{audio_file}"
        convert_txt_to_pdf(tmp_filename, f"{tmp_filename.split('.')[0]}.pdf")

        # 6. generate audio for each txt file
        parser = get_parser('note')
        script = parser.normalize(filename=tmp_filename)
        for idx, (speaker, text, _) in enumerate(script):
            print(f"{speaker}: {text}")
        await save_conversation(script, output_file=tmp_audio_file, times=int(times))

        # copy audio
        copy_file(TMP_FOLDER, audio_file, notes_folder)
        # copy pdf at the last step, ensure the audio is generated successfully
        copy_file(TMP_FOLDER, pdf_file, notes_folder)
    # 5. remove the tmp folder
    if os.path.exists(TMP_FOLDER):
        shutil.rmtree(TMP_FOLDER)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Generate the pdf and audio automatically, and copy them to the remote folder"
    )
    parser.add_argument(
        "-u", "--user",
        type=str,
        required=False,
        help="Specify the user"
    )
    parser.add_argument(
        "-t", "--times",
        type=str,
        required=False,
        help="Speak Chinese times"
    )
    parser.add_argument(
        "-w", "--workdir",
        type=str,
        required=False,
        help="Work Directory"
    )
    args = parser.parse_args()
    workdir = args.workdir
    times = args.times if args.times else 1
    asyncio.run(flow(args.user, workdir, times=times))


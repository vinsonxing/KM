import asyncio
import json
import shutil

from scripts.convert_txt_pdf import convert_txt_to_pdf
from scripts.read_notes import get_local_notes, copy_file, get_local_valid_users
from scripts.script_parser import get_parser
from util import *
from generator import save_conversation

TMP_FOLDER = "tmp"
TWO_TIMES_USERS = ["SOO"]

class GeneratorContext:

    def __init__(self, user):
        self.user = user
    def __enter__(self):
        # check if the folder exists before removal
        if os.path.exists(TMP_FOLDER):
            shutil.rmtree(TMP_FOLDER)
        os.makedirs(TMP_FOLDER, exist_ok=True)

    def __exit__(self, exc_type, exc_val, exc_tb):
        if os.path.exists(TMP_FOLDER):
            shutil.rmtree(TMP_FOLDER)
        print(f"✅ Generated audio and pdf for user [{self.user}]")

async def generate_by_user(user, workdir, times=1):
    notes_folder = os.path.join(workdir, get_path(user))

    # # 1. get the target notes
    files = get_local_notes(notes_folder, last_x_days=7)
    if not files:
        return
    await _generate_by_user(user, files, notes_folder, times=times)


async def _generate_by_user(user, files, notes_folder, times=1):
    with GeneratorContext(user):
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
            # for idx, (speaker, text, _) in enumerate(script):
            #     print(f"{speaker}: {text}")
            await save_conversation(script, output_file=tmp_audio_file, times=int(times))

            # copy audio
            copy_file(TMP_FOLDER, audio_file, notes_folder)
            # copy pdf at the last step, ensure the audio is generated successfully
            copy_file(TMP_FOLDER, pdf_file, notes_folder)

async def generate_all_users(workdir, times=1):
    users = get_local_valid_users(os.path.join(workdir, SLIDES_DIR))
    if not users or len(users) == 0:
        print("All users are all up to date, no need to generate audio and pdf files")
        return
    print(f"Will generate audio and pdf for the following users: {[user for user in users.keys()]}")
    report = {}
    for user, files in users.items():
        notes_folder = os.path.join(workdir, get_path(user))
        if user in TWO_TIMES_USERS:
            times = 2
        await _generate_by_user(user, files, notes_folder, times=times)
        report[user] = [file['name'] for file in files]
    print(f"""
Report:
Generate audio and pdf for the following users:
{format_report(report)}
    """)
    with open('report.json', 'w') as f:
        f.write(format_report(report))

def format_report(report):
    return json.dumps(report, indent=4, ensure_ascii=False)


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
    if args.user:
        asyncio.run(generate_by_user(args.user, workdir, times=times))
    else:
        asyncio.run(generate_all_users(workdir, times=times))

import smbclient
import os
from datetime import datetime
from util import GRAMMAR_NOTES_DIR


def get_local_notes(folder_path, require_most_recent=False, last_x_days=7):
    try:
        files = []
        for file in os.scandir(folder_path):
            if ((file.name.lower().endswith('.txt') or file.name.lower().endswith('.mp3'))
                    and file.is_file()):
                stat = file.stat()
                _record_file(file, stat, files, last_x_days)

        return _extract_missed_mp3_files(files, require_most_recent)
    except Exception as e:
        print(f"Error accessing shared folder: {e}")
        return None


def _record_file(file, stat, files, last_x_days):
    if (datetime.now() - datetime.fromtimestamp(stat.st_mtime)).days <= last_x_days:
        files.append({
            'name': file.name,
            'type': 'txt' if file.name.lower().endswith('.txt') else 'mp3',
            'path': file.path,
            'mtime': stat.st_mtime
        })


def _extract_missed_mp3_files(files, require_most_recent=False):
    if not files:
        return None
    txt_files = [file for file in files if file['type'] == 'txt']
    mp3_files = [file for file in files if file['type'] == 'mp3']
    txt_files_no_mp3 = [file for file in txt_files if
                        file['name'].replace('.txt', '.mp3') not in [mp3_file['name'] for mp3_file in
                                                                     mp3_files]]
    if require_most_recent:
        most_recent = max(txt_files_no_mp3, key=lambda x: x['mtime'])
        return [most_recent]

    return txt_files_no_mp3


def get_remote_notes(hostname, username, password, share_name, require_most_recent=False, last_x_days=7):
    # Configure the SMB client
    smbclient.ClientConfig(username=username, password=password)

    try:
        # Connect to the shared folder
        share_path = f'\\\\{hostname}\\{share_name}'

        # Find all .txt files with their modification times
        files = []
        for file in smbclient.scandir(share_path):
            if ((file.name.lower().endswith('.txt') or file.name.lower().endswith('.mp3'))
                    and file.is_file()):
                stat = smbclient.stat(os.path.join(share_path, file.name))
                _record_file(file, stat, files, last_x_days)
        return _extract_missed_mp3_files(files, require_most_recent)

    except Exception as e:
        print(f"Error accessing shared folder: {e}")
        return None


def get_local_valid_users(workdir, notes_folder=GRAMMAR_NOTES_DIR, last_x_days=7):
    # list all the folder names in workdir
    users = {}
    for folder in os.scandir(workdir):
        if folder.is_dir() and not folder.name.startswith('.'):
            full_notes_folder = os.path.join(workdir, folder.name, notes_folder)
            if not is_valid_folder(full_notes_folder):
                continue
            files = get_local_notes(full_notes_folder, last_x_days=last_x_days)
            if files and len(files) > 0:
                users[folder.name] = files
    return users


def is_valid_folder(path):
    return os.path.exists(path) and os.path.isdir(path)

def copy_file(source_folder, file_name, target_folder):
    try:
        # Connect to the shared folder
        source_path = os.path.join(source_folder, file_name)
        target_file_path = os.path.join(target_folder, file_name)

        # Open remote file in binary mode and write to local file with correct encoding
        with open(source_path, mode='rb') as source_file:
            content = source_file.read()
            try:
                decoded_content = content.decode('gbk')
                with open(target_file_path, 'w', encoding='utf-8') as local_file:
                    local_file.write(decoded_content)
            except UnicodeDecodeError:
                # If fail, fall back to binary copy
                with open(target_file_path, 'wb') as local_file:
                    local_file.write(content)

    except Exception as e:
        print(f"Error copying file to remote folder: {e}")
        raise e


def copy_file_to_local(hostname, username, password, share_name, file_name, local_folder):
    # Configure the SMB client
    smbclient.ClientConfig(username=username, password=password)

    try:
        # Connect to the shared folder
        share_path = f'\\\\{hostname}\\{share_name}'
        remote_path = os.path.join(share_path, file_name)
        local_file_path = os.path.join(local_folder, file_name)

        # Open remote file in binary mode and write to local file with correct encoding
        with smbclient.open_file(remote_path, mode='rb') as remote_file:
            content = remote_file.read()
            try:
                # If UTF-8 fails, try GBK (common for Chinese Windows systems)
                decoded_content = content.decode('gbk')
                with open(local_file_path, 'w', encoding='utf-8') as local_file:
                    local_file.write(decoded_content)
            except UnicodeDecodeError:
                # If fail, fall back to binary copy
                with open(local_file_path, 'wb') as local_file:
                    local_file.write(content)
    except Exception as e:
        print(f"Error copying file to remote folder: {e}")
        raise e


# define a method to copy a specify file to a remote folder
def copy_file_to_remote_folder(hostname, username, password, share_name, file_path):
    # Configure the SMB client
    smbclient.ClientConfig(username=username, password=password)

    try:
        # Connect to the shared folder
        share_path = f'\\\\{hostname}\\{share_name}'
        remote_path = os.path.join(share_path, os.path.basename(file_path))
        with open(file_path, 'rb') as f:
            smbclient.open_file(remote_path, mode='wb').write(f.read())
    except Exception as e:
        print(f"Error copying file to remote folder: {e}")
        raise e


if __name__ == "__main__":
    from util import find_project_root, GRAMMAR_NOTES_DIR
    import json

    # Configuration - replace with your Windows share details
    # HOSTNAME = "192.168.0.107"  # or IP address like "192.168.1.100"
    # USERNAME = "luqian"
    # PASSWORD = "Lovelq123"
    # SHARE_NAME = "teaching slides/Alvin/grammar notes"  # Name of the shared folder
    # Get the most recent .txt file and its content
    # filename = get_remote_notes(HOSTNAME, USERNAME, PASSWORD, SHARE_NAME, last_x_days=7000)
    # copy_file_to_remote_folder(HOSTNAME, USERNAME, PASSWORD, SHARE_NAME, f"{find_project_root()}/test.mp3")

    res = get_local_valid_users("/Users/vixing/Vinson/test/teaching slides", last_x_days=7000)
    # print the result in formatted json

    res = json.dumps(res, indent=4, ensure_ascii=False)
    print(res)

import argparse
import os
from pathlib import Path
from typing import Any, Dict, Optional, List

MALE_VOICES = [
    "zh-CN-YunjianNeural",  # 中年
    "zh-CN-YunxiaNeural",  # 小孩
    "zh-CN-YunxiNeural"  # 青年
]

FEMALE_VOICES = [
    "zh-CN-XiaoxiaoNeural",  # 青年
    "zh-CN-XiaoxuanNeural",  # 小孩
]

VOICES = {
    "张伟": "zh-CN-YunxiNeural",
    "李娜": "zh-CN-XiaoxiaoNeural"
}

DEFAULT_SPEAKER1 = "张伟"
DEFAULT_SPEAKER2 = "李娜"

DEFAULT_SPEAKER_VOICE = "zh-CN-XiaoxiaoNeural"
TEMP_AUDIO_DIRECTORY = "temp_audio"
OUTPUT_AUDIO_FILE = "output.mp3"

ENG_LANGUAGE_CODE = "eng"
CHN_LANGUAGE_CODE = "chn"

HOSTNAME = "192.168.0.107"
USERNAME = "luqian"
PASSWORD = "Lovelq123"


def parse_args(additional_args: Optional[List[Dict[str, Any]]] = None) -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="A simple Python application that processes files based on mode"
    )
    parser.add_argument(
        "-m", "--mode",
        type=str,
        required=True,
        help="Operation mode (e.g., 'note' or 'conversation')"
    )
    parser.add_argument(
        "-f", "--file-path",
        type=str,
        required=True,
        help="Path to the file to process"
    )
    if additional_args is None:
        additional_args = []
    for additional_arg in additional_args:
        parser.add_argument(
            additional_arg["short"],
            additional_arg["full"],
            required=additional_arg["required"],
            help=additional_arg["help"]
        )
    return parser.parse_args()


def find_project_root(indicator_files=None):
    if indicator_files is None:
        indicator_files = ['.git', 'requirements.txt']

    current_path = Path(os.getcwd())
    for parent in current_path.parents:
        for indicator in indicator_files:
            if (parent / indicator).exists():
                return parent
    return None


def get_path(user):
    return f"teaching slides/{user}/grammar notes"

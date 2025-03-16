import argparse
from typing import Any, Dict, Optional

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


def parse_args(additional_args: Optional[Dict[str, Any]] = None) -> argparse.Namespace:
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
    if additional_args:
        parser.add_argument(
            additional_args["short"],
            additional_args["full"],
            required=additional_args["required"],
            help=additional_args["help"]
        )
    return parser.parse_args()

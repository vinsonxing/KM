from typing import List, Tuple
from scripts.parser import Parser, normalize_line
from util import DEFAULT_SPEAKER1, DEFAULT_SPEAKER2, CHN_LANGUAGE_CODE


def read_conversation_to_list(filename:str, *, speaker1:str = DEFAULT_SPEAKER1, speaker2:str = DEFAULT_SPEAKER2) -> \
List[Tuple[str, str]]:
    # Define the two users
    user1 = speaker1
    user2 = speaker2

    conversation_list = []

    # Open and read the file
    try:
        with open(filename, 'r', encoding='utf-8') as file:
            lines = file.readlines()

        current_speaker = None
        chinese_line = None

        # Process each line
        for line in lines:
            line = line.strip()

            # Handle format 1:"张伟:你好，李娜！今天过得怎么样？" (speaker and Chinese on same line)
            if line.startswith(f"{user1}:"):
                if current_speaker and chinese_line:  # If previous entry incomplete, skip or handle
                    conversation_list.append((current_speaker, normalize_line(chinese_line), CHN_LANGUAGE_CODE))
                current_speaker = user1
                chinese_line = line[len(f"{user1}:"):]  # Extract Chinese text after "张伟:"

            elif line.startswith(f"{user2}:"):
                if current_speaker and chinese_line:  # If previous entry incomplete, skip or handle
                    conversation_list.append((current_speaker, normalize_line(chinese_line), CHN_LANGUAGE_CODE))
                current_speaker = user2
                chinese_line = line[len(f"{user2}:"):]  # Extract Chinese text after "李娜:"

            # Handle format 2:"张伟:" (speaker on own line)
            elif line == f"{user1}:":
                current_speaker = user1
                chinese_line = None

            elif line == f"{user2}:":
                current_speaker = user2
                chinese_line = None

            # Handle Chinese line in format 2 or English line in either format
            elif current_speaker and line and line != f"{user1}:" and line != f"{user2}:":
                if not chinese_line and not line.startswith(f"{user1}:") and not line.startswith(f"{user2}:"):
                    chinese_line = line  # This is the Chinese line in format 2
                elif chinese_line and not line.startswith(f"{user1}:") and not line.startswith(f"{user2}:"):
                    # This is the English line, combine and add to list
                    combined_text = f"{normalize_line(chinese_line)}{normalize_line(line)}"
                    conversation_list.append((current_speaker, combined_text, CHN_LANGUAGE_CODE))
                    chinese_line = None

        # Handle any remaining entry
        if current_speaker and chinese_line:
            conversation_list.append((current_speaker, normalize_line(chinese_line), CHN_LANGUAGE_CODE))

        return conversation_list

    except FileNotFoundError:
        print(f"Error:The file '{filename}' was not found.")
        return []
    except Exception as e:
        print(f"An error occurred:{str(e)}")
        return []


class ConversationParser(Parser):
    def normalize(
            self,
            filename: str = "",
            text: str = "",
            speaker1: str = DEFAULT_SPEAKER1,
            speaker2: str = DEFAULT_SPEAKER2) -> List[Tuple[str, str]]:
        return read_conversation_to_list(filename, speaker1=speaker1, speaker2=speaker2)

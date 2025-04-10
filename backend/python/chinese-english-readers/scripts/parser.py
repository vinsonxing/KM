import re
from typing import List, Tuple
from util import DEFAULT_SPEAKER1, DEFAULT_SPEAKER2


def remove_content_in_parentheses(text: str) -> str:
    cleaned_text = re.sub(r'\([^)]*\)', '', text)  # Remove text inside parentheses
    cleaned_text = re.sub(r'（[^）]*）', '', cleaned_text)  # Remove text inside parentheses
    return cleaned_text


def remove_dot(text: str) -> str:
    return re.sub(r'\.', '', text)


def remove_pinyin(text: str) -> str:
    cleaned_text = re.sub(r'[a-zA-Z]*[0-9]', '', text)  # Remove English letters and numbers
    return cleaned_text


def remove_comments(text: str) -> str:
    return re.sub(r'^(--.*|\*.*|#.*|//.*)', '', text, flags=re.MULTILINE)



def format_light_tone(text: str, light_tone: str) -> str:
    return re.sub(fr'{light_tone}(?![0-9])', f'{light_tone}0', text)


def normalize_line(text: str) -> str:
    cleaned_text = remove_content_in_parentheses(text)
    cleaned_text = format_light_tone(cleaned_text, "了le")
    cleaned_text = format_light_tone(cleaned_text, "么me")
    cleaned_text = format_light_tone(cleaned_text, "吗ma")
    cleaned_text = format_light_tone(cleaned_text, "的de")
    cleaned_text = format_light_tone(cleaned_text, "得de")
    cleaned_text = format_light_tone(cleaned_text, "地de")
    cleaned_text = format_light_tone(cleaned_text, "情qing")
    cleaned_text = format_light_tone(cleaned_text, "西xi")
    cleaned_text = format_light_tone(cleaned_text, "息xi")
    cleaned_text = format_light_tone(cleaned_text, "子zi")
    cleaned_text = format_light_tone(cleaned_text, "方fang")
    cleaned_text = format_light_tone(cleaned_text, "呢ne")
    cleaned_text = remove_dot(cleaned_text)
    cleaned_text = remove_pinyin(cleaned_text)

    return cleaned_text

class Parser:
    def normalize(self,
                  filename: str = "",
                  text: str = "",
                  speaker1: str = DEFAULT_SPEAKER1,
                  speaker2: str = DEFAULT_SPEAKER2) -> List[Tuple[str, str]]:
        pass

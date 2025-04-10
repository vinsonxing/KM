import re
from typing import List, Dict, Tuple
from util import DEFAULT_SPEAKER2, DEFAULT_SPEAKER1, ENG_LANGUAGE_CODE, CHN_LANGUAGE_CODE
from scripts.parser import Parser, remove_comments, normalize_line


def contains_chinese(text: str) -> bool:
    return bool(re.search(r'[\u4e00-\u9fff]', text))


def contains_english(text: str) -> bool:
    return bool(re.search(r'[a-zA-Z]', text))


def read_script(filename: str) -> str:
    text = ""
    with open(filename, "r", encoding="utf-8") as file:
        for line in file:
            line = line.strip()
            text += line + "\n"
    return text


def extract_sentence_pairs_from_text(text: str) -> List[List[Dict[str, str]]]:
    lines = text.strip().split("\n")
    return _extract_sentence_pairs_from_lines(lines)


def extract_sentence_pairs_from_file(filename: str) -> List[List[Dict[str, str]]]:
    lines = read_script(filename).split("\n")
    return _extract_sentence_pairs_from_lines(lines)


def _process_single_line(line: str, pairs: List[List[Dict[str, str]]]) -> None:
    pairs.append([
        {"lang": "english", "content": ""},
        {"lang": "chinese", "content": line}
    ])
    print(f"English: ")
    print(f"Chinese: {line}")


def _process_pair_in_chn(eng: str, chn: str, pairs: List[List[Dict[str, str]]]) -> None:
    pairs.append([
        {"lang": "english", "content": ""},
        {"lang": "chinese", "content": eng}
    ])
    print(f"English: ")
    print(f"Chinese: {eng}")
    pairs.append([
        {"lang": "english", "content": ""},
        {"lang": "chinese", "content": chn}
    ])
    print(f"English: ")
    print(f"Chinese: {chn}")


def _extract_sentence_pairs_from_lines(raw_lines: List[str]) -> List[List[Dict[str, str]]]:
    pairs = []
    i = 0
    lines = [line for line in raw_lines if line.strip()]
    while i < len(lines) - 1:
        eng = remove_comments(lines[i].strip())

        # Skip empty lines
        if not eng:
            i += 1
            continue
        chn = lines[i + 1].strip()

        chn = normalize_line(chn)
        eng = normalize_line(eng)
        # single line have both English and Chinese
        if contains_english(eng) and contains_chinese(eng):
            _process_single_line(eng, pairs)
            i += 1
        # Check both are Chinese
        elif contains_chinese(eng) and contains_chinese(chn):
            _process_pair_in_chn(eng, chn, pairs)
            i += 2
        # Check if the first line is English and the second is Chinese
        elif re.search(r'[a-zA-Z]', eng) and contains_chinese(chn):
            pairs.append([
                {"lang": "english", "content": eng},
                {"lang": "chinese", "content": chn}
            ])
            print(f"English: {eng}")
            print(f"Chinese: {chn}")
            i += 2  # Move to the next potential pair
        else:
            i += 1  # Move to the next line

    return pairs


class NoteParser(Parser):
    def normalize(
            self,
            filename: str = "",
            text: str = "",
            speaker1: str = DEFAULT_SPEAKER1,
            speaker2: str = DEFAULT_SPEAKER2) -> List[Tuple[str, str]]:
        pairs = []
        if filename:
            pairs = extract_sentence_pairs_from_file(filename)
        elif text:
            pairs = extract_sentence_pairs_from_text(text)
        return self._normalize(pairs, speaker1=speaker1, speaker2=speaker2)


    def _normalize(self,
                   pairs: List[List[Dict[str, str]]],
                   speaker1: str = DEFAULT_SPEAKER1,
                   speaker2: str = DEFAULT_SPEAKER2) -> List[Tuple[str, str]]:
        readable_script = []
        for pair in pairs:
            content_en = f"{pair[0]["content"]}"
            content_ch = f"{pair[1]["content"]}"
            if content_en:
                readable_script.append((speaker2, content_en, ENG_LANGUAGE_CODE))
            if content_ch:
                readable_script.append((speaker2, content_ch, CHN_LANGUAGE_CODE))
        return readable_script
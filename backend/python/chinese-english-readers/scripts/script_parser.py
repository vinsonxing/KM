from scripts.conversation_parser import ConversationParser
from scripts.note_parser import NoteParser

from typing import Union


def get_parser(mode: str) -> Union[ConversationParser, NoteParser]:
    if mode == "conversation":
        return ConversationParser()
    elif mode == "note":
        return NoteParser()
    else:
        raise ValueError(f"Invalid mode: {mode}")

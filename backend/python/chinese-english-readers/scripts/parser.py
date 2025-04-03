from typing import List, Tuple
from util import DEFAULT_SPEAKER1, DEFAULT_SPEAKER2


class Parser:
    def normalize(self,
                  filename: str = "",
                  text: str = "",
                  speaker1: str = DEFAULT_SPEAKER1,
                  speaker2: str = DEFAULT_SPEAKER2) -> List[Tuple[str, str]]:
        pass

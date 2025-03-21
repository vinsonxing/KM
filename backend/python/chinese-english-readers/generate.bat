@echo off
if exist temp_audio rmdir /s /q temp_audio
if exist *.mp3 del *.mp3
python generator.py -m note -f note.txt
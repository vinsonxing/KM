@echo off
rmdir /s /q temp_audio
del *.mp3
python generator.py -m note -f note.txt
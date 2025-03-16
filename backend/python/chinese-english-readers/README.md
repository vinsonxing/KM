# Setup
> Python 3.12.2+ required

## Install dependencies
```shell
pip install -r requirments.txt
```

## Read the given file
#### Read the given file as conversation
```shell
python reader.py -m=conversation -f=conversation.txt
```

#### Read the given notes in literal
```shell
python reader.py -m=note -f=note.txt
```

## Generate a audio by the given script in file
#### Generate a audio by the given conversation
```shell
python generator.py -m=conversation -f=conversation.txt -o=conversation.mp3
```

#### Generate a audio by the given notes in literal
```shell
python generator.py -m=note -f=note.txt -o=note.mp3
```


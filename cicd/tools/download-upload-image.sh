#!/bin/bash
SOURCE_PATH=$1
set -x
if [[ $SOURCE_PATH =~ "/" ]]; then
  FILE_NAME=${SOURCE_PATH##*/}
else
  FILE_NAME=$SOURCE_PATH
fi

scp vixing@171.70.43.52:${SOURCE_PATH} ./
unset http_proxy
curl -v -u admin:Cisco123! --upload-file ${FILE_NAME} http://10.74.68.9/repository/files/${FILE_NAME}
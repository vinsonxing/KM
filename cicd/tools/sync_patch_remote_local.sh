#!/bin/bash

ORIGINAL_PATH=$(dirname $0)
PATCH_NAME="diff.patch"
set  -x
print_help() {
  echo '''
  Usage: lsync [OPTIONS]
  Description: Command to sync the code changes between dev machine and local
  Options:
     -r, remote source code path, -r <remote_source_path>
     -l, local source code path, -l <local_source_path>
     -d, sync direction, lr=local_to_remote, rl=remote_to_local
     -p, if pull the latest code
     -c, pull changes in the first commit to a patch and apply to local
     -h, print this help
'''
}

shift_count=0
direction=""
remote_path="/home/vixing/co/manage"
local_path="/Users/vixing/Development/meraki/SourceCode/co/manage"
pullCode="false"
isCommit="false"
sshPrefix="vixing@dev159"
while getopts ":d:rl:pch" opt; do
    case ${opt} in
    d)
        shift_count=$((shift_count+2))
        direction=${OPTARG}
        ;;
    r)
        shift_count=$((shift_count+2))
        remote_path=${OPTARG}
        ;;
    l)
        shift_count=$((shift_count+2))
        local_path=${OPTARG}
        ;;
    p)
        shift_count=$((shift_count+1))
        pullCode="true"
        ;;
    c)
        shift_count=$((shift_count+1))
        isCommit="true"
        ;;
    h)
        shift_count=$((shift_count+1))
        print_help
        exit 0
        ;;
    \?)
        print_help >&2
        exit 1
    esac
done
shift $((shift_count))


trap clean_up EXIT

function apply_remote_first_commit_to_local() {
  # remove the patch from remote if exists
  ssh ${sshPrefix} "cd ${remote_path} && ls | grep '.*.patch' | xargs rm -rf"

  # clean remote code base
  ssh ${sshPrefix} "cd ${remote_path} && git reset --hard && git clean -f -d ."

  # generate the patch based on the latest commit
  ## get the latest commit
  lastest_commit_id=$(ssh ${sshPrefix} "cd ${remote_path} && git log | head -1 | cut -d' ' -f2")
  ## generate patch from the latest commit
  patch_name=$(ssh ${sshPrefix} "cd ${remote_path} && git format-patch -1 ${lastest_commit_id}")

  # clean current code base
  cd $local_path
  git status -sb
  git reset --hard
  git clean -d -f .

  if [ "${pullCode}" = "true" ]; then
    git pull --rebase
  fi

  # scp the patch to local source code
  scp ${sshPrefix}:${remote_path}/${patch_name} "${local_path}/"

  # clean the patch file
  ssh ${sshPrefix} "cd ${remote_path} && ls | grep '.*.patch' | xargs rm -rf"

  # apply patch to local code
  git apply ${patch_name}
  rm -rf ${patch_name}
}

function apply_remote_diff_to_local() {
  # remove the patch from remote if exists
  ssh ${sshPrefix} "cd ${remote_path} && ls | grep '.*.patch' | xargs rm -rf"

  ### stage all changes
  ssh ${sshPrefix} "cd ${remote_path} && git add ."
  ### prepare patch from staged code
  ssh ${sshPrefix} "cd ${remote_path} && git diff --staged > ${PATCH_NAME}"
  # clean current code base
  cd $local_path
  git status -sb
  git reset --hard
  git clean -d -f .
  if [ "${pullCode}" = "true" ]; then
    git pull --rebase
  fi

  # scp the patch to local source code
  scp ${sshPrefix}:${remote_path}/${PATCH_NAME} "${local_path}/"

  # clean the patch file
  ssh ${sshPrefix} "cd ${remote_path} && ls | grep '.*.patch' | xargs rm -rf"

  # apply patch to local code
  git apply ${PATCH_NAME}
  rm -rf ${PATCH_NAME}
  cd -
}

function apply_local_diff_to_remote() {
  # remove the patch from remote if exists
  cd $local_path
  ls | grep *.patch | xargs rm -rf

  # clean remote code base
  ssh ${sshPrefix} "cd ${remote_path} && git status -sb && git reset --hard && git clean -d -f ."

  if [ "${pullCode}" = "true" ]; then
    ssh ${sshPrefix} "cd ${remote_path} && git pull --rebase"
  fi

  # prepate local patch
  cd $local_path
  git status -sb
  git add .
  git diff --staged > ${PATCH_NAME}

  # scp the patch to local source code
  scp "${local_path}/${PATCH_NAME}" ${sshPrefix}:${remote_path}/

  # clean the patch file after copying
  ls | grep *.patch | xargs rm -rf

  # apply patch to local code
  ssh ${sshPrefix} "cd ${remote_path} && git apply ${PATCH_NAME}"
  ssh ${sshPrefix} "cd ${remote_path} && rm -rf ${PATCH_NAME}"
  cd -
}

function clean_up() {
  # go back to original path
  cd ${ORIGINAL_PATH}
}

if [ "${isCommit}" = "true" ]; then
  if [ "${direction}" = "rl" ]; then
    apply_remote_first_commit_to_local
  else
    echo "Not support yet !!"
  fi
else
  if [ "${direction}" = "rl" ]; then
    apply_remote_diff_to_local
  else
    apply_local_diff_to_remote
  fi
fi

#!/usr/bin/env bash

WORK_DIR=$(cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)

pushd "${WORK_DIR}" &>/dev/null

    TARGET_DIR=$WORK_DIR/target
    echo ">>> Removing Taurus target dir"
    rm -rf ${TARGET_DIR}
    echo ">>> Recreating Taurus target dir"
    mkdir -p ${TARGET_DIR}

    runTaurusTest="bzt benchmark.yml \
        -o settings.artifacts-dir=${TARGET_DIR} \
        -report"

    echo ">>> Running Taurus test"
    eval ${runTaurusTest}
    result=$?
    exit $result

popd
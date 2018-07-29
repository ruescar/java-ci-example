#!/usr/bin/env bash

WORK_DIR=$(cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)

pushd "${WORK_DIR}" &>/dev/null
    RESULTS_DIR=${WORK_DIR}/results

    echo ">>> Removing jcstress results gz files ${RESULTS_DIR}"
    rm jcstress-results-*
    echo ">>> Removing jcstress results directory ${RESULTS_DIR}"
    rm -rf ${RESULTS_DIR}
    echo ">>> Recreating jcstress results directory ${RESULTS_DIR}"
    mkdir -p ${RESULTS_DIR}

    runJcstressTest="java -jar target/jcstress.jar -v -iter 1 -f 1 -bs 1 -r ${RESULTS_DIR} -t ApplicationJcStressTest"

    echo ">>> Running Concurrency Stress test using jcstess by executing command:"
    echo "${runJcstressTest}"
    eval ${runJcstressTest}

    if [ $? -eq 0 ]
    then
        # Fail if no tests run
        if [ $(grep "Overall pass rate:.*0/0" $RESULTS_DIR/index.html | wc -l) -gt 0 ]
        then
            echo ">>> No Concurrency Stress tests run" >&2
            exit 1
        fi
      echo ">>> Concurrency Stress test ran ok"
      exit 0
    else
      echo ">>> Concurrency Stress tests failed" >&2
      exit 1
    fi
popd
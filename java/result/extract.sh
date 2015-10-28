#!/bin/bash

for f in $(find . -iname 'tmjee.ConcurrentSkipListSet-*-noTx*.txt' | sort); do
  echo "------ $f"
  grep -i 'Benchmark:\|Number of threads\|Throughput\|Operations' $f
done

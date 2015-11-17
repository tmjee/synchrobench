#!/bin/bash

out1=extracted-results-withupdates.txt

cat /dev/null > ${out1}
for f in $(find . -iname 'tmjee*-withupdates.txt' | sort); do
  echo "------ $f"
  grep -i 'Benchmark:\|Number of threads\|Throughput\|Operations' $f | tee -a ${out1}
done


for l in out1; do
 echo ${l}
done;


out2=extracted-results-noupdates.txt

cat /dev/null > ${out2}
for f in $(find . -iname 'tmjee*-noupdates.txt' | sort); do
    echo "------ $f"
  grep -i 'Benchmark:\|Number of threads\|Throughput\|Operations' $f | tee -a ${out2}
done


for l in out2; do
 echo ${l}
done;


#!/bin/bash

out=extracted-results.txt

cat /dev/null > ${out}
for f in $(find . -iname 'tmjee*.txt' | sort); do
  echo "------ $f"
  grep -i 'Benchmark:\|Number of threads\|Throughput\|Operations' $f | tee -a ${out}
done


for l in out; do 
 echo ${l}

done;



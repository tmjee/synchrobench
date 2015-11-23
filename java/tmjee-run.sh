#!/bin/bash

rm -fr result/*.txt

for RECIPE in "withupdates" "noupdates"; do
    for BENCH_CLASS in "tmjee.BaselineSet" "tmjee.ConcurrentSkipListSet" "tmjee.SynchronizedSet"; do
		for N_THREADS in 1 2 4 8 16 32 64; do
			echo "working on ${BENCH_CLASS} - ${N_THREADS} - ${RECIPE}"
			t=$(printf "%03d" ${N_THREADS})
			out="result/${BENCH_CLASS}-t${t}-${RECIPE}.txt"
			make N_THREADS=${N_THREADS} BENCH_CLASS=${BENCH_CLASS} ${RECIPE} | tee ${out}
		done
    done
done

out1=result/extracted-results-withupdates.txt

cat /dev/null > ${out1}
for f in $(find ./result -iname 'tmjee*-withupdates.txt' | sort); do
  echo "------ $f"
  grep -i 'Benchmark:\|Number of threads\|Throughput\|Operations' $f | tee -a ${out1}
done


for l in out1; do
     echo ${l}
done;


out2=result/extracted-results-noupdates.txt

cat /dev/null > ${out2}
for f in $(find ./result -iname 'tmjee*-noupdates.txt' | sort); do
   echo "------ $f"
   grep -i 'Benchmark:\|Number of threads\|Throughput\|Operations' $f | tee -a ${out2}
done


for l in out2; do
    echo ${l}
done;

ant -f ./../charts/build.xml chart


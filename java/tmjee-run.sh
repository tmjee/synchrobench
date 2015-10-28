#!/bin/bash


#make 

for RECIPE in "notx" "withtx"; do
    for BENCH_CLASS in "tmjee.NoOpSet" "tmjee.ConcurrentSkipListSet" "tmjee.SynchronizedSet"; do
	for N_THREADS in 1 2 3 4 5 6; do
		echo "working on ${BENCH_CLASS} - ${N_THREADS} - ${RECIPE}"
		out="result/${BENCH_CLASS}-t${N_THREADS}-${RECIPE}.txt"
		make N_THREADS=${N_THREADS} BENCH_CLASS=${BENCH_CLASS} ${RECIPE} | tee ${out}
	done
    done
done


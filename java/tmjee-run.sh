#!/bin/bash

for RECIPE in "notx"; do
    for BENCH_CLASS in "tmjee.NoOpSet" "tmjee.ConcurrentSkipListSet" "tmjee.SynchronizedSet"; do
	for N_THREADS in 1 2 4 8 16 32 64; do
		echo "working on ${BENCH_CLASS} - ${N_THREADS} - ${RECIPE}"
		t=$(printf "%03d" ${N_THREADS})
		out="result/${BENCH_CLASS}-t${t}-${RECIPE}.txt"
		make N_THREADS=${N_THREADS} BENCH_CLASS=${BENCH_CLASS} ${RECIPE} | tee ${out}
	done
    done
done


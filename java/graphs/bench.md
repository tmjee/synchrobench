
# 1
```	
${JAVA} ${JAVAOPT} \
	-cp bin \
	contention.benchmark.Test \
	-v -W 2 -u 30 -a 0 -s 10 -d 2000 -t ${N_THREADS} -i 4096 -r 8192 -b ${BENCH_CLASS}
```

- [Graph On a local quad-core intel core i7](http://rawgit.com/tmjee/synchrobench/tmjee/java/graphs/local.html)
- [Graph On Artemis 16-core AMD](http://rawgit.com/tmjee/synchrobench/tmjee/java/graphs/artemis.html)



## Graphs
Cause it's easier to visualise.

### Graph 1
This shows the throughput of the followings 
- NoOpSet-noTx
- NoOpSet-withTx
- SychronizedSet-noTx
- SynchronizedSet-withTx
- ConcurrentSkipListSet-noTx
- ConcurrentSkipListSet-withTx

[Graph](https://rawgit.com/tmjee/synchrobench/master/graph.html)


### Graph 2
This shows the throughput of the followings
- SychronizedSet-noTx
- SynchronizedSet-withTx
- ConcurrentSkipListSet-noTx
- ConcurrentSkipListSet-withTx
[Graph](https://rawgit.com/tmjee/synchrobench/master/graph2.html)



## Sample of output

```
$> make 
$> make check-noop
java -server  -Dorg.deuce.exclude="java.util.*,java.lang.*,sun.*" \
-javaagent:lib/deuceAgent-1.3.0.jar \
-Dorg.deuce.transaction.contextClass=org.deuce.transaction.estmmvcc.Context \
-cp lib/compositional-deucestm-0.1.jar:lib/mydeuce.jar:bin \
-Xbootclasspath/p:lib/rt_instrumented.jar \
contention.benchmark.Test \
-v -W 2 -u 20 -a 0 -s 0 -d 2000 -t 5 -i 4096 -r 8192 -b tmjee.NoOpSet
Found!!! stm org.deuce.transaction.estmmvcc.Context
-------------------------------------------------------------------------------
Synchrobench-java
A benchmark-suite to evaluate synchronization techniques
-------------------------------------------------------------------------------

Benchmark parameters
--------------------
  Detailed stats:          	enabled
  Number of threads:       	5
  Length:                  	2000 ms
  Write ratio:             	20 %
  WriteAll ratio:          	0 %
  Snapshot ratio:          	0 %
  Size:                    	4096 elts
  Range:                   	8192 elts
  WarmUp:                  	2 s
  Iterations:              	1
  Benchmark:               	tmjee.NoOpSet
waiting for join
Thread #2 finished.
Thread #1 finished.
Thread #0 finished.
Thread #3 finished.
Thread #4 finished.
waiting for join
Thread #4 finished.
Thread #1 finished.
Thread #3 finished.
Thread #2 finished.
Thread #0 finished.
-------------------------------------------------------------------------------
Benchmark statistics
-------------------------------------------------------------------------------
  Average traversal length: 	NaN
  Struct Modifications:     	0
  Throughput (ops/s):       	1.0638242023928216E8
  Elapsed time (s):         	2.006
  Operations:               	213403135	( 100 %)
    effective updates:     	42681541	( 20.00 %)
    |--add successful:     	21341355	( 10.00 %)
    |--remove succ.:       	21340186	( 10.00 %)
    |--addAll succ.:       	0	( 0.00 %)
    |--removeAll succ.:    	0	( 0.00 %)
    size successful:       	0	( 0.00 %)
    contains succ.:        	170721594	( 80.00 %)
    unsuccessful ops:      	0	( 0.00 %)
  Final size:              	0
  Expected size:           	5265
-------------------------------------------------------------------------------
TM statistics
-------------------------------------------------------------------------------
  Commits:                  	0
  |--regular read only  (%) 	0	( NaN %)
  |--elastic (%)            	0	( NaN %)
  |--regular update (%)     	0	( NaN %)
  Starts:                   	0
  Aborts:                   	0	( 100 %)
  |--between succ. reads:   	0	( NaN %)
  |--between read & write:  	0	( NaN %)
  |--extend upon read:      	0	( NaN %)
  |--write after read:      	0	( NaN %)
  |--locked on write:       	0	( NaN %)
  |--locked before read:    	0	( NaN %)
  |--locked before eread:   	0	( NaN %)
  |--locked on read:        	0	( NaN %)
  |--invalid commit:        	0	( NaN %)
  |--invalid snapshot:      	0	( NaN %)
  Read set size on avg.:    	NaN
  Write set size on avg.:   	NaN
  Tx time-to-commit on avg.:	NaN microsec
  Number of elastic reads       0
  Number of reads in RO prefix  0
```


```
$> make
$> make check-notx-noop
java -server  \
-cp bin \
contention.benchmark.Test \
-v -W 2 -u 20 -a 0 -s 0 -l 2000 -t 5 -i 4096 -r 8192 -b tmjee.NoOpSet
-------------------------------------------------------------------------------
Synchrobench-java
A benchmark-suite to evaluate synchronization techniques
-------------------------------------------------------------------------------

Benchmark parameters
--------------------
  Detailed stats:          	enabled
  Number of threads:       	5
  Length:                  	5000 ms
  Write ratio:             	20 %
  WriteAll ratio:          	0 %
  Snapshot ratio:          	0 %
  Size:                    	4096 elts
  Range:                   	8192 elts
  WarmUp:                  	2 s
  Iterations:              	1
  Benchmark:               	tmjee.NoOpSet
waiting for join
Thread #2 finished.
Thread #1 finished.
Thread #0 finished.
Thread #4 finished.
Thread #3 finished.
waiting for join
Thread #1 finished.
Thread #3 finished.
Thread #0 finished.
Thread #4 finished.
Thread #2 finished.
-------------------------------------------------------------------------------
Benchmark statistics
-------------------------------------------------------------------------------
  Average traversal length: 	NaN
  Struct Modifications:     	0
  Throughput (ops/s):       	1.613995080951429E8
  Elapsed time (s):         	5.003
  Operations:               	807481739	( 100 %)
    effective updates:     	161489096	( 20.00 %)
    |--add successful:     	80747852	( 10.00 %)
    |--remove succ.:       	80741244	( 10.00 %)
    |--addAll succ.:       	0	( 0.00 %)
    |--removeAll succ.:    	0	( 0.00 %)
    size successful:       	0	( 0.00 %)
    contains succ.:        	645992643	( 80.00 %)
    unsuccessful ops:      	0	( 0.00 %)
  Final size:              	0
  Expected size:           	10704
-------------------------------------------------------------------------------
TM statistics
-------------------------------------------------------------------------------
  Commits:                  	0
  |--regular read only  (%) 	0	( NaN %)
  |--elastic (%)            	0	( NaN %)
  |--regular update (%)     	0	( NaN %)
  Starts:                   	0
  Aborts:                   	0	( 100 %)
  |--between succ. reads:   	0	( NaN %)
  |--between read & write:  	0	( NaN %)
  |--extend upon read:      	0	( NaN %)
  |--write after read:      	0	( NaN %)
  |--locked on write:       	0	( NaN %)
  |--locked before read:    	0	( NaN %)
  |--locked before eread:   	0	( NaN %)
  |--locked on read:        	0	( NaN %)
  |--invalid commit:        	0	( NaN %)
  |--invalid snapshot:      	0	( NaN %)
  Read set size on avg.:    	NaN
  Write set size on avg.:   	NaN
  Tx time-to-commit on avg.:	NaN microsec
  Number of elastic reads       0
  Number of reads in RO prefix  0
```


```
$> make
$> make check-csls
java -server  -ea -Dorg.deuce.exclude="java.util.*,java.lang.*,sun.*" \
-javaagent:lib/deuceAgent-1.3.0.jar \
-Dorg.deuce.transaction.contextClass=org.deuce.transaction.estmmvcc.Context \
-cp lib/compositional-deucestm-0.1.jar:lib/mydeuce.jar:bin \
-Xbootclasspath/p:lib/rt_instrumented.jar \
contention.benchmark.Test \
-v -W 2 -u 20 -a 0 -s 0 -d 2000 -t 5 -i 4096 -r 8192 -b tmjee.ConcurrentSkipListSet
Found!!! stm org.deuce.transaction.estmmvcc.Context
-------------------------------------------------------------------------------
Synchrobench-java
A benchmark-suite to evaluate synchronization techniques
-------------------------------------------------------------------------------

Benchmark parameters
--------------------
  Detailed stats:          	enabled
  Number of threads:       	5
  Length:                  	2000 ms
  Write ratio:             	20 %
  WriteAll ratio:          	0 %
  Snapshot ratio:          	0 %
  Size:                    	4096 elts
  Range:                   	8192 elts
  WarmUp:                  	2 s
  Iterations:              	1
  Benchmark:               	tmjee.ConcurrentSkipListSet
waiting for join
Thread #4 finished.
Thread #3 finished.
Thread #2 finished.
Thread #1 finished.
Thread #0 finished.
waiting for join
Thread #3 finished.
Thread #0 finished.
Thread #1 finished.
Thread #2 finished.
Thread #4 finished.
-------------------------------------------------------------------------------
Benchmark statistics
-------------------------------------------------------------------------------
  Average traversal length: 	NaN
  Struct Modifications:     	0
  Throughput (ops/s):       	1.2137006454816287E7
  Elapsed time (s):         	2.014
  Operations:               	24443931	( 100 %)
    effective updates:     	2446198	( 10.01 %)
    |--add successful:     	1223064	( 5.00 %)
    |--remove succ.:       	1223134	( 5.00 %)
    |--addAll succ.:       	0	( 0.00 %)
    |--removeAll succ.:    	0	( 0.00 %)
    size successful:       	0	( 0.00 %)
    contains succ.:        	9781745	( 40.02 %)
    unsuccessful ops:      	12215988	( 49.98 %)
  Final size:              	4026
  Expected size:           	4026
-------------------------------------------------------------------------------
TM statistics
-------------------------------------------------------------------------------
  Commits:                  	0
  |--regular read only  (%) 	0	( NaN %)
  |--elastic (%)            	0	( NaN %)
  |--regular update (%)     	0	( NaN %)
  Starts:                   	0
  Aborts:                   	0	( 100 %)
  |--between succ. reads:   	0	( NaN %)
  |--between read & write:  	0	( NaN %)
  |--extend upon read:      	0	( NaN %)
  |--write after read:      	0	( NaN %)
  |--locked on write:       	0	( NaN %)
  |--locked before read:    	0	( NaN %)
  |--locked before eread:   	0	( NaN %)
  |--locked on read:        	0	( NaN %)
  |--invalid commit:        	0	( NaN %)
  |--invalid snapshot:      	0	( NaN %)
  Read set size on avg.:    	NaN
  Write set size on avg.:   	NaN
  Tx time-to-commit on avg.:	NaN microsec
  Number of elastic reads       0
  Number of reads in RO prefix  0
```


```
$> make
$> make check-notx-csls
java -server  \
-cp bin \
contention.benchmark.Test \
-v -W 2 -u 20 -a 0 -s 0 -l 2000 -t 5 -i 4096 -r 8192 -b tmjee.ConcurrentSkipListSet
-------------------------------------------------------------------------------
Synchrobench-java
A benchmark-suite to evaluate synchronization techniques
-------------------------------------------------------------------------------

Benchmark parameters
--------------------
  Detailed stats:          	enabled
  Number of threads:       	5
  Length:                  	5000 ms
  Write ratio:             	20 %
  WriteAll ratio:          	0 %
  Snapshot ratio:          	0 %
  Size:                    	4096 elts
  Range:                   	8192 elts
  WarmUp:                  	2 s
  Iterations:              	1
  Benchmark:               	tmjee.ConcurrentSkipListSet
waiting for join
Thread #0 finished.
Thread #3 finished.
Thread #4 finished.
Thread #1 finished.
Thread #2 finished.
waiting for join
Thread #0 finished.
Thread #1 finished.
Thread #2 finished.
Thread #3 finished.
Thread #4 finished.
-------------------------------------------------------------------------------
Benchmark statistics
-------------------------------------------------------------------------------
  Average traversal length: 	NaN
  Struct Modifications:     	0
  Throughput (ops/s):       	1.3843037954454653E7
  Elapsed time (s):         	5.006
  Operations:               	69298248	( 100 %)
    effective updates:     	6925854	( 9.99 %)
    |--add successful:     	3462913	( 5.00 %)
    |--remove succ.:       	3462941	( 5.00 %)
    |--addAll succ.:       	0	( 0.00 %)
    |--removeAll succ.:    	0	( 0.00 %)
    size successful:       	0	( 0.00 %)
    contains succ.:        	27730672	( 40.02 %)
    unsuccessful ops:      	34641722	( 49.99 %)
  Final size:              	4068
  Expected size:           	4068
-------------------------------------------------------------------------------
TM statistics
-------------------------------------------------------------------------------
  Commits:                  	0
  |--regular read only  (%) 	0	( NaN %)
  |--elastic (%)            	0	( NaN %)
  |--regular update (%)     	0	( NaN %)
  Starts:                   	0
  Aborts:                   	0	( 100 %)
  |--between succ. reads:   	0	( NaN %)
  |--between read & write:  	0	( NaN %)
  |--extend upon read:      	0	( NaN %)
  |--write after read:      	0	( NaN %)
  |--locked on write:       	0	( NaN %)
  |--locked before read:    	0	( NaN %)
  |--locked before eread:   	0	( NaN %)
  |--locked on read:        	0	( NaN %)
  |--invalid commit:        	0	( NaN %)
  |--invalid snapshot:      	0	( NaN %)
  Read set size on avg.:    	NaN
  Write set size on avg.:   	NaN
  Tx time-to-commit on avg.:	NaN microsec
  Number of elastic reads       0
  Number of reads in RO prefix  0

```







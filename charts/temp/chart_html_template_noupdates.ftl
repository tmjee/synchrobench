<html>
<head>
    <script type="text/javascript"
            src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart', 'line']
            }]
          }"></script>

    <script type="text/javascript">
        google.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Thread', 'SynchronizedSkipListSet', 'ConcurrentSkipListSet', "Baseline"],
            <#list data?keys as dk>
                ['${data[dk].getThreads()}', ${data[dk].getSync()?string("##############.####")}, ${data[dk].getConc()?string("##############.####")}, ${data[dk].getNoop()?string("##############.####")}]<#if dk?has_next>,</#if>
            </#list>
            ]);

            var options = {
                title: 'Performance (No Updates)',
                legend: { position: 'bottom' },
                hAxis: {
                    title: 'Threads'
                },
                vAxis: {
                    title: 'Ops/secs'
                }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
        }
    </script>
    <pre>
	$\{JAVA\} $\{JAVAOPT\} \
	-cp bin \
	contention.benchmark.Test \
	-v -W 20 -u 30 -a 0 -s 0 -d 10000 -t $\{N_THREADS\} -i 4096 -r 8192 -b $\{BENCH_CLASS\}
    </pre>

    <table border="1">
        <tr>
            <td>Thread</td>
            <td>Synchronized SkipListSet</td>
            <td>ConcurrentSkipListSet</td>
            <td>Baseline</td>
        </tr>
    <#list data?keys as dk>
        <tr>
            <td>${data[dk].getThreads()}</td>
            <td>${data[dk].getSync()?string("##############.####")}</td>
            <td>${data[dk].getConc()?string("##############.####")}</td>
            <td>${data[dk].getNoop()?string("##############.####")}</td>
        </tr>
    </#list>
    </table>
</head>
<body>
<div id="curve_chart" style="width: 900px; height: 500px"></div>
</body>
</html>

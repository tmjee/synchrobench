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
                ['Thread', 'Sync', 'Conc', "Noop"],
                <#list data?keys as dk>
                    ['${data[dk].getThreads()}', ${data[dk].getSync()?string("##############.####")}, ${data[dk].getConc()?string("##############.####")}, ${data[dk].getNoop()?string("##############.####")}]<#if dk?has_next>,</#if>
                </#list>
            ]);

            var options = {
                title: 'Performance',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
        }
    </script>

    <table border="1">
        <tr>
            <td>Thread</td>
            <td>Sync</td>
            <td>Conc</td>
            <td>Noop</td>
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
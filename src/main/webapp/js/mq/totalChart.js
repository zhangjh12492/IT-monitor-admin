var queueTimer;


function getDetailInfo() {
    queueTimer = setInterval(function () {
        $.ajax({
            type: "post",
            url: "mq_totalChart.do?getOverview",
            dataType: "json",
            data: {groupSid: 1, status: 1},
            async: false,
            success: function (data) {
                y = data.queue_totals.messages;
                $("#con_id").text(data.object_totals.connections);
                $("#chan_id").text(data.object_totals.channels);
                $("#cons_id").text(data.object_totals.consumers);
                $("#ex_id").text(data.object_totals.exchanges);
                $("#qu_id").text(data.object_totals.queues);
                $("#mes_id").text(data.queue_totals.messages);
                $("#rea_id").text(data.queue_totals.messages_ready);
                $("#un_id").text(data.queue_totals.messages_unacknowledged);
                $("#pub_id").text(data.message_stats.publish);
                $("#ack_id").text(data.message_stats.ack);
                $("#del_id").text(data.message_stats.deliver_get);
                $("#red_id").text(data.message_stats.redeliver);
                $("#deget_id").text(data.message_stats.deliver);
                $("#get_id").text(data.message_stats.get);
                $("#getno_id").text(data.message_stats.get_no_ack);
            }
        });
    }, 10000);

}

function dongtaitu() {
    var chart;

    var chartData = [
        {
            year: 1994,
            cars: 1587,
            motorcycles: 650,
            bicycles: 121
        },
        {
            year: 1995,
            cars: 1567,
            motorcycles: 683,
            bicycles: 146
        },
        {
            year: 1996,
            cars: 1617,
            motorcycles: 691,
            bicycles: 138
        },
        {
            year: 1997,
            cars: 1630,
            motorcycles: 642,
            bicycles: 127
        },
        {
            year: 1998,
            cars: 1660,
            motorcycles: 699,
            bicycles: 105
        },
        {
            year: 1999,
            cars: 1683,
            motorcycles: 721,
            bicycles: 109
        },
        {
            year: 2000,
            cars: 1691,
            motorcycles: 737,
            bicycles: 112
        },
        {
            year: 2001,
            cars: 1298,
            motorcycles: 680,
            bicycles: 101
        },
        {
            year: 2002,
            cars: 1275,
            motorcycles: 664,
            bicycles: 97
        },
        {
            year: 2003,
            cars: 1246,
            motorcycles: 648,
            bicycles: 93
        },
        {
            year: 2004,
            cars: 1218,
            motorcycles: 637,
            bicycles: 101
        },
        {
            year: 2005,
            cars: 1213,
            motorcycles: 633,
            bicycles: 87
        },
        {
            year: 2006,
            cars: 1199,
            motorcycles: 621,
            bicycles: 79
        },
        {
            year: 2007,
            cars: 1110,
            motorcycles: 210,
            bicycles: 81
        },
        {
            year: 2008,
            cars: 1165,
            motorcycles: 232,
            bicycles: 75
        },
        {
            year: 2009,
            cars: 1145,
            motorcycles: 219,
            bicycles: 88
        },
        {
            year: 2010,
            cars: 1163,
            motorcycles: 201,
            bicycles: 82
        },
        {
            year: 2011,
            cars: 1180,
            motorcycles: 285,
            bicycles: 87
        },
        {
            year: 2012,
            cars: 1159,
            motorcycles: 277,
            bicycles: 71
        }
    ];

    var chartData2 = [
        {
            year: 1995,
            cars: 1567,
            motorcycles: 683,
            bicycles: 146
        },
        {
            year: 1996,
            cars: 1617,
            motorcycles: 691,
            bicycles: 138
        },
        {
            year: 1997,
            cars: 1630,
            motorcycles: 642,
            bicycles: 127
        },
        {
            year: 1998,
            cars: 1660,
            motorcycles: 699,
            bicycles: 105
        },
        {
            year: 1999,
            cars: 1683,
            motorcycles: 721,
            bicycles: 109
        },
        {
            year: 2000,
            cars: 1691,
            motorcycles: 737,
            bicycles: 112
        },
        {
            year: 2001,
            cars: 1298,
            motorcycles: 680,
            bicycles: 101
        },
        {
            year: 2002,
            cars: 1275,
            motorcycles: 664,
            bicycles: 97
        },
        {
            year: 2003,
            cars: 1246,
            motorcycles: 648,
            bicycles: 93
        },
        {
            year: 2004,
            cars: 1218,
            motorcycles: 637,
            bicycles: 101
        },
        {
            year: 2005,
            cars: 1213,
            motorcycles: 633,
            bicycles: 87
        },
        {
            year: 2006,
            cars: 1199,
            motorcycles: 621,
            bicycles: 79
        },
        {
            year: 2007,
            cars: 1110,
            motorcycles: 210,
            bicycles: 81
        },
        {
            year: 2008,
            cars: 1165,
            motorcycles: 232,
            bicycles: 75
        },
        {
            year: 2009,
            cars: 1145,
            motorcycles: 219,
            bicycles: 88
        },
        {
            year: 2010,
            cars: 1163,
            motorcycles: 201,
            bicycles: 82
        },
        {
            year: 2011,
            cars: 1180,
            motorcycles: 285,
            bicycles: 87
        },
        {
            year: 2012,
            cars: 1159,
            motorcycles: 277,
            bicycles: 71
        },
        {
            year: 2013,
            cars: 1259,
            motorcycles: 377,
            bicycles: 91
        }
    ];

    AmCharts.ready(function () {
        // SERIAL CHART
        chart = new AmCharts.AmSerialChart();
        chart.pathToImages = "../amcharts/images/";
        chart.zoomOutButton = {
            backgroundColor: "#000000",
            backgroundAlpha: 0.15
        };
        chart.dataProvider = chartData;
        chart.categoryField = "year";

        chart.addTitle("Traffic incidents per year", 15);

        // AXES
        // Category
        var categoryAxis = chart.categoryAxis;
        categoryAxis.gridAlpha = 0.07;
        categoryAxis.axisColor = "#DADADA";
        categoryAxis.startOnAxis = true;

        // Value
        var valueAxis = new AmCharts.ValueAxis();
        valueAxis.title = "percent"; // this line makes the chart "stacked"
        valueAxis.stackType = "100%";
        valueAxis.gridAlpha = 0.07;
        chart.addValueAxis(valueAxis);

        // GRAPHS
        // first graph
        var graph = new AmCharts.AmGraph();
        graph.type = "line"; // it's simple line graph
        graph.title = "Cars";
        graph.valueField = "cars";
        graph.balloonText = "[[value]] ([[percents]]%)";
        graph.lineAlpha = 0;
        graph.fillAlphas = 0.6; // setting fillAlphas to > 0 value makes it area graph
        chart.addGraph(graph);

        // second graph
        var graph = new AmCharts.AmGraph();
        graph.type = "line";
        graph.title = "Motorcycles";
        graph.valueField = "motorcycles";
        graph.balloonText = "[[value]] ([[percents]]%)";
        graph.lineAlpha = 0;
        graph.fillAlphas = 0.6;
        chart.addGraph(graph);

        // third graph
        var graph = new AmCharts.AmGraph();
        graph.type = "line";
        graph.title = "Bicycles";
        graph.valueField = "bicycles";
        graph.balloonText = "[[value]] ([[percents]]%)";
        graph.lineAlpha = 0;
        graph.fillAlphas = 0.6;
        chart.addGraph(graph);

        // LEGEND
        var legend = new AmCharts.AmLegend();
        legend.align = "center";
        chart.addLegend(legend);

        // CURSOR
        var chartCursor = new AmCharts.ChartCursor();
        chartCursor.zoomable = false; // as the chart displayes not too many values, we disabled zooming
        chartCursor.cursorAlpha = 0;
        chart.addChartCursor(chartCursor);

        // WRITE
        chart.write("totalChart");
    });
    //刷新事件
    function reloadData(url) {
        var dynamicData = loadStringData(url);
        //fileSystemChart.dataProvider = eval('(' + dynamicData + ')');//如果ajax获取得来的数据含有引号，需要eval()函数处理一下
        chart.dataProvider = dynamicData;
        chart.validateNow();
        chart.validateData();
    }

    //ajax请求
    function loadStringData(link) {

        return chartData2;

        /* 下面的是ajax请求，可以从服务器获取动态数据
         if (window.XMLHttpRequest) {
         // IE7+, Firefox, Chrome, Opera, Safari
         var request = new XMLHttpRequest();
         }
         else {
         // code for IE6, IE5
         var request = new ActiveXObject('Microsoft.XMLHTTP');
         }
         // load
         request.open('GET', link, false);
         request.send();
         return request.responseText;
         */
    }

    window.setInterval("reloadData('')", 5000);//五秒刷新
}

var chart;
function charView(c) {
    $('#totalChart').highcharts({
        chart: {
            type: 'spline',
            height: c,
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {

                    // set up the updating of the chart each second
                    var series = this.series[0];
                    queueTimer = setInterval(function () {
                        var x = ((new Date()).getTime()) + 8 * 3600000, // current time
                            y = 0;
                        $.ajax({
                            type: "post",
                            url: "mq_totalChart.do?getOverview",
                            dataType: "json",
                            data: {groupSid: 1, status: 1},
                            async: false,
                            success: function (data) {
                                y = data.queue_totals.messages;
                                $("#con_id").text(data.object_totals.connections);
                                $("#chan_id").text(data.object_totals.channels);
                                $("#cons_id").text(data.object_totals.consumers);
                                $("#ex_id").text(data.object_totals.exchanges);
                                $("#qu_id").text(data.object_totals.queues);
                                $("#mes_id").text(data.queue_totals.messages);
                                $("#rea_id").text(data.queue_totals.messages_ready);
                                $("#un_id").text(data.queue_totals.messages_unacknowledged);
                                $("#pub_id").text(data.message_stats.publish);
                                $("#ack_id").text(data.message_stats.ack);
                                $("#del_id").text(data.message_stats.deliver_get);
                                $("#red_id").text(data.message_stats.redeliver);
                                $("#deget_id").text(data.message_stats.deliver);
                                $("#get_id").text(data.message_stats.get);
                                $("#getno_id").text(data.message_stats.get_no_ack);
                            }
                        });

                        series.addPoint([x, y], true, true);
                    }, 10000);
//                    }, 10000000);
                }
            }
        },
        title: {
            text: ''
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: '消息数'
            },
            min: 0,
            plotLines: [
                {
                    value: 0,
                    width: 1,
                    color: '#808080'
                }
            ]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br>' +
                    Highcharts.numberFormat(this.y, 0);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [
            {
                name: '消息数',
                data: (function () {
                    // generate an array of random data
                    var data = [],
                        time = ((new Date()).getTime()) + 8 * 3600000,
                        i;
                    for (i = -19; i <= 0; i++) {
                        data.push({
                            x: time + i * 10000,
                            y: 0
                        });
                    }
                    return data;
                })()
            }
        ]
    });
}

function charView(c) {
    $('#totalChart').highcharts({
        chart: {
            type: 'spline',
            height: 500,
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
            events: {
                load: function () {

                    // set up the updating of the chart each second
                    var series = this.series[0];
                    queueTimer = setInterval(function () {
                        var x = ((new Date()).getTime()) + 8 * 3600000, // current time
                            y = 0;
                        $.ajax({
                            type: "post",
                            url: "mq_totalChart.do?getOverview",
                            dataType: "json",
                            data: {groupSid: 1, status: 1},
                            async: false,
                            success: function (data) {
                                y = data.queue_totals.messages;
                                $("#con_id").text(data.object_totals.connections);
                                $("#chan_id").text(data.object_totals.channels);
                                $("#cons_id").text(data.object_totals.consumers);
                                $("#ex_id").text(data.object_totals.exchanges);
                                $("#qu_id").text(data.object_totals.queues);
                                $("#mes_id").text(data.queue_totals.messages);
                                $("#rea_id").text(data.queue_totals.messages_ready);
                                $("#un_id").text(data.queue_totals.messages_unacknowledged);
                                $("#pub_id").text(data.message_stats.publish);
                                $("#ack_id").text(data.message_stats.ack);
                                $("#del_id").text(data.message_stats.deliver_get);
                                $("#red_id").text(data.message_stats.redeliver);
                                $("#deget_id").text(data.message_stats.deliver);
                                $("#get_id").text(data.message_stats.get);
                                $("#getno_id").text(data.message_stats.get_no_ack);
                            }
                        });

                        series.addPoint([x, y], true, true);
                    }, 10000);
//                    }, 10000000);
                }
            }
        },
        title: {
            text: ''
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: '消息数'
            },
            min: 0,
            plotLines: [
                {
                    value: 0,
                    width: 1,
                    color: '#808080'
                }
            ]
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br>' +
                    Highcharts.numberFormat(this.y, 0);
            }
        },
        legend: {
            enabled: false
        },
        exporting: {
            enabled: false
        },
        series: [
            {
                name: '消息数',
                data: (function () {
                    // generate an array of random data
//                    在这里处理时区问题 +  + 3600000*8
                    var data = [],
                        time = ((new Date()).getTime()) + 8 * 3600000,
                        i;

                    for (i = -19; i <= 0; i++) {
                        data.push({
                            x: time + i * 10000,
                            y: 0
                        });
                    }
                    return data;
                })()
            }
        ]
    });
}


function stopView() {

    clearInterval(queueTimer);
}


$(function () {
//    getDetailInfo();

//    alert("abc");
//    dongtaitu();
    charView(1);
});

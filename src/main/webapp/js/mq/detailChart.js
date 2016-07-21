var groupTable;
var usersTable;
var userEditTable;
var groupDlg;
var index = 0;
//float bar chart
var queueName = '';
var queueData = [];

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function getArrayData(value) {
    var timeStr = new Date().Format("mm:ss");
    if (queueData.length <= 20) {
        queueData.push([timeStr, value]);
    } else {
        queueData.shift();
        queueData.push([timeStr, value]);
    }
    return queueData;
}

function barChart(data, title) {
    if ($('#barChart').size() != 1) {
        return;
    }

    function randValue() {
        return (Math.floor(Math.random() * (1 + 40 - 20))) + 20;
    }

    var pageviews = [
        [1, randValue()],
        [2, randValue()],
        [3, 2 + randValue()],
        [4, 3 + randValue()],
        [5, 5 + randValue()],
        [6, 10 + randValue()]
    ];
    var plot = $.plot($("#barChart"), [
        {
            data: data,
            label: title,
            lines: {
                lineWidth: 1
            },
            shadowSize: 0

        }
    ], {
        series: {
            lines: {
                show: true,
                lineWidth: 2,
                fill: true,
                fillColor: {
                    colors: [
                        {
                            opacity: 0.05
                        },
                        {
                            opacity: 0.01
                        }
                    ]
                }
            },
            points: {
                show: true,
                radius: 3,
                lineWidth: 1
            },
            shadowSize: 2
        },
        grid: {
            hoverable: true,
            clickable: true,
            tickColor: "#eee",
            borderColor: "#eee",
            borderWidth: 1
        },
        colors: ["#37b7f3", "#52e136"],
        xaxis: {

            tickLength: 0,
            tickDecimals: 0,
            mode: "categories",
            min: 0,
            font: {
                lineHeight: 14,
                style: "normal",
                variant: "small-caps",
                color: "#6F7B8A"
            }
        },
        yaxis: {
            ticks: 5,
            tickDecimals: 0,
            tickColor: "#eee",
            font: {
                lineHeight: 14,
                style: "normal",
                variant: "small-caps",
                color: "#6F7B8A"
            }
        }
    });


    function showTooltip(x, y, contents) {
        $('<div id="tooltip">' + contents + '</div>').css({
            position: 'absolute',
            display: 'none',
            top: y + 5,
            left: x + 15,
            border: '1px solid #333',
            padding: '4px',
            color: '#fff',
            'border-radius': '3px',
            'background-color': '#333',
            opacity: 0.80
        }).appendTo("body").fadeIn(200);
    }

    var previousPoint = null;
    $("#barChart").bind("plothover", function (event, pos, item) {
        $("#x").text(pos.x.toFixed(2));
        $("#y").text(pos.y.toFixed(2));

        if (item) {
            if (previousPoint != item.dataIndex) {
                previousPoint = item.dataIndex;

                $("#tooltip").remove();
                var x = item.datapoint[0].toFixed(2),
                    y = item.datapoint[1];

                showTooltip(item.pageX, item.pageY, item.series.label + " of " + y);
            }
        } else {
            $("#tooltip").remove();
            previousPoint = null;
        }
    });
}


function amPieChart(dataSet) {
    var chart = AmCharts.makeChart("pieChart", {
        "type": "pie",
        "theme": "light",
        "fontFamily": 'Open Sans',
        "color": '#888',
        "dataProvider": dataSet,
        "valueField": "data",
        "titleField": "label",
        "exportConfig": {
            menuItems: [
                {
                    icon: Metronic.getGlobalPluginsPath() + "amcharts/amcharts/images/export.png",
                    format: 'png'
                }
            ]
        }
    });
    chart.addListener("clickSlice", pieErrorChartClick);
    $('#amChart').closest('.portlet').find('.fullscreen').click(function () {
        chart.invalidateSize();
    });
}


function getGroupList() {
    index = 0;
    $('#group').dataTable().fnDestroy();
    groupTable = $("#group").dataTable({
        "bDestory": true,
        "bFilter": false,

        "sAjaxSource": "mq_detailChart.do?getQueuesList&groupSid=1&status=1",
        "bProcessing": true,
        "searching": false, //去掉搜索框
        "bLengthChange": false,// 是否允许自定义每页显示条数.
        "aLengthMenu": [
            [1, 5, 10, 25, 50, 100, -1],
            [1, 5, 10, 25, 50, 100, "所有"]
        ],
        "bServerSide": true,
        "bPaginate": false,  //是否显示分页器
        "sScrollY": "350px",
        "iDisplayLength": 10,
        "oLanguage": {//语言设置
            "sLengthMenu": "每页显示  _MENU_ 条记录",
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            },
            "sZeroRecords": "抱歉， 没有找到",
            "sInfoEmpty": "没有数据"
        },
//        "sAjaxDataProp": 'object',  //这个改写默认取值的JSON对象
        "aoColumns": [
            /*
             {
             "mDataProp": 'sid',
             "sTitle": "编号",
             "bSortable": false
             },
             */
            {
                "mDataProp": 'name',
                "sTitle": "队列名称",
                "bSortable": false
            },
            {
                "mDataProp": 'state',
                "sTitle": "状态",
                "bSortable": false
            },
            {
                "mDataProp": 'messages',
                "sTitle": "消息数",
                "bSortable": false
            },
            {
                "mDataProp": 'messagesReady',
                "sTitle": "等待数",
                "bSortable": false
            },
            {
                "mDataProp": 'messagesUnacknowledged',
                "sTitle": "未收到消息数",
                "bSortable": false
            },
            {
                "mDataProp": 'durable',
                "sTitle": "持久化",
                "bSortable": false
            },
            {
                "mDataProp": 'autoDelete',
                "sTitle": "自动删除",
                "bSortable": false
            },
            {
                "mDataProp": 'memory',
                "sTitle": "占用内存",
                "bSortable": false
            }
        ],
        "aoColumnDefs": [
            {
                sDefaultContent: '',
                aTargets: [ '_all' ]
            }
        ],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            if (aData['state'] == 'running') {
                $('td:eq(1)', nRow).css('background-color', '#00ee00');
            } else {
                $('td:eq(1)', nRow).css('background-color', '#CC0000');
            }
        }
    });
}

function pieChart(groupSid) {
    $.ajax({
        url: 'mq_detailChart.do?getQueueChartList&groupSid=1',
        type: 'POST',
        dataType: 'json',
        data: {},
        success: function (result) {
            console.log(result);
            var pieData = new Array();
            var dtt = null;
            for (var i = 0; i < result.length; i++) {
                pieData.push({
                    label: result[i].name,
                    data: [
                        result[i].messages
                    ]
                });
                if (queueName == '' && result[i].messages != 0) {
                    queueName = result[i].name;
                    dtt = getArrayData(result[i].messages);
                    barChart(dtt, queueName);
                } else {
                    if(queueName == result[i].name){
                        dtt = getArrayData(result[i].messages);
                        barChart(dtt, queueName);
                    }
                }
            }
            amPieChart(pieData);
        }

    })
}

function barChart3(groupSid) {
    $.ajax({
        url: 'mq_detailChart.do?getQueueChartList&groupSid=1',
        type: 'POST',
        dataType: 'json',
        data: {
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            var barData = [];
            for (var i = 0; i < result.length; i++) {
                barData[i] = {
                    "year": result[i].name,
                    "income": result[i].messages,
                    "expenses": result[i].messages
                };
            }
            amChart(barData);
        }
    });


}

function pieErrorChartClick(data) {
    queueName = data.dataItem.title;
    var msgCount = data.dataItem.value;
    queueData.length = 0;
    var chartData = getArrayData(msgCount);
//    alert(chartData);
    barChart(chartData, queueName);

}

$(function () {
    getGroupList();
    setInterval("getGroupList()",10000);//1000为1秒钟

    pieChart(1);
    setInterval("pieChart(1)",10000);//1000为1秒钟
//    barChart();
//    barChart3(1);

//    amChart('');
});



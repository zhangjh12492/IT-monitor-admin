function Map() {
	var struct = function(key, value) {
		this.key = key;
		this.value = value;
	};

	var put = function(key, value) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				this.arr[i].value = value;
				return;
			}
		}
		this.arr[this.arr.length] = new struct(key, value);
	};

	var get = function(key) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				return this.arr[i].value;
			}
		}
		return null;
	};

	var remove = function(key) {
		var v;
		for ( var i = 0; i < this.arr.length; i++) {
			v = this.arr.pop();
			if (v.key === key) {
				continue;
			}
			this.arr.unshift(v);
		}
	};

	var size = function() {
		return this.arr.length;
	};

	var isEmpty = function() {
		return this.arr.length <= 0;
	};
	this.arr = new Array();
	this.get = get;
	this.put = put;
	this.remove = remove;
	this.size = size;
	this.isEmpty = isEmpty;
}  

/**
 * 初始化webSocket的方法
 */
//function initWSConnect(){
//	/**
//	 * 系统监控信息index界面调用websocket
//	 */
//	var ws_sysMonitorIndex = new WebSocket("ws://localhost:8080/exceptionSocketPro/sysMonitorIndex.do");
//	ws_sysMonitorIndex.onopen = function() {
//		ws_sysMonitorIndex.send("hello");
//	};
//	ws_sysMonitorIndex.onmessage = function(message) {
//		var item=$.parseJSON(message.data);
//
//		if(item!=null&&item!=undefined ){
//			initSysMonitorChart(item);
//			initErrRateChart(item);
//			throughputRate(item);	//吞吐率
//			theCpuUsedRate(item);	//cpu使用率
//			totalPhysicalMemory(item);	//物理内存
//			apdexIndex(item);	//apdex指标
//		}
//	};
//
//
//	/**
//	 * 系统监控列表信息,展示底部列表数据
//	 */
//	var ws_sysMonitorList = new WebSocket("ws://localhost:8080/exceptionSocketPro/sysMonitorList.do");
//	ws_sysMonitorList.onopen = function() {
//		ws_sysMonitorList.send("hello");
//	};
//	ws_sysMonitorList.onmessage = function(message) {
//		var item=$.parseJSON(message.data);
//
//		if(item!=null&&item!=undefined ){
//			table_sys_monitor_list(item);
//		}
//	};
//
//}

/*存放所有的map对象，用于在折线图的鼠标悬浮事件中使用*/
var initMap=new Map();

/**
 * 初始化系统监控界面
 */
function initMomitor(){
	initMap=new Map();
	getSysInfo();	//获取系统信息
	initMonitorData();	
	//initWSConnect();	//初始化webSocket
}
/**
 * 初始化系统监控界面的折线图数据
 */
function initMonitorData(){
	$.ajax({
		 url:'sysMonitor.do?sysMonitorIndex&sysCode='+$("#code").val(), 
		 type:'post',    
		 cache:false,
		 async:false,
		 timeout:1000*60,   
	//   url: 'sysOperate.do?addSysInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(), 
	   dataType: 'json',
	   error: function () {
	       alert('网络失败！');
	   },
	   success: function (item) { 
		    
			if(item!=null&&item!=undefined ){  
				
				initSysMonitorChart(item.listMonitorAvag); 
				initErrRateChart(item.listMonitorAvag);   
				throughputRate(item.listMonitorAvag);	//吞吐率
				theCpuUsedRate(item.listMonitorAvag);	//cpu使用率
				totalPhysicalMemory(item.listMonitorAvag);	//物理内存
				apdexIndex(item.listMonitorAvag);	//apdex指标
				
				table_sys_monitor_list(item.listMonitorData);	//展示系统监控信息的列表
			}
	   }

	});
}


/**
 * 初始化系统名称
 */
function getSysInfo(){
	$.ajax({
		url:"sysOperate.do?getSysInfoAjax",
		type:"post",
		cache:false,
		async:false,
		timeout:1000*60,
		dateType:"json",
		success:function(data){ 
			loadSysSelect($.parseJSON(data));
		},error:function(e){
			alert(e);
		}
	});
}

/**
 * 装在顶部的系统下拉框
 */
function loadSysSelect(data){
	if(data==null){
		return;
	}
	var code=$("#code").val();
	var li="";
	$.each(data,function(index,item){
		if(item.sysCode==code){
			$("#js_sele").html("<span>"+item.sysName+"-"+item.sysCode+"</span><b class=\"posa icon_1 seat1\"></b>");
			li+="<li class=\"active-result highlighted\"><a class=\"pl_15\">"+item.sysName+"-"+item.sysCode+"</a></li>";
		}else{
			li+="<li class=\"active-result\"><a class=\"pl_15\">"+item.sysName+"-"+item.sysCode+"</a></li>";
		} 
	});
	$("#chosen_results_sys_select").html(li);
}

/**
 * 应用服务器响应折线图展示
 */


function initSysMonitorChart(data) {
	if ($('#chart_2').size() != 1) { 
		return;
	}
	
	var visitors = [];
	var contentMap=new Map();

	
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.serverResTime ];
		visitors.push(visitor);
		var serverReqObj={"timeQuantum":item.timeQuantum,"serverResTime":item.serverResTime,"sumdealReqCounts":item.sumdealReqCounts};
		contentMap.put(item.createTime,serverReqObj);
//		contentMap.put(item.createTime,);
	});
	initMap.put("initSysMonitorChart_contentMap",contentMap);
	var plot = $.plot($("#chart_2"), [ {
		data : visitors, 
		label : "应用服务器响应时间",
		lines : {
			lineWidth : 2, 
		},
		shadowSize : 0

	} ], {
		series : {
			lines : {
				show : true,
				lineWidth : 2,
				fill : true,
				fillColor : {
					colors : [ {
						opacity : 0.60
					}, {
						opacity : 0.60 
					} ]
				}
			}, 
			points : {
				show : true, 
				radius : 3, 
				fillColor: "#3598DB",  
				lineWidth : 2  
			},
			shadowSize : 0  
		},

		colors : [ "#3598DB", "#3598DB", "#66B1E3" ],
		xaxis : {

			tickLength : 0,
			tickDecimals : 0,
			mode : "categories",
			min : 0,
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		yaxis : {
			ticks : 5,
			tickDecimals : 0,
			tickColor : "#eee",
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		grid : {
			hoverable : true,
			clickable : true,
			tickColor : "#eee",
			borderColor : "#eee",
			borderWidth : 1
		}
	});

	
	
	
	function showTooltip(x, y, contentObj) { 
		$("<div id=\"tooltip\" class=\"highcharts-tooltip\"  > <table> <tbody>  <tr> <td align=\"center\" colspan=\"2\">响应时间</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> <tr><td>请求响应时间:</td><td>"+contentObj["serverResTime"]+"(毫秒)</td></tr><tr><td>访问量:</td><td>"+contentObj["sumdealReqCounts"]+"</td></tr></tbody></table> </div>")
		.css({ 
			position : 'absolute',
			display : 'none',
			top : y + 5,
			left : x - 15,
			border : '1px solid #333',
			padding : '4px', 
			color : '#080808',
			'border-radius' : '3px',
			'background-color' : '#FCFCFC',  
			opacity : 0.80
		}).appendTo("body").fadeIn(200);
	}

	var previousPoint = null;
	// 折线图点的鼠标悬浮事件
	$("#chart_2").bind("plothover",function(event, pos, item) {
				$("#x").text(pos.x.toFixed(1));
				$("#y").text(pos.y.toFixed(1));

				if (item) { 
					if (previousPoint != item.dataIndex) {
						previousPoint = item.dataIndex;
						$("#tooltip").remove();
						var x = item.series.data[previousPoint][0];
						y = item.datapoint[1].toFixed(2);

						showTooltip(item.pageX, item.pageY,initMap.get("initSysMonitorChart_contentMap").get(x) );
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}


/**
 * apdex指标
 */
function apdexIndex(data) {
	if(data==null || data.length==0){
		return;
	}
	var visitors = [];
	var contentMap=new Map(); 
	$.each(data, function(index, item) {
		if(index==0){
			var dataObj={
		            "createTime": item.createTime,
		            "europe": 70,
		            "namerica": 15,
		            "asia": 9,
		            "lamerica": 6,
					"apdexIndex": item.apdexIndex
		    } 
			visitors.push(dataObj);
			contentMap.put(item.createTime,item.apdexIndex/100);
		}else{
			var dataObj={"createTime": item.createTime,"apdexIndex": item.apdexIndex};
			visitors.push(dataObj);
			contentMap.put(item.createTime,item.apdexIndex/100);
		}
//		contentMap.put(item.createTime,"<span style='font-size:13px;'>吞吐率  :<b>"+item.apdexIndex/100+"</b> [[additional]]</span>");
//		var visitor = [ item.createTime, item.apdexIndex ];
//		visitors.push(visitor);
		
//		contentMap.put(item.createTime,);
	});	


    


        // SERIAL CHART
		var chart = new AmCharts.AmSerialChart();
        chart.dataProvider = visitors;
        chart.categoryField = "createTime";
        // sometimes we need to set margins manually
        // autoMargins should be set to false in order chart to use custom margin values
      
		
// AXES
     
		
        // AXES
        // category
        var categoryAxis = chart.categoryAxis;
		categoryAxis.gridAlpha = 0.1;
        categoryAxis.axisAlpha = 0;
        categoryAxis.gridPosition = "start";

        // value
        var valueAxis = new AmCharts.ValueAxis();
        valueAxis.stackType = "100%"; // this line makes the chart 100% stacked
        valueAxis.labelsEnabled = false;
	//	valueAxis.reversed = true; //扭转y轴的值
		valueAxis.gridAlpha = 0.1;
        valueAxis.axisAlpha = 0;
        chart.addValueAxis(valueAxis);

        // GRAPHS
        // first graph
        var graph = new AmCharts.AmGraph();
        graph.labelText = "0.7";
        graph.valueField = "europe";
        graph.type = "column"; 
        graph.lineAlpha = 0.1;
        graph.fillAlphas = 0.8; 
        graph.lineColor = "#F39E10";
		graph.fixedColumnWidth=10;
		graph.labelPosition="left"; 
		graph.visibleInLegend=false;	//是否显示图例名称
		graph.showBalloon=false;	//不显示鼠标悬浮上去的气球
        chart.addGraph(graph);

        // second graph
        graph = new AmCharts.AmGraph();
        graph.labelText = "0.85";
        graph.valueField = "namerica";
        graph.type = "column";
        graph.lineAlpha = 0.1;
        graph.fillAlphas = 0.8;
        graph.lineColor = "#E0DA0C";
		graph.fixedColumnWidth=10;
		graph.labelPosition="left";
		graph.visibleInLegend=false;
		graph.showBalloon=false;	//不显示鼠标悬浮上去的气球
        chart.addGraph(graph);

        // third graph
        graph = new AmCharts.AmGraph();
        graph.labelText = "0.94";
        graph.valueField = "asia";
        graph.type = "column";
        graph.lineAlpha = 0.1;
        graph.fillAlphas = 0.8;
        graph.lineColor = "#2E9FCB"; 
		graph.fixedColumnWidth=10;
		graph.labelPosition="left";
		graph.visibleInLegend=false;
		graph.showBalloon=false;	//不显示鼠标悬浮上去的气球
        chart.addGraph(graph);

        // fourth graph
        graph = new AmCharts.AmGraph();
        graph.labelText = "1.0";
        graph.valueField = "lamerica";
        graph.type = "column";
        graph.lineAlpha = 0.1;
        graph.fillAlphas = 0.8;
        graph.lineColor = "#75A722";
		graph.fixedColumnWidth=10;
		graph.labelPosition="left";
		graph.visibleInLegend=false;
		graph.showBalloon=false;	//不显示鼠标悬浮上去的气球
        chart.addGraph(graph);
		
		
	
      // line
        var graph2 = new AmCharts.AmGraph();
        graph2.type = "line";
        graph2.title = "Expenses";
        graph2.lineColor = "#3598DB"; 
        graph2.valueField = "apdexIndex";
        graph2.lineThickness = 2;
        graph2.bullet = "round";
        graph2.bulletBorderThickness = 3; 
        graph2.bulletBorderColor = "#3598DB"; 
        graph2.bulletBorderAlpha = 1;
        graph2.bulletColor = "#3598DB";
        graph2.dashLengthField = "dashLengthLine";
		graph2.stackable=false;  
        graph2.balloonText = "<span style='font-size:13px;'>[[title]] in [[category]]<b>[[value]]</b> [[additional]]</span>";
//        graph2.balloonText = contentMap.get("[[category]]");//"<span style='font-size:13px;'>[[title]] in [[category]]:<b>[[value]]</b> [[additional]]</span>";
        chart.addGraph(graph2);
        
        // LEGEND
        var legend = new AmCharts.AmLegend();
        legend.borderAlpha = 0.2;
        legend.horizontalGap = 10;
        legend.autoMargins = false;
		legend.useGraphSettings = true;
        chart.addLegend(legend);

        // WRITE
        chart.write("chart_7");


}


/**
 * 错误率折线图展示
 */
function initErrRateChart(data) {
	if ($('#chart_3').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.errRate ];
		visitors.push(visitor);
		var contentObj={"timeQuantum":item.timeQuantum,"sumdealReqCounts":item.sumdealReqCounts,"sumErrReqCounts":item.sumErrReqCounts,"errRate":item.errRate};
		contentMap.put(item.createTime,contentObj);
//		contentMap.put(item.createTime,);
	});
	initMap.put("initErrRateChart_contentMap",contentMap);
	var plot = $.plot($("#chart_3"), [ {
		data : visitors, 
		label : "错误率",
		lines : {
			lineWidth : 2, 
		},
		shadowSize : 0

	} ], {
		series : {
			lines : {
				show : true,
				lineWidth : 2,
				fill : true,
				fillColor : {
					colors : [ {
						opacity : 0.60
					}, {
						opacity : 0.60
					} ]
				}
			},
			points : {
				show : true,
				radius : 3,  
				fillColor: "#3598DB", 
				lineWidth : 2 
			},
			shadowSize : 0 
		},

		colors : [ "#3598DB", "#37b7f3", "#52e136" ],
		
		xaxis : {

			tickLength : 0,
			tickDecimals : 0,
			mode : "categories",
			min : 0,
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		yaxis : {
			ticks : 5,
			tickDecimals : 0,
			tickColor : "#eee",
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		grid : {
			hoverable : true,
			clickable : true,
			tickColor : "#eee",
			borderColor : "#eee",
			borderWidth : 1
		}
	});

	
	
	
	function showTooltip(x, y, contentObj) { 
		$("<div id=\"tooltip\" class=\"highcharts-tooltip\"  > <table> <tbody>  " +
				"<tr><td align=\"center\" colspan=\"2\">错误百分比</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>错误百分比:</td><td>"+contentObj["errRate"]+"(%)</td></tr>" +
				"<tr><td>请求响应时间:</td><td>"+contentObj["sumErrReqCounts"]+"</td></tr>" +
				"<tr><td>访问量:</td><td>"+contentObj["sumdealReqCounts"]+"</td></tr></tbody></table> </div>").css({ 
			position : 'absolute',
			display : 'none',
			top : y + 5,
			left : x + 15, 
			border : '1px solid #333',
			padding : '4px', 
			color : '#080808',
			'border-radius' : '3px',
			'background-color' : '#FCFCFC',  
			opacity : 0.80
		}).appendTo("body").fadeIn(200); 
	}

	var previousPoint = null;
	// 折线图点的鼠标悬浮事件
	$("#chart_3").bind(
			"plothover",
			function(event, pos, item) {
				$("#x").text(pos.x.toFixed(1));
				$("#y").text(pos.y.toFixed(1));

				if (item) { 
					if (previousPoint != item.dataIndex) {
						previousPoint = item.dataIndex;
						$("#tooltip").remove();
						var x = item.series.data[previousPoint][0];
						y = item.datapoint[1].toFixed(2);
						//alert(contentMap.get(x));
						showTooltip(item.pageX, item.pageY, initMap.get("initErrRateChart_contentMap").get(x));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}




/**
 * 最耗时Web应用过程（Web Action）图表
 */
function theMostTimeConsuming(data) {
	if ($('#chart_3').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.errRate ];
		visitors.push(visitor);
		
//		contentMap.put(item.createTime,);
	});	
	var plot = $.plot($("#chart_3"), [ {
		data : visitors, 
		label : "最耗时Web应用",
		lines : {
			lineWidth : 2, 
		},
		shadowSize : 0

	} ], {
		series : {
			lines : {
				show : true,
				lineWidth : 2,
				fill : true,
				fillColor : {
					colors : [ {
						opacity : 0.05
					}, {
						opacity : 0.01
					} ]
				}
			},
			points : {
				show : true,
				radius : 3,
				lineWidth : 1
			},
			shadowSize : 2
		},

		colors : [ "#d12610", "#37b7f3", "#52e136" ],
		xaxis : {

			tickLength : 0,
			tickDecimals : 0,
			mode : "categories",
			min : 0,
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		yaxis : {
			ticks : 5,
			tickDecimals : 0,
			tickColor : "#eee",
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		grid : {
			hoverable : true,
			clickable : true,
			tickColor : "#eee",
			borderColor : "#eee",
			borderWidth : 1
		}
	});

	
	var divContent="<div class=\"highcharts-tooltip\"  > <table> <tbody>  <tr> <td align=\"center\" colspan=\"2\">应用服务器响应时间</td></tr><tr><td colspan=\"2\">09-11 19:12--09-11 19:13</td></tr> <tr><td>每请求响应时间:</td><td>0.056(毫秒)</td></tr></tbody></table> </div>";
	
	
	function showTooltip(x, y, content) { 
		$('<div id="tooltip">' + divContent + '</div>').css({ 
			position : 'absolute',
			display : 'none',
			top : y + 5,
			left : x + 15, 
			border : '1px solid #333',
			padding : '4px', 
			color : '#080808',
			'border-radius' : '3px',
			'background-color' : '#FCFCFC',  
			opacity : 0.80
		}).appendTo("body").fadeIn(200); 
	}

	var previousPoint = null;
	// 折线图点的鼠标悬浮事件
	$("#chart_2").bind(
			"plothover",
			function(event, pos, item) {
				$("#x").text(pos.x.toFixed(1));
				$("#y").text(pos.y.toFixed(1));

				if (item) { 
					if (previousPoint != item.dataIndex) {
						previousPoint = item.dataIndex;
						$("#tooltip").remove();
						var x = item.series.data[previousPoint][0];
						y = item.datapoint[1].toFixed(2);

						showTooltip(item.pageX, item.pageY, item.series.label
								+ " 日期: " + x + " ,数量 " + y);
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}

/**
 * 吞吐率
 */
function throughputRate(data) {
	if ($('#chart_1').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.throughputRate ];
		visitors.push(visitor);
		var contentObj={"timeQuantum":item.timeQuantum,"throughputRate":item.throughputRate,"sumdealReqCounts":item.sumdealReqCounts};
		contentMap.put(item.createTime,contentObj);
//		contentMap.put(item.createTime,);
	});
	initMap.put("throughputRate_contentMap",contentMap);
	var plot = $.plot($("#chart_1"), [ {
		data : visitors, 
		label : "吞吐率(%)",
		lines : {
			lineWidth : 2, 
		},
		shadowSize : 0

	} ], {
		series : {
			lines : {
				show : true,
				lineWidth : 2,
				fill : true,
				fillColor : {
					colors : [ {
						opacity : 0.00
					}, {
						opacity : 0.00
					} ]
				}
			},
			points : {
				show : true,
				radius : 3,  
				fillColor: "#fff", 
				lineWidth : 2 
			},
			shadowSize : 2  
		},

		colors : [ "#3598DB", "#37b7f3", "#52e136" ],
		xaxis : {

			tickLength : 0,
			tickDecimals : 0,
			mode : "categories",
			min : 0,
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		yaxis : {
			ticks : 5,
			tickDecimals : 0,
			tickColor : "#eee",
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		grid : {
			hoverable : true,
			clickable : true,
			tickColor : "#eee",
			borderColor : "#eee",
			borderWidth : 1
		}
	});

	
	
	
	function showTooltip(x, y, contentObj) { 
		$("<div id=\"tooltip\" class=\"highcharts-tooltip\"  > <table> <tbody>  " +
				"<tr> <td align=\"center\" colspan=\"2\">吞吐率</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>吞吐率:</td><td>"+contentObj["throughputRate"]+"(rpm)</td></tr>" +
				"<tr><td>访问量:</td><td>"+contentObj["sumdealReqCounts"]+"</td></tr></tbody></table> </div>").css({ 
			position : 'absolute',
			display : 'none',
			top : y + 5,
			left : x + 15, 
			border : '1px solid #333',
			padding : '4px', 
			color : '#080808',
			'border-radius' : '3px',
			'background-color' : '#FCFCFC',   
			opacity : 0.80
		}).appendTo("body").fadeIn(200); 
	}

	var previousPoint = null;
	// 折线图点的鼠标悬浮事件
	$("#chart_1").bind(
			"plothover",
			function(event, pos, item) {
				$("#x").text(pos.x.toFixed(1));
				$("#y").text(pos.y.toFixed(1));

				if (item) { 
					if (previousPoint != item.dataIndex) {
						previousPoint = item.dataIndex;
						$("#tooltip").remove();
						var x = item.series.data[previousPoint][0];
						y = item.datapoint[1].toFixed(2);

						showTooltip(item.pageX, item.pageY, initMap.get("throughputRate_contentMap").get(x));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}




/**
 * cup使用率
 */
function theCpuUsedRate(data) {
	if ($('#chart_5').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.cpuRate ];
		visitors.push(visitor);
		var contentObj={"timeQuantum":item.timeQuantum,"cpuRate":item.cpuRate};
		contentMap.put(item.createTime,contentObj); 
//		contentMap.put(item.createTime,);
	});
	initMap.put("theCpuUsedRate_contentMap",contentMap);
	var plot = $.plot($("#chart_5"), [ {
		data : visitors, 
		label : "cup使用率(%)",
		lines : {
			fill:1,
			lineWidth : 2, 
		}, 
		shadowSize : 0

	} ], {
		series : {
			lines : {
				show : true,
				lineWidth : 2,
				fill : true,
				fillColor : {
					colors : [ {
						opacity : 0.00
					}, {
						opacity : 0.00
					} ]
				}
			},
			points : {
				show : true,
				radius : 3,  
				fillColor: "#fff",  
				lineWidth : 2 
			},
			shadowSize : 0 
		},

		colors : [ "#3598DB", "#37b7f3", "#52e136" ],
		xaxis : {
 
			tickLength : 0,
			tickDecimals : 0, 
			mode : "categories",
			min : 0,
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		yaxis : {
			ticks : 5,
			tickDecimals : 0,
			tickColor : "#eee",
			font : {
				lineHeight : 14,
				style : "normal",
				variant : "small-caps",
				color : "#6F7B8A"
			}
		},
		grid : {
			hoverable : true, 
			clickable : true,
			tickColor : "#eee",
			borderColor : "#eee",
			borderWidth : 1
		}
	});

	function showTooltip(x, y, contentObj) { 
		$("<div id=\"tooltip\" class=\"highcharts-tooltip\"  > <table> <tbody>  " +
				"<tr> <td align=\"center\" colspan=\"2\">CPU使用率</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>CPU使用率:</td><td>"+contentObj["cpuRate"]+"(%)</td></tr></tbody></table> </div>").css({ 
			position : 'absolute',
			display : 'none',
			top : y + 5,
			left : x + 15, 
			border : '1px solid #333',
			padding : '4px', 
			color : '#080808',
			'border-radius' : '3px',
			'background-color' : '#FCFCFC',  
			opacity : 0.80
		}).appendTo("body").fadeIn(200); 
	}

	var previousPoint = null;
	// 折线图点的鼠标悬浮事件
	$("#chart_5").bind(
			"plothover",
			function(event, pos, item) {
				$("#x").text(pos.x.toFixed(1));
				$("#y").text(pos.y.toFixed(1));

				if (item) { 
					if (previousPoint != item.dataIndex) {
						previousPoint = item.dataIndex;
						$("#tooltip").remove();
						var x = item.series.data[previousPoint][0];
						y = item.datapoint[1].toFixed(2);

						showTooltip(item.pageX, item.pageY, initMap.get("theCpuUsedRate_contentMap").get(x));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}



/**
 * 物理内存使用量
 */
function totalPhysicalMemory(data) {
	if ($('#chart_6').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.usedPhysicalSpaceSize ]; 
		visitors.push(visitor); 
		var contentObj={"timeQuantum":item.timeQuantum,"usedPhysicalSpaceSize":item.usedPhysicalSpaceSize};
		contentMap.put(item.createTime,contentObj);
//		contentMap.put(item.createTime,);
	});
	initMap.put("totalPhysicalMemory_contentMap",contentMap);
	var plot = $.plot($("#chart_6"), 
	[ {
		data : visitors,  
		label : "物理内存使用量(MB)",
		lines : {
			fill:1,
			lineWidth : 2, 
		},
		shadowSize : 0,
		color:["#3598DB"]

	}], {
			series: {
				lines: {
					show: true,
					lineWidth: 2,
					fill: true,
					fillColor: {
						colors: [{
							opacity: 0.00
						}, {
							opacity: 0.00
						}]
					}
				},
				points: {
					show: true,
					radius: 3,
					fillColor: "#fff",
					lineWidth: 2
				},
				shadowSize: 0
			},

			colors: ["#3598DB", "#37b7f3", "#52e136"],
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
			},
			grid: {
				hoverable: true,
				clickable: true,
				tickColor: "#eee",
				borderColor: "#eee",
				borderWidth: 1
			}
		});

	
	function showTooltip(x, y, contentObj) { 
		$("<div id=\"tooltip\" class=\"highcharts-tooltip\"  > <table> <tbody>  " +
				"<tr> <td align=\"center\" colspan=\"2\">内存使用量</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>内存使用量:</td><td>"+contentObj["usedPhysicalSpaceSize"]+"(MB)</td></tr></tbody></table> </div>").css({ 
			position : 'absolute',
			display : 'none',
			top : y + 5,
			left : x + 15, 
			border : '1px solid #333',
			padding : '4px', 
			color : '#080808',
			'border-radius' : '3px',
			'background-color' : '#FCFCFC',  
			opacity : 0.80
		}).appendTo("body").fadeIn(200); 
	}

	var previousPoint = null;
	// 折线图点的鼠标悬浮事件
	$("#chart_6").bind(
			"plothover",
			function(event, pos, item) {
				$("#x").text(pos.x.toFixed(1));
				$("#y").text(pos.y.toFixed(1));

				if (item) { 
					if (previousPoint != item.dataIndex) {
						previousPoint = item.dataIndex;
						$("#tooltip").remove();
						var x = item.series.data[previousPoint][0];
						y = item.datapoint[1].toFixed(2);

						showTooltip(item.pageX, item.pageY, initMap.get("totalPhysicalMemory_contentMap").get(x));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}

/**
 * 展示系统监控信息的列表
 */ 
function table_sys_monitor_list(sysMonitorShowDataList){
//	$.each(sysMonitorMap,function(key,value){
//		for(var i = 0 ; i < value.length; i++ ){
//		  var tmpArr = [];
//		  var obj = value[i];
//		  // tmpArr.push(obj["collectDate"]);
//		  tmpArr.push(3*i);
//		  tmpArr.push(obj["kpiValue"]);
//		   
//		  arr.push(tmpArr);
//		  myChart.setTooltip(tmpArr);
//		}
//	});  
	var tr="<tr class=\"table_head\">"+$("#table_sys_monitor_list tbody tr:first").html()+"</tr>";  
	$.each(sysMonitorShowDataList,function(index,item){   
		var url="sysMonitor.do?intoSysJvmIndex&name="+item.name+"&parentName="+item.parentName+"&rootName="+item.rootName+"&code="+item.code;
		tr+="<tr><td><a class='a_no_text_decoration' href="+url+">"+item.nodeDesc+"<div class='t_line_18'>"+item.nodeIp+"</div></a></td><td>"+item.apdexIndex+"</td><td>"+item.serverResTime+"</td>" +
				"<td>"+item.throughputRate+"</td><td>"+item.errRate+"</td><td>"+item.cpuRate+"</td><td>"+item.usedPhysicalSpaceSize+"</td></tr>";
	});
	$("#table_sys_monitor_list tbody").html(tr);    
}
/**
 * 加载页面的时候加载此方法 
 * 获取界面右下角展示的异常信息 
*/
var event_li_all=$("#event li:eq(0)");
selectMessageInfoForSysMonitorShow(event_li_all,'');
function selectMessageInfoForSysMonitorShow(event_li,errLevel){

	var code=$("#code").val();

	$.ajax({
		url:"sysMonitor.do?selectMessageInfoForSysMonitorShow&dateType=today&errLevel="+errLevel+"&code="+code,
		type:'post',
		cache:false,
		async:false,
		timeout:1000*10,
		dateType:'json',
		error:function(){
			alert('网络失败');
		},success:function(data){
			if(data!=null && data.length>0){
				var li="";
				$.each(data,function(index,item){
					li+="<li>" +
							"<span class=\"fl tab_content_l\">" +
							"<b class=\"icon_ban\"></b>" +
							"<span class=\"times\">"+item.createDate+"</span>" +
							"<span>"+item.sysErrDesc+"</span>" +
							"</span>" +
						"<span class=\"fr tab_content_r\" onclick='showMesDetail(\""+item.errId+"\")'>详情</span>" +
						"</li>";
				});
				li+="<li><a href=\"javascript:windowIntoMessageList('"+code+"','today','"+errLevel+"')\" class=\"fr tab_content_r\">加载更多</a></li>";
				$("#js_list_1").html(li);
			}else{
				$("#js_list_1").html("<li><span>暂无数据</span></li>");
			}
		}
	});

		$.ajax({
			url:"sysMonitor.do?selectMessageInfoForSysMonitorShow&dateType=yesterday&errLevel="+errLevel+"&code="+code,
			type:'post',
			cache:false,
			async:false,
			timeout:1000*10,
			dateType:'json',
			error:function(){
				alert('网络失败');
			},success:function(data){
				if(data!=null&& data.length>0){
					var li="";
					$.each(data,function(index,item){
						li+="<li>" +
								"<span class=\"fl tab_content_l\">" +
								"<b class=\"icon_ban icon_plus\"></b>" +
								"<span class=\"times\">"+item.createDate+"</span>" +
								"<span>"+item.sysErrDesc+"</span>" +
								"</span>" +
							"<span class=\"fr tab_content_r\" onclick='showMesDetail(\""+item.errId+"\")'>详情</span>" +
							"</li>";
					});
					li+="<li><a href=\"javascript:windowIntoMessageList('"+code+"','yesterday','"+errLevel+"')\" class=\"fr tab_content_r\">加载更多</a></li>";
					$("#js_list_2").html(li);
				}else{
					$("#js_list_2").html("<li><span>暂无数据</span></li>");
				}
			}
		});
		$.ajax({
			url:"sysMonitor.do?selectMessageInfoForSysMonitorShow&dateType=moreEarly&errLevel="+errLevel+"&code="+code,
			type:'post',
			cache:false,
			async:false,
			timeout:1000*10,
			dateType:'json',
			error:function(){
				alert('网络失败');
			},success:function(data){
				if(data!=null&& data.length>0){
					var li="";
					$.each(data,function(index,item){
						li+="<li>" +
								"<span class=\"fl tab_content_l\">" +
								"<b class=\"icon_ban icon_plus\"></b>" +
								"<span class=\"times\">"+item.createDate+"</span>" +
								"<span>"+item.sysErrDesc+"</span>" +
								"</span>" +
								"<span class=\"fr tab_content_r\" onclick='showMesDetail('"+item.errId+"')'>详情</span>" +
							"</li>";
					});
					li+="<li><a href=\"javascript:windowIntoMessageList('"+code+"','moreEarly','"+errLevel+"')\" class=\"fr tab_content_r\">加载更多</a></li>";
					$("#js_list_3").html(li);
				}else{
					$("#js_list_3").html("<li><span>暂无数据</span></li>");
				}
			}
		});

		$("#event li").removeClass("on");
	    $(event_li).addClass("on");
	
}

function windowIntoMessageList(code,dateType,errLevel){
	var codeType="sys";
	window.location.href="mesChart.do?chartToMesListView&code=" + code + "&codeType=" + codeType + "&dateType=" + dateType +"&errLevel="+errLevel;
}

/**
 * 显示单条信息的详细信息
 * @param errId
 */
function showMesDetail(errId){
//	var errId=$("#showMesDetail").html();
	$.ajax({
		url:'mesChart.do?findMessByErrId&errId='+errId,
		cache:false,
		async:false,
		timeout:1000*60,
		success:function(item){
			$("#detail_sysCode").html(item.sysCode);
			$("#detail_busiCode").html(item.busiCode);
			$("#detail_busiDesc").html(item.busiDesc);
			$("#detail_errCode").html(item.errCode);
			$("#detail_errDesc").html(item.errDesc);
			$("#detail_sysErrCode").html(item.sysErrCode);
			$("#detail_sysErrDesc").html(item.sysErrDesc);
			$("#detail_throwableDesc").html(item.throwableDesc);
		},
		error:function(){
			alert("查询失败!");
		}
	});
	$("#mesDetail").modal("show");
}

/*****************************************************************************************************/


var chart;

var chartData = [];

AmCharts.ready(function () {
    // first we generate some random data
    generateChartData();

    // SERIAL CHART
    chart = new AmCharts.AmSerialChart(); 

    chart.dataProvider = chartData;
    chart.categoryField = "date";

    // data updated event will be fired when chart is first displayed,
    // also when data will be updated. We'll use it to set some
    // initial zoom
    chart.addListener("dataUpdated", zoomChart);

    // AXES
    // Category
    var categoryAxis = chart.categoryAxis;
    categoryAxis.parseDates = true; // in order char to understand dates, we should set parseDates to true
    categoryAxis.minPeriod = "mm"; // as we have data with minute interval, we have to set "mm" here.
    categoryAxis.gridAlpha = 0.07;
    categoryAxis.axisColor = "#DADADA";

    // Value
    var valueAxis = new AmCharts.ValueAxis();
    valueAxis.gridAlpha = 0.07;
    valueAxis.title = "Unique visitors";
    chart.addValueAxis(valueAxis);

    // GRAPH
    var graph = new AmCharts.AmGraph();
    graph.type = "line"; // try to change it to "column"
    graph.title = "red line";
    graph.valueField = "visits";
    graph.lineAlpha = 1;
    graph.lineColor = "#d1cf2a";
    graph.fillAlphas = 0.3; // setting fillAlphas to > 0 value makes it area graph
    chart.addGraph(graph);

    // CURSOR
    var chartCursor = new AmCharts.ChartCursor();
    chartCursor.cursorPosition = "mouse";
    chartCursor.categoryBalloonDateFormat = "JJ:NN, DD MMMM";
    chart.addChartCursor(chartCursor);

    // SCROLLBAR
    var chartScrollbar = new AmCharts.ChartScrollbar();

    chart.addChartScrollbar(chartScrollbar);

    // WRITE
//    chart.write("chart_2");  
});

// generate some random data, quite different range
function generateChartData() {
    // current date
    var firstDate = new Date();
    // now set 1000 minutes back
    firstDate.setMinutes(firstDate.getDate() - 1000);

    // and generate 1000 data items
    for (var i = 0; i < 1000; i++) {
        var newDate = new Date(firstDate);
        // each time we add one minute
        newDate.setMinutes(newDate.getMinutes() + i);
        // some random number
        var visits = Math.round(Math.random() * 40) + 10;
        // add data item to the array
        chartData.push({
            date: newDate,
            visits: visits
        });
    }
}

// this method is called when chart is first inited as we listen for "dataUpdated" event
function zoomChart() {
    // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
    chart.zoomToIndexes(chartData.length - 40, chartData.length - 1);
}





// generate some random data, quite different range
function generateChartData() {
    var firstDate = new Date();
    firstDate.setDate(firstDate.getDate() - 50);

    for (var i = 0; i < 50; i++) {
        // we create date objects here. In your data, you can have date strings
        // and then set format of your dates using chart.dataDateFormat property,
        // however when possible, use date objects, as this will speed up chart rendering.
        var newDate = new Date(firstDate);
        newDate.setDate(newDate.getDate() + i);

        var visits = Math.round(Math.random() * 40) + 100;
        var hits = Math.round(Math.random() * 80) + 500;
        var views = Math.round(Math.random() * 6000);

        chartData.push({
            date: newDate,
            visits: visits,
            hits: hits,
            views: views
        });
    }
}

// this method is called when chart is first inited as we listen for "dataUpdated" event
function zoomChart() {
    // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
    chart.zoomToIndexes(10, 20);
}
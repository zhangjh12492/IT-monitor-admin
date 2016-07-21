
//Interactive Chart

function err_line_chart_init() {
	if ($('#chart_2').size() != 1) {
		return;
	}

	function randValue() {
		return (Math.floor(Math.random() * (1 + 40 - 20))) + 20;
	}
	var sysCode = $("#code").val();
	var codeType = $("#codeType").val();
	var errLevel = $("#errLevel").val();
	var dateType =$("#dateType").val();
	var pageviews = [];
	var visitors = [];
	$.ajax({
		url : 'mesChart.do?selectOneHourLine&toData&code=' + sysCode
				+ "&codeType=" + codeType+"&dateType="+dateType,
		type : 'post',

		cache : false,
		async : false,
		timeout : 1000 * 60,
		success : function(data) {
			data = $.parseJSON(data);
			$.each(data,
					function(index, item) {
						var pageview = [ item.createdTime, item.errorCount ];
						var visitor = [ item.createdTime, item.warnCount ];
						if (errLevel == null || errLevel == undefined
								|| errLevel == '') {
							visitors.push(visitor);
							pageviews.push(pageview);
						} else {
							if (errLevel == 'warn') {
								visitors.push(visitor);
							} else if (errLevel == 'error') {
								pageviews.push(pageview);
							}
						}
					});

		}
	});

	var plot = $.plot($("#chart_2"), [ {
		data : pageviews,
		label : "异常",
		lines : {
			lineWidth : 2,
		},
		shadowSize : 0

	}, {
		data : visitors,
		label : "警告",
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

		colors : [ "#d12610", "#44B6AE", "#52e136" ],
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

	function showTooltip(x, y, contents) {
		$('<div id="tooltip">' + contents + '</div>').css({
			position : 'absolute',
			display : 'none',
			top : y + 5,
			left : x + 15,
			border : '1px solid #333',
			padding : '4px',
			color : '#fff',
			'border-radius' : '3px',
			'background-color' : '#333',
			opacity : 0.80
		}).appendTo("body").fadeIn(200);
	}

	/**
	 * 折线图鼠标悬浮事件
	 */
	var previousPoint = null;
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

	/**
	 * 折线图点单击事件
	 */
	$("#chart_2").bind(
			"plotclick",
			function(event, pos, item) {
				var dateType = $("#dateType").val();
				var currentTime;

				previousPoint = item.dataIndex;
				var x = item.series.data[previousPoint][0];
				y = item.datapoint[1].toFixed(2);
				currentTime = x;

				window.location.href = "mesChart.do?chartToMesListView&code="
						+ sysCode + "&codeType=" + codeType + "&dateType="
						+ dateType + "&currentTime=" + currentTime;
			});
}

/**
 * 初始化某业务下的所有的各系统自定义异常的异常数据的图表
 */
function err_pie_radar_chart_init() {

	var sysCode = $("#code").val();
	var codeType = $("#codeType").val();
	var errLevel = $("#errLevel").val();
	var dateType =$("#dateType").val();
	var err_warn_pie_array = [];	//警告饼图数组
	var err_error_pie_array = [];	//异常饼图数组
	var warn_radar_array=[];	//警告雷达图数组
	var error_radar_array=[];	//异常雷达图数组 
	$.ajax({
		url : 'mesChart.do?selectOneHourPie&toData&code=' + sysCode
				+ "&codeType=" + codeType+"&dateType="+dateType,
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		cache : false,
		async : false,
		timeout : 1000 * 60,
		success : function(data) {
			data = $.parseJSON(data);
			$.each(data, function(index, item) {
				var error_pie_json = {"busidesc" : item.code + "-异常","litres" : item.errorCount};
				var warn_pie_json = {"busidesc" : item.code + "-警告","litres" : item.warnCount};
				err_error_pie_array.push(error_pie_json);
				err_warn_pie_array.push(warn_pie_json);
				
				var warn_radar_data={"direction": ""+item.code+"","value": item.warnCount};	//雷达图单条警告数据
				var error_radar_data={"direction": ""+item.code+"","value": item.errorCount};	//雷达图单条异常数据
				warn_radar_array.push(warn_radar_data);
				error_radar_array.push(error_radar_data);
			});
		} 
	});

	var err_warn_pie_chart = AmCharts.makeChart("err_warn_pie_chart", {
		"type" : "pie",
		"theme" : "light",
		"fontFamily" : 'Open Sans',
		"colors" : ["#67B7DC", "#FDD400", "#84B761", "#CC4748", "#CD82AD", "#2F4074", "#448E4D", "#B7B83F", "#B9783F", "#2A0CD0", "#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333", "#000000", "#57032A", "#CA9726", "#990000", "#4B0C25"],
		"dataProvider" : err_warn_pie_array,
		"valueField" : "litres",
		"titleField" : "busidesc",
		"exportConfig" : {
			menuItems : [ {
				icon : Metronic.getGlobalPluginsPath()
						+ "amcharts/amcharts/images/export.png",
				format : 'png'
			} ]
		}
	});
	$('#err_warn_pie_chart').closest('.portlet').find('.fullscreen').click(
			function() {
				err_warn_pie_chart.invalidateSize();
			});
	/*警告饼图的单击事件*/
	err_warn_pie_chart.addListener("clickSlice", pieWarnChartClick);
	
	var err_error_pie_chart = AmCharts.makeChart("err_error_pie_chart", {
		"type" : "pie",
		"theme" : "light",
		"fontFamily" : 'Open Sans',
		"colors" : ["#67B7DC", "#FDD400", "#84B761", "#CC4748", "#CD82AD", "#2F4074", "#448E4D", "#B7B83F", "#B9783F", "#2A0CD0", "#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333", "#000000", "#57032A", "#CA9726", "#990000", "#4B0C25"],
		"dataProvider" : err_error_pie_array,
		"valueField" : "litres",
		"titleField" : "busidesc",
		"exportConfig" : {
			menuItems : [ {
				icon : Metronic.getGlobalPluginsPath()
						+ "amcharts/amcharts/images/export.png",
				format : 'png'
			} ]
		}
	});

	$('#err_error_pie_chart').closest('.portlet').find('.fullscreen').click(
			function() {
				err_error_pie_chart.invalidateSize();
			});
	/*异常饼图的单击事件*/ 
	err_error_pie_chart.addListener("clickSlice", pieErrorChartClick);
	
	/*-------------------雷达图---------------------*/
	/*
	 * 警告雷达图
	 */
	var warn_radar_chart = AmCharts.makeChart("chart_8", {
	    "type": "radar", 
	    "theme": "light",

	    "fontFamily": 'Open Sans',
	    "colors" : ["#67B7DC", "#FDD400", "#84B761", "#CC4748", "#CD82AD", "#2F4074", "#448E4D", "#B7B83F", "#B9783F", "#2A0CD0", "#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333", "#000000", "#57032A", "#CA9726", "#990000", "#4B0C25"],
	    "dataProvider": warn_radar_array,
	    "valueAxes": [{
	        "gridType": "circles",
	        "minimum": 0,
	        "autoGridCount": false,
	        "axisAlpha": 0.2,
	        "fillAlpha": 0.05,
	        "fillColor": "#FFFFFF",
	        "gridAlpha": 0.08,
	        "position": "left"
	    }],
	    "startDuration": 1,
	    "graphs": [{
	        "balloonText": "[[category]]: [[value]] ",
	        "bullet": "round",
	        "fillAlphas": 0.3,
	        "valueField": "value"
	    }],
	    "categoryField": "direction"
	});

	$('#chart_8').closest('.portlet').find('.fullscreen').click(function() {
		warn_radar_chart.invalidateSize();
	});
	//雷达图警告单击事件
	warn_radar_chart.addListener("clickGraphItem",function(graph){
		var code = graph.item.category;
		var dateType=$("#dateType").val();
		var codeType = "err"; 
		var errLevel="warn";
		window.location.href = "mesChart.do?chartToMesListView&code=" + code + "&codeType=" + codeType + "&dateType=" + dateType +"&errLevel="+errLevel;
	}); 
	
	  
	/**
	 * 异常雷达图
	 */
	var error_radar_chart = AmCharts.makeChart("chart_9", {
	    "type": "radar", 
	    "theme": "light",

	    "fontFamily": 'Open Sans',
	    "colors" : ["#67B7DC", "#FDD400", "#84B761", "#CC4748", "#CD82AD", "#2F4074", "#448E4D", "#B7B83F", "#B9783F", "#2A0CD0", "#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333", "#000000", "#57032A", "#CA9726", "#990000", "#4B0C25"],
	    "dataProvider": error_radar_array,
	    "valueAxes": [{
	        "gridType": "circles",
	        "minimum": 0,
	        "autoGridCount": false,
	        "axisAlpha": 0.2,
	        "fillAlpha": 0.05,
	        "fillColor": "#FFFFFF",
	        "gridAlpha": 0.08,
	        "position": "left"
	    }],
	    "startDuration": 1,
	    "graphs": [{
	        "balloonText": "[[category]]: [[value]] ",
	        "bullet": "round",
	        "fillAlphas": 0.3,
	        "valueField": "value"
	    }],
	    "categoryField": "direction"
	});

	$('#chart_9').closest('.portlet').find('.fullscreen').click(function() {
		error_radar_chart.invalidateSize();
	});
	/**
	 * 异常雷达图数据单击事件
	 */
	error_radar_chart.addListener("clickGraphItem",function(graph){
		var dateType=$("#dateType").val();
		var codeType = "err"; 
		var errLevel="error";
		var code = graph.item.category;
		window.location.href = "mesChart.do?chartToMesListView&code=" + code + "&codeType=" + codeType + "&dateType=" + dateType +"&errLevel="+errLevel;
	});
	/**--------------------------------雷达图--------------------------------*/
	
	/*根据异常等级显示不同级别的数据*/
	if (errLevel == null & errLevel == undefined & errLevel == '') {
		$('#err_error_pie_chart_div').css("display", "none");
		$('#err_warn_pie_chart_div').css("display", "none");
		$('#err_error_radar_chart_div').css("display", "none");
		$('#err_warn_radar_chart_div').css("display", "none");
	} else {
		if (errLevel == 'warn') {
			$('#err_error_pie_chart_div').css("display", "none");
			$('#err_error_radar_chart_div').css("display", "none");
		} else if (errLevel == 'error') {
			$('#err_warn_pie_chart_div').css("display", "none");
			$('#err_warn_radar_chart_div').css("display", "none");  
		}
	}
}
/**
 * 饼图块警告单击事件
 * 
 * @param event
 */
function pieWarnChartClick(event) { 
	var dateType=$("#dateType").val();
	var codeType = "err"; 
	var errLevel="warn";
	var code = event.dataItem.title.substr(0, 8);
	window.location.href = "mesChart.do?chartToMesListView&code=" + code + "&codeType=" + codeType + "&dateType=" + dateType +"&errLevel="+errLevel;
}

/**
 * 饼图块异常单击事件  
 * 
 * @param event
 */
function pieErrorChartClick(event) {
	var dateType=$("#dateType").val(); 
	var codeType = "err";
	var errLevel="error";
	var code = event.dataItem.title.substr(0, 8);
	window.location.href = "mesChart.do?chartToMesListView&code=" + code + "&codeType=" + codeType + "&dateType=" + dateType +"&errLevel="+errLevel;
}
 /**
 * 日期类型改变的事件
 */
function dateTypeChange(dateType){
	$("#dateType").val(dateType);
	$("#"+dateType).siblings().removeClass("active").end().addClass("active");
	 err_line_chart_init();
	 err_pie_radar_chart_init();

}


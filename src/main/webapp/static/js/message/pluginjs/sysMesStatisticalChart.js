/**
 * 折线图展示
 */
function busi_line_chart_init() {
	if ($('#chart_2').size() != 1) {
		return;
	}

	function randValue() {
		return (Math.floor(Math.random() * (1 + 40 - 20))) + 20;
	}
	var sysCode = $("#code").val();
	var codeType = $("#codeType").val();
	var dateType =$("#dateType").val();

	var pageviews = [];
	var visitors = [];
	$.ajax({
		url : 'mesChart.do?selectOneHourLine&toData&code=' + sysCode
				+ "&codeType=" + codeType + "&dateType="+dateType,
		type : 'post',

		cache : false,
		async : false,
		timeout : 1000 * 60,
		success : function(data) {
			data = $.parseJSON(data);
			$.each(data, function(index, item) {
				var pageview = [ item.createdTime, item.errorCount ];
				var visitor = [ item.createdTime, item.warnCount ];
				pageviews.push(pageview);
				visitors.push(visitor);
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

	/**
	 * 折线图点单击事件
	 */
	$("#chart_2").bind(
			"plotclick",
			function(event, pos, item) {
				var dateType = $("#dateType").val();
				var currentTime;
				var errLevel="";
				if(item.series.label=='异常'){
					errLevel='2';
				}else if(item.series.label='警告'){
					errLevel='1';
				}
				previousPoint = item.dataIndex;
				var x = item.series.data[previousPoint][0];
				y = item.datapoint[1].toFixed(2);
				currentTime = x;

				window.location.href = "mesChart.do?chartToMesListView&code="
						+ sysCode + "&codeType=" + codeType + "&dateType="
						+ dateType + "&currentTime=" + currentTime+"&errLevel="+errLevel;
			});

}

/**
 * 饼图展示
 */
function busi_pie_chart_init() {
	var sysCode = $("#code").val();
	var codeType = $("#codeType").val();
	var dateType =$("#dateType").val();
	var warn_pie_array = [];	//警告饼图数组
	var error_pie_array = [];	//异常饼图数组
	var warn_radar_array=[];	//警告雷达图数组
	var error_radar_array=[];	//异常雷达图数组
	$.ajax({
		url : 'mesChart.do?selectOneHourPie&toData&code=' + sysCode + "&codeType=" + codeType+"&dateType="+dateType,
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		cache : false,
		async : false,
		timeout : 1000 * 60,
		success : function(data) {
			data = $.parseJSON(data);
			$.each(data, function(index, item) {
				var busiInfo=getBusiInfoBySysBusiCode(item.code.substr(0, 2),item.code.substr(2, 3));
				var error_pie_json = {"busidesc" : item.code + "--"+ busiInfo["busiDesc"] + "--异常","litres" : item.errorCount};	//饼图单条数据
				var warn_pie_json = {"busidesc" : item.code + "--"+ busiInfo["busiDesc"] + "--警告","litres" : item.warnCount};	//饼图单条警告数据
				warn_pie_array.push(warn_pie_json);		//
				error_pie_array.push(error_pie_json);
				
				var warn_radar_data={"direction": ""+item.code +"--"+ busiInfo["busiDesc"]+"--警告","value": item.warnCount};	//雷达图单条警告数据
				var error_radar_data={"direction": ""+item.code + "--" +busiInfo["busiDesc"]+"--异常","value": item.errorCount};	//雷达图单条异常数据
				warn_radar_array.push(warn_radar_data);
				error_radar_array.push(error_radar_data);
			});  
		}
	});
	//异常饼图
	var error_chart = AmCharts.makeChart("busi_error_chart", {
		"type" : "pie",
		"theme" : "light", 
		"fontFamily" : 'Open Sans',
		"colors" : ["#67B7DC", "#FDD400", "#84B761", "#CC4748", "#CD82AD", "#2F4074", "#448E4D", "#B7B83F", "#B9783F", "#2A0CD0", "#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333", "#000000", "#57032A", "#CA9726", "#990000", "#4B0C25"],
		"dataProvider" : error_pie_array,
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

	$('#busi_error_chart').closest('.portlet').find('.fullscreen').click(
			function() {
				error_chart.invalidateSize();
			});
	error_chart.addListener("clickSlice", pieErrorChartClick);	//饼图的单击监听事件

	//警告饼图
	var warn_chart = AmCharts.makeChart("busi_warn_chart", {
		"type" : "pie",
		"theme" : "light",
		"fontFamily" : 'Open Sans',
		"colors" : ["#67B7DC", "#FDD400", "#84B761", "#CC4748", "#CD82AD", "#2F4074", "#448E4D", "#B7B83F", "#B9783F", "#2A0CD0", "#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333", "#000000", "#57032A", "#CA9726", "#990000", "#4B0C25"],
		"dataProvider" : warn_pie_array,
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

	$('#busi_warn_chart').closest('.portlet').find('.fullscreen').click(
			function() {
				warn_chart.invalidateSize();
			});
	warn_chart.addListener("clickSlice", pieWarnChartClick);	//警告饼图的单击监听事件
	
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
		var codeType = "err";
		var dateType =$("#dateType").val();
		var code = graph.item.category.substr(0, 5);
		window.location.href = "admin.do?selectTopBusiPro&toView&code=" + code
				+ "&codeType=" + codeType + "&errLevel=warn&dateType="+dateType;
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
 
	$('#chart_7').closest('.portlet').find('.fullscreen').click(function() {
		error_radar_chart.invalidateSize();
	});
	/**
	 * 异常雷达图数据单击事件
	 */
	error_radar_chart.addListener("clickGraphItem",function(graph){
		var codeType = "err";  
		var code = graph.item.category.substr(0, 5);;
		var dateType =$("#dateType").val();
		window.location.href = "admin.do?selectTopBusiPro&toView&code=" + code
				+ "&codeType=" + codeType + "&errLevel=error&dateType="+dateType;
	});
	
	
}

/**
 * 饼图块警告单击事件
 * 
 * @param event
 */
function pieWarnChartClick(event) {
	var codeType = "err";
	var dateType =$("#dateType").val();
	var code = event.dataItem.title.substr(0, 5);
	window.location.href = "admin.do?selectTopBusiPro&toView&code=" + code
			+ "&codeType=" + codeType + "&errLevel=warn&dateType="+dateType;
}

/**
 * 饼图块异常单击事件
 * 
 * @param event
 */
function pieErrorChartClick(event) {
	var codeType = "err";  
	var code = event.dataItem.title.substr(0, 5);
	var dateType =$("#dateType").val();
	window.location.href = "admin.do?selectTopBusiPro&toView&code=" + code
			+ "&codeType=" + codeType + "&errLevel=error&dateType="+dateType;
}
/**
 * 日期类型改变的事件
 */
function dateTypeChange(dateType){
	$("#dateType").val(dateType);
	$("#"+dateType).siblings().removeClass("active").end().addClass("active");
	busi_line_chart_init();
	busi_pie_chart_init();

}
//$("#dateType").change(function() {
//	chart2();
//	busi_pie_chart_init();
//});

/**
 * 根据系统码和业务码查询业务
 */
function getBusiInfoBySysBusiCode(sysCode,busiCode){
	var busiInfo;
	$.ajax({
		url : 'busi.do?getBusiInfoBySysBusiCode&sysCode='+sysCode+'&busiCode='+busiCode,
		type : 'post',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		cache : false,
		async : false,
		timeout : 1000 * 60,
		success : function(data) {
			busiInfo={"busiDesc":data.busiDesc,"busiCode":data.busiCode};
		}
	});
	return busiInfo;
}




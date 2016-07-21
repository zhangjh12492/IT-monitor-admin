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

///**
// * 系统监控信息index界面调用websocket
// */
//var ws_sysMonitorIndex = new WebSocket("ws://localhost:8080/exceptionSocketPro/sysMonitorIndex.do");      
//ws_sysMonitorIndex.onopen = function() { 
//	ws_sysMonitorIndex.send("hello");
//};
//ws_sysMonitorIndex.onmessage = function(message) {  
//	var item=$.parseJSON(message.data);
//	
//	if(item!=null&&item!=undefined ){ 
//		
//	}
//};
// 
// 
///**
// * 系统监控列表信息,展示底部列表数据
// */
//var ws_sysMonitorList = new WebSocket("ws://localhost:8080/exceptionSocketPro/sysMonitorList.do");      
//ws_sysMonitorList.onopen = function() { 
//	ws_sysMonitorList.send("hello"); 
//};
//ws_sysMonitorList.onmessage = function(message) {  
//	var item=$.parseJSON(message.data); 
//	if(item!=null&&item!=undefined ){ 
//		
//	}
//};

/*存放所有的map对象，用于在折线图的鼠标悬浮事件中使用*/
var initMemoryMap=new Map();
var initThreadMap=new Map();
/**
 * init page data
 */
function initOneNodeJvm(){
	initMemoryMap=new Map();

	getSysInfo();
	initZkNodeDetail();
	initMemChart();
}

/**
 * 初始化信息,展示页面顶部的系统下此节点的系统信息
 */
function initZkNodeDetail(){
	$.ajax({
		 url:'sysMonitor.do?getSysOneNodeMonitorInfoByNameCode&name='+$("#nodeName").val()+"&parentName="+$("#parentName").val()+"&rootName="+$("#rootName").val()+"&code="+$("#code").val(),
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
				 
				var tr="<tr class=\"table_head\">"+$("#table_oneSysNode_monitorinfo tbody tr:first").html()+"</tr>";  
				tr+="<tr><td>"+item.nodeDesc+"<div class='t_line_18'>"+item.nodeIp+"</div></td><td>"+item.apdexIndex+"</td><td>"+item.serverResTime+"</td>" +
						"<td>"+item.throughputRate+"</td><td>"+item.errRate+"</td><td>"+item.cpuRate+"</td><td>"+item.usedPhysicalSpaceSize+"</td></tr>";
				$("#table_oneSysNode_monitorinfo tbody").html(tr); 
				valuetionSysOneNodeInfo(item);
			}
	   } 

	}); 
}
/**
 * 页面上边的信息赋值
 * @param item
 */
function valuetionSysOneNodeInfo(item){
	$("#jvm_font_w1_font01_apdex").html(item.apdexIndex);
	$("#jvm_font_w1_font01_serverReqTime").html(item.serverResTime);
	$("#jvm_font_w1_font01_throughputRate").html(item.throughputRate);
	$("#jvm_font_w1_font01_cpuRate").html(item.cpuRate);
	$("#jvm_font_w1_font01_memoryPhysicalSpaceSize").html(item.usedPhysicalSpaceSize);
	$("#titbar_pl_15_name").html(item.nodeDesc+"  "+item.nodeIp); 
	$("#jvm_font_w1_font01_server").html(item.serverName+" "+item.serverVersion);
}

/**
 * 展示内存折线图
 */
function initMemChart(){

	$.ajax({
		url:"sysMonitor.do?getSysOneNodeUsageMemory&&name="+$("#nodeName").val()+"&parentName="+$("#parentName").val()+"&rootName="+$("#rootName").val()+"&code="+$("#code").val(),
		type:"post",
		cache:false,
		async:false,
		timeout:1000*30,
		dateType:'json',
		success:function(data){
			var data=$.parseJSON(data);
			heapMemoryUsage(data);	//heap Memory Usage 
			CodeCacheManager(data);
			parSurvivorSpace(data);
			parEdenSpace(data);
			cmsOldGen(data);
			cmsPermGen(data);
			garbageCollectionCpuTime(data); 
			classCountTableShow(data[0].classPath);  
			libraryCountTableShow(data[0].libraryPath); 
//			getThreadCount(data); 
		},error:function(){  
			
		}
		
	});
}
/**
 * 展示线程的折线图
 */
function initThreadChart(){
	initThreadMap=new Map();
	$.ajax({
		url:"sysMonitor.do?getSysOneNodeUsageMemory&&name="+$("#nodeName").val()+"&parentName="+$("#parentName").val()+"&rootName="+$("#rootName").val()+"&code="+$("#code").val(),
		type:"post",
		cache:false,
		async:false,
		timeout:1000*30,
		dateType:'json',
		success:function(data){
			var data=$.parseJSON(data);
			getThreadCount(data); 
			getPeakThreadCount(data); 
			getDaemonThreadCount(data);
		},error:function(){  
			
		}
		
	});
}

/**
 * Heap memory Usage
 * 
 */
function heapMemoryUsage(data) {
	if ($('#heap_memory_usage_chart').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	
	var committedDatas=[];
	var maxDatas=[];
	var usedDatas=[];
	$.each(data, function(index, item) {
		var committedData = [ item.createTime, item.heapMemoryUsage.committed ];
		var maxData = [ item.createTime, item.heapMemoryUsage.max ];
		var usedData = [ item.createTime, item.heapMemoryUsage.used ];
		
		committedDatas.push(committedData);
		maxDatas.push(maxData);
		usedDatas.push(usedData);
		
		var committedContentObj={"timeQuantum":item.timeQuantum,"committedSize":item.heapMemoryUsage.committed};
		var maxContentObj={"timeQuantum":item.timeQuantum,"committedSize":item.heapMemoryUsage.max};
		var usedContentObj={"timeQuantum":item.timeQuantum,"committedSize":item.heapMemoryUsage.used};
		
		contentMap.put(item.createTime+"-"+Math.round(item.heapMemoryUsage.committed),{"timeQuantum":item.timeQuantum,"heapMemorySize":item.heapMemoryUsage.committed,"typeName":"Committed Heap"});
		contentMap.put(item.createTime+"-"+Math.round(item.heapMemoryUsage.max),{"timeQuantum":item.timeQuantum,"heapMemorySize":item.heapMemoryUsage.max,"typeName":"max Heap"});
		contentMap.put(item.createTime+"-"+Math.round(item.heapMemoryUsage.used),{"timeQuantum":item.timeQuantum,"heapMemorySize":item.heapMemoryUsage.used,"typeName":"used Heap"});
		
	});

	initMemoryMap.put("heapMemoryUsage_contentMap",contentMap);

	var plotCommitted={"data":committedDatas,"label":"Committed Heap","lines":{"lineWidth":3},"shadowSize":0};
	var plotMax={"data":maxDatas,"label":"Max Heap","lines":{"lineWidth":3},"shadowSize":0};
	var plotUsed={"data":usedDatas,"label":"Used Heap","lines":{"lineWidth":3},"shadowSize":0};
	
	var plotData=[plotCommitted,plotMax,plotUsed];
	var plot = $.plot($("#heap_memory_usage_chart"), plotData, {
		
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,
				lineWidth : 3
			},
			shadowSize : 3
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
		
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
				"<tr> <td align=\"center\" colspan=\"2\">"+contentObj["typeName"]+"</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>"+contentObj["typeName"]+":</td><td>"+contentObj["heapMemorySize"]+"(MB)</td></tr></tbody></table> </div>").css({ 
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
	$("#heap_memory_usage_chart").bind(
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

						showTooltip(item.pageX, item.pageY, initMemoryMap.get("heapMemoryUsage_contentMap").get(x+"-"+Math.round(y)));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}

/**
 * Code Cache
 * 
 */
function CodeCacheManager(data) {
	if ($('#code_cache_chart').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	
	var committedDatas=[];
	var maxDatas=[];
	var usedDatas=[];
	$.each(data, function(index, item) {
		var committedData = [ item.createTime, item.codeCache.committed ];
		var maxData = [ item.createTime, item.codeCache.max ];
		var usedData = [ item.createTime, item.codeCache.used ];
		
		committedDatas.push(committedData);
		maxDatas.push(maxData);
		usedDatas.push(usedData);
		
		contentMap.put(item.createTime+"-"+Math.round(item.codeCache.committed),{"timeQuantum":item.timeQuantum,"codeCacheSize":item.codeCache.committed,"typeName":"Committed Code Cache"});
		contentMap.put(item.createTime+"-"+Math.round(item.codeCache.max),{"timeQuantum":item.timeQuantum,"codeCacheSize":item.codeCache.max,"typeName":"max Code Cache"});
		contentMap.put(item.createTime+"-"+Math.round(item.codeCache.used),{"timeQuantum":item.timeQuantum,"codeCacheSize":item.codeCache.used,"typeName":"used Code Cache"});
		
	});

	initMemoryMap.put("CodeCacheManager_contentMap",contentMap);

	var plotCommitted={"data":committedDatas,"label":"Committed Code Cache","lines":{"lineWidth":3},"shadowSize":0};
	var plotMax={"data":maxDatas,"label":"Max Code Cache","lines":{"lineWidth":3},"shadowSize":0};
	var plotUsed={"data":usedDatas,"label":"Used Code Cache","lines":{"lineWidth":3},"shadowSize":0};
	
	var plotData=[plotCommitted,plotMax,plotUsed];
	var plot = $.plot($("#code_cache_chart"), plotData, {
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,
				lineWidth : 3
			},
			shadowSize : 3
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
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
				"<tr> <td align=\"center\" colspan=\"2\">"+contentObj["typeName"]+"</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>"+contentObj["typeName"]+":</td><td>"+contentObj["codeCacheSize"]+"(MB)</td></tr></tbody></table> </div>").css({ 
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
	$("#code_cache_chart").bind(
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

						showTooltip(item.pageX, item.pageY, initMemoryMap.get("CodeCacheManager_contentMap").get(x+"-"+Math.round(y)));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}

/**
 * Par Survivor Space
 * 
 */
function parSurvivorSpace(data) {
	if ($('#par_survivor_space_chart').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	
	var committedDatas=[];
	var maxDatas=[];
	var usedDatas=[];
	$.each(data, function(index, item) {
		var committedData = [ item.createTime, item.psSurvivorSpace.committed ];
		var maxData = [ item.createTime, item.psSurvivorSpace.max ];
		var usedData = [ item.createTime, item.psSurvivorSpace.used ];
		
		committedDatas.push(committedData);
		maxDatas.push(maxData);
		usedDatas.push(usedData);
		
		contentMap.put(item.createTime+"-"+Math.round(item.psSurvivorSpace.committed),{"timeQuantum":item.timeQuantum,"psSurvivorSpaceSize":item.psSurvivorSpace.committed,"typeName":"Committed Par Survivor Space"});
		contentMap.put(item.createTime+"-"+Math.round(item.psSurvivorSpace.max),{"timeQuantum":item.timeQuantum,"psSurvivorSpaceSize":item.psSurvivorSpace.max,"typeName":"max Par Survivor Space"});
		contentMap.put(item.createTime+"-"+Math.round(item.psSurvivorSpace.used),{"timeQuantum":item.timeQuantum,"psSurvivorSpaceSize":item.psSurvivorSpace.used,"typeName":"used Par Survivor Space"});
		
	});

	initMemoryMap.put("parSurvivorSpace_contentMap",contentMap);

	var plotCommitted={"data":committedDatas,"label":"Committed Par Survivor Space","lines":{"lineWidth":3},"shadowSize":0};
	var plotMax={"data":maxDatas,"label":"Max Par Survivor Space","lines":{"lineWidth":3},"shadowSize":0};
	var plotUsed={"data":usedDatas,"label":"Used Par Survivor Space","lines":{"lineWidth":3},"shadowSize":0};
	
	var plotData=[plotCommitted,plotMax,plotUsed];
	var plot = $.plot($("#par_survivor_space_chart"), plotData, {
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,  
				fillColor: "#eee",  
				lineWidth : 3 
			},
			shadowSize : 4 
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
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
				"<tr> <td align=\"center\" colspan=\"2\">"+contentObj["typeName"]+"</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>"+contentObj["typeName"]+":</td><td>"+contentObj["psSurvivorSpaceSize"]+"(MB)</td></tr></tbody></table> </div>").css({ 
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
	$("#par_survivor_space_chart").bind(
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
						showTooltip(item.pageX, item.pageY, initMemoryMap.get("parSurvivorSpace_contentMap").get(x+"-"+Math.round(y)));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}

/**
 * Par Eden Space
 * 
 */
function parEdenSpace(data) {
	if ($('#par_eden_space_chart').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	
	var committedDatas=[];
	var maxDatas=[];
	var usedDatas=[];
	$.each(data, function(index, item) {
		var committedData = [ item.createTime, item.psEdenSpace.committed ];
		var maxData = [ item.createTime, item.psEdenSpace.max ];
		var usedData = [ item.createTime, item.psEdenSpace.used ];
		
		committedDatas.push(committedData);
		maxDatas.push(maxData);
		usedDatas.push(usedData);
		
		contentMap.put(item.createTime+"-"+Math.round(item.psEdenSpace.committed),{"timeQuantum":item.timeQuantum,"psEdenSpaceSize":item.psEdenSpace.committed,"typeName":"Committed Par Eden Space"});
		contentMap.put(item.createTime+"-"+Math.round(item.psEdenSpace.max),{"timeQuantum":item.timeQuantum,"psEdenSpaceSize":item.psEdenSpace.max,"typeName":"max Par Eden Space"});
		contentMap.put(item.createTime+"-"+Math.round(item.psEdenSpace.used),{"timeQuantum":item.timeQuantum,"psEdenSpaceSize":item.psEdenSpace.used,"typeName":"used Par Eden Space"});
		
	});

	initMemoryMap.put("parEdenSpace_contentMap",contentMap);

	var plotCommitted={"data":committedDatas,"label":"Committed Par Eden Space","lines":{"lineWidth":3},"shadowSize":0};
	var plotMax={"data":maxDatas,"label":"Max Par Eden Space","lines":{"lineWidth":3},"shadowSize":0};
	var plotUsed={"data":usedDatas,"label":"Used Par Eden Space","lines":{"lineWidth":3},"shadowSize":0};
	
	var plotData=[plotCommitted,plotMax,plotUsed];
	var plot = $.plot($("#par_eden_space_chart"), plotData, {
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,  
				fillColor: "#eee",  
				lineWidth : 3 
			},
			shadowSize : 4 
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
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
				"<tr> <td align=\"center\" colspan=\"2\">"+contentObj["typeName"]+"</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>"+contentObj["typeName"]+":</td><td>"+contentObj["psEdenSpaceSize"]+"(MB)</td></tr></tbody></table> </div>").css({ 
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
	$("#par_eden_space_chart").bind(
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

						showTooltip(item.pageX, item.pageY, initMemoryMap.get("parEdenSpace_contentMap").get(x+"-"+Math.round(y)));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}

/**
 * CMS Old Gen
 * 
 */
function cmsOldGen(data) {
	if ($('#cms_old_gen_chart').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	
	var committedDatas=[];
	var maxDatas=[];
	var usedDatas=[];
	$.each(data, function(index, item) {
		var committedData = [ item.createTime, item.psOldGen.committed ];
		var maxData = [ item.createTime, item.psOldGen.max ];
		var usedData = [ item.createTime, item.psOldGen.used ];
		
		committedDatas.push(committedData);
		maxDatas.push(maxData);
		usedDatas.push(usedData);
		
		contentMap.put(item.createTime+"-"+Math.round(item.psOldGen.committed),{"timeQuantum":item.timeQuantum,"psOldGenSize":item.psSurvivorSpace.committed,"typeName":"Committed CMS Old Gen"});
		contentMap.put(item.createTime+"-"+Math.round(item.psOldGen.max),{"timeQuantum":item.timeQuantum,"psOldGenSize":item.psSurvivorSpace.max,"typeName":"max CMS Old Gen"});
		contentMap.put(item.createTime+"-"+Math.round(item.psOldGen.used),{"timeQuantum":item.timeQuantum,"psOldGenSize":item.psSurvivorSpace.used,"typeName":"used CMS Old Gen"});
		
	});

	initMemoryMap.put("cmsOldGen_contentMap",contentMap);

	var plotCommitted={"data":committedDatas,"label":"Committed CMS Old Gen","lines":{"lineWidth":3},"shadowSize":0};
	var plotMax={"data":maxDatas,"label":"Max CMS Old Gen","lines":{"lineWidth":3},"shadowSize":0};
	var plotUsed={"data":usedDatas,"label":"Used CMS Old Gen","lines":{"lineWidth":3},"shadowSize":0};
	
	var plotData=[plotCommitted,plotMax,plotUsed];
	var plot = $.plot($("#cms_old_gen_chart"), plotData, {
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,  
				fillColor: "#eee",  
				lineWidth : 3 
			},
			shadowSize : 4 
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
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
				"<tr> <td align=\"center\" colspan=\"2\">"+contentObj["typeName"]+"</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>"+contentObj["typeName"]+":</td><td>"+contentObj["psOldGenSize"]+"(MB)</td></tr></tbody></table> </div>").css({ 
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
	$("#cms_old_gen_chart").bind(
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

						showTooltip(item.pageX, item.pageY, initMemoryMap.get("cmsOldGen_contentMap").get(x+"-"+Math.round(y)));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}

/**
 * CMS Perm Gen
 * 
 */
function cmsPermGen(data) {
	if ($('#cms_perm_gen_chart').size() != 1) { 
		return;
	}

	var visitors = [];
	var contentMap=new Map();
	
	var committedDatas=[];
	var maxDatas=[];
	var usedDatas=[];
	$.each(data, function(index, item) {
		var committedData = [ item.createTime, item.psPermGen.committed ];
		var maxData = [ item.createTime, item.psPermGen.max ];
		var usedData = [ item.createTime, item.psPermGen.used ];
		
		committedDatas.push(committedData);
		maxDatas.push(maxData);
		usedDatas.push(usedData);
		
		contentMap.put(item.createTime+"-"+Math.round(item.psPermGen.committed),{"timeQuantum":item.timeQuantum,"psPermGenSize":item.psPermGen.committed,"typeName":"Committed CMS Perm Gen"});
		contentMap.put(item.createTime+"-"+Math.round(item.psPermGen.max),{"timeQuantum":item.timeQuantum,"psPermGenSize":item.psPermGen.max,"typeName":"max CMS Perm Gen"});
		contentMap.put(item.createTime+"-"+Math.round(item.psPermGen.used),{"timeQuantum":item.timeQuantum,"psPermGenSize":item.psPermGen.used,"typeName":"used CMS Perm Gen"});
		
	});

	initMemoryMap.put("cmsPermGen_contentMap",contentMap);

	var plotCommitted={"data":committedDatas,"label":"Committed CMS Perm Gen","lines":{"lineWidth":3},"shadowSize":0};
	var plotMax={"data":maxDatas,"label":"Max CMS Perm Gen","lines":{"lineWidth":3},"shadowSize":0};
	var plotUsed={"data":usedDatas,"label":"Used CMS Perm Gen","lines":{"lineWidth":3},"shadowSize":0};
	
	var plotData=[plotCommitted,plotMax,plotUsed];
	var plot = $.plot($("#cms_perm_gen_chart"), plotData, {
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,  
				fillColor: "#eee",  
				lineWidth : 3 
			},
			shadowSize : 4 
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
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
				"<tr> <td align=\"center\" colspan=\"2\">"+contentObj["typeName"]+"</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>"+contentObj["typeName"]+":</td><td>"+contentObj["psPermGenSize"]+"(MB)</td></tr></tbody></table> </div>").css({ 
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
	$("#cms_perm_gen_chart").bind(
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

						showTooltip(item.pageX, item.pageY, initMemoryMap.get("cmsPermGen_contentMap").get(x+"-"+Math.round(y)));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});

}

/**
 * Garbage collection CPU time
 */
function garbageCollectionCpuTime(data){
	if ($('#garbage_collection_cpu_time_chart').size() != 1) { 
		return;
	}
	var visitors = [];
	var contentMap=new Map(); 
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.gcPsScavenge.collectionCount ];
		visitors.push(visitor);
		
		var contentObj={"timeQuantum":item.timeQuantum,"collectionCount":item.gcPsScavenge.collectionCount,"collectionTime":item.gcPsScavenge.collectionTime};
		contentMap.put(item.createTime,contentObj);
//		contentMap.put(item.createTime,);
	});

	initMemoryMap.put("garbageCollectionCpuTime_contentMap",contentMap);

	var plot = $.plot($("#garbage_collection_cpu_time_chart"), [ {
		data : visitors, 
		label : "Garbage collection CPU time",
		lines : {
			lineWidth : 3, 
		},
		shadowSize : 0

	} ], {
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,  
				fillColor: "#eee",  
				lineWidth : 3 
			},
			shadowSize : 4 
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
		
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
				"<tr><td align=\"center\" colspan=\"2\">ParNew</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>GC :</td><td>"+contentObj["collectionTime"]+"(毫秒)</td></tr>" +
				"<tr><td>GC次数:</td><td>"+contentObj["collectionCount"]+"</td></tr></tbody></table> </div>").css({ 
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
	$("#garbage_collection_cpu_time_chart").bind(
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

						showTooltip(item.pageX, item.pageY, initMemoryMap.get("garbageCollectionCpuTime_contentMap").get(x+"-"+Math.round(y)));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});
}

/**
 * 展示引用到的class
 */
function classCountTableShow(str) {
	var data = str.split(";");
	if (data == null) {
		return;
	} 
	var dataSet = [];
	$.each(data, function(index, item) {
		if (item != null && item != '') {
			var dt = [index,item]; 
			dataSet.push(dt);  
		} 
	});  
//	$('#class_count_table').dataTable().fnDestroy();  
//	 $("#class_count_table").dataTable({ 
//		 "bPaginate": false, 
//		 "data":dataSet,
//		 "bFilter": false,
//		  "oLanguage" : {
//			  "sLengthMenu": "每页显示 _MENU_ 条记录",
//			  "sProcessing" : "<img src='/plug-in/images/loading.gif' />",// 加载gif
//
//				"sZeroRecords" : "<span style='color:red;'>没有符合条件的记录......</span>",
//				"oPaginate": {
//					"sFirst": "首页",
//					"sPrevious": "上一页 ", 
//					"sNext":     "下一页 ", 
//					"sLast": "尾页"
//				},
//				"sInfoEmpty":"显示<b style='color:red;'> _START_ </b>到<b style='color:red;'> _END_ </b>条记录",
//				"sInfo" : "显示<b style='color:red;'> _START_ </b>到<b style='color:red;'> _END_ </b>条记录/共<b style='color:red;'> _TOTAL_</b>条记录"
//			  }
//	});  
	
//	function table_html(id,title,body){
        //var tit=["1","2","3","4","5"],bd=[["a","b","c","d","e"],["a","b","c","d","e"]];
        var obj=$("#box_2");
        var thead="",tbody="";
        var title=["序号","名称"];
        var body=dataSet;
        
        for(var i in title){
            thead+='<th class="sorting_disabled" rowspan="1" colspan="1" aria-label="Points">'+title[i]+'</th>';
        }
        for(var k in body){ 
            var td=""
            for(var j in body[k]){
                td+='<td>'+body[k][j]+'</td>';
            }
            tbody += "<tr>"+td+"</tr>";
        }
        thead = "<tr>"+thead+"</tr>";
        obj.html(thead);
        obj.append(tbody);
//    }	

} 
/**
 * 展示引用到的library
 */
function libraryCountTableShow(str) {
	var data = str.split(";");
	if (data == null) {
		return;
	} 
	var dataSet = [];
	$.each(data, function(index, item) {
		if (item != null && item != '') {
			var dt = [index,item]; 
			dataSet.push(dt);  
		} 
	});  
        var obj=$("#library_path_table");
        var thead="",tbody="";
        var title=["序号","名称"];
        var body=dataSet;
        
        for(var i in title){
            thead+='<th class="sorting_disabled" rowspan="1" colspan="1" aria-label="Points">'+title[i]+'</th>';
        }
        for(var k in body){ 
            var td=""
            for(var j in body[k]){
                td+='<td>'+body[k][j]+'</td>';
            }
            tbody += "<tr>"+td+"</tr>";
        }
        thead = "<tr>"+thead+"</tr>";
        obj.html(thead);
        obj.append(tbody);
//    }	

} 


/**
 * 获取线程数量
 * @param data
 */
function getThreadCount(data){
	if ($('#thread_count_chart').size() != 1) { 
		return;
	}
	var visitors = []; 
	var contentMap=new Map(); 
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.threadCount ]; 
		visitors.push(visitor);
		
		var contentObj={"timeQuantum":item.timeQuantum,"threadCount":item.threadCount};
		contentMap.put(item.createTime,contentObj);
//		contentMap.put(item.createTime,);
	});

	initThreadMap.put("getThreadCount_contentMap",contentMap);

	var plot = $.plot($("#thread_count_chart"), [ {
		data : visitors, 
		label : "线程数",
		lines : {
			lineWidth : 3, 
		},
		shadowSize : 0

	} ], {
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,  
				fillColor: "#eee",  
				lineWidth : 3 
			},
			shadowSize : 4 
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
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
				"<tr><td align=\"center\" colspan=\"2\">线程数</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>数量:</td><td>"+contentObj["threadCount"]+"</td></tr></tbody></table> </div>").css({ 
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
	$("#thread_count_chart").bind(
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

						showTooltip(item.pageX, item.pageY, initThreadMap.get("getThreadCount_contentMap").get(x));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});
}


/**
 * 获取峰值线程
 * @param data
 */
function getPeakThreadCount(data){
	if ($('#peak_thread_count_chart').size() != 1) { 
		return;
	}
	var visitors = []; 
	var contentMap=new Map(); 
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.peakThreadCount ]; 
		visitors.push(visitor);
		
		var contentObj={"timeQuantum":item.timeQuantum,"peakThreadCount":item.peakThreadCount};
		contentMap.put(item.createTime,contentObj);
//		contentMap.put(item.createTime,);
	});

	initThreadMap.put("getPeakThreadCount_contentMap",contentMap);

	var plot = $.plot($("#peak_thread_count_chart"), [ {
		data : visitors, 
		label : "线程数",
		lines : {
			lineWidth : 3, 
		},
		shadowSize : 0

	} ], {
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,  
				fillColor: "#eee",  
				lineWidth : 3 
			},
			shadowSize : 4 
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
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
				"<tr><td align=\"center\" colspan=\"2\">峰值活动线程数</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>数量:</td><td>"+contentObj["peakThreadCount"]+"</td></tr></tbody></table> </div>").css({ 
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
	$("#peak_thread_count_chart").bind(
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

						showTooltip(item.pageX, item.pageY, initThreadMap.get("getPeakThreadCount_contentMap").get(x));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});
}

/**
 * 获取守护线程数量
 * @param data
 */
function getDaemonThreadCount(data){
	if ($('#daemon_thread_count_chart').size() != 1) {
		return;
	}
	var visitors = []; 
	var contentMap=new Map(); 
	$.each(data, function(index, item) {
		var visitor = [ item.createTime, item.threadCount ]; 
		visitors.push(visitor);
		
		var contentObj={"timeQuantum":item.timeQuantum,"daemonThreadCount":item.daemonThreadCount};
		contentMap.put(item.createTime,contentObj);
//		contentMap.put(item.createTime,);
	});

	initThreadMap.put("getDaemonThreadCount_contentMap",contentMap);

	var plot = $.plot($("#daemon_thread_count_chart"), [ {
		data : visitors, 
		label : "线程数",
		lines : {
			lineWidth : 3, 
		},
		shadowSize : 2

	} ], {
		series : {
			lines : {
				show : true,
				lineWidth : 3,
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
				radius : 4,  
				fillColor: "#eee",  
				lineWidth : 3 
			},
			shadowSize : 4 
		},

		colors : [ "#3598DB", "#01CDCE", "#826FFF" ],
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
				"<tr><td align=\"center\" colspan=\"2\">线程数</td></tr><tr><td colspan=\"2\">"+contentObj["timeQuantum"]+"</td></tr> " +
				"<tr><td>守护线程数量:</td><td>"+contentObj["daemonThreadCount"]+"</td></tr></tbody></table> </div>").css({
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
	$("#daemon_thread_count_chart").bind(
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

						showTooltip(item.pageX, item.pageY, initThreadMap.get("getDaemonThreadCount_contentMap").get(x));
					}
				} else {
					$("#tooltip").remove();
					previousPoint = null;
				}
			});
}
/*****************************************************************************************************/


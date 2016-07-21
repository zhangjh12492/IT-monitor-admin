<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file='sourceTop.jsp'%>
<%@include file="initJs.jsp" %>

<style>
.a_name_font{
	font-size: 20px;  
	color: #ffffff;
}
.a_count_font{
	font-size: 30px;  
	color: #ffffff;
}
</style>
<style type="text/css">        
        .item{position: relative;width: 280px;margin-left: 105px;margin-bottom:20px;float:left;}
	    .matterInfo{background: #44B6AE;color: #fff;width: 300px;height: 120px;text-align: center;font-size: 18px;}
	    .ml60{margin-left: 60px;padding-top: 1px;padding-right: 10px;}
	    .matterInfo a.tit{padding: 0px;margin: 15px 0 10px 0;display: block;font-size: 24px;color: #fff;}
	    .matterInfo .countbox a{color: #fff;text-decoration: none;font-size: 22px;margin: 0px 10px;}
	    .matterInfo .countbox a.more{font-size: 14px;float: right;margin-top: 5px;}
	    .matterInfo .countbox a.more:hover{text-decoration: underline;}
	    .item_left{width: 120px;height: 120px;border-radius: 50% !important;-webkit-border-radius: 50% !important;-moz-border-radius: 50% !important;position: absolute;top: 0px;left: -60px;text-align: center;}
	    .text{width: 80px;height: 80px;border-radius: 50% !important;-webkit-border-radius: 50% !important;-moz-border-radius: 50% !important;background: #fff;margin: 20px auto;}
	    .text strong{font-size: 40px;color: red;}
	    .text p{padding: 0px;margin: 0px;font-size: 14px;color: #464646;}
        .yellow{background: #ffff00;}
        .green{background:#21F500;}
        .red{background:#FF3400;}
        .matterInfoRed{background:#e35b5a;}
        .matterInfoYellow{background:#f4d03f;}
    </style>
<script type="text/javascript">
if (window.WebSocket) {
	console.log("This browser supports WebSocket!");
} else {
	console.log("This browser does not support WebSocket.");
}
//var ws = new WebSocket("");
////var ws = new WebSocket("ws://localhost:8080/exceptionSocketPro/sysMesIndex.do");
//ws.onopen = function() {
//	ws.send("hello");
//};
//ws.onmessage = function(message) {
//	//alert(message.data);
//	var item=$.parseJSON(message.data);
//
//	if(item!=null&&item!=undefined ){
//		 if(item.sysCode=="01"){
//			 var html="<span >异常:<a class=\"a_count_font\" href=\"javascript:findBusiMegBySysCode("+item.code+",'error')\">"+item.sysErrCount +"</a></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>警告:<a class=\"a_count_font\" href=\"javascript:findBusiMegBySysId("+item.code+",'warn')\">"+item.sysWarnCount+
//			 "</a></span><input type=\"hidden\" value='"+item.id+"' id=\"\" />";
//			 $("#order_center").html(html);
//		 }else if(item.sysCode=="02"){
//			 var html="异常:<a class=\"a_count_font\" href=\"javascript:findBusiMegBySysCode("+item.code+",'error')\">"+item.sysErrCount +"</a>&nbsp;&nbsp;&nbsp;&nbsp;警告:<a class=\"a_count_font\" href=\"javascript:findBusiMegBySysCode("+item.code+",'warn')\">"+item.sysWarnCount+
//			 "</a><input type=\"hidden\" value='"+item.id+"' id=\"\" />";
//			 $("#product_center").html(html);
//		 }else if(item.sysCode=="03"){
//			 var html="异常:<a class=\"a_count_font\" href=\"javascript:findBusiMegBySysCode("+item.code+",'error')\">"+item.sysErrCount +"</a>&nbsp;&nbsp;&nbsp;&nbsp;警告:<a class=\"a_count_font\" href=\"javascript:findBusiMegBySysCode("+item.code+",'warn')\">"+item.sysWarnCount+
//			 "</a><input type=\"hidden\" value='"+item.id+"' id=\"\" />";
//			 $("#product_cust_ser").html(html);
//		 }
//	}
//};
function postToServer() {
	ws.send(document.getElementById("msg").value);
	document.getElementById("msg").value = "";
}
function closeConnect() {
	ws.close();
}

	
/**
 * 进入业务展示异常数量的index
 */
function findBusiMegBySysCode(sysCode,messType){
	window.location.href="${contextPath}admin.do?findBusiMesBySysCode&toJsp&sysCode="+sysCode+"&messType="+messType+"&processStatus=0";	//processStatus后续处理
}	

function selectTopTenSysPro(sysCode){
	window.location.href="${contextPath}/admin.do?selectTopTenSysPro&toView&sysCode="+sysCode;
}

/** 
 * 进入系统信息监控界面 
 */
function intoSysMonitor(sysChildActiveCountValue,code){
	if(sysChildActiveCountValue==0){
		
	}else{
		window.location.href="${contextPath}/sysMonitor.do?intoSysMonitorIndex&code="+code;
	}
}


</script>
<body > 
	<!-- BEGIN HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN CONTENT -->
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<h3 class="c_tit">
        	<span class="glyphicon glyphicon-th" aria-hidden="true"></span>
			异常·系统监控     <small style="color:#fff;"></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="javascript:void(0);">系统监控</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="javascript:void(0);">监控·监控首页</a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN DASHBOARD STATS -->
			<!-- warning -->
			
			<div class="row" id="row_all_sys_index_show" >
			
			</div>
			<!-- other row -->
			<!-- error -->
			
			
		</div>
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="page-footer-inner">
		 2015 &copy; wangfujing system monitor
	</div>
	<div class="scroll-to-top">
		<i class="icon-arrow-up"></i>
	</div>
</div>
</body>

<%@ include file="sourceBottom.jsp" %>




<!-- BEGIN PAGE LEVEL PLUGINS --> 
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>


<script type="text/javascript" src="${contextPath }static/js/index.js"></script> 

<script>

jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   QuickSidebar.init(); // init quick sidebar
   Demo.init(); // init demo features 
   
   Index.init();   
//   Index.initDashboardDaterange();
 //  Index.initJQVMAP(); // init index page's custom scripts
 //  Index.initCalendar(); // init index page's custom scripts
 //  Index.initChat();
//   Index.initMiniCharts();
//   Tasks.initDashboardWidget();

//   ChartsFlotcharts.init();
}); 

//var sysMap=new Map();	//系统的map集合
var sysErrorWarnThresholdCountMap=new Map();	//系统异常数量的阀值集合
var sysChildActiveCountMap=new Map()	//存活实例数量的map集合

var sysProcessMessMap=new Map();	//系统处理异常的集合
/**
 * 
 

*/
 
/**
 * 获取到所有的系统

function initSysInfoMap(){
	$.ajax({
		 url:'${contextPath}sysOperate.do?getSysInfoAjax',
		 type:'post',      
		 cache:false, 
		 async:false, 
		 timeout:1000*60,   
		 success:function(data){
			 data=$.parseJSON(data);
			 var html="";
			 if(data!=null&&data.length>0){
				 $.each(data,function(index,item){
					 var sysInfo={"id":item.id,"sysCode":item.sysCode,"sysName":item.sysName,"sysDesc":item.sysDesc,"sysChildActiveThresholdMaxCount":item.sysChildActiveThresholdMaxCount,"sysChildActiveThresholdMinCount":item.sysChildActiveThresholdMinCount};
					 sysMap.put(item.sysCode,sysInfo);
				 });
			 }
		 } 
	});	 
}   */
/**
 * 根据系统id获取到系统预警信息
 */
function initSysErrorWarnThresholdCountMap(id){
	var earlyInfo={}; 
	$.ajax({
		 url:'${contextPath}mesEarlyWarn.do?getAllEarlyWarnBySysIdAjax&sysId='+id,
		 type:'post',      
		 cache:false, 
		 async:false, 
		 timeout:1000*60,   
		 success:function(data){
			 data=$.parseJSON(data);
			 var html="";
			 if(data!=null&&data.length>0){
				 $.each(data,function(index,item){
					 earlyInfo={"warnCountMin":item.warnCountMin,"warnCountMax":item.warnCountMax,"errorCountMin":item.errorCountMin,"errorCountMax":item.errorCountMax,"sysChildActiveThresholdMaxCount":item.sysChildActiveThresholdMaxCount,"sysChildActiveThresholdMinCount":item.sysChildActiveThresholdMinCount};
					
				 }); 
			 }else{
				 earlyInfo={"warnCountMin":10,"warnCountMax":20,"errorCountMin":5,"errorCountMax":10,"sysChildActiveThresholdMaxCount":6,"sysChildActiveThresholdMinCount":3};
			 }
			 
		 }
	});	
	return earlyInfo;
}

function div_mouseover(div){     
	$(div).css("cursor","pointer"); 
}


/**
 * 初始化系统展示界面
 */
function initUserAllSys(){
	$.ajax({
		 url:'${contextPath}sysOperate.do?getUserSysInfo',
		 type:'post',      
		 cache:false, 
		 async:false, 
		 timeout:1000*60,   
		 success:function(data){
			 var html="";
			 if(data!=null&&data.length>0){
				 $.each(data,function(index,item){
					 var leftBgColorClass="item_left red";	//存活实例颜色
					 var matterInfo="matterInfo matterInfoRed";	//异常数量颜色
					 var earlyInfo=initSysErrorWarnThresholdCountMap(item.id);
					 var sysChildActiveCountKey=item.sysName+"-"+item.sysCode;		//存活实例名称
					 var sysChildActiveCountValue=sysChildActiveCountMap.get(sysChildActiveCountKey); 	//存活实例值
					 var sysProcessMes=sysProcessMessMap.get(item.sysCode);		//系统的处理结果集合 
					 if(sysProcessMes ==null || sysProcessMes == undefined){ 
						 sysProcessMes={"warnCount":0,"errorCount":0,"code":item.sysCode}
					 }
					 if(sysChildActiveCountValue==null){
						 sysChildActiveCountValue=0;
					 }
					 
					 //系统下存活子节点的数量
					 var activeSpan='<strong onclick="intoSysMonitor('+sysChildActiveCountValue+',\''+item.sysCode+'\')" style="cursor:pointer;">'+sysChildActiveCountValue+'</strong>'+"/"+earlyInfo.sysChildActiveThresholdMaxCount;
					 if(sysChildActiveCountValue>=earlyInfo.sysChildActiveThresholdMaxCount){
						 leftBgColorClass="item_left green";
					 }else if(sysChildActiveCountValue>earlyInfo.sysChildActiveThresholdMinCount&&sysChildActiveCountValue<earlyInfo.sysChildActiveThresholdMaxCount){
						 leftBgColorClass="item_left yellow"; 
					 }else if(sysChildActiveCountValue<=earlyInfo.sysChildActiveThresholdMinCount){
						 leftBgColorClass="item_left red";
					 }
					 if(earlyInfo!=null){
						 if(sysProcessMes.errorCount<=earlyInfo.errorCountMin){  
							 matterInfo="matterInfo";
						 }else if(sysProcessMes.errorCount>earlyInfo.errorCountMin && sysProcessMes.errorCount<=earlyInfo.errorCountMax){
							 matterInfo="matterInfo matterInfoYellow";
						 }else if(sysProcessMes.errorCount>earlyInfo.errorCountMax){
							 matterInfo="matterInfo matterInfoRed";
						 }
					 }
					
					html+='<div class="item"> '+ 
			    		' <div class="'+matterInfo+'">	'+  
			    		'    <div class="ml60" onmouseover="div_mouseover(this)" onclick="intoSysMonitor(\''+item.sysCode+'\')" >'+
//			    		'    <div class="ml60" >'+
			    		'     <a class="tit" href="javascript:findBusiMegBySysCode(\''+item.sysCode+'\',\'\')">'+item.sysName+'</a>	'+ 
			    		'        <div class="countbox">	'+ 
			    		'            <a href="javascript:findBusiMegBySysCode(\''+item.sysCode+'\',\'error\')">异常:<strong>'+sysProcessMes.errorCount+'</strong></a>	'+
			    		'             <a href="javascript:findBusiMegBySysCode(\''+item.sysCode+'\',\'warn\')">警告:<strong>'+sysProcessMes.warnCount+'</strong></a>	'+
			    		'             <a href="javascript:selectTopTenSysPro(\''+item.sysCode+'\')" class="more">VIEW MORE</a>	'+
			    		'         </div>	'+  
			    		'      </div>	'+
			    		'  </div>	'+ 
					    '  <div class="'+leftBgColorClass+'">	'+ 
					    '      <div class="text">	'+ 
					    '          '+activeSpan+' 	'+  
					    '         <p>存活</p>	'+
					    '     </div>	'+
					    '  </div>	'+  
					    ' </div>	';
				 }); 
			 } 
			 $("#row_all_sys_index_show").html(html); 
		 },error:function(){
			 alert("error");
		 }
	});
}


 
 

/**
 * 初始化异常index界面展示的数据
 * 初始化系统的处理信息
 */
function initSysProcessMes(){
	$.ajax({
		 url:'${contextPath}admin/initSysData.do?sysCode=&processStatus=0',
		 type:'post',      
		 cache:false, 
		 async:false, 
		 timeout:1000*60,   
		 success:function(data){
			 data=$.parseJSON(data);
			 var html="";
			 if(data!=null&&data.length>0){
				 $.each(data,function(index,item){
					 sysProcessMessMap.put(item.code,{"warnCount":item.warnCount,"errorCount":item.errorCount,"code":item.code});
				 }); 
			 } 
			 
		 },error:function(){
			 alert("error");
		 }
	});
} 
 
function getSysChildActiveCount(){
	$.ajax({
		 url: '${contextPath}admin.do?getSysChildActiveCount',
		 type:'post',      
		 cache:false, 
		 async:false, 
		 timeout:1000*60,   
		 success:function(data){
//			 data= $.parseJSON(data);
			 var html="";
			 $.each(data,function(index,item){
				 sysChildActiveCountMap.put(item.parentName,item.nodeCount);
			 });


		 }
	});	 
} 
 
function initSysMesIndex(){ 
	getSysChildActiveCount();
	initSysProcessMes();
//	initSysInfoMap(); 
	initUserAllSys();
	
}
initSysMesIndex();
/**
 * 页面初始化信息的定时器，5分钟执行一次，与hadoop的mapReduce时间相同
 */
$(document).ready(function(){
	setInterval(initSysMesIndex, 1000*60)
});
</script>
</html>
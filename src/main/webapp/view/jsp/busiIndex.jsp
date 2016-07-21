<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="initJs.jsp" %>
<html lang="en" ><!-- class="no-js" -->
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="UTF-8"/>
<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<title>Metronic | Admin Dashboard Template</title>

<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="<%=request.getContextPath() %>/static/css/metronic/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/static/css/metronic/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
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
<script type="text/javascript">

function messageList(count,code,messType){
//	window.location.href="${contextPath}/admin.do?toMessageListView&busiId="+busiId+"&messType="+messType;
	if(count==0){
		
	}else{
		var dateType="all";
		window.location.href = "${contextPath}mesChart.do?chartToMesListView&code=" + code+ "&dateType=" + dateType +"&codeType=busi&errLevel="+messType+"&processStatus="+$("#processStatus").val(); //此处展示未处理的信息
	}
	
}

function selectTopBusiPro(sysCode,busiCode,errLevel){
	window.location.href="${contextPath}admin.do?selectTopBusiPro&toView&code="+sysCode+busiCode+"&errLevel="+errLevel;
}
	
</script>

</head>
<body class="page-header-fixed page-quick-sidebar-over-content page-style-square" > 
	<!-- BEGIN HEADER -->
<!-- END HEADER -->
<input type="hidden" name="sysCode" id="sysCode" value="${sysCode }"/>
<input type="hidden" name="messType" id="messType" value="${messType }"/>
<input type="hidden" name="processStatus" id="processStatus" value="${processStatus }"/>

<div class="clearfix">
</div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
	
	<!-- BEGIN CONTENT -->
	<div class="page-container">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<h3 class="c_tit">
        	<span class="glyphicon glyphicon-th" aria-hidden="true"></span>
					业务异常 <small style="color:#fff;"></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="javascript:void(0);">系统监控</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="javascript:void(0);">业务异常</a>
					</li>
				</ul>

			</div>
			<!-- other row -->
			
			<div class="row" id="row_all_busi_index_show"> 
				 
				<c:if test="${messType=='error' }" >
					<c:forEach var="busiMes" items="${listBusi }">
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12" >
							<div class="dashboard-stat green-haze" >
								<div class="visual">
									<i class="fa fa-comments"></i>
								</div>
								<div class="details">
									<div class="number" id="order_center_warn">
									异常:<c:if test="${busiMes.busiErrCount ==0}" >
											0
										</c:if>
										<c:if test="${busiMes.busiErrCount >0}" >
											<a class="a_count_font" href="javascript:messageList('${busiMes.sysCode }','${busiMes.busiCode }','${messType }')">${busiMes.busiErrCount }	</a>
										</c:if>
											
									</div>
									<div class="desc">
										<a class="a_name_font" href="javascript:messageList('${busiMes.sysCode }','${busiMes.busiCode }','error')">${busiMes.busiCode }</a> 
									</div>
								</div>
								<a class="more" href="javascript:selectTopBusiPro('${busiMes.sysCode }','${busiMes.busiCode }','')" >
								View more <i class="m-icon-swapright m-icon-white"></i>
								</a>
							</div> 
						</div>
					</c:forEach>
				</c:if>
				
				
				<c:if test="${messType=='warn' }" >
					<c:forEach var="busiMes" items="${listBusi }">
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12" onclick="">
							<div class="dashboard-stat green-haze">
								<div class="visual">
									<i class="fa fa-comments"></i>
								</div>
								<div class="details">
									<div class="number" id="order_center_warn">
										警告:<c:if test="${busiMes.busiWarnCount ==0}" >
											0
										</c:if>
										<c:if test="${busiMes.busiWarnCount >0}" >
											<a class="a_count_font" href="javascript:messageList('${busiMes.sysCode }','${busiMes.busiCode }','${messType }')">${busiMes.busiWarnCount }	</a>
										</c:if>
										  
									</div>
									<div class="desc">
										<a class="a_name_font" href="javascript:messageList('${busiMes.sysCode }','${busiMes.busiCode }','warn')">${busiMes.busiCode }</a> 
									</div>
								</div>
								<a class="more" href="javascript:selectTopBusiPro('${busiMes.sysCode }','${busiMes.busiCode }','')" >
								View more <i class="m-icon-swapright m-icon-white"></i>
								</a>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${messType=='' || messType==null }" >
					<c:forEach var="busiMes" items="${listBusi }">
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12" onclick="">
							<div class="dashboard-stat green-haze">
								<div class="visual">
									<i class="fa fa-comments"></i>
								</div>
								<div class="details">
									<div class="number" id="order_center_warn">
									异常:<c:if test="${busiMes.busiErrCount ==0}" >0</c:if>
										<c:if test="${busiMes.busiErrCount >0}" ><a class="a_count_font" href="javascript:messageList('${busiMes.sysCode }','${busiMes.busiCode }','${messType }')">${busiMes.busiErrCount}</a></c:if>
									警告:<c:if test="${busiMes.busiWarnCount ==0}" >0</c:if>
									<c:if test="${busiMes.busiWarnCount >0}" ><a class="a_count_font" href="javascript:messageList('${busiMes.sysCode }','${busiMes.busiCode }','${messType }')">${busiMes.busiWarnCount}</a></c:if>
										  
									</div>
									<div class="desc">
										<a class="a_name_font" href="javascript:messageList('${busiMes.sysCode }','${busiMes.busiCode }','')">${busiMes.busiCode }</a> 
									</div>
								</div>
								<a class="more" href="javascript:selectTopBusiPro('${busiMes.sysCode }','${busiMes.busiCode }','')" >
								View more <i class="m-icon-swapright m-icon-white"></i>
								</a>
							</div>
						</div> 
					</c:forEach>	
				</c:if>
			
			</div>
		</div>
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
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>


<script src="<%=request.getContextPath() %>/static/js/index.js"></script>


<script>
jQuery(document).ready(function() {       
   Metronic.init(); // init metronic core components
Layout.init(); // init current layout
QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
});


var sysMap=new Map();
 
/**
 * 获取到所有的系统
 */
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
} 
/**
 * 根据系统码获取到系统最近一条异常统计结果
 */
function getProcessBySysCode(){
	var sysProcessInfo={};
	$.ajax({
		 url:'${contextPath}mesProcess.do?selectOneTopProcessMesBySysCode&code='+$("#sysCode").val()+"&processStatus="+$("#processStatus").val(),
		 type:'post',      
		 cache:false, 
		 async:false, 
		 timeout:1000*60,   
		 success:function(data){
			 if(data!=null){
				 sysProcessInfo={"code":data.code,"warnCount":data.warnCount,"errorCount":data.errorCount,"createdTime":data.createdTime}; 
			 }
		 }
	});	 
	return sysProcessInfo;
} 


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
					 earlyInfo={"warnCountMin":item.warnCountMin,"warnCountMax":item.warnCountMax,"errorCountMin":item.errorCountMin,"errorCountMax":item.errorCountMax};
				 });
			 }else{
				 return null;
			 }
			 
		 }
	});	
	return earlyInfo;
}
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

/**
 * 初始化业务的异常处理数量
 */
function initBusiProcessCount(){
	var messType=$("#messType").val(); 
	var bgColorClass="dashboard-stat green-haze";
	var sysId=sysMap.get($("#sysCode").val())["id"];
	var earlyWarnInfo=initSysErrorWarnThresholdCountMap(sysId);
	var sysProcessInfo=getProcessBySysCode();
	if(earlyWarnInfo!=null){
		if(sysProcessInfo.errorCount<=earlyWarnInfo.errorCountMin){ 
			 bgColorClass="dashboard-stat green-haze"; 
		 }else if(sysProcessInfo.errorCount>earlyWarnInfo.errorCountMin && sysProcessInfo.errorCount<=earlyWarnInfo.errorCountMax){
			 bgColorClass="dashboard-stat yellow-saffron"; 
		 }else if(sysProcessInfo.errorCount>earlyWarnInfo.errorCountMax){
			 bgColorClass="dashboard-stat red-sunglo";
		 }
	 }
	 
	$.ajax({
		 url:'${contextPath}admin.do?findBusiMesBySysCode&toData&sysCode='+$("#sysCode").val()+"&messType="+$("#messType").val()+"&processStatus="+$("#processStatus").val(),
		 type:'post',      
		 cache:false,  
		 async:false, 
		 timeout:1000*60,   
		 success:function(data){
			 data=$.parseJSON(data);
			 var html="";
			 if(data!=null&&data.length>0){
				 $.each(data,function(index,item){
					var busiInfo=getBusiInfoBySysBusiCode(item.code.substr(0,2),item.code.substr(2,3));
					var errorOrWarn="";
					if(messType!=null&&messType!=''){
						if(messType=='error'){
							errorOrWarn+='异常:<a class="a_count_font" href="javascript:messageList(\''+item.errorCount+'\',\''+item.code+'\',\''+messType+'\')">'+item.errorCount+'</a>   ';
						}else if(messType == 'warn'){
							errorOrWarn+='警告:<a class="a_count_font" href="javascript:messageList(\''+item.warnCount+'\',\''+item.code+'\',\''+messType+'\')">'+item.warnCount+'</a>   ';
						}
					}else{
						errorOrWarn+='异常:<a class="a_count_font" href="javascript:messageList(\''+item.errorCount+'\',\''+item.code+'\',\'error\')">'+item.errorCount+'</a>   警告:<a class="a_count_font" href="javascript:messageList(\''+item.warnCount+'\',\''+item.code+'\',\'warn\')">'+item.warnCount+'</a>';
					}
					html+='<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12" onclick="">	'+
					'	<div class="'+bgColorClass+'">	'+
					'	<div class="visual">	'+
					'		<i class="fa fa-comments"></i>	'+
					'	</div>	'+
					'	<div class="details">	'+
					'		<div class="number" id="order_center_warn">	'+
					errorOrWarn+
					'		</div>	'+
					'		<div class="desc">	'+ 
					'			<a class="a_name_font" href="javascript:messageList(\''+item.errorCount+'\',\''+item.code+'\',\'\')">'+busiInfo["busiDesc"]+'</a> 	'+
					'		</div>	'+
					'	</div>	'+
					'	<a class="more" href="javascript:selectTopBusiPro(\''+item.code.substr(0,2)+'\',\''+item.code.substr(2,3)+'\',\'\')" >	'+
					'	View more <i class="m-icon-swapright m-icon-white"></i>	'+
					'	</a>	'+
					'	</div>	'+
					'	</div> ';
				 }); 
			 }  
			 $("#row_all_busi_index_show").html(html); 
		 },error:function(){
			 alert("error");
		 }
	});
}

function initBusiIndex(){
	initSysInfoMap();
	initBusiProcessCount();
}
initBusiIndex();


</script>
</html>
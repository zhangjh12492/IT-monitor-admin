<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file='sourceTop.jsp'%>
<%@include file="initJs.jsp" %>

<script type="text/javascript">

function selectTopTenSysPro(sysCode){
	window.location.href="${contextPath}/admin.do?selectTopTenSysPro&toData&sysCode="+sysCode;
}

</script>
<body class="page-header-fixed page-quick-sidebar-over-content page-style-square" > 
	<!-- BEGIN HEADER -->
	 
	
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN CONTENT -->
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<h3 class="c_tit">
        	<span class="glyphicon glyphicon-th" aria-hidden="true"></span>
			业务异常信息统计图表     <small style="color:#fff;"></small>
			</h3>
			
			<!-- END PAGE HEADER-->
			<!-- BEGIN DASHBOARD STATS -->
			<!-- warning -->
			
			<!-- other row -->
			<!-- error -->
			
			 <input type="hidden" name="code" id="code" value="${mesAllReqCond.code }"/>
			 <input type="hidden" name="codeType" id="codeType" value="${mesAllReqCond.codeType }"/>
			 
			 <input type="hidden" name="currentTime" id="currentTime" value="${mesAllReqCond.currentTime }"/>
			 <input type="hidden" name="errLevel" id="errLevel" value="${mesAllReqCond.errLevel }"/>
			 <input type="hidden" name="dateType" id="dateType" value="${mesAllReqCond.dateType }"/>
			 
			<div class="row">
				<div class="col-md-12">
			<!-- BEGIN INTERACTIVE CHART PORTLET-->
					<div class="table_box">
						<div class="table_tit">
							<div class="titname">
									<i class="fa fa-gift"></i>系统下所有的异常信息折线图
								</div>
								<div class="actions">
									<div class="btn-group pull-right">
										<a href="" class="btn grey-steel btn-sm dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
										日期选择 <span class="fa fa-angle-down">
										</span>
										</a>
										<ul class="dropdown-menu pull-right">
											<li id="hour" value="hour" onclick="dateTypeChange('hour')">
												<a href="javascript:;">
												一小时
												</a>
											</li>
											<li id="day" value="day" onclick="dateTypeChange('day')">
												<a href="javascript:;">
												一天
												</a>
											</li>
											<li id="week" value="week" onclick="dateTypeChange('week')">
												<a href="javascript:;">
												一周
												</a>
											</li>
											<li id="month" value="month" onclick="dateTypeChange('month')">
												<a href="javascript:;">
												一个月
												</a>
											</li>
											<li id="quarter" value="quarter" onclick="dateTypeChange('quarter')">
												<a href="javascript:;">
												一季度
												</a>
											</li>
											<li id="halfYear" value="halfYear" onclick="dateTypeChange('halfYear')">
												<a href="javascript:;">
												半年
												</a>
											</li>
											<li id="year" value="year" onclick="dateTypeChange('year')">
												<a href="javascript:;">
												一年
												</a>
											</li>
										</ul>
									</div>
								</div>
								
						</div>
						<div class="portlet-body">
							<div id="chart_2" class="chart">
							</div>
						</div>
					</div>
					
				</div>
			</div>	
			
			<div class="row">
					<div class="col-md-6" id="err_warn_pie_chart_div">
						<div class="portlet-title">
							<div class="portlet light bordered">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-gift"></i>用户自定义err警告饼图
									</div>
									<div class="tools">
										
									</div>
								</div>
								<div class="portlet-body">
									<div id="err_warn_pie_chart" class="chart" style="height: 325px;"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="err_error_pie_chart_div">
						<div class="portlet-title">
							<div class="portlet light bordered">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-gift"></i>用户自定义err异常饼图
									</div>
									<div class="tools">
										
									</div>
								</div> 
								<div class="portlet-body">
									<div id="err_error_pie_chart" class="chart" style="height: 325px;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>	
				<!-- start radar chart -->
				<div class="row">
						<div class="col-md-6" id="err_warn_radar_chart_div">
							<!-- BEGIN CHART PORTLET-->
							<div class="portlet light bordered" >
								<div class="portlet-title">
									<div class="caption">
										<i class="icon-bar-chart font-green-haze"></i>
										<span class="caption-subject bold uppercase font-green-haze"> 用户自定义err警告雷达图</span>
									</div>
									<div class="tools">
										
									</div>
								</div>
								<div class="portlet-body">
									<div id="chart_8" class="chart" style="height: 300px;">
									</div>
								</div>
							</div>
							<!-- END CHART PORTLET-->
						</div> 
						<div class="col-md-6" id="err_error_radar_chart_div" >
							<!-- BEGIN CHART PORTLET-->
							<div class="portlet light bordered">
								<div class="portlet-title">
									<div class="caption">
										<i class="icon-bar-chart font-green-haze"></i>
										<span class="caption-subject bold uppercase font-green-haze">用户自定义err异常雷达图</span>
									</div>
									<div class="tools">
										
									</div>
								</div>
								<div class="portlet-body">
									<div id="chart_9" class="chart" style="height: 300px;">
									</div>
								</div>
							</div>
							<!-- END CHART PORTLET-->
						</div>
					</div>
				<!-- end radar chart -->
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
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/amcharts/amcharts/pie.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->   
 
<script src="<%=request.getContextPath() %>/static/js/message/pluginjs/busiMesStatisticalChart.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/amcharts/amcharts/radar.js" type="text/javascript"></script>
<script type="text/javascript">  

var dateType=$("#dateType").val();
if(dateType == null || dateType == undefined || dateType == ''){
	$("#dateType").val("week");
	dateType=$("#dateType").val();  
} 
$("#"+dateType).siblings().removeClass("active").end().addClass("active");  

jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core componets
	   Layout.init(); // init layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features 
	   err_line_chart_init();	//折线图
	   err_pie_radar_chart_init();	//饼图 与 折线图
	});
	

</script>
</html>
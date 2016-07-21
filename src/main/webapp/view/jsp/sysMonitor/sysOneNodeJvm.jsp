<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../initJs.jsp"%>
<html lang="en">
<head>
<title>JVM监控</title>
<meta charset="UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<!--<link href="../../metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="../../metronic_v35/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
    &lt;!&ndash; END GLOBAL MANDATORY STYLES &ndash;&gt;
    &lt;!&ndash; BEGIN PAGE LEVEL STYLES &ndash;&gt;
    <link rel="stylesheet" type="text/css" href="../../metronic_v35/theme/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css" href="../../metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
    <link rel="stylesheet" type="text/css" href="../../metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
    <link rel="stylesheet" type="text/css" href="../../metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../../metronic_v35/theme/assets/global/plugins/jquery-tags-input/jquery.tagsinput.css"/>
    <link rel="stylesheet" type="text/css" href="../../metronic_v35/theme/assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet" type="text/css" href="../../metronic_v35/theme/assets/global/plugins/typeahead/typeahead.css">
    &lt;!&ndash; END PAGE LEVEL STYLES &ndash;&gt;
    &lt;!&ndash; BEGIN THEME STYLES &ndash;&gt;
    <link href="../../metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="../../metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
    <link href="../../metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
    <link href="../../static/css/metronic/components.css" id="style_components" rel="stylesheet" type="text/css"/>
    <link href="../../static/css/metronic/layout.css" rel="stylesheet" type="text/css"/>-->
        <link href="${contextPath }metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath }metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath }metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="${contextPath }metronic_v35/theme/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath }metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="${contextPath }metronic_v35/theme/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
    <link href="${contextPath }metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath }metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="${contextPath }metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath }metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->

<link href="${contextPath}css/layout.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	
</script>
</head> 
<body>
	<input type="hidden" name="code" id="code" value="${appMonitorCond.code }" /> <!--  -->
	<input type="hidden" name="nodeName" id="nodeName" value="${appMonitorCond.name }" /> <!--  -->
	<input type="hidden" name="parentName" id="parentName" value="${appMonitorCond.parentName }" /> <!--  -->
	<input type="hidden" name="rootName" id="rootName" value="${appMonitorCond.rootName }" /> <!--  -->
	<div class="conright">
		<div class="titbar">
			<div class="fl posr seleBox">
				<a class="posr ak" id="js_sele"> 
					<span></span> 
					<b class="posa icon_1 seat1"></b>
				</a>
				<div class="posa chosen-drop" id="js_sel_box">
					<div class="chosen-search">
						<input type="text" autocomplete="off">
					</div>
					<ul class="chosen-results" id="chosen_results_sys_select">
						<!-- <li class="active-result highlighted"><a class="pl_15">PHP Application</a></li>
						<li class="active-result"><a class="pl_15">java_demo</a></li>
						<li class="active-result"><a class="pl_15">Welcome to Tomcat</a></li> -->
					</ul>
				</div>
			</div>
			<div class="fr timeMeun" id="js_timeMeun">
				<%--<span>最近</span> <span>560分钟</span> <b class="icon_2 seat2"></b>--%>
			</div>
		</div>
		<div class="main">
			<div class="m_top10">
				<table class=" chart_table" border="0" cellspacing="0" cellpadding="0" id="table_oneSysNode_monitorinfo">
					<colgroup>
						<col class="col_1">
						<col class="col_2">
						<col class="col_3">
						<col class="col_4">
						<col class="col_5">
						<col class="col_6">
						<col class="col_7">
					</colgroup>
					<tr class="table_head">
						<th>名称</th>
						<th>Apdex</th>
						<th>响应时间(ms)</th>
						<th>吞吐率(次/秒)</th>
						<th>错误率(%)</th>
						<th>CPU使用率(%)</th>
						<th>内存使用量(MB)</th>
					</tr>

				</table>
			</div>
			<div class="titbar m_top10">
				<div class="fl pl_15">
					<span id="titbar_pl_15_name"></span>
					<!-- <a href="javascript:void(0);" class="start_profile_btn">开始线程剖析</a> -->
				</div>
				<div class="fr timeMeun">
					<%--<span>最近</span> <span>560分钟</span> <b class="icon_2 seat2"></b>--%>
				</div>
			</div>
			<div class="jvm_time clearfix">
				<div class="jvm_data">
					<div class="jvm_font_w1">
						<span class="jvm_font01" id="jvm_font_w1_font01_apdex"></span><span class="jvm_font02"></span>
					</div>
					<div class="jvm_font_w2">
						<span class="jvm_font02">Apdex </span>
					</div>
				</div>
				<div class="jvm_data">
					<div class="jvm_font_w1">
						<span class="jvm_font01" id="jvm_font_w1_font01_serverReqTime"></span><span class="jvm_font02">ms</span>
					</div>
					<div class="jvm_font_w2">
						<span class="jvm_font02">响应时间 </span>
					</div>
				</div>
				<div class="jvm_data">
					<div class="jvm_font_w1">
						<span class="jvm_font01" id="jvm_font_w1_font01_throughputRate">0.939</span><span class="jvm_font02">次/秒</span>
					</div>
					<div class="jvm_font_w2">
						<span class="jvm_font02">吞吐率 </span>
					</div>
				</div>
				<div class="jvm_data">
					<div class="jvm_font_w1">
						<span class="jvm_font01" id="jvm_font_w1_font01_cpuRate">0.939</span><span class="jvm_font02">%</span>
					</div>
					<div class="jvm_font_w2">
						<span class="jvm_font02">CPU使用率 </span>
					</div>
				</div>
				<div class="jvm_data">
					<div class="jvm_font_w1">
						<span class="jvm_font01" id="jvm_font_w1_font01_memoryPhysicalSpaceSize">0.939</span><span class="jvm_font02">MB</span>
					</div>
					<div class="jvm_font_w2">
						<span class="jvm_font02">内存使用量</span>
					</div>
				</div>
				<div class="jvm_data_auto"> 
	                <div class="jvm_font_auto1">
	                    <span class="jvm_font01" id="jvm_font_w1_font01_server">Apache Tomcat 6.0.41</span><span class="jvm_font02"></span>
	                </div>
	                <div class="jvm_font_auto2">
	                    <span class="jvm_font02">应用服务器</span>
	                </div>
	            </div>
			</div>
			<div class="m_top10 submenu_box clearfix">
				<ul id="js_tab" class="submenu_tab clearfix">
					<li class="on">内存使用量</li>
					<li>线程</li>
					<li class="last">Http会话</li>
				</ul>
				<!-- 内存使用量 -->
				<div class="subcenter subcenter_db clearfix">
					<div class="item_1">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>Heap memory Usage(MB)</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="heap_memory_usage_chart"></div>
						</div>
					</div>
					<div class="item_2">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>Code Cache</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="code_cache_chart"></div>
						</div>
					</div>
					<div class="item_2 item_3">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>Par Survivor Space</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="par_survivor_space_chart"></div>
						</div>
					</div>
					<div class="item_2">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>Par Eden Space</span> 
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="par_eden_space_chart"></div>
						</div>
					</div>
					<div class="item_2 item_3">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>CMS Old Gen</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="cms_old_gen_chart"></div>
						</div>
					</div>
					<div class="item_2">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>CMS Perm Gen</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="cms_perm_gen_chart"></div>
						</div>
					</div>
					<div class="item_2 item_3">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>Garbage collection CPU time</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="garbage_collection_cpu_time_chart"></div>
						</div>
					</div>
					<!-- BEGIN SAMPLE TABLE PORTLET-->
					<div class="item_2 item_3">
						<div class="hd">
							<div class="fl"> 
								<b class="fl icon_t">?</b> <span>library path</span>
							</div>
						</div>   
			            <div class="portlet-body tabbox_2">
			                <table class='table table-striped table-bordered table-hover' id='library_path_table'></table>
			            </div>
					</div>
					
					<div class="item_2">
			            <div class="hd">
							<div class="fl"> 
								<b class="fl icon_t">?</b> <span>classPath</span>
							</div>
						</div>  
			            <div class="portlet-body tabbox_2">
			                <table class='table table-striped table-bordered table-hover' id='box_2'></table>
			            </div>
			        </div>
					
				</div>
				<!-- 内存使用量 -->

				<!-- 线程 -->
				<div class="subcenter clearfix">
					<div class="item_1">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>Thread Count</span>
							</div>
						</div> 
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart"  id="thread_count_chart"></div>
						</div>
					</div>
					<div class="item_2">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>Peak Thread Count</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="peak_thread_count_chart"></div>
						</div>
					</div>
					<div class="item_2 item_3">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>Daemon Thread Count</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="daemon_thread_count_chart"></div>
						</div>
					</div>
				</div>
				<!-- 线程 -->
				<!-- Http会话 -->
				<div class="subcenter clearfix">
					
					<div class="item_2">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>manager</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="chart_9"></div>
						</div>
					</div>
					<div class="item_2 item_3">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>host-manager</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="chart_10"></div>
						</div>
					</div>
					<div class="item_2">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>java_demo</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="chart_11"></div>
						</div>
					</div>
					<div class="item_2 item_3">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>examples</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="chart_12"></div>
						</div>
					</div>
					<div class="item_2">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>docs</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="chart_13"></div>
						</div>
					</div>
					<div class="item_2 item_3">
						<div class="hd">
							<div class="fl">
								<b class="fl icon_t">?</b> <span>/</span>
							</div>
						</div>
						<div class="bd clearfix">
							<div style="height: 260px;" class="chart" id="chart_14"></div>
						</div>
					</div>
				</div>
				<!-- Http会话 -->
			</div>
		</div>
	</div>
	<div class="page-footer">
		<div class="page-footer-inner">
			2015 &copy; wangfujing system monitor
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</div>
	<!-- [endif]-->
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/amcharts/serial.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/amcharts/pie.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/amcharts/radar.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/amcharts/themes/light.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/amcharts/themes/patterns.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/amcharts/themes/chalk.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/ammap/ammap.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/ammap/maps/js/worldLow.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/amcharts/amstockcharts/amstock.js" type="text/javascript"></script>
	<!-- datatables -->
	<script type="text/javascript" src="${contextPath }metronic_v35/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${contextPath }metronic_v35/theme/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>
	<script type="text/javascript" src="${contextPath }metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.js"></script>
	<script type="text/javascript" src="${contextPath }metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.js"></script>
	<script type="text/javascript" src="${contextPath }metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>

	 
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS --> 
	<script src="${contextPath }metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/admin/pages/scripts/charts-amcharts.js"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
	<script src="${contextPath }metronic_v35/theme/assets/admin/pages/scripts/table-managed.js"></script>
	<!-- user js -->
	<script src="${contextPath }static/js/message/sysMonitor/sysOneNodeJvm.js"></script>
	<script src="${contextPath }static/js/message/sysMonitor/sysMonitorIndex.js"></script> 
 
 


	<script>
		/*页面功能*/
		jQuery(function() {
			var $w = $(".conright").width() - 20;
			$(".item_2").css({
				width : $w / 2 - 6
			});

			$("#js_sele").click(function() {
				if ($("#js_sel_box").css("display") == "block") {
					$("#js_sel_box").hide();
					$(this).removeClass("selbottom");
					return;
				}
				$(this).addClass("selbottom");
				$("#js_sel_box").show().find("li").hover(function() {
					$(this).addClass("highlighted");
				}, function() {
					$(this).removeClass("highlighted");
				}).click(function() {
					$("#js_sele span").text($(this).text());
					$("#js_sele").removeClass("selbottom");
					$("#js_sel_box").hide();
					var clickLiText=$(this).text();
		            clickLiText=clickLiText.substr(clickLiText.lastIndexOf("-")+1,2);
					window.location.href="sysMonitor.do?intoSysMonitorIndex&code="+clickLiText;
				});
			});

			$("#js_tab li").click(
					function() {
						var index = $(this).index();
						$("#js_tab li").removeClass("on");
						$(this).addClass("on");
						$(this).parents(".submenu_box").find(".subcenter")
								.eq(index).addClass("subcenter_db").siblings()
								.removeClass("subcenter_db");
						if(index==0){
							initMemChart();
							/*首先清空一下定时器*/
							jvmInterval=setInterval(initOneNodeJvm, 1000*60);
							clearInterval(threadInterval);
						}else if(index==1){

							initThreadChart();
							/*首先清空一下定时器*/
							var threadInterval=setInterval(initThreadChart, 1000*60);
							clearInterval(jvmInterval);
						}
					});
		});
		/**
		 * 页面初始化信息的定时器，1分钟执行一次
		 */

		initOneNodeJvm();	//init page all data
		var jvmInterval=setInterval(initOneNodeJvm, 1000*60);
	</script>
	

</body>
</html>
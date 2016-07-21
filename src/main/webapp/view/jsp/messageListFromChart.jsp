<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery-tags-input/jquery.tagsinput.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/typeahead/typeahead.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="<%=request.getContextPath() %>/static/css/metronic/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/static/css/metronic/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<!--bootstrap日期控件-->
<%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/datetimepicker/bootstrap-datetimepicker.css"/>--%>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/clockface/css/clockface.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>


<style type="text/css">
	.tdFloat{float: left;height: 26px; margin-left: 30px;}
</style>


<script type="text/javascript">


	
</script>
</head>




<!-- END THEME STYLES -->
<body class="page-header-fixed page-quick-sidebar-over-content page-style-square" > 
	<!-- BEGIN HEADER -->
<!-- END HEADER -->
	<input type="hidden" name="code" id="code" value="${mesAllReqCond.code }"/>
	<input type="hidden" name="codeType" id="codeType" value="${mesAllReqCond.codeType }"/>
	<input type="hidden" name="dateType" id="dateType" value="${mesAllReqCond.dateType }"/>
	<input type="hidden" name="currentTime" id="currentTime" value="${mesAllReqCond.currentTime }"/>
	<input type="hidden" name="processStatus" id="processStatus" value="${mesAllReqCond.processStatus }"/>
	<input type="hidden" name="errLevel" id="errLevel" value="${mesAllReqCond.errLevel }"/>
	<input type="hidden" name="contextPath" id="contextPath" value="${contextPath }"/>
	<input type="hidden" name="startRow" id="startRow" value=""/>
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN CONTENT -->
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			
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
						<a href="javascript:void(0);">异常信息列表</a>
					</li>
				</ul>

			</div>
			<!-- other row -->
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="table_box">
			            <div class="table_tit">
			                <div class="titname">
			                    <i class="fa fa-globe"></i>异常信息列表
			                </div>
			                <div class="actions">
			                   <button class='btn btn-info btn-xs' onclick='processMoreMes()' style="width:67px;height:30px;">批量处理</button>
			                </div>
			            </div>
			            <div class="portlet-body" style="height: 100%">

							<table width="100%"  class="table table-striped table-bordered table-hover">
								<%--<tr>--%>
									<%--<td>--%>
										<%--系统：--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--<select id="sysCode" name="sysCode" style="width:300px;" class="tdFloat"></select>--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--业务：--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--<select id="busiCode" name="busiCode" style="width:300px;" class="tdFloat"></select>--%>
									<%--</td>--%>
								<%--</tr>--%>
								<%--<tr>--%>
									<%--<td>--%>
										<%--异常等级：--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--<select id="errLevel" name="errLevel" style="width:300px;" class="tdFloat"></select>--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--处理状态：--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--<select id="processStatus" name="processStatus" style="width:300px;" class="tdFloat" ></select>--%>
									<%--</td>--%>
								<%--</tr>--%>
								<tr>
									<td>
										开始时间：
									</td>
									<td width="40%" align="center">
										<div style="width: 60%;">
											<div class="input-group date form_datetime tdFloat">
												<input type="text" size="16" readonly="" class="form-control" id="startTime" value="${ mesAllReqCond.startTime==null?'':mesAllReqCond.startTime }">
												<span class="input-group-btn">
												<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
											<!-- /input-group -->
										</div>

									</td>
									<td>
										结束时间:
									</td>
									<td width="40%" align="center">
										<div style="width: 60%;">
											<div class="input-group date form_datetime tdFloat">
												<input type="text" size="16" readonly="" class="form-control" id="endTime">
												<span class="input-group-btn">
												<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
											<!-- /input-group -->
										</div>
									</td>

								</tr>

								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td style="text-align: left;">
										<button onclick="getMessageListNoPage(null)" type="button"
												style="width: 50px; height: 26px; margin-left: 30px;"
												class="btn btn-info btn-xs">搜索
										</button>
								 	</td>
								</tr>
							</table>
							<div id="messageList_wrapper" class="dataTables_wrapper no-footer">
								<div class="row">
									<div class="col-md-6 col-sm-12">
									</div>
									<div class="col-md-6 col-sm-12">
									</div>
									<div id="messageList_processing" class="dataTables_processing" style="display: block;">
										Processing...
									</div>
								</div>
								<div class="table-scrollable">

									<table id="messageList" class="table table-striped table-bordered table-hover">
										<thead>
										<tr>
											<th>
												选择
											</th>
											<th>
												异常编号
											</th>
											<th>
												系统编码
											</th>
											<th>
												业务编码
											</th>
											<th>
												用户定义异常编码
											</th>
											<th>
												处理状态
											</th>
											<th>
												异常类型
											</th>
											<th>
												创建日期
											</th>
											<th>
												操作
											</th>
										</tr>

										</thead>
									</table>
								</div>
								<div class="row">
									<div class="col-md-5 col-sm-12"></div>
									<div class="col-md-7 col-sm-12">
										<div class="dataTables_paginate paging_simple_numbers" id="messageList_paginate">
											<ul class="pagination">
												<li class="paginate_button previous disabled" aria-controls="messageList" tabindex="0" id="messageList_previous"><a>前一页</a></li>
												<li class="paginate_button next" aria-controls="messageList" tabindex="0" id="messageList_next"><a>后一页</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
			            </div>
			        </div>
				</div>
				
			</div>
		</div>
</div>
<div id="mesDetail" class="modal fade" role="dialog" aria-hidden="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
											<h4 class="modal-title">详细信息</h4>
										</div>
										<div class="modal-body form">
											<form action="#" class="form-horizontal form-row-seperated">
												<div class="form-group">
													<label class="col-sm-4 control-label">系统码</label>
													<div class="col-sm-8">
														<p id="detail_sysCode" class="help-block">
															
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">业务码</label>
													<div class="col-sm-8">
														<p id="detail_busiCode" class="help-block">
															
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">业务描述</label>
													<div class="col-sm-8">
														<p id="detail_busiDesc" class="help-block">
															 
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">errCode</label>
													<div class="col-sm-8">
														<p id="detail_errCode" class="help-block">
															 
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">错误描述</label>
													<div class="col-sm-8">
														<p id="detail_errDesc" class="help-block">
															 
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">sysErrCode</label>
													<div class="col-sm-8">
														<p id="detail_sysErrCode" class="help-block">
															 
														</p>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">底层错误描述</label>
													<div class="col-sm-8">
														<p id="detail_sysErrDesc" class="help-block">
															 
														</p>
													</div>
												</div>
												<div class="form-group last">
													<label class="col-sm-4 control-label">异常详细信息</label>
													<div class="col-sm-8">
														<p id="detail_throwableDesc" class="help-block">
															
														</p>
													</div>
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											<!-- <button type="button" class="btn btn-primary"><i class="fa fa-check"></i> Save changes</button> -->
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

<!-- bootStrap 日期控件 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/datetimepicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/datetimepicker/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/static/js/message/messageListFromChart.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/datetimepicker/components-pickers.js"></script>

<script>
jQuery(document).ready(function() {       
   Metronic.init(); // init metronic core components
Layout.init(); // init current layout
QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
	ComponentsPickers.init();
});


</script>
</html>
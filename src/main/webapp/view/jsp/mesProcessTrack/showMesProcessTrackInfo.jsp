<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../initJs.jsp" %>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.0
Version: 3.5
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<meta charset="utf-8"/>
<title>Metronic | Data Tables - Editable Datatables</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="${contextPath }/metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }/metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }/metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${contextPath }metronic_v35/theme/assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath }metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${contextPath }static/css/metronic/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }static/css/metronic/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="${contextPath }metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="./metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->
<body>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<input type="hidden" name="contextPath" id="contextPath" value="${contextPath}"/>
<div class="page-container">
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->		
		<div class="page-content">
	        <h3 class="c_tit">
        	<span class="glyphicon glyphicon-th" aria-hidden="true"></span>
				异常·异常处理轨迹管理
				</h3>
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="javascript:void(0);">系统监控</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="javascript:void(0);">异常·异常处理轨迹管理</a>
						</li>
					</ul>
					
				</div>
				
	        <div class="table_box">
	            <div class="table_tit">
	                <div class="titname">
	                    <i class="fa fa-globe"></i>异常处理轨迹信息
	                </div>
	            </div>
	            <div class="portlet-body">
	                <table id="mesProcessTrack" class="table table-striped table-bordered table-hover">
	                    <thead>
	                    <tr>
	                        <th>
	                            编号
	                        </th>
	                        <th>
	                           异常编号
	                        </th>
	                        <th>
	                           系统
	                        </th>
	                        <th>
	                           用户编号
	                        </th>
	                         <th>
	                           处理状态
	                        </th>
	                        <th>
	                           描述
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
	        </div>
	    </div>

	<%--form--%>
<div id="mesProcessTrackListFormDlg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style=" width: 900px;" >  
        <div class="modal-content"> 
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">处理完成信息</h4> 
            </div> 
            <div class="portlet-body">
                <table id="mesProcessTrackByErrId" class="table table-striped table-bordered table-hover">
	                <thead> 
	                    <tr>
	                        <th>
	                            编号
	                        </th>
	                        <th>  
	                           异常编号
	                        </th>
	                        <th>
	                           用户编号
	                        </th>
	                         <th>
	                           处理状态
	                        </th>
	                        <th>
	                           描述
	                        </th>
	                        <th>
	                          创建日期
	                        </th>
	                        
	                    </tr>
	                    
	                    </thead>
	                </table>
            </div>
            <div class="modal-footer">
                
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<div id="mesProcessTrackFormDlg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">处理完成信息</h4>
            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>
                               异常编号
                            </th>
                            <td>
                                <input id="id" name="id" style="display: none" type="text"/>
                                <input id="errId" name="errId" type="text" style="width: 250px" readonly="readonly"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                              处理人编号
                            </th>
                            <td>
                                <input id="userId" name="userId" type="text" style="width: 250px" />
                                
                            </td>
                        </tr>
                        <tr>
                            <th>
                               处理状态
                            </th>
                            <td>
                            	<select class="form-control input-medium" name="processStatus" id="processStatus">
									<option value="0">未处理</option> 
									<option value="1">正在处理</option>
									<option value="2">处理完成</option>
								</select>
                            </td>
                        </tr>
                        <tr>
                            <th>
                              备注
                            </th>
                            <td>
                                <textarea class="form-control" rows="3" id="description" name="description"  ></textarea>
                            </td>
                        </tr>
                        </thead>
                    </table>
            </div>
            <div class="modal-footer">
            	<button id="btnSaveMesProcessTrack" type="button" class="btn blue">保存</button>
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
	<!-- END CONTENT -->
	<!-- BEGIN QUICK SIDEBAR -->
	<a href="javascript:;" class="page-quick-sidebar-toggler"><i class="icon-close"></i></a>
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
<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/respond.min.js"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${contextPath }metronic_v35/theme/assets/global/plugins/select2/select2.js"></script>
<script type="text/javascript" src="${contextPath }metronic_v35/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${contextPath }metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${contextPath }metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${contextPath }metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>


<script src="${contextPath }static/js/message/mesProcessTrack/showMesProcessTrackInfo.js"></script>

<script>
jQuery(document).ready(function() {       
   Metronic.init(); // init metronic core components
Layout.init(); // init current layout
QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
});
</script>
</body>
<!-- END BODY -->
</html>
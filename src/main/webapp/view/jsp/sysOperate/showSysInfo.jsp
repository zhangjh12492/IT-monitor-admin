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
<link href="${contextPath }metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="./metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<body>
<!-- BEGIN CONTAINER -->
	<!-- BEGIN CONTENT -->
	<div class="page-container">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
	        <h3 class="c_tit">
        	<span class="glyphicon glyphicon-th" aria-hidden="true"></span>
				业务·系统管理
				</h3>
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="javascript:void(0);">系统菜单管理</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="javascript:void(0);">业务·系统管理</a>
						</li>
					</ul>
					
				</div>
				
	        <div class="table_box">
	            <div class="table_tit">
	                <div class="titname">
	                    <i class="fa fa-globe"></i>系统信息
	                </div>
	                <div class="actions">
	                   <button id="sysInfoAdd" data-toggle="modal" data-target="#sysInfoFormDlg" class="btn btn-default btn-sm">
	                        <i class="fa fa-pencil"></i> 新增
	                    </button>
	                </div>
	            </div>
	            <div class="portlet-body" style="height: 100%">
	                <table id="sysInfo" class="table table-striped table-bordered table-hover">
	                    <thead>
	                    <tr>
	                        <th>
	                            编号
	                        </th>
	                        <th>
	                            代码
	                        </th>
	                        <th>
	                           名称
	                        </th>
	                        <th>
	                           描述
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
<div id="sysInfoFormDlg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">增加系统信息</h4>
            </div>
            <div class="portlet-body">
                <form id="sysInfoForm" method="post">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>
                                代码
                            </th>
                            <td>
                                <input id="id" name="id" style="display: none" type="text"/>
                                <input id="sysCode" name="sysCode" type="text" style="width: 200px"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                               名称
                            </th>
                            <td>
                                <input id="sysName" name="sysName" type="text" style="width: 200px"/>
                                
                            </td>
                        </tr>
                       
                        <tr>
                            <th>
                               描述
                            </th>
                            <td>
                               <!--  <input id="sysDesc" name="sysDesc" type="text" style="width: 200px"/> -->
                                <textarea class="form-control" rows="3" id="sysDesc" name="sysDesc" ></textarea>
                                
                            </td>
                        </tr>
                        </thead>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveSysInfo" type="button" class="btn blue">保存</button>
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


<script src="${contextPath }static/js/message/sysOperateJs/sysInfo.js"></script>

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
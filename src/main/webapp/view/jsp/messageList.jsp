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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/typeahead/typeahead.css">
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="<%=request.getContextPath() %>/static/css/metronic/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/static/css/metronic/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>


<script type="text/javascript">


	
</script>
</head>




<!-- END THEME STYLES -->
<body class="page-header-fixed page-quick-sidebar-over-content page-style-square" > 
	<!-- BEGIN HEADER -->
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<h3 class="page-title">
			业务     <small></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="index.html">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#">Dashboard</a>
					</li>
				</ul>

			</div>
			<!-- other row -->
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue-hoki">
			            <div class="portlet-title">
			                <div class="caption">
			                    <i class="fa fa-globe"></i>系统信息
			                </div>
			                <div class="actions">
			                   
			                </div>
			            </div>
			            <div class="portlet-body" style="height: 100%">
			            <input type="hidden" name="busiId" id="busiId" value="${busiId }"/>
			            <input type="hidden" name="messType" id="messType" value="${messType }"/>
			            
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
			                    </tr>
			                    
			                    </thead>
			                </table>
			            </div>
			        </div>
				</div>
				
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
<script src="<%=request.getContextPath() %>/static/js/message/messageList.js"></script>
<script>
jQuery(document).ready(function() {       
   Metronic.init(); // init metronic core components
Layout.init(); // init current layout
QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
   TableAdvanced.init();
});
</script>
</html>
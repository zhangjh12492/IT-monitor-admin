<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<title>right</title>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/> -->
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
<link href="<%=request.getContextPath() %>/static/css/metronic/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/static/css/metronic/layout.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>


<script type="text/javascript">
if (window.WebSocket) {
	console.log("This browser supports WebSocket!");
} else {
	console.log("This browser does not support WebSocket.");
}
var ws = new WebSocket("ws://192.168.6.197:8080/webSocketPro/webSocketServer");
ws.onopen = function() {
	ws.send("hello");
};
ws.onmessage = function(message) {
	//alert(message.data);
	var item=$.parseJSON(message.data);
	
	if(item!=undefined&&item!=null ){
		 if(item.sysCode=="01"){ 
			 var html="<span >异常:<a  href=\"javascript:findBusiMegBySysId("+item.id+",'error')\">"+item.sysErrCount +"</a></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>警告:<a href=\"javascript:findBusiMegBySysId("+item.id+",'warn')\">"+item.sysWarnCount+
			 "</a></span><input type=\"hidden\" value='"+item.id+"' id=\"\" />";
			 $("#order_center").html(html);
		 }else if(item.sysCode=="02"){
			 var html="异常:<a href=\"javascript:findBusiMegBySysId("+item.id+",'error')\">"+item.sysErrCount +"</a>&nbsp;&nbsp;&nbsp;&nbsp;警告:<a href=\"javascript:findBusiMegBySysId("+item.id+",'warn')\">"+item.sysWarnCount+
			 "</a><input type=\"hidden\" value='"+item.id+"' id=\"\" />";
			 $("#product_center").html(html);
		 }else if(item.sysCode=="03"){
			 var html="异常:<a href=\"javascript:findBusiMegBySysId("+item.id+",'error')\">"+item.sysErrCount +"</a>&nbsp;&nbsp;&nbsp;&nbsp;警告:<a href=\"javascript:findBusiMegBySysId("+item.id+",'warn')\">"+item.sysWarnCount+
			 "</a><input type=\"hidden\" value='"+item.id+"' id=\"\" />";
			 $("#product_cust_ser").html(html);
		 }
	}
};
function postToServer() {
	ws.send(document.getElementById("msg").value);
	document.getElementById("msg").value = "";
}
function closeConnect() {
	ws.close();
}
function initSysMes(){
		$.ajax({
			 url:'initSysData',
			 type:'post',    
			 data:{sysCode:null},
			 cache:false,
			 async:false,
			 timeout:1000*60,   
			 success:function(data){
				 data=$.parseJSON(data);
				 $.each(data,function(index,item){
					 if(item.sysCode=="01"){ 
						 var html="<span >异常:<a  href=\"javascript:findBusiMegBySysId("+item.id+",'error')\">"+item.sysErrCount +"</a></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>警告:<a href=\"javascript:findBusiMegBySysId("+item.id+",'warn')\">"+item.sysWarnCount+
						 "</a></span><input type=\"hidden\" value='"+item.id+"' id=\"\" />";
						 $("#order_center").html(html);
					 }else if(item.sysCode=="02"){
						 var html="异常:<a href=\"javascript:findBusiMegBySysId("+item.id+",'error')\">"+item.sysErrCount +"</a>&nbsp;&nbsp;&nbsp;&nbsp;警告:<a href=\"javascript:findBusiMegBySysId("+item.id+",'warn')\">"+item.sysWarnCount+
						 "</a><input type=\"hidden\" value='"+item.id+"' id=\"\" />";
						 $("#product_center").html(html);
					 }else if(item.sysCode=="03"){
						 var html="异常:<a href=\"javascript:findBusiMegBySysId("+item.id+",'error')\">"+item.sysErrCount +"</a>&nbsp;&nbsp;&nbsp;&nbsp;警告:<a href=\"javascript:findBusiMegBySysId("+item.id+",'warn')\">"+item.sysWarnCount+
						 "</a><input type=\"hidden\" value='"+item.id+"' id=\"\" />";
						 $("#product_cust_ser").html(html);
					 }
				 }); 
			 },error:function(){
				 alert("error");
			 }
		});
	}
	
	
function findBusiMegBySysId(id,messType){
	window.location.href="admin/findBusiMesBySysId.do?id="+id+"&messType="+messType;
}	

	
</script>
</head>
<body>
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
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler">
				</div>
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
						THEME COLOR </span>
						<ul>
							<li class="color-default current tooltips" data-style="default" data-container="body" data-original-title="Default">
							</li>
							<li class="color-darkblue tooltips" data-style="darkblue" data-container="body" data-original-title="Dark Blue">
							</li>
							<li class="color-blue tooltips" data-style="blue" data-container="body" data-original-title="Blue">
							</li>
							<li class="color-grey tooltips" data-style="grey" data-container="body" data-original-title="Grey">
							</li>
							<li class="color-light tooltips" data-style="light" data-container="body" data-original-title="Light">
							</li>
							<li class="color-light2 tooltips" data-style="light2" data-container="body" data-html="true" data-original-title="Light 2">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>
						Theme Style </span>
						<select class="layout-style-option form-control input-sm">
							<option value="square" selected="selected">Square corners</option>
							<option value="rounded">Rounded corners</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Layout </span>
						<select class="layout-option form-control input-sm">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Header </span>
						<select class="page-header-option form-control input-sm">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Top Menu Dropdown</span>
						<select class="page-header-top-dropdown-style-option form-control input-sm">
							<option value="light" selected="selected">Light</option>
							<option value="dark">Dark</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar Mode</span>
						<select class="sidebar-option form-control input-sm">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar Menu </span>
						<select class="sidebar-menu-option form-control input-sm">
							<option value="accordion" selected="selected">Accordion</option>
							<option value="hover">Hover</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar Style </span>
						<select class="sidebar-style-option form-control input-sm">
							<option value="default" selected="selected">Default</option>
							<option value="light">Light</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar Position </span>
						<select class="sidebar-pos-option form-control input-sm">
							<option value="left" selected="selected">Left</option>
							<option value="right">Right</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Footer </span>
						<select class="page-footer-option form-control input-sm">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			系统123     <small>reports & statistics</small>
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
				<div class="page-toolbar">
					<div id="dashboard-report-range" class="pull-right tooltips btn btn-fit-height grey-salt" data-placement="top" data-original-title="Change dashboard date range">
						<i class="icon-calendar"></i>&nbsp;
						<span class="thin uppercase visible-lg-inline-block">&nbsp;</span>&nbsp;
						<i class="fa fa-angle-down"></i>
					</div>
				</div>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN DASHBOARD STATS -->
			<!-- warning -->
			
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow-saffron">
						<div class="visual">
							<i class="fa fa-comments">订单中心</i>
						</div>
						<div class="details">
							<div class="number" id="order_center">
								 
							</div>
							
							<div class="desc">
								 订单中心
							</div>
						</div>
						<a class="more" href="javascript:findBusiMegBySysId('order_center_warn')" >
						View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow-casablanca">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="number" id="product_center">
								 
							</div>
							<div class="desc">
								 商品中心
							</div>
						</div>
						<a class="more" href="javascript:findBusiMegBySysId('product_center_warn')" >
						View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
					<div class="dashboard-stat green-meadow">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="number" id="product_cust_ser">
								 
							</div>
							<div class="desc">
								 客服中心
							</div>
						</div>
						<a class="more" href="javascript:findBusiMegBySysId('product_cust_ser_warn')" >
						View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
					<div class="dashboard-stat purple-studio">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="number">
								 
							</div>
							<div class="desc">
								 营销中心
							</div>
						</div>
						<a class="more" href="#">
						View more <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
			</div>
			<!-- other row -->
			<!-- error -->
			
			
			
			
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
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/pages/scripts/table-advanced.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>


<script>
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   QuickSidebar.init(); // init quick sidebar
   Demo.init(); // init demo features 
   Index.init();   
   Index.initDashboardDaterange();
   Index.initJQVMAP(); // init index page's custom scripts
   Index.initCalendar(); // init index page's custom scripts
   Index.initCharts(); // init index page's custom scripts
   Index.initChat();
   Index.initMiniCharts();
   Tasks.initDashboardWidget();
});
</script>
</html>
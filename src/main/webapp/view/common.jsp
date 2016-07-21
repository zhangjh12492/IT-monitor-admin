<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="<%=path%>/metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="<%=path%>/metronic_v35/theme/assets/admin/pages/css/login.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="<%=path%>/metronic_v35/theme/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="<%=path%>/metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->



<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/respond.min.js"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js"
        type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=path%>/metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="<%=path%>/metronic_v35/theme/assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

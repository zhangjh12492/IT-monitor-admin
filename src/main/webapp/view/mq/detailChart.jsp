<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <meta charset="utf-8"/>
    <title>Metronic | Data Tables - Advanced Datatables</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->

    <link href="./metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="./metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="./metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="./metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet"
          type="text/css"/>
    <link href="./metronic_v35/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
          rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="./metronic_v35/theme/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css"
          href="./metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="./metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="./metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="./metronic_v35/theme/assets/global/css/components.css" id="style_components" rel="stylesheet"
          type="text/css"/>
    <link href="./metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="./metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="./metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet"
          type="text/css"/>
    <link href="./metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="favicon.ico"/>
</head>

<body>
<div class="page-container">
    <div class="page-content">
        <h3 class="c_tit">
            <span class="glyphicon glyphicon-th" aria-hidden="true"></span>
            详细分析图
            <%--<small>advanced datatables</small>--%>
        </h3>
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="fa fa-home"></i>
                    <a href="#">消息平台监控</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <a href="#">详细分析图</a>
                    <i class="fa fa-angle-right"></i>
                </li>
            </ul>            
        </div>
        <div class="table_box">
            <div class="table_tit broder_r">
                <div class="titname">
                    <i class="fa fa-globe"></i>队列消息详细信息
                </div>
            </div>
            <div class="portlet-body" style="height: 450px">
                <table id="group" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>队列名称</th>
                        <th>状态</th>
                        <th>消息数</th>
                        <th>等待数</th>
                        <th>未收到消息数</th>
                        <%--<th>传输速度</th>--%>
                        <th>持久化</th>
                        <th>自动删除</th>
                        <th>占用内存</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
        <div class="table_box">
            <div class="table_tit broder_r">
                <div class="titname">
                    <i class="fa fa-globe"></i>队列占比分析图
                </div>
            </div>
            <div class="panel-body">
                <div id="pieChart" class="chart"></div>
            </div>
        </div>
        <div class="table_box">
            <div class="table_tit broder_r">
                <div class="titname">
                    <i class="fa fa-globe"></i>队列消息走势图
                </div>
            </div>
            <div class="panel-body">
                <div id="barChart" class="chart"></div>
            </div>
        </div>
    </div>
</div>

<!--[if lt IE 9]>
<script src="./metronic_v35/theme/assets/global/plugins/respond.min.js"></script>
<script src="./metronic_v35/theme/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="./metronic_v35/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="./metronic_v35/theme/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
        type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
        type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="./metronic_v35/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
        src="./metronic_v35/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
        src="./metronic_v35/theme/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js"></script>
<script type="text/javascript"
        src="./metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js"></script>
<script type="text/javascript"
        src="./metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js"></script>
<script type="text/javascript"
        src="./metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="./metronic_v35/theme/assets/global/plugins/flot/jquery.flot.all.min.js"></script>
<script src="./metronic_v35/theme/assets/global/plugins/flot/jquery.flot.min.js"></script>
<script src="./metronic_v35/theme/assets/global/plugins/flot/jquery.flot.resize.min.js"></script>
<script src="./metronic_v35/theme/assets/global/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="./metronic_v35/theme/assets/global/plugins/flot/jquery.flot.stack.min.js"></script>
<script src="./metronic_v35/theme/assets/global/plugins/flot/jquery.flot.crosshair.min.js"></script>
<script src="./metronic_v35/theme/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="./metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/admin/pages/scripts/table-advanced.js"></script>
<%--<script src="./metronic_v35/theme/assets/admin/pages/scripts/charts-flotcharts.js"></script>--%>
<script type="text/javascript" src="<%=basePath%>/js/mq/detailChart.js"></script>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/amcharts/serial.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/amcharts/pie.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/amcharts/radar.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/amcharts/themes/light.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/amcharts/themes/patterns.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/amcharts/themes/chalk.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/ammap/ammap.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/ammap/maps/js/worldLow.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/global/plugins/amcharts/amstockcharts/amstock.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script>
    jQuery(document).ready(function() {
        // initiate layout and plugins
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
//        ChartsAmcharts.init(); // init demo charts
    });
</script>


</body>
<!-- END BODY -->
</html>
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
        String basePathVal = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
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
<%--<body class="page-header-fixed page-quick-sidebar-over-content ">--%>
<body>
<!-- BEGIN HEADER -->
<!-- END HEADER -->
<input id="groupId" style="display: none"/>
<!-- BEGIN CONTAINER -->
<div id="menu_1" style="display: none">

</div>
<div id="menu_2" style="display: none">

</div>

<div class="page-container">
    <div class="page-content">
        <h3 class="c_tit">
            <span class="glyphicon glyphicon-th" aria-hidden="true"></span>
            角色管理
            <%--<small>advanced datatables</small>--%>
        </h3>

        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="fa fa-home"></i>
                    <a href="index.html">系统管理</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <a href="#">角色管理</a>
                </li>

            </ul>
        </div>
        <div class="table_box" style="height: 100%">
            <div class="table_tit broder_r">
                <div class="titname">
                    <i class="fa fa-globe"></i>角色管理
                </div>
                <div class="actions">
                    <button id="menuAdd" data-toggle="modal" data-target="#groupFrom" class="btn btn-default btn-sm">
                        <i class="fa fa-pencil"></i> 新增
                    </button>
                </div>
            </div>
            <div class="portlet-body">
                <table id="group" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>
                            角色名称
                        </th>
                        <th>
                            描述
                        </th>
                        <th>
                            排序
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
</div>
<%--一级菜单--%>
<div id="groupFrom" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div id="groupDialog" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">增加一级菜单</h4>
            </div>
            <div class="portlet-body">
                <form id="groupForm" method="post">
                    <ul class="groupList">
                        <li>
                            <label>名称</label>
                            <input id="id" name="id" style="display: none" type="text"/>
                            <input id="text" name="text" type="text"/>
                        </li>
                        <li>
                            <label>排序</label>
                            <input type="text" id="seq" name="seq" onkeyup="this.value=this.value.replace(/\D/g,'')"
                                   onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                        </li>
                        <li>
                            <label>描述</label>
                            <input type="text" id="descript" name="descript"/>
                        </li>
                    </ul>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveMenu_1" type="button" class="btn blue">保存</button>
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
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
<script src="./metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="./metronic_v35/theme/assets/admin/pages/scripts/table-advanced.js"></script>
<script type="text/javascript" src="<%=basePathVal%>/admin/js/role_2.js"></script>
<script>
    /*
     jQuery(document).ready(function () {
     Metronic.init(); // init metronic core components
     Layout.init(); // init current layout
     QuickSidebar.init(); // init quick sidebar
     Demo.init(); // init demo features
     TableAdvanced.init();
     });
     */
</script>

</body>
<!-- END BODY -->
</html>
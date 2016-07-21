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

    <link href="../metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="../metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="../metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="../metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet"
          type="text/css"/>
    <link href="../metronic_v35/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
          rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="../metronic_v35/theme/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css"
          href="../metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="../metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="../metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="../metronic_v35/theme/assets/global/css/components.css" id="style_components" rel="stylesheet"
          type="text/css"/>
    <link href="../metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="../metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="../metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet"
          type="text/css"/>
    <link href="../metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<%--<body class="page-header-fixed page-quick-sidebar-over-content ">--%>
<body>
<!-- BEGIN HEADER -->
<!-- END HEADER -->
<input id="dicNameVal" style="display: none"/>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <div class="page-content">
        <h3 class="page-title">
            系统参数管理12
            <%--<small>advanced datatables</small>--%>
        </h3>
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="fa fa-home"></i>
                    <a href="index.html">消息平台管理</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <a href="#">系统参数管理</a>
                </li>

            </ul>
        </div>
        <%--
        <div class="row">
        <div class="col-md-12">
        --%>
        <div class="portlet box blue-hoki" style="float: left; width: 40%; height: 100%">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>字典名称
                </div>
            </div>
            <div class="portlet-body" style="height: 100%">
                <table id="group" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>
                            编号
                        </th>
                        <th>
                            字典名称
                        </th>
                        <th>
                            字典描述
                        </th>
                        <%--
                                                <th>
                                                    类型
                                                </th>
                        --%>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>

        <div class="portlet box blue-hoki" style="float: left; width: 60%; height: 100%">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-globe"></i>字典代码
                </div>
                <div class="actions">
                    <button id="userAdd" data-toggle="modal" data-target="#groupDlg" class="btn btn-default btn-sm">
                        <i class="fa fa-pencil"></i> 新增
                    </button>
                </div>
            </div>
            <div class="portlet-body" style="height: 100%">
                <%--
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="btn-group">
                                        <button id="sample_editable_1_new" class="btn green">
                                            新增 <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                --%>
                <table id="users" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>
                            编号
                        </th>
                        <th>
                            字典项目
                        </th>
                        <th>
                            字典值
                        </th>
                        <th>
                            字典名称
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>

        </div>
        <!-- END EXAMPLE TABLE PORTLET-->
        <!-- BEGIN EXAMPLE TABLE PORTLET-->
        <%--</div>--%>
        <%--</div>--%>
        <!-- END PAGE CONTENT-->
    </div>
</div>
<!-- END CONTENT -->
<!-- BEGIN QUICK SIDEBAR -->

<%--组form--%>
<div id="groupDlg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div id="groupDialog" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">增加字典</h4>
            </div>
            <div class="portlet-body">
                <form id="groupForm" method="post">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>
                                字典名称
                            </th>
                            <td>
                                <input id="sid" name="sid" style="display: none" type="text"/>
                                <input id="dicName" name="dicName" type="text" style="width: 200px" readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <th>
                                字典项
                            </th>
                            <td>
                                <input type="text" id="dicItem" name="dicItem" style="width: 200px"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                字典值
                            </th>
                            <td>
                                <input type="text" id="dicValue" name="dicValue" style="width: 200px"/>
                            </td>
                        </tr>
                        </thead>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSaveUser" type="button" class="btn blue">保存</button>
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<%--easyui组form--%>
<%--
<div id="groupDlg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div id="groupDialog_easyui" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">增加人员</h4>
            </div>
            <div>
                <div id="toolbar" class="stockIn_datagrid-toolbar" style="height: 60px;">
                </div>
                <table id="userTable_easyui"></table>
            </div>
            <div class="modal-footer">
                <button id="btnSaveUser" type="button" class="btn blue">保存</button>
                <button type="button" class="btn default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
--%>

<!--[if lt IE 9]>
<script src="../metronic_v35/theme/assets/global/plugins/respond.min.js"></script>
<script src="../metronic_v35/theme/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="../metronic_v35/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="../metronic_v35/theme/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
        type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
        type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="../metronic_v35/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
        src="../metronic_v35/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
        src="../metronic_v35/theme/assets/global/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js"></script>
<script type="text/javascript"
        src="../metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js"></script>
<script type="text/javascript"
        src="../metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/js/dataTables.scroller.min.js"></script>
<script type="text/javascript"
        src="../metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../metronic_v35/theme/assets/admin/pages/scripts/table-advanced.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/mq/outbound.js"></script>
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


<link rel="stylesheet" type="text/css" href="./../js/jquery-easyui-1.3.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="./../js/jquery-easyui-1.3.2/themes/icon.css">
<%--<script type="text/javascript" src="./../js/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>--%>
<script type="text/javascript" src="./../js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>


</body>
<!-- END BODY -->
</html>
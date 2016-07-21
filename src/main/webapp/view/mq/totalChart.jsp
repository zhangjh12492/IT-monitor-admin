<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>字典维护</title>
    <link rel="stylesheet"
          href="${ctx}/js/bootstrap3/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet"
          href="${ctx}/js/bootstrap3/css/font-awesome.min.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="${ctx}/js/bootstrap3/css/ionicons.min.css" type="text/css"/>
    <link rel="stylesheet"
          href="${ctx}/js/AdminLTE/css/jQueryUI/jquery-ui-1.10.3.custom.min.css"/>
    <link rel="stylesheet" href="${ctx}/js/bootstrap3/css/AdminLTE.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="${ctx}/js/bootstrap3/css/datatables/dataTables.bootstrap.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="${ctx}/js/datatables/css/jquery.dataTables.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="${ctx}/js/jquery-tagEditor/jquery.tag-editor.css"
          type="text/css"/>
    <link rel="stylesheet" href="${ctx}/js/validform/css/style.css"
          type="text/css" media="all"/>
    <link href="./metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">
        .add_form {
            margin: 10px;
            height: 30px;
            width: 150px;
        }

        th, td {
            white-space: nowrap;
        }

        div.dataTables_wrapper {
            margin: 0 auto;
        }

        .display {
            text-align: left;
            margin-left: 15px;
        }
    </style>

</head>

<body>
<div class="page-container">
    <div class="page-content">
        <h3 class="c_tit">
            <span class="glyphicon glyphicon-th" aria-hidden="true"></span>
            队列总体监控
            <%--<small>advanced datatables</small>--%>
        </h3>

        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="fa fa-home"></i>
                    <a href="#">Home</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <a href="#">队列监控</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <a href="#">总体消息</a>
                </li>
            </ul>
        </div>
        <!-- Main content -->
        <div class="table_box">
            <div class="table_tit broder_r">
                <div class="titname">
                    <i class="fa fa-globe"></i>队列消息详细信息
                </div>
            </div>
            <div class="portlet-body">
                <table class="table">
                    <tr>
                        <th colspan="6">连接信息</th>
                    </tr>
                    <tr>
                        <td width="100px;">connections:</td>
                        <td id="con_id">0</td>
                        <td>channels:</td>
                        <td id="chan_id" colspan="2">0</td>
                    </tr>
                    <tr>
                        <td>exchanges:</td>
                        <td id="ex_id">0</td>
                        <td>queues:</td>
                        <td id="qu_id">0</td>
                        <td>consumers:</td>
                        <td id="qu_id">0</td>
                    </tr>
                    <tr>
                        <th colspan="6">队列信息</th>
                    </tr>
                    <tr>
                        <td>messages:</td>
                        <td id="mes_id">0</td>
                        <td>ready:</td>
                        <td id="rea_id">0</td>
                        <td>unacknowledged:</td>
                        <td id="un_id">0</td>
                    </tr>
                    <tr>
                        <th colspan="6">消息信息</th>
                    </tr>
                    <tr>
                        <td>publish:</td>
                        <td id="pub_id">0</td>
                        <td>ack:</td>
                        <td id="ack_id">0</td>
                        <td>deliver:</td>
                        <td id="del_id">0</td>
                    </tr>
                    <tr>
                        <td>redeliver:</td>
                        <td id="red_id">0</td>
                        <td>deliver_get:</td>
                        <td id="deget_id">0</td>
                        <td>get:</td>
                        <td id="get_id">0</td>
                    </tr>
                    <tr>
                        <td>get_no_ack:</td>
                        <td id="getno_id" colspan="5">0</td>
                    </tr>
                </table>
            </div>

            <div class="table_box">
                <div class="table_tit">
                    <div class="titname">
                        <i class="fa fa-globe"></i>队列消息动态曲线图
                    </div>
                </div>
            </div>
            <div class="portlet-body">
                <div id="totalChart"/>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript"
        src="${ctx}/js/jquery/jquery2.1.3.min.js"></script>
<script type="text/javascript"
        src="${ctx}/js/bootstrap3/js/jquery-ui-1.10.3.min.js"></script>
<script type="text/javascript"
        src="${ctx}/js/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-tagEditor/jquery.caret.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-tagEditor/jquery.tag-editor.js"></script>
<script type="text/javascript"
        src="${ctx}/js/datatables/jquery.dataTables.js"></script>
<script type="text/javascript"
        src="${ctx}/js/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript"
        src="${ctx}/js/bootstrap3/js/app.js"></script>
<script type="text/javascript"
        src="${ctx}/js/jquery/jquery.json-2.4.min.js"></script>
<script type="text/javascript"
        src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
        src="${ctx}/js/lhgdilhgdialogalog/lhgdialog.min.js"></script>
<script type="text/javascript"
        src="${ctx}/js/validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript"
        src="${ctx}/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/js/js/home.js"></script>
<script type="text/javascript">
    var basepath = '${ctx}';
    //var permit=${sessionScope.PERMISSIONS};
    var menu =${sessionScope.MENUS};
    var menuStatus =${sessionScope.menuStatus};
</script>
<script type="text/javascript" src="${ctx}/js/mq/totalChart.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>队列消息监控</title>

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
    <style type="text/css">

        #detail_tab {
            text-align: center;
        }
    </style>
</head>

<body>
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>队列消息监控</h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">队列监控</a></li>
        <li class="active">消息监控</li>
    </ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div id="parent_chart" class="box">
                <div class="box-header"></div>

                <div class="box-body table-responsive"
                     style="overflow: hidden; overflow-x: hidden;width:100%">
                    <div class="row">
                        <div class="row">
                            <div class="col-md-12">
                                <div id="child1_chart" class="panel panel-default">
                                    <div class="panel-heading" style="height: 40px;">
									<span class="glyphicon glyphicon-book" aria-hidden="true">
									         队列消息详细信息
									</span>
                                    </div>
                                    <div class="panel-body">
                                        <table id="detail_tab" class="display" cellspacing="0">
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
                                            <tbody></tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div id="child2_chart" class="panel panel-default">
                                <div class="panel-heading" style="height: 40px; width: 100%">
									<span class="glyphicon glyphicon-stats" aria-hidden="true">
									  队列占比分析图
									</span>
                                </div>
                                <div class="panel-body">
                                    <div id="pieChart"></div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div id="child_chart" class="panel panel-default">
                                    <div class="panel-heading" style="height: 40px;">
									<span class="glyphicon glyphicon-stats" aria-hidden="true">
									  队列详细分析图
									</span>
                                    </div>
                                    <div class="panel-body">
                                        <div id="columnChart"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>
<!-- <div  id="win_dialog" class="progress progress-striped active">
   <div id="pro_dialog" class="progress-bar progress-bar-success" role="progressbar" 
      aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 
      style="width:10%;">
      <span class="sr-only"></span>
   </div>
</div> -->
<script type="text/javascript"
        src="${ctx}/js/jquery/jquery2.1.3.min.js"></script>
<script type="text/javascript"
        src="${ctx}/js/bootstrap3/js/jquery-ui-1.10.3.min.js"></script>
<script type="text/javascript"
        src="${ctx}/js/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-tagEditor/jquery.caret.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-tagEditor/jquery.tag-editor.js"></script>
<script type="text/javascript"
        src="${ctx}/js/AdminLTE/js/jquery-ui-1.10.3.min.js"></script>
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
<script type="text/javascript">
    var basepath = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}/js/mq/detailChart.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
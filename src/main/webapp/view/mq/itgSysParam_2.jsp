<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典维护</title>
</head>

<body>
	<!-- Content Header (Page header) -->
	<section class="content-header">
	<h1>字典配置</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="#">配置管理</a></li>
		<li class="active">字典配置</li>
	</ol>
	</section>
	<!-- Main content -->
	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div id="parent_dic" class="box">
				<div class="box-header"></div>

				<div  class="box-body table-responsive"
					style="overflow: hidden; overflow-x: hidden;width:100%">
					<div class="row">
						<div class="col-md-5">
							<div id="child1_dic" class="panel panel-default">
								<div class="panel-heading" style="height: 40px;">
									<span class="glyphicon glyphicon-book" aria-hidden="true"></span>
								</div>
								<div class="panel-body">
									<table id="grid_dicName" class="display" cellspacing="0">
										<thead>
											<tr>
												<th>序号</th>
												<th>字典名称</th>
												<th>字典描述</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>

						<div class="col-md-7">
							<div id="child2_dic" class="panel panel-default">
								<div class="panel-heading" style="height: 40px;">
									<span class="glyphicon glyphicon-wrench" aria-hidden="true">
										<a style="margin-bottom: 5px;"
										onclick="updateList('add','null')"> 新建 </a>
									</span>

									<!--  <a style="margin-bottom:5px;"  onclick="delList()">删除</a> -->
								</div>
								<div class="panel-body">
									<table id="grid-sysParam" style="nowrap: true" class="display"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>序号</th>
												<th>字典名称</th>
												<th>字典项</th>
												<th>字典值</th>
												<th>数值</th>
												<th>状态</th>
												<th>操作项</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<input type="hidden" id="default" />

	<div id="sysParam_dialog" style="display: none;">
		<div class="panel-body">
			<form id="sysParam_form" id="form1">
				<input type="hidden" id="sid" name="sid" />
				<table class="bootstrap">
					<tr>
						<td width="80px"><span>字典名称</span></td>
						<td><input id="dicName" datatype="*" nullmsg="请输入字典名称！"
							errormsg="请输入字典名称！" readonly="readonly" class="add_form"
							name="dicName" /> <span class="Validform_checktip"></span></td>
					</tr>

					<tr>
						<td><font>字典项</font></td>
						<td><input id="dicItem" datatype="*" nullmsg="请输入字典项！"
							errormsg="请输入字典项！" class="add_form" name="dicItem" /> <span
							class="Validform_checktip"></span></td>
					</tr>
					<tr id="dic_val">
						<td><font>字典值</font></td>
						<td><input id="dicValue" datatype="*" nullmsg="请输入字典值！"
							errormsg="请输入字典值！" class="add_form" name="dicValue" /> <span
							class="Validform_checktip"></span></td>
					</tr>
					<tr id="dic_num">
						<td><font>数值</font></td>
						<td><input id="dicValueSecond" datatype="n1-10" nullmsg="请输入数字！"
							errormsg="请输入数字！" class="add_form" name="dicValueSecond" /> <span
							class="Validform_checktip"></span></td>
					</tr>
					<tr>
						<td><font>状态</font></td>
						<td><select id="status" datatype="*" nullmsg="请选择状态！"
							errormsg="请选择状态！" class="add_form" name="status">
								<option value="">请选择</option>
								<option value="0">禁用</option>
								<option value="1">启用</option>
						</select> <span class="Validform_checktip"></span></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript"
		src="${ctx}/js/mq/itgSysParam.js"></script>
</body>
</html>
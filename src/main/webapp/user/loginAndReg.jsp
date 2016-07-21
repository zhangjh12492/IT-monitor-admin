<%@ page language="java" pageEncoding="UTF-8"%>

<div id="loginAndRegDialog" style="width: 250px;height: 170px;display: none;">
	<div id="loginTabs">
		<div title="输入方式" align="center" style="overflow: hidden;padding: 5px;">
			<form id="loginInputForm" method="post">
				<table class="tableForm">
					<tr>
						<th>登录名</th>
						<td><input name="name" class="easyui-validatebox" required="true" value="" />
						</td>
					</tr>
					<tr>
						<th>密码</th>
						<td><input name="password" type="password" class="easyui-validatebox" required="true" value="" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<div id="regDialog" style="width:250px;display: none;padding: 5px;" align="center">
	<form id="regForm" method="post">
		<table class="tableForm">
			<tr>
				<th>登录名</th>
				<td><input name="name" class="easyui-validatebox" required="true" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="password" type="password" class="easyui-validatebox" required="true" />
				</td>
			</tr>
			<tr>
				<th>重复密码</th>
				<td><input name="rePassword" type="password" class="easyui-validatebox" required="true" validType="eqPassword['#regForm input[name=password]']" />
				</td>
			</tr>
		</table>
	</form>
</div>
<script src="<%=request.getContextPath()%>/js/syLoginPup.js" charset="UTF-8" type="text/javascript"></script>
var dictObj;
$(function() {
	var a = $(window).height();
	var b = $("#parent_dic").offset().top;
	$("#parent_dic").css("height", a - b - 40);
	var c = $("#child1_dic").offset().top;
	$("#child1_dic").css("height", a - c - 50);
	$("#child2_dic").css("height", a - c - 50);
	var scroll = (a - c) * 0.6;
	getDicNameList(scroll);
});
var i = 0;
function getDicNameList(s) {
	var dicName_grid = $("#grid_dicName")
			.dataTable(
					{
						"scrollY" : s,
						// "scrollCollapse":true,
						"paging" : false,
						"bDestory" : true,
						"sAjaxSource" : basepath + '/sysParam/queryList',
						"bPaginate" : false, // 是否分页
						"bLengthChange" : false,// 是否允许自定义每页显示条数.
						'bProcessing' : true, // 当datatable获取数据时候是否显示正在处理提示信息。
						'bServerSide' : false, // 服务端是否分页
						'iDisplayLength' : 10, // 每页显示10条记录
						// "sPaginationType": "bootstrap", //分页样式 full_numbers
						"sServerMethod" : "POST",
						"bFilter" : false,
						"bSort" : true,
						"bInfo" : true,
						"bAutoWidth" : true,
						// "sDom": "<'wrap'flipt>",
						"oLanguage" : {
							"sLengthMenu" : "每页显示 _MENU_ 条记录",
							"sProcessing" : "<img src='" + basepath
									+ "/plug-in/images/loading.gif' />",// 加载gif

							"sZeroRecords" : "<span style='color:red;'>没有符合条件的记录......</span>",
							"oPaginate" : {
								"sFirst" : "首页",
								"sPrevious" : "上一页 ",
								"sNext" : "下一页 ",
								"sLast" : "尾页"
							},
							"sInfoEmpty" : "显示<b style='color:red;'> _START_ </b>到<b style='color:red;'> _END_ </b>条记录",
							"sInfo" : "显示<b style='color:red;'> _START_ </b>到<b style='color:red;'> _END_ </b>条记录/共<b style='color:red;'> _TOTAL_</b>条记录"
						},

						// 对应列表中显示的字段
						"aoColumnDefs" : [ {
							"bSortable" : false,
							"aTargets" : [ 0 ]
						}, {
							"mData" : null,
							"sWidth" : "40px",
							"aTargets" : [ 0 ]
						}, {
							"mDataProp" : "dicName",
							"sWidth" : "100px",
							"aTargets" : [ 1 ]

						}, {
							"mDataProp" : "dicDesc",
							"sWidth" : "100px",
							"aTargets" : [ 2 ]
						} ],

						"fnRowCallback" : function(nRow, aData, iDisplayIndex,
								iDisplayIndexFull) {
							$('td:eq(0)', nRow).html(iDisplayIndex + 1);
							/*
							 * var group_val=''; if (groupObj) {
							 * $.each(groupObj, function(index, value) { if
							 * (value.sid == aData['groupSid']) { group_val =
							 * value.groupName; } }); }
							 */
							// $('td:eq(1)', nRow).html("<a class='btn btn-info
							// btn-xs' onclick='updateList(\"update\",\""
							// + aData['sid'] + "\")'>编辑</a>");
						}
					});

	$('#grid_dicName').on('click', 'tbody tr', function() {
		// 设置选中样式
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			dicName_grid.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}

		var sTitle;
		var nTds = $('td', this);
		var dicName = $(nTds[1]).text();
		$("#default").val(dicName);
		$('#grid-sysParam').dataTable().fnDestroy();
		getItemList(s);
	});

}

function getItemList(s) {

	var dicItem_grid = $("#grid-sysParam")
			.dataTable(
					{
						"scrollY" : s,
						// "scrollX":true,
						"paging" : false,
						"bDestory" : true,
						"sAjaxSource" : basepath + '/sysParam/itemList',
						"bPaginate" : false, // 是否分页
						"bLengthChange" : false,// 是否允许自定义每页显示条数.
						'bProcessing' : true, // 当datatable获取数据时候是否显示正在处理提示信息。
						'bServerSide' : false, // 服务端是否分页
						'iDisplayLength' : 10, // 每页显示10条记录
						"sPaginationType" : "bootstrap", // 分页样式 full_numbers
						"sServerMethod" : "POST",
						"bFilter" : false,
						"bSort" : true,
						"bInfo" : true,
						"bAutoWidth" : true,
						// "sDom": "<'wrap'flipt>",
						"oLanguage" : {
							"sLengthMenu" : "每页显示 _MENU_ 条记录",
							"sProcessing" : "<img src='" + basepath
							+ "/plug-in/images/loading.gif' />",// 加载gif
							"sZeroRecords" : "<span style='color:red;'>没有符合条件的记录......</span>",
							"oPaginate" : {
								"sFirst" : "首页",
								"sPrevious" : "上一页 ",
								"sNext" : "下一页 ",
								"sLast" : "尾页"
							},
							"sInfoEmpty" : "显示<b style='color:red;'> _START_ </b>到<b style='color:red;'> _END_ </b>条记录",
							"sInfo" : "显示<b style='color:red;'> _START_ </b>到<b style='color:red;'> _END_ </b>条记录/共<b style='color:red;'> _TOTAL_</b>条记录"
						},
						"fnServerParams" : function(aoData) {
							// 对应表单中的查询条件
							aoData.push({
								"name" : "dicName",
								"value" : $("#default").val()
							});
						},
						// 对应列表中显示的字段
						"aoColumnDefs" : [ {
							"sClass" : "left",
							"aTargets" : [ 0, 1, 2, 3, 4, 5, 6 ]
						}, {
							"mData" : null,
							// "sWidth" : "50px",
							"aTargets" : [ 0 ]
						}, {
							"mDataProp" : "dicName",
							"sClass" : "left",
							// "sWidth" : "100px",
							"aTargets" : [ 1 ]

						}, {
							"mDataProp" : "dicItem",
							"sClass" : "left",
							// "sWidth" : "100px",
							"aTargets" : [ 2 ]
						}, {
							"mDataProp" : "dicValue",
							"sClass" : "left",
							// "sWidth" : "100px",
							"aTargets" : [ 3 ]
						}, {
							"mDataProp" : "dicValueSecond",
							"sClass" : "left",
							// "sWidth" : "100px",
							"aTargets" : [ 4 ]
						}, {
							"mDataProp" : "status",
							"sClass" : "left",
							// "sWidth" : "100px",
							"aTargets" : [ 5 ]
						}, {
							"mData" : null,
							"sClass" : "left",
							"aTargets" : [ 6 ]
						} ],
						"fnRowCallback" : function(nRow, aData, iDisplayIndex,
								iDisplayIndexFull) {
							var num_val="";
							if(aData['dicName'].trim() == "交换类型"){
								num_val=aData['dicValueSecond'];
							}
							$('td:eq(0)', nRow).html(iDisplayIndex + 1);
							$('td:eq(4)', nRow).html(num_val);
							$('td:eq(5)', nRow).html(
									aData['status'] == 0 ? '禁用' : '启用');
							$('td:eq(6)', nRow).html(
									"<a class='btn btn-info btn-xs' onclick='updateList(\"update\",\""
											+ aData['sid'] + "\")'>编辑</a>");
						}
					});

}

// 表单查询
function getSysInfo() {
	$("#grid-sysParam").bootgrid("destroy");
	getList();
}

// 新建/修改
function updateList(operType, id) {

	if ($("#default").val().trim() == "交换类型") {
		$("#dic_val").css("display", "none");
		$("#dicValue").removeAttr("dataType");
		$("#dic_num").css("display", "");
		$("#dicValueSecond").attr("dataType", "n1-10");
	} else {
		$("#dic_num").css("display", "none");
		$("#dicValueSecond").removeAttr("dataType");
		$("#dic_val").css("display", "");
		$("#dicValue").attr("dataType", "*");
	}
	var sysParameForm;
	var sysParamDialog = $.dialog({
		width : '600px',
		fixed : false,
		lock : true,
		max : false,
		min : false,
		title : operType == 'add' ? '添加' : '修改',
		content : $('#sysParam_dialog').html(),
		okVal : '保存',
		ok : function() {
			var url = basepath + "/sysParam/add";
			if (operType != 'add') {
				url = basepath + "/sysParam/update";
			}
			sysParamForm.ajaxPost(false, false, url);
			return false;
		},
		cancelVal : '取消',
		cancel : true
	});
	$("#dicName").val($("#default").val());
	// 先初始化lhgDialog，再初始化验证表单，否则验证不起作用
	sysParamForm = $("#sysParam_form").Validform({
		tiptype : function(msg, o, cssctl) {

			if (!o.obj.is("form")) {
				var objtip = o.obj.parent("td").find(".Validform_checktip");
				cssctl(objtip, o.type);
				objtip.text(msg);
			}
		},
		callback : function(data) {
			if (data > 0) {
				clearTable("操作成功！");
				sysParamDialog.close();
				$(".glyphicon-refresh").click();
			} else if (data == -2) {
				$.dialog.alert("记录重复!", function() {
				});
			} else {
				$.dialog.alert("数据库异常！", function() {
				});
			}

		}
	});
	$('#oper_type').val(operType);
	if (operType == "update") {
		$.post(basepath + "/sysParam/findById", {
			"id" : id
		}, function(o) {
			var obj = JSON.parse(o);
			$("#sid").val(id);
			$("#dicName").val(obj.dicName);
			$("#dicItem").val(obj.dicItem);
			$("#dicValue").val(obj.dicValue);
			$("#dicValueSecond").val(obj.dicValueSecond);
			$("#status").val(obj.status);
			// $("#dicValueSecond").val(obj.dicValueSecond);

		});
	}
}
// 批量删除

function delList() {

	var _arr = [];
	var _ck_arr = $("input[name=select]:checked");
	if (_ck_arr.length > 0) {
		$.each(_ck_arr, function(i, o) {
			if ($(o).val() != 'all') {
				_arr.push(parseInt($(o).val()));
			}
		});
		$.dialog.confirm('你确定要删除该记录吗？', function() {

			$.ajax({
				type : 'POST',
				url : basepath + "/sysParamConfig/del.rest",
				data : {
					arr : JSON.stringify(_arr)
				},
				dataType : 'json',
				// contentType: "application/json; charset=utf-8",
				success : function(data) {

					clearTable("删除成功！");
					$(".glyphicon-refresh").click();

				}
			});

		}, function() {
		});

	} else {
		$.dialog.alert("未选中信息！");
		return false;
	}
}
function clearTable(_msg) {
	$.dialog.tips(_msg, 1, 'tips.gif');
	$('#grid-sysParam').dataTable().fnDestroy();
	var a = $(window).height();
	var c = $("#child1_dic").offset().top;
	var scroll = (a - c) * 0.6;
	getItemList(scroll);

}
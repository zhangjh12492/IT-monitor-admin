String.prototype.trim = function() {
	return this.replace(/\s/ig, "");
};
/**
* 格式化日期
*/
function formatDate(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
};
var editFlag = false;
var editRow = null;
var tempFlag=0;
/**
* 时间比较
*/
function compareDate(date1, date2) {
	var ds1 = date1.split("-");
	var ds2 = date2.split("-");
	var d1 = new Date(ds1[0], ds1[1], ds1[2]);
	var d2 = new Date(ds2[0], ds2[1], ds2[2]);
	if (d1.getTime() > d2.getTime()) {
		return false;
	}
	return true;
}
/**
* 状态数据
*/
var cFlagData = [
	{name: "请选择状态", value: "-2", selected: "true"},
	{name: "未提交", value: "-1"},
	{name: "已提交,未处理", value: "0"},
	{name: "处理成功", value: "1"},
	{name: "处理失败", value: "2"}
	];

/**
 * 收银类型
 */
var transFlagData =[
	{name: "请选择交易类型", value: "-2", selected: "true"},
	{name: "交易", value: "1"},
	{name: "退货", value: "4"}
    ];
/**
* 加载页面
*/
$(function(){
	initData();
	$(window).resize(function () { $("#posOnlineSalesDetail").datagrid("resize");$("#posOnlineSalesDetailTable").datagrid("resize"); });
});
/**
* 初始化数据
*/
function initData() {
	initDefault();
	initSearch();
	initRefresh();
	initEdit();
	initSave();
	initUnedit();
	initPosOnlineSalesDetailDiv();
	initView();
	initPosOnlineSalesDetail();
	initDlgRefresh();
	initDlgEdit();
	initDlgSave();
	initDlgUnedit();
	initPosOnlineSalesDetailTable();
}
/**
* 初始化默认数据
*/
function initDefault() {
	initCashTime();
	initCFlagState();
	initTransFlagState();
}
/**
* 初始化时间
*/
function initCashTime() {
	$("#cashTimeBetween").val(formatDate(new Date()));
	$("#cashTimeEnd").val(formatDate(new Date()));
	$("#cashTimeBetween").datebox({
		editable: false,
		current: new Date(),
		onSelect: function(date) {
			if (!compareDate(formatDate(date), $("#cashTimeEnd").datebox("getValue"))) {
				$("#cashTimeBetween").datebox("setValue", $("#cashTimeEnd").datebox("getValue"));
			}
		}
	});
	$("#cashTimeEnd").datebox({
		editable: false,
		current: new Date(),
		onSelect: function(date) {
			if (compareDate(formatDate(date), $("#cashTimeBetween").datebox("getValue"))) {
				$("#cashTimeEnd").datebox("setValue", $("#cashTimeBetween").datebox("getValue"));
			}
		}
	});
	$("#cashTimeBetween").datebox("setValue", formatDate(new Date()));
	$("#cashTimeEnd").datebox("setValue", formatDate(new Date()));
}
/**
* 初始化状态
*/
function initCFlagState() {
	$("#cFlag").combobox({
		editable: false,
		multiple: false,
		panelHeight: "50px",
        data: cFlagData,
        valueField: "value",
        textField: "name"
	});
}

/**
* 初始化状态
*/
function initTransFlagState() {
	$("#transFlag").combobox({
		editable: false,
		multiple: false,
		panelHeight: "50px",
        data: transFlagData,
        valueField: "value",
        textField: "name"
	});
}

/**
* 初始化收银表格
*/
function initPosOnlineSalesDetail() {
	$("#posOnlineSalesDetail").datagrid({
		url: "erpPosOnlineSalesController.do?getPosOnlineSalesByPars.json",
        toolbar: "#toolbar",
        title: "",
        iconCls: "icon-save",
        width: $(this).width() * 0.067,
        pagination: true,
        pageSize: 20,
        pageList: [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers:true,
        nowrap: false,
        border: false,
        sortName: "OPT_CREATE_TIME",
        sortOrder: "desc",
        idField: "POS_ONLINE_SALES_SID",
        columns:[
        	[
        		{
        			field: "SHOP_SID",
        			title: "门店名称",
        			sortable: true,
        			width: 100,
        			formatter: function(value, rowData, rowIndex) {
        				return rowData["SHOP_NAME"];
        			}
        		},
        		{
        			field: "CARD_NO",
        			title: "会员卡号",
        			sortable: true,
        			width: 100,
        			editor: {
        				type: "text"
        			}
        		},
        		{
        			field: "MONEY_SUM",
        			title: "金额",
        			sortable: true,
        			width: 100
        		},
        		{
        			field: "TRANSACTION_TYPE",
        			title: "交易类型",
        			sortable: true,
        			width: 100,
        			formatter: function(value, rowData, rowIndex) {
        				if ("1" == value) {
        					return "交易";
        				} else if ("4" == value) {
        					return "退货";
        				}
        				return "";
        			}
        		},
                {
                    field: "CASHIER_NUMBER",
                    title: "收银流水号",
                    sortable: true,
                    width: 100
                },
        		{
        			field: "CASH_TIME",
        			title: "收银时间",
        			sortable: true,
        			width: 100
        		},
        		{
        			field: "C_FLAG",
        			title: "收银状态",
        			sortable: true,
        			width: 100,
        			formatter: function(value, rowData, rowIndex) {
        				if ("-1" == value) {
        					return "未提交";
        				} else if ("0" == value) {
        					return "已提交,未处理";
        				} else if ("1" == value) {
        					return "处理成功";
        				} else if ("2" == value) {
        					return "处理失败";
        				}
        				return "";
        			}
        		},
        		{
        			field: "C_ERROR",
        			title: "错误提示",
        			sortable: true,
        			width: 100
        		},
        		{
        			field: "OPT_USER_SID",
        			title: "创建人",
        			sortable: true,
        			width: 100,
        			formatter: function(value, rowData, rowIndex) {
        				return rowData["OPT_USER_NAME"];
        			}
        		},
        		{
        			field: "OPT_CREATE_TIME",
        			title: "创建时间",
        			sortable: true,
        			width: 100
        		},
        		{
        			field: "OPT_UPDATE_USER_SID",
        			title: "修改人",
        			sortable: true,
        			width: 100,
        			formatter: function(value, rowData, rowIndex) {
        				return rowData["OPT_UPDATE_USER_NAME"];
        			}
        		},
        		{
        			field: "OPT_UPDATE_TIME",
        			title: "修改时间",
        			sortable: true,
        			width: 100
        		}
        	]
        ],
        onDblClickRow: function(rowIndex, rowData) {
        	if (!editFlag) {
        		if(rowData['C_FLAG']==2){
        			$("#posOnlineSalesDetail").datagrid("beginEdit", rowIndex);
            		editFlag = true;
            		editRow = rowIndex;
        		}else{
        			$.messager.show({
            			title: "提示",
            			msg: "该收银状态不允许做相关操作!"
            		});
        		}

        	} else {
        		$.messager.show({
        			title: "提示",
        			msg: "您没有结束之前编辑的数据, 请先保存或取消编辑!"
        		});
        	}
        },
        onAfterEdit: function(rowIndex, rowData, changes) {
        	if (editFlag) {
        		$.ajax({
        			url: "erpPosOnlineSalesController.do?updateCardNoBySid.json",
        			dataType: "json",
        			data: rowData,
        			success: function(message) {
        				if (message.success) {
        					$.messager.show({title: "提示", msg: "保存收银信息成功!"});
        				} else {
        					$.messager.show({title: "提示", msg: "保存收银信息失败!"});
        				}
        				$("#posOnlineSalesDetail").datagrid("reload");
        				editFlag = false;
        			}
        		});
        	}
        },
        onRowContextMenu: function(e, rowIndex, rowData) {
        	$(this).datagrid("selectRow", rowIndex);
        	e.preventDefault();
        	$("#menu").menu("show", {
				left : e.pageX,
				top : e.pageY
			});
        }
	});
}
/**
* 初始化收银明细表格
*/
function initPosOnlineSalesDetailTable() {
	$("#posOnlineSalesDetailTable").datagrid({
		url: "erpPosOnlineSalesDetailController.do?getPosOnlineSalesDetailsByPars.json",
        toolbar: "#dlgToolbar",
        title: "",
        iconCls: "icon-save",
        pagination: true,
        pageSize: 10,
        pageList: [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers:true,
        nowrap: false,
        border: false,
        sortName: "OPT_CREATE_TIME",
        sortOrder: "desc",
        idField: "POS_ONLINE_SALES_DETAIL_SID",
        columns:[
        	[
        		{
        			field: "ERP_POS_ONLINE_SALES_SID",
        			title: "收银表主键",
        			sortable: true,
        			width: 80
        		},
        		{
        			field: "ERP_COLLECT_DETAIL_SID",
        			title: "集货表主键",
        			sortable: true,
        			width: 80
        		},
        		{
        			field: "DISCOUNT_RATE_CODE",
        			title: "折率码",
        			sortable: true,
        			width: 100,
        			editor: {
        				type: "text"
        			}
        		},
        		{
        			field: "NUM",
        			title: "号码",
        			sortable: true,
        			width: 50
        		},
        		{
        			field: "MONEY_SUM",
        			title: "金额",
        			sortable: true,
        			width: 80
        		},
        		{
        			field: "OFF_VALUE",
        			title: "折扣金额",
        			sortable: true,
        			width: 50
        		},
        		{
        			field: "ERROR",
        			title: "错误提示",
        			sortable: true,
        			width: 100
        		},
        		{
        			field: "OPT_USER_SID",
        			title: "创建人",
        			sortable: true,
        			width: 80,
        			formatter: function(value, rowData, rowIndex) {
        				return rowData["OPT_USER_NAME"];
        			}
        		},
        		{
                    field: "OPT_CREATE_TIME",
                    title: "创建时间",
                    sortable: true,
                    width: 80
                },
        		{
        			field: "OPT_UPDATE_USER_SID",
        			title: "修改人",
        			sortable: true,
        			width: 80,
        			formatter: function(value, rowData, rowIndex) {
        				return rowData["OPT_UPDATE_USER_NAME"];
        			}
        		},
        		{
        			field: "OPT_UPDATE_TIME",
        			title: "修改时间",
        			sortable: true,
        			width: 80
        		},
        		{
        			field: "ORDERS_NO",
        			title: "订单号",
        			sortable: true,
        			width: 120
        		}
        	]
        ],
        onDblClickRow: function(rowIndex, rowData) {
        	if (!editFlag) {
        		if(tempFlag==2){
        			$("#posOnlineSalesDetailTable").datagrid("beginEdit", rowIndex);
            		editFlag = true;
            		editRow = rowIndex;
        		}else{
        			$.messager.show({
            			title: "提示",
            			msg: "该收银状态不允许做相关操作!"
            		});
        		}

        	} else {
        		$.messager.show({
        			title: "提示",
        			msg: "您没有结束之前编辑的数据, 请先保存或取消编辑!"
        		});
        	}
        },
        onAfterEdit: function(rowIndex, rowData, changes) {
        	if (editFlag) {
        		$.ajax({
        			url: "erpPosOnlineSalesDetailController.do?updateDiscountRateCodeBySid.json",
        			dataType: "json",
        			data: rowData,
        			success: function(message) {
        				if (message.success) {
        					$.messager.show({title: "提示", msg: "保存收银信息成功!"});
        				} else {
        					$.messager.show({title: "提示", msg: "保存收银信息失败!"});
        				}
        				$("#posOnlineSalesDetailTable").datagrid("reload");
        				$("#posOnlineSalesDetail").datagrid("reload");
        				editFlag = false;
        			}
        		});
        	}
        }
	});
}
/**
* 初始化收银详细对话框
*/
function initPosOnlineSalesDetailDiv() {
	$("#posOnlineSalesDetailDiv").window({
		title: "收银详细",
		width:1024,
	    height:768,
	    collapsible: false,
	    minimizable: false,
	    closed: true,
	    modal: true,
	    onClose: function() {
	    	editFlag = false;
	    	editRow = null;
	    }
	});
}
/**
* 初始化搜索
*/
function initSearch() {
	$("#search").click(function (){
		var cashTimeBetween = $("#cashTimeBetween").datebox("getValue");
		var cashTimeEnd = $("#cashTimeEnd").datebox("getValue");
		var date = new Date(cashTimeEnd);
		date = new Date(date.getTime()+24*60*60*1000);
		cashTimeEnd = formatDate(date);
		var pars = new Array();
		pars["CASH_TIME_BETWEEN"] = cashTimeBetween;
		pars["CASH_TIME_END"] = cashTimeEnd;
		
		var cashier_number=$("#cashier_number").val();
		if(cashier_number.trim()!=null && cashier_number.trim()!="" && cashier_number.trim()!="undefined"){
			pars["CASHIER_NUMBER"] = cashier_number;
		}
		
		if ("-2" != $("#cFlag").combobox("getValue")) {
			pars["C_FLAG"] = $("#cFlag").combobox("getValue");
		}
		if ("-2" != $("#transFlag").combobox("getValue")) {
			pars["TRANSACTION_TYPE"] = $("#transFlag").combobox("getValue");
		}
		$("#posOnlineSalesDetail").datagrid("reload", pars);
	});
}
/**
* 初始化刷新
*/
function initRefresh() {
	$("#refresh").click(function() {
		$("#posOnlineSalesDetail").datagrid("reload");
	});
}
/**
* 初始化对话框刷新
*/
function initDlgRefresh() {
	$("#dlgRefresh").click(function() {
		$("#posOnlineSalesDetailTable").datagrid("reload");
	});
}
/**
* 初始化编辑
*/
function initEdit() {
	$("#edit").click(function() {
		var rowData = $("#posOnlineSalesDetail").datagrid("getSelected");
		if (!rowData) {
			$.messager.show({
				title: "提示",
				msg: "请选择要编辑的数据!"
			});
			return;
		}
		if (!editFlag) {
			if(rowData['C_FLAG']==2){
				var rowIndex = $("#posOnlineSalesDetail").datagrid("getRowIndex", rowData);
    			$("#posOnlineSalesDetail").datagrid("beginEdit", rowIndex);
    			editRow = rowIndex;
    			editFlag = true;
    		}else{
    			$.messager.show({
        			title: "提示",
        			msg: "该收银状态不允许做相关操作!"
        		});
    		}
		} else {
			$.messager.show({
				title: "提示",
				msg: "您没有结束之前编辑的数据, 请先保存或取消编辑!"
			});
		}
	});
}
/**
* 初始化对话框编辑
*/
function initDlgEdit() {
	$("#dlgEdit").click(function() {
		var rowData = $("#posOnlineSalesDetailTable").datagrid("getSelected");
		if (!rowData) {
			$.messager.show({
				title: "提示",
				msg: "请选择要编辑的数据!"
			});
			return;
		}
		if (!editFlag) {
			if(tempFlag==1){
    			$.messager.show({
        			title: "提示",
        			msg: "该收银已经完成，不允许做相关操作!"
        		});
    		}else{
    			var rowIndex = $("#posOnlineSalesDetailTable").datagrid("getRowIndex", rowData);
    			$("#posOnlineSalesDetailTable").datagrid("beginEdit", rowIndex);
    			editRow = rowIndex;
    			editFlag = true;
    		}
		} else {
			$.messager.show({
				title: "提示",
				msg: "您没有结束之前编辑的数据, 请选择保存或取消编辑!"
			});
		}
	});
}
/**
* 初始化保存
*/
function initSave() {
	$("#save").click(function() {
		if (null != editRow) {
			$("#posOnlineSalesDetail").datagrid("endEdit", editRow);
        }
	});
}
/**
* 初始化对话框保存
*/
function initDlgSave() {
	$("#dlgSave").click(function() {
		if (null != editRow) {
			$("#posOnlineSalesDetailTable").datagrid("endEdit", editRow);
		}
	});
}
/**
* 初始化取消编辑
*/
function initUnedit() {
	$("#unedit").click(function() {
		if (editFlag) {
			$("#posOnlineSalesDetail").datagrid("cancelEdit", editRow);
			editFlag = false;
		}
	});
}
/**
* 初始化取消编辑
*/
function initDlgUnedit() {
	$("#dlgUnedit").click(function() {
		if (editFlag) {
			$("#posOnlineSalesDetailTable").datagrid("cancelEdit", editRow);
			editFlag = false;
		}
	});
}
/**
* 初始化收银详细
*/

function initView() {
	$("#view").click(function() {
		if (editFlag) {
			$.messager.show({
				title: "提示",
				msg: "请保存或取消编辑的数据!"
			});
			return;
		}
		var rowData = $("#posOnlineSalesDetail").datagrid("getSelected");
		if (!rowData) {
			$.messager.show({
				title: "提示",
				msg: "请选择要查看的收银!"
			});
			return;
		}
		tempFlag=rowData["C_FLAG"];
		$("#posOnlineSalesDetailDiv").window("open");
		$("#posOnlineSalesDetailTable").datagrid("load", {"POS_ONLINE_SALES_SID":rowData["POS_ONLINE_SALES_SID"]});
	});
	$("#contextMenuView").click(function() {
		if (editFlag) {
			$.messager.show({
				title: "提示",
				msg: "请保存或取消编辑的数据!"
			});
			return;
		}
		var rowData = $("#posOnlineSalesDetail").datagrid("getSelected");
		if (!rowData) {
			$.messager.show({
				title: "提示",
				msg: "请选择要查看的收银!"
			});
			return;
		}
		$("#posOnlineSalesDetailDiv").window("open");
		$("#posOnlineSalesDetailTable").datagrid("load", {"POS_ONLINE_SALES_SID":rowData["POS_ONLINE_SALES_SID"]});
	});
}
var busiTable;
var sysGroupTable;
var groupEditTable;
var groupDlg;
var userTable_easyui;

var sysInfoId = '';

$('#groupAdd').click(function () {
    if(sysInfoId == ''){
        alert("先选择系统，再操作");
        return false;
    }

    $('#groupEdit').dataTable().fnDestroy();
    groupEditForm(sysInfoId);
//    $('#groupDlg').dialog('open');
//    return false;
});

systemTable = $("#system").dataTable({
    "sAjaxSource": "sysGroup.do?getSystemList",
    "bProcessing": true,
    "searching": false, //去掉搜索框
    "bLengthChange": false,// 是否允许自定义每页显示条数.
    "aLengthMenu": [
        [1, 5, 10, 25, 50, 100, -1],
        [1, 5, 10, 25, 50, 100, "所有"]
    ],
    "bServerSide": true,
    "iDisplayLength": 10,
    "oLanguage": {//语言设置
        "sLengthMenu": "每页显示  _MENU_ 条记录",
        "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "前一页",
            "sNext": "后一页",
            "sLast": "尾页"
        },
        "sZeroRecords": "抱歉， 没有找到",
        "sInfoEmpty": "没有数据"
    },
//        "sAjaxDataProp": 'object',  //这个改写默认取值的JSON对象
    "aoColumns": [
        {
            "mDataProp": 'id',
            "sTitle": "编号",
            "bSortable": true
        },
        {
            "mDataProp": 'sysCode',
            "sTitle": "系统",
            "bSortable": true
        },
        {
            "mDataProp": 'sysDesc',
            "sTitle": "系统描述",
            "bSortable": true
        }/*,
         {
         "mDataProp": null,
         "sTitle": "类型",
         "bSortable": true
         }*/
    ],
    "aoColumnDefs": [
        {
            sDefaultContent: '',
            aTargets: [ '_all' ]
        }
    ],
    "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
    }

});


function editGroup(id) {

    $.ajax({
        url: 'userGroup.do?getUserGroupList&id=' + id,
        type: 'POST',
        dataType: 'json',
        data: {
            iDisplayStart: 0,
            iDisplayLength: 1
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.aaData != '') {
                $('#id').val(id);
                $('#groupName').val(result.aaData[0].groupName);
                $('#groupDesc').val(result.aaData[0].groupDesc);
                $('#groupDlg').dialog('open');
                return false;
            } else {
                alert(result.aaData);
            }

        }

    })
}

$('#system').on('click', 'tbody tr', function () {
    // 设置选中样式
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        systemTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    var sTitle;
    var nTds = $('td', this);
    sysInfoId = $(nTds[0]).text();
    getGroupTable(sysInfoId);
});

function groupEditForm(groupId) {

    if (groupEditTable != null) {
//        usersTable.fnClearTable();
        $('#groupEdit').dataTable().fnDestroy();
    }
    groupEditTable = $("#groupEdit").dataTable({
            "bDestory": true,
            "bFilter": false,
            "bSort": true,
            "sAjaxSource": "sysGroup.do?getNotExistGroup&sysInfoId=" + sysInfoId,
            "bProcessing": true,
            "searching": false, //去掉搜索框
            "bLengthChange": false,// 是否允许自定义每页显示条数.
            "aLengthMenu": [
                [1, 5, 10, 25, 50, 100, -1],
                [1, 5, 10, 25, 50, 100, "所有"]
            ],
            "bServerSide": true,
            "iDisplayLength": 10,
            "oLanguage": {//语言设置
                "sLengthMenu": "每页显示  _MENU_ 条记录",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                },
                "sZeroRecords": "抱歉， 没有找到",
                "sInfoEmpty": "没有数据"
            },
//        "sAjaxDataProp": 'object',  //这个改写默认取值的JSON对象
            "aoColumns": [
                {
                    "mDataProp": null,
                    "sTitle": "",
                    "bSortable": false
                },
                {
                    "mDataProp": 'id',
                    "sTitle": "编号",
                    "bSortable": true
                },
                {
                    "mDataProp": 'groupName',
                    "sTitle": "组名",
                    "bSortable": true
                },
                {
                    "mDataProp": 'groupDesc',
                    "sTitle": "描述",
                    "bSortable": true
                }
            ],
            "aoColumnDefs": [
                {
                    sDefaultContent: '',
                    aTargets: [ '_all' ]
                }
            ],
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                $('td:eq(0)', nRow).html(
                        "<input type='checkbox' name='sysid' value='" + aData['id'] + "'  />");
            }

        }
    )
}

function getGroupTable(sysInfoId) {
    if (sysInfoId == '') {
        sysInfoId = -1;
    }
    if (sysGroupTable != null) {
//        usersTable.fnClearTable();
        $('#sysGroup').dataTable().fnDestroy();
    }
    sysGroupTable = $("#sysGroup").dataTable({
        "bDestory": true,
        "bFilter": false,
        "bSort": true,

//        "bRetrieve": true,
        "sAjaxSource": "sysGroup.do?selectGroupBySysid&sysInfoId=" + sysInfoId,
        "bProcessing": true,
        "searching": false, //去掉搜索框
        "bLengthChange": false,// 是否允许自定义每页显示条数.
        "aLengthMenu": [
            [1, 5, 10, 25, 50, 100, -1],
            [1, 5, 10, 25, 50, 100, "所有"]
        ],
        "bServerSide": true,
        "iDisplayLength": 10,
        "oLanguage": {//语言设置
            "sLengthMenu": "每页显示  _MENU_ 条记录",
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            },
            "sZeroRecords": "抱歉， 没有找到",
            "sInfoEmpty": "没有数据"
        },
//        "sAjaxDataProp": 'object',  //这个改写默认取值的JSON对象
        "aoColumns": [
            {
                "mDataProp": 'id',
                "sTitle": "编号",
                "bSortable": true
            },
            {
                "mDataProp": 'groupName',
                "sTitle": "业务组",
                "bSortable": true
            },
            {
                "mDataProp": 'sysDesc',
                "sTitle": "系统描述",
                "bSortable": true
            },
            {
                "mDataProp": null,
                "sTitle": "操作",
                "bSortable": true
            }
        ],
        "aoColumnDefs": [
            {
                sDefaultContent: '',
                aTargets: [ '_all' ]
            }
        ],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {

            $('td:eq(3)', nRow).html(
                    "<a class='btn btn-info btn-xs' onclick='deleteGroup(\""
                    + aData['id'] + "\")'>删除</a>");

        }
    });
}


function deleteGroup(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'sysGroup.do?delUserGroup&id=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getGroupTable(sysInfoId);
                } else {
                    alert(result.desc);
                }
            }

        })

    } else {

    }
}


$(function () {
    $('#btnSaveGroup').click(function () {
        var ids = [];
        $("#groupEdit tbody tr").each(function () {
            var checkbox = $(this).children("td").eq(0).children("input:checkbox");
            if (checkbox.attr("checked")) {
                ids.push(checkbox.val());
            }
        });
        $.ajax({
            url: 'sysGroup.do?addGroup&ids=' + ids,
            type: 'POST',
            dataType: 'json',
            data: {
                sysInfoId: sysInfoId
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getGroupTable(sysInfoId);
                    $('#groupDlg').modal('hide');
                } else {
                    alert(result.desc);
                }
            }

        })
    });

    /* 专题数据列表 */
    /*
     userTable_easyui = $('#userTable_easyui').datagrid({
     //        url:'erpallocateController.do?showCollectUnAllocateOrderList.json',
     toolbar:'#toolbar',
     title:'',
     iconCls:'icon-save',
     pagination:false,
     pageSize:10,
     pageList:[ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
     fit:true,
     fitColumns:false,
     remoteSort:false,
     nowrap:false,
     singleSelect:true,
     border:false,
     idField:'SID',
     rownumbers:true,
     columns:[
     [
     {
     field:'brandName',
     title:'品牌',
     width:150,
     sortable:true
     },
     {
     field:'proSku',
     title:'款号',
     width:100,
     sortable:true
     }
     ]
     ],
     onDblClickRow:function (rowIndex, rowData) {
     //            addData(rowData.barCode);
     }
     });
     */
    getGroupTable('');
})
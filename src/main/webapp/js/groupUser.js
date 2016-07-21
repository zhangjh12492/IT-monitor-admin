var groupTable;
var usersTable;
var userEditTable;
var groupDlg;
var userTable_easyui;

var groupId = '';

$('#userAdd').click(function () {
    if(groupId == ''){
        alert("先选择再操作");
        return false;
    }
    $('#userEdit').dataTable().fnDestroy();
    userEditForm(groupId);
//    $('#groupDlg').dialog('open');
//    return false;
});

groupTable = $("#group").dataTable({
    "sAjaxSource": "userGroup.do?getUserGroupList",
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
            "sTitle": "组名",
            "bSortable": true
        },
        {
            "mDataProp": 'groupDesc',
            "sTitle": "组名描述",
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

$('#group').on('click', 'tbody tr', function () {
    // 设置选中样式
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        groupTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    var sTitle;
    var nTds = $('td', this);
    groupId = $(nTds[0]).text();
    getUsersTable(groupId);
});

function userEditForm(groupId) {

    if (userEditTable != null) {
//        usersTable.fnClearTable();
        $('#usersEdit').dataTable().fnDestroy();
    }
    userEditTable = $("#usersEdit").dataTable({
            "bDestory": true,
            "bFilter": false,
            "bSort": true,
            "sAjaxSource": "userGroup.do?getNotExistUser&userGroupId=" + groupId,
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
                    "mDataProp": 'name',
                    "sTitle": "账号",
                    "bSortable": true
                },
                {
                    "mDataProp": 'userName',
                    "sTitle": "人员",
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

function getUsersTable(groupId) {
    if (groupId == '') {
        groupId = -1;
    }
    if (usersTable != null) {
//        usersTable.fnClearTable();
        $('#users').dataTable().fnDestroy();
    }
    usersTable = $("#users").dataTable({
        "bDestory": true,
        "bFilter": false,
        "bSort": true,

//        "bRetrieve": true,
        "sAjaxSource": "userGroup.do?selectUsersByGroupid&userGroupId=" + groupId,
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
                "mDataProp": 'userName',
                "sTitle": "人员",
                "bSortable": true
            },
            {
                "mDataProp": 'userType',
                "sTitle": "人员类型",
                "bSortable": true
            },
            {
                "mDataProp": 'groupName',
                "sTitle": "业务组",
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
            if (aData['userType'] == 0) {
                $('td:eq(2)', nRow).html("管理员");
            } else if (aData['userType'] == 1) {
                $('td:eq(2)', nRow).html("普通用户");
            }

            $('td:eq(4)', nRow).html(
                    "<a class='btn btn-info btn-xs' onclick='deleteUser(\""
                    + aData['id'] + "\")'>删除</a>");

        }
    });
}


function deleteUser(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'userGroup.do?delUserGroupUserInfo&id=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getUsersTable(groupId);
                } else {
                    alert(result.desc);
                }
            }

        })

    } else {

    }
}


$(function () {
    $('#btnSaveUser').click(function () {
        var ids = [];
        $("#usersEdit tbody tr").each(function () {
            var checkbox = $(this).children("td").eq(0).children("input:checkbox");
            if (checkbox.attr("checked")) {
                ids.push(checkbox.val());
            }
        });
        $.ajax({
            url: 'userGroup.do?addUser&ids=' + ids,
            type: 'POST',
            dataType: 'json',
            data: {
                groupId: groupId,
                userType: $('#userType').val()
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getUsersTable(groupId);
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
    getUsersTable(-1);
})
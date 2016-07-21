var groupTable;
var usersTable;
var groupDlg;
var dicName = '';

$('#userAdd').click(function () {
    $('#id').val(null);
    $('#dicName').val(dicName);
    $('#dicItem').val('');
    $('#dicValue').val('');
});

groupTable = $("#group").dataTable({
    "sAjaxSource": "/mq_sysParam.do?queryList",
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
            "mDataProp": 'sid',
            "sTitle": "编号",
            "bSortable": true
        },
        {
            "mDataProp": 'dicName',
            "sTitle": "字典名称",
            "bSortable": true
        },
        {
            "mDataProp": 'dicDesc',
            "sTitle": "字典描述",
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
        url: '/userGroup.do?getUserGroupList&id=' + id,
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
    dicName = $(nTds[1]).text();
    getUsersTable(dicName);
});

function userEditForm(dicName) {

}

function getUsersTable(dicName) {
    if (dicName == '') {
        dicName = '';
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
        "sAjaxSource": "/mq_sysParam.do?itemList&dicName=" + dicName,
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
                "mDataProp": 'sid',
                "sTitle": "编号",
                "bSortable": false
            },
            {
                "mDataProp": 'dicItem',
                "sTitle": "字典项",
                "bSortable": false
            },
            {
                "mDataProp": 'dicValue',
                "sTitle": "字典值",
                "bSortable": false
            },
            {
                "mDataProp": 'dicName',
                "sTitle": "字典名称"
            },
            {
                "mDataProp": null,
                "sTitle": "操作"
            }
        ],
        "aoColumnDefs": [
            {
                sDefaultContent: '',
                aTargets: [ '_all' ]
            }
        ],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {

            $('td:eq(4)', nRow).html(
                    "<a class='btn btn-info btn-xs' onclick='deleteUser(\""
                    + aData['sid'] + "\", \"" + aData['dicName'] + "\")'>删除</a>");

        }
    });
}


function deleteUser(id, dicNameVal) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'mq_sysParam.do?deleteById&sid=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getUsersTable(dicNameVal);
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
        $.ajax({
            url: 'mq_sysParam.do?add',
            type: 'POST',
            dataType: 'json',
            data: {
                dicName: $('#dicName').val(),
                dicItem: $('#dicItem').val(),
                dicValue: $('#dicValue').val()
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getUsersTable($('#dicName').val());
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
    getUsersTable('qwert');
})
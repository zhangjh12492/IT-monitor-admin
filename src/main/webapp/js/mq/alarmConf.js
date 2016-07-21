var groupTable;
var usersTable;
var groupDlg;
var dicItem = '';
var exchangeType_html = '';
var exchangeConf_html = '';
var serviceNo_html = "<option value='0000'>请选择</option>";

$('#userAdd').click(function () {
    if (dicItem == null | dicItem == '') {
        alert("选择业务，再操作!")
        return false;
    }
    $('#sid').val(null);
    $('#systemNo').val(dicItem);
    $('#alarmNo').val('');
});

function getGroupList() {
    groupTable = $("#group").dataTable({
        "sAjaxSource": "mq_inbound.do?getSystemList",
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
                "sTitle": "系统名称",
                "bSortable": false
            },
            {
                "mDataProp": 'dicValue',
                "sTitle": "系统代码",
                "bSortable": false
            }
        ],
        "aoColumnDefs": [
            {
                sDefaultContent: '',
                aTargets: [ '_all' ]
            }
        ]
    });
}

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
//                $('#id').val(id);
                /*
                 $('#groupName').val(result.aaData[0].groupName);
                 $('#groupDesc').val(result.aaData[0].groupDesc);
                 $('#groupDlg').dialog('open');
                 */
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
        if (groupTable != null) {
            groupTable.$('tr.selected').removeClass('selected');
        }
        $(this).addClass('selected');
    }
    var nTds = $('td', this);
    dicItem = $(nTds[2]).text();
    getUsersTable(dicItem);
});

function userEditForm(dicName) {

}

function getUsersTable(id) {
    if (id == null || id == '') {
        id = 'xx';
    }
    if (usersTable != null) {
//        usersTable.fnClearTable();
        $('#users').dataTable().fnDestroy();
    }
    usersTable = $("#users").dataTable({
        "bDestory": true,
//        "bFilter": false,
//        "bSort": true,

//        "bRetrieve": true,
        "sAjaxSource": "mq_alarmInfo.do?selectAlarmInfoBySystemNo&systemNo=" + id,
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
                "mDataProp": 'alarmNo',
                "sTitle": "报警配置",
                "bSortable": false
            },
            {
                "mDataProp": 'alarmType',
                "sTitle": "报警类型",
                "bSortable": false
            },
            {
                "mDataProp": 'systemNo',
                "sTitle": "系统编号",
                "bSortable": false
            },
            {
                "mDataProp": null,
                "sTitle": "操作",
                "bSortable": false
            }
        ],
        "aoColumnDefs": [
            {
                sDefaultContent: '',
                aTargets: [ '_all' ]
            }
        ],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
            if (aData['alarmType'] == 0) {
                $('td:eq(2)', nRow).html('短信');
            } else {
                $('td:eq(2)', nRow).html('邮件');
            }
            $('td:eq(4)', nRow).html(
                    "<a class='btn btn-info btn-xs' onclick='deleteUser(\""
                    + aData['sid'] + "\")'>删除</a>");

        }
    });
}

function deleteUser(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'mq_alarmInfo.do?deleteById&sid=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getUsersTable(dicItem);
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
        if ($('#alarmNo').val() == '') {
            alert("报警配置不能为空!")
            return false;
        }
        $.ajax({
            url: 'mq_alarmInfo.do?saveOrUpdate',
            type: 'POST',
            dataType: 'json',
            data: {
                sid: $('#sid').val(),
                systemNo: $('#systemNo').val(),
                alarmNo: $('#alarmNo').val(),
                alarmType: $('#alarmType').val()
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
//                    getUsersTable($('#dicName').val());
                    getUsersTable(dicItem);
                    $('#groupDlg').modal('hide');
                } else {
                    alert(result.desc);
                }
            }
        })
        getUsersTable('ee');
    })

    getGroupList();
    getUsersTable('ee');

})
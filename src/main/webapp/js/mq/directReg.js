var groupTable;
var usersTable;
var groupDlg;
var dicItem = '';
var exchangeConf_html = '';
var inboundConfSid_html = "<option value='-9999'>请选择</option>";
var queueConf_html = "<option value='-9999'>请选择</option>";

$('#userAdd').click(function () {
    if (dicItem == null | dicItem == '') {
        alert("选择业务，再操作!")
        return false;
    }
    $('#id').val(null);
    $('#systemNo').val(dicItem);
//    exchangeType_change();
//    $('#dicItem')s.val('');
//    $('#dicValue').val('');
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
    inboundConfInit(dicItem);
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
        "sAjaxSource": "mq_directReg.do?selectDirectRegBySystemNo&systemNo=" + id,
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
                "mDataProp": 'serviceNo',
                "sTitle": "业务代码",
                "bSortable": false
            },
            {
                "mDataProp": 'systemNo',
                "sTitle": "系统编号",
                "bSortable": false
            },
            {
                "mDataProp": 'queueName',
                "sTitle": "队列名称",
                "bSortable": false
            },
            {
                "mDataProp": 'routeKey',
                "sTitle": "RouteKey",
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

            $('td:eq(5)', nRow).html(
                    "<a class='btn btn-info btn-xs' onclick='deleteUser(\""
                    + aData['sid'] + "\")'>删除</a>");

        }
    });
}

function exchangeType_change() {
    var id = $('#exchangeType').val();
    $.ajax({
        url: 'mq_exchangeConf.do?getExchangeList',
        type: 'POST',
        dataType: 'json',
        data: {
            exchangeType: id
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            exchangeConf_html = '';
            for (var i = 0; i < result.length; i++) {
                exchangeConf_html += "<option value='" + result[i].sid + "'>"
                    + result[i].exchangeName + "</option>";
            }
            $("#exchangeConfSid").html(exchangeConf_html);
        }

    })

}

//    初始化队列
function queueInit() {
    $.ajax({
        url: 'mq_inbound.do?getQueueList&queueType=0',
        type: 'POST',
        dataType: 'json',
        data: {
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                queueConf_html += "<option value=" + result[i].sid + ">"
                    + "[" + result[i].queueName + "]" + result[i].queueDesc + "</option>";
            }
            $("#queueConfSid").html(queueConf_html);
        }

    })
}

//根据系统代码初始化接入服务列表
function inboundConfInit(systemNo) {
    //    初始化字典
    $.ajax({
        url: 'mq_inbound.do?getInboundList',
        type: 'POST',
        dataType: 'json',
        data: {
            systemNo: systemNo
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                inboundConfSid_html += "<option value='" + result[i].sid + "'>"
                    + "[" + result[i].serviceNo + "]" + result[i].inboundDesc + "</option>";
            }
            $("#inboundConfSid").html(inboundConfSid_html);
        }
    })
}

function deleteUser(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'mq_directReg.do?deleteDirectReg&sid=' + id,
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
        if ($('#inboundConfSid').val() == '-9999') {
            alert("请选择接入服务!")
            return false;
        }
        if ($('#queueConfSid').val() == '-9999') {
            alert("队列不能为空!")
            return false;
        }
        if ($('#routeKey').val() == '') {
            alert("routekey不能为空!")
            return false;
        }

        $.ajax({
            url: 'mq_directReg.do?saveOrUpdate',
            type: 'POST',
            dataType: 'json',
            data: {
                inboundConfSid: $('#inboundConfSid').val(),
                queueConfSid: $('#queueConfSid').val(),
                routeKey: $('#routeKey').val()
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getUsersTable(dicItem);
                    $('#groupDlg').modal('hide');
                } else {
                    alert(result.desc);
                }
            }

        })
    });


    getGroupList();
    getUsersTable('ee');
    queueInit();

})
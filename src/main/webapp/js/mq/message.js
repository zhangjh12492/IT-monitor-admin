var groupTable;
var usersTable;
var userEditTable;
var groupDlg;


$('#groupAdd').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    $('#sid').val(null);
    $('#exchangeName').val('');
    $('#exchangeDesc').val('');
    $('#exchangeType').val(0);
//    $('#groupFrom').dialog('open');
//    return false;
});

//保存按钮
$('#btnSaveGroup').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    $.ajax({
        url: 'mq_exchangeConf.do?saveOrUpdateOutboundConf',
        type: 'POST',
        dataType: 'json',
        data: {
            sid: $('#sid').val(),
            exchangeName: $('#exchangeName').val(),
            exchangeDesc: $('#exchangeDesc').val(),
            exchangeType: $('#exchangeType').val()
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                $('#groupFrom').modal('hide');
                getGroupList();
            } else {
                alert(result.desc);
            }
        }

    })
})


function getGroupList() {
    $('#group').dataTable().fnDestroy();
    groupTable = $("#group").dataTable({
        "bDestory": true,
        "bFilter": false,

        "sAjaxSource": "mq_message.do?queryList",
        "fnServerParams": function (aoData) {
            // 对应表单中的查询条件
            aoData.push({
                    "name": "messageId",
                    "value": $("#message_id").val()
                },
                {
                    "name": "serviceNo",
                    "value": $("#msgText").val()
                },
                {
                    "name": "status",
                    "value": $("#status").val()
                },
                {
                    "name": "systemNo",
                    "value": $("#systemNo").val()
                }
            )
        },
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
                "mDataProp": 'messageId',
                "sTitle": "消息编号",
                "bSortable": false
            },
            {
                "mDataProp": 'status',
                "sTitle": "消息状态",
                "bSortable": false
            },
            {
                "mDataProp": 'systemNo',
                "sTitle": "系统编号",
                "bSortable": false
            }
            ,
            {
                "mDataProp": 'serviceNo',
                "sTitle": "服务编号",
                "bSortable": false
            },
            /*
             {
             "mDataProp": 'queueName',
             "sTitle": "队列名称",
             "bSortable": false
             },
             */
            {
                "mDataProp": "inTime",
                "sTitle": "接入时间",
                "bSortable": false
            },
            {
                "mDataProp": "outTime",
                "sTitle": "接出时间",
                "bSortable": false
            },
            {
                "mDataProp": "retryTimes",
                "sTitle": "重试次数",
                "bSortable": false
            },
            {
                "mDataProp": null,
//                "sTitle": "操作",
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
            var reSend = null;
            //修改消息发送状态显示
            var xx = '';
            if (aData['status'] == 1) {
                xx = '<span style="background-color:yellow;border-radius:2;">已发送到队列</span>';
            } else if (aData['status'] == 2) {
                xx = '<span style="background-color:yellow;border-radius:2">发送中...</span>'
            } else if (aData['status'] == 3) {
                xx = '<span style="background-color:lawngreen;border-radius:2">消息已到达</span>'
            } else if (aData['status'] == 9) {
                xx = '<span style="background-color:yellow;border-radius:2">重试发送中...</span>'
            } else if (aData['status'] == 8) {
                xx = '<span style="background-color:red;border-radius:2">发送失败</span>'
            }
            $('td:eq(2)', nRow).html(xx);

            if (aData['status'] == 8) {
                reSend = " <a class='btn btn-info btn-xs' onclick='retrySend(\""
                    + aData['sid']
                    + "\");'>重新发送</a>"
            } else {
                reSend = '';
            }
            $('td:eq(8)', nRow).html(
                    "<a class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#groupFrom\"  onclick='messageView(\""
                    + aData['sid']
                    + "\",\""
                    + aData['messageId']
                    + "\");'>查看消息</a>"
                    /*
                     + " <a class='btn btn-info btn-xs' onclick='messageInfo(\""
                     + aData['sid']
                     + "\");'>查看轨迹</a>"
                     */
                    + reSend + "");
            return nRow;
        }
    });
}

function contentView(id) {
    var ss = $("#" + id).html();
    $("#messageText").html(ss);
    /*
     var ss = document.getElementById(id).innerHTML;
     document.getElementById("messageText").innerHTML = ss;
     */
}


function messageView(sid, messageId) {

    if (userEditTable != null) {
//        usersTable.fnClearTable();
        $('#usersEdit').dataTable().fnDestroy();
    }

    userEditTable = $("#usersEdit").dataTable({
            "bDestory": true,
            "bFilter": false,
            "bSort": true,
            "sAjaxSource": "mq_message/queryDetailList.do?sid=" + sid,
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
                    "mDataProp": 'messageId',
                    "sTitle": "消息号",
                    "bSortable": false
                },
                {
                    "mDataProp": 'messageType',
                    "sTitle": "消息类型",
                    "bSortable": false
                },
                {
                    "mDataProp": 'createTime',
                    "sTitle": "业务时间",
                    "bSortable": false
                },
                {
                    "mDataProp": null,
                    "sTitle": "消息内容",
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
                if (aData['messageType'] == 0) {
                    $('td:eq(2)', nRow).html("接入");
                } else {
                    $('td:eq(2)', nRow).html("接出");
                }

                $("#messageTextInput").append(
                        "<div style='display:none' id='messageTextInput_" + aData['sid']
                        + "' >" + aData['content'] + "</div>");
                $('td:eq(4)', nRow).html(
                        "<a class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#messageFrom\"  onclick='contentView(\"messageTextInput_" + +aData['sid'] + "\");'>查看</a>"
                );
            }

        }
    )
}


//编辑组
function editGroup(id) {
    $.ajax({
        url: 'mq_outbound.do?findById&sid=' + id,
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
                $('#sid').val(id);
                $('#outboundName').val(result.outboundName);
                $('#outboundDesc').val(result.outboundDesc);
                $('#wsType').val(result.wsType);
                $('#outboundUrl').val(result.outboundUrl);
                $('#timeout').val(result.timeout);
                return false;
            } else {
                alert(result.aaData);
            }
        }

    })
}

//删除组
function delGroup(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'mq_outbound.do?deleteById&sid=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getGroupList();
                } else {
                    alert(result.desc);
                }
            }

        })

    } else {

    }
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
    var groupId = $(nTds[0]).text();
//    getUsersTable(groupId);
});


function deleteUser(id) {
    alert(id);
}

$(function () {
    getGroupList();
})
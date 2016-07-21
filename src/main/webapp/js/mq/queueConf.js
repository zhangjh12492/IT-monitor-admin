var groupTable;
var usersTable;
var userEditTable;
var groupDlg;
var outboundConfSid_html = "<option value='-9999'>请选择</option>";


$('#groupAdd').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    $('#sid').val(null);
    $('#queueName').val('');
    $('#queueDesc').val('');
    $('#queueType').val(0);
    $('#outboundConfSid').val(-9999);
    $('#maxNum').val(2);
    $('queueName').removeAttr("readonly");
    $("#queueType").attr("disabled",false);

//    $('#groupFrom').dialog('open');
//    return false;
});

//根据系统代码初始化接入服务列表
function outboundConfInit() {
    //    初始化字典
    $.ajax({
        url: 'mq_outbound.do?getOutboundConfList',
        type: 'POST',
        dataType: 'json',
        data: {
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                outboundConfSid_html += "<option value='" + result[i].sid + "'>"
                    + "[" + result[i].outboundName + "]" + result[i].outboundUrl + "</option>";
            }
            $("#outboundConfSid").html(outboundConfSid_html);
        }
    })
}


//保存按钮
$('#btnSaveGroup').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    var queueName = $('#queueName').val().trim();
    if (queueName == '') {
        alert("队列名称不能为空!")
        return false;
    }
    var queueType = $('#queueType').val();
    var outboundConfSid = $('#outboundConfSid').val();
    if (queueType != 0 && outboundConfSid == -9999) {
        alert("非direct队列接出服务不能为空!")
        return false;
    }
    if (outboundConfSid == -9999) {
        outboundConfSid = '';
    }

    $("#queueType").attr("disabled",false);


    $.ajax({
        url: 'mq_queueConf.do?saveOrUpdateQueueConf',
        type: 'POST',
        dataType: 'json',
        data: {
            sid: $('#sid').val(),
            queueName: $('#queueName').val(),
            queueDesc: $('#queueDesc').val(),
            queueType: $('#queueType').val(),
            queueListenType: $('#queueListenType').val(),
            maxNum: $('#maxNum').val(),
            outboundConfSid: $('#outboundConfSid').val()
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
        "sAjaxSource": "mq_queueConf.do?queryList",
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
                "sWidth": "30px",
                "bSortable": false
            },
            {
                "mDataProp": 'queueName',
                "sTitle": "队列名称",
                "sWidth": "100px",
                "bSortable": false
            },
            {
                "mDataProp": 'queueDesc',
                "sTitle": "队列描述",
                "bSortable": false
            },
            {
                "mDataProp": 'maxNum',
                "sTitle": "并发数量",
                "bSortable": false
            },
            {
                "mDataProp": 'outboundName',
                "sTitle": "接出服务名称",
                "bSortable": false
            },
            {
                "mDataProp": 'queueTypeHZ',
                "sTitle": "交换类型",
                "bSortable": false
            },
            {
                "mDataProp": 'outboundUrl',
                "sTitle": "接出服务URL",
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
            $('td:eq(7)', nRow).html(
                    "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#groupFrom\" onclick='editGroup("
                    + aData['sid'] + ");'>编辑</button>"
            );
            /*
             $('td:eq(6)', nRow).html(
             "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#groupFrom\" onclick='editGroup("
             + aData['sid'] + ");'>编辑</button>"
             + "<button class='btn btn-info btn-xs' onclick='delGroup("
             + aData['sid'] + ");'>删除</button>"
             );
             */
        }
    });
}

//编辑组
function editGroup(id) {
    $.ajax({
        url: 'mq_queueConf.do?findById&sid=' + id,
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
                $('#queueName').val(result.queueName);
                $('#queueDesc').val(result.queueDesc);
                $('#queueType').val(result.queueType);
                $('#queueListenType').val(result.queueListenType);
                $('#maxNum').val(result.maxNum);
                $('#outboundConfSid').val(result.outboundConfSid);
                $('#queueName').attr("readonly","readonly");
                $("#queueType").attr("disabled",true);
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

$(function () {
    getGroupList();
    outboundConfInit();
})
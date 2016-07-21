var groupTable;
var usersTable;
var userEditTable;
var groupDlg;


$('#groupAdd').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    $('#sid').val(null);
    $('#outboundName').val('');
    $('#outboundDesc').val('');
    $('#wsType').val(0);
    $('#outboundUrl').val('');
    $('#timeout').val(20000);
//    $('#groupFrom').dialog('open');
//    return false;
});

//保存按钮
$('#btnSaveGroup').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    var outboundName = $('#outboundName').val();
    if (outboundName == '') {
        alert("服务名称不能为空！")
        return false;
    }
    var outboundUrl = $('#outboundUrl').val();
    if (outboundUrl == '') {
        alert("URL不能为空！")
        return false;
    }

    $.ajax({
        url: 'mq_outbound.do?saveOrUpdateOutboundConf',
        type: 'POST',
        dataType: 'json',
        data: {
            sid: $('#sid').val(),
            outboundName: $('#outboundName').val(),
            outboundDesc: $('#outboundDesc').val(),
            wsType: $('#wsType').val(),
            outboundUrl: $('#outboundUrl').val(),
            timeout: $('#timeout').val()
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

        "sAjaxSource": "mq_outbound.do?queryList",
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
                "sWidth": "20px",
                "bSortable": false
            },
            {
                "mDataProp": 'outboundName',
                "sTitle": "服务名称",
                "sWidth": "50px",
                "bSortable": false
            },
            {
                "mDataProp": 'outboundDesc',
                "sTitle": "服务描述",
                "sWidth": "60px",
                "bSortable": false
            },
            {
                "mDataProp": 'wsType',
                "sTitle": "服务类型",
                "sWidth": "30px",
                "bSortable": false
            },
            {
                "mDataProp": 'outboundUrl',
                "sTitle": "URL",
                "sWidth": "90px",
                "bSortable": false
            },
            {
                "mDataProp": 'timeout',
                "sTitle": "超时时间(MS)",
                "sWidth": "30px",
                "bSortable": false
            },
            {
                "mDataProp": null,
                "sTitle": "操作",
                "sWidth": "30px",
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
            if (aData['wsType'] == 0) {
                $('td:eq(3)', nRow).html(
                    "HTTP"
                );
            } else {
                $('td:eq(3)', nRow).html(
                    "SOAP"
                );
            }
            $('td:eq(6)', nRow).html(
                    "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#groupFrom\" onclick='editGroup("
                    + aData['sid'] + ");'>编辑</button>"
                    + "<button class='btn btn-info btn-xs' onclick='delGroup("
                    + aData['sid'] + ");'>删除</button>"
            );
        }
    });
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
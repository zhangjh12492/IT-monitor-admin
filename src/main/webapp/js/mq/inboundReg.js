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
    $('#id').val(null);
    $('#systemNo').val(dicItem);
    exchangeType_change();
//    $('#dicItem').val('');
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
        "sAjaxSource": "mq_inbound.do?selectInboundBySystemNo&systemNo=" + id,
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
                "mDataProp": 'maxLength',
                "sTitle": "最大长度(KB)",
                "bSortable": false
            },
            {
                "mDataProp": 'inboundDesc',
                "sTitle": "描述",
                "bSortable": false
            },
            {
                "mDataProp": 'exchangeType_hz',
                "sTitle": "交换机名称",
                "bSortable": false
            },
            {
                "mDataProp": 'exchangeType_hz',
                "sTitle": "交换机类型",
                "bSortable": false
            },
            {
                "mDataProp": 'routeKey',
                "sTitle": "RouteKey(topic有效)",
                "bSortable": false
            },
/*
            {
                "mDataProp": 'groupName',
                "sTitle": "组名",
                "bSortable": false
            },
*/
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

             $('td:eq(8)', nRow).html(
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

function deleteUser(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'mq_inbound.do?deleteById&sid=' + id,
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
        if($('#serviceNo').val()=='0000'){
            alert("请选择业务代码!")
            return false;
        }
        if($('#maxLength').val()==''){
            alert("最大消息长度不能为空!")
            return false;
        }
        if($('#exchangeType').val()==2 && $('#routeKey').val()==''){
            alert("topic模式routekey不能为空!")
            return false;
        }

        $.ajax({
            url: 'mq_inbound.do?saveOrUpdate',
            type: 'POST',
            dataType: 'json',
            data: {
/*
                private String systemNo;  //
            private String serviceNo;  //
            private String routeKey;  //
            private Integer groupSid;  //
            private String inboundDesc;  //
            private Integer exchangeType;  //
            private Integer sid;  //
            private Integer maxLength;  //
            private Integer exchangeConfSid; //
*/
                systemNo: $('#systemNo').val(),
                serviceNo: $('#serviceNo').val(),
                exchangeType: $('#exchangeType').val(),
                exchangeConfSid: $('#exchangeConfSid').val(),
                inboundDesc: $('#inboundDesc').val(),
                routeKey: $('#routeKey').val(),
                maxLength: $('#maxLength').val()
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
    });

    $.ajax({
        url: 'mq_sysParam.do?getDicItem',
        type: 'POST',
        dataType: 'json',
        data: {
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            for (var i = 0; i < result.length; i++) {
                if (result[i].dicName == "交换类型") {
                    exchangeType_html += "<option value='" + result[i].dicValueSecond + "'>"
                        + "[" + result[i].dicValueSecond + "]" + result[i].dicItem + "</option>";
                }
                if (result[i].dicName == "业务代码") {
                    serviceNo_html += "<option value='" + result[i].dicValue + "'>"
                        + "[" + result[i].dicValue + "]" + result[i].dicItem + "</option>";
                }
            }
            $("#exchangeType").html(exchangeType_html);
            $("#serviceNo").html(serviceNo_html);
            $('#exchangeType').val(1);
            $('#exchangeType').val(0);
        }

    })

    getGroupList();
    getUsersTable('ee');

})
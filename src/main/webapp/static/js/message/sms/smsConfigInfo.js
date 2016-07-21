var smsConfigInfoTable;


//保存按钮
$('#btnSaveSmsConfigInfo').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);

    if($('#smsSendUrl').val()=='' ){
        $("#smsSendUrl").css("borderColor","#ebccd1");
        return false;
    }
    if($('#usedStatus').val()==''){
        $("#usedStatus").css("borderColor","#ebccd1");
        return false;
    }


    $.ajax({
        url:'smsConfigInfo.do?insertSmsConfigInfo',
        type:'post',
        cache:false,
        async:false,
        timeout:1000*60,
//        url: 'smsConfigInfo.do?addSmsConfigInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(), 
        dataType: 'json',
        data: {
            "id": $('#id').val(),
            "smsSendUrl": $('#smsSendUrl').val(),
            "usedStatus": $('#usedStatus').val()

        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                $('#smsConfigInfoFormDlg').modal('hide');
                getSmsConfigInfoList();
            } else {
                alert(result.desc);
            }
        }

    });
});

/**
 * 分页展示系统数据
 */
function getSmsConfigInfoList() {
    $('#smsConfigInfoTable').dataTable().fnDestroy();
    smsConfigInfoTable = $("#smsConfigInfoTable").dataTable({
        "bDestory": true,
        "bFilter": false,

        "sAjaxSource": "smsConfigInfo.do?pageSmsConfigInfoAjax",
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
        "aoColumns": [
            {
                "mDataProp": 'id',
                "sTitle": "序号",
                "bSortable": false
            },
            {
                "mDataProp": 'smsSendUrl',
                "sTitle": "短信发送地址",
                "bSortable": false
            },
            {
                "mDataProp": 'usedStatus',
                "sTitle": "使用状态",
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
            $('td:eq(0)', nRow).html(iDisplayIndex+1);


            $('td:eq(3)', nRow).html(
                "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#smsConfigInfoFormDlg\" onclick='editSmsConfigInfo("
                + aData['id'] + ");'>编辑</button>"
                + "<button class='btn btn-info btn-xs' onclick='delSmsConfigInfo("
                + aData['id'] + ");'>删除</button>"
            );

            if (aData['usedStatus'] == 0) {
                $('td:eq(2)', nRow).html("停止");
            }else if (aData['usedStatus'] == 1) {
                $('td:eq(2)', nRow).html("运行");
            }

        }

    });

}


//编辑短信配置信息
function editSmsConfigInfo(id) {
    $.ajax({
        url: 'smsConfigInfo.do?pageSmsConfigInfoAjax&id='+id,
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
                $('#smsSendUrl').val(result.aaData[0].smsSendUrl);
                $('#usedStatus').val(result.aaData[0].usedStatus);
//                $('#userFromDlg').dialog('open');
                return false;
            } else {
                alert(result.aaData);
            }
        }

    });
}

//删除系统信息
function delSmsConfigInfo(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'smsConfigInfo.do?deleteSmsConfigInfo&id=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getSmsConfigInfoList();
                } else {
                    alert(result.desc);
                }
            }
        });
    } else {

    }
}
/**
 * 添加按钮单击事件
 */
$("#smsConfigInfoAdd").click(function(){
    $('#id').val("");
    $('#smsSendUrl').val("");
    $('#usedStatus').val("0");

});

$('#smsConfigInfoTable').on('click', 'tbody tr', function () {
    // 设置选中样式
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        smsConfigInfoTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    var sTitle;
    var nTds = $('td', this);
    var groupId = $(nTds[0]).text();
//    getsmsConfigInfoTable(groupId);
});




$(function () {
    getSmsConfigInfoList();
})
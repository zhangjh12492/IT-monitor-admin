var mailConfigInfoTable;


//保存按钮
$('#btnSaveMailConfigInfo').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);

    if($('#mailServer').val()=='' ){
        $("#mailServer").css("borderColor","#ebccd1");
        return false;
    }
    if($('#mailSender').val()==''){
        $("#mailSender").css("borderColor","#ebccd1");
        return false;
    }
    if($('#mailNickName').val()==''){
        $("#mailNickName").css("borderColor","#ebccd1");
        return false;
    }
    if($('#mailUserName').val()==''){
        $("#mailUserName").css("borderColor","#ebccd1");
        return false;
    }
    if($('#mailPassword').val()==''){
        $("#mailPassword").css("borderColor","#ebccd1");
        return false;
    }
    if($('#usedStatus').val()==''){
        $("#usedStatus").css("borderColor","#ebccd1");
        return false;
    }

    $.ajax({
    	 url:'mailConfigInfo.do?insertMailConfigInfo',
    	 type:'post',    
    	 cache:false,
    	 async:false,
    	 timeout:1000*60,   
//        url: 'mailConfigInfo.do?addMailConfigInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(), 
        dataType: 'json',
        data: {
            "id": $('#id').val(),
            "mailServer": $('#mailServer').val(),
            "mailNickName": $('#mailNickName').val(),
            "mailSender": $('#mailSender').val(),
            "mailUserName": $('#mailUserName').val(),
            "mailPassword": $('#mailPassword').val(),
            "usedStatus": $('#usedStatus').val()
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                $('#mailConfigInfoFormDlg').modal('hide');
                getMailConfigInfoList();
            } else {
                alert(result.desc);
            }
        }

    });
});

/**
 * 分页展示系统数据
 */
function getMailConfigInfoList() {
    $('#mailConfigInfoTable').dataTable().fnDestroy();
    mailConfigInfoTable = $("#mailConfigInfoTable").dataTable({
        "bDestory": true,
        "bFilter": false,

        "sAjaxSource": "mailConfigInfo.do?pageMailConfigInfoAjax",
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
                "mDataProp": 'mailServer',
                "sTitle": "mail代理发送的地址",
                "bSortable": false
            },
            {
                "mDataProp": 'mailSender',
                "sTitle": "发送者的邮箱地址",
                "bSortable": false
            },
            {
                "mDataProp": 'mailNickName',
                "sTitle": "发送者昵称",
                "bSortable": false
            },
            {
                "mDataProp": 'mailUserName',
                "sTitle": "用户登陆邮箱地址",
                "bSortable": false
            },
            {
                "mDataProp": 'mailPassword',
                "sTitle": "用户登陆邮箱密码",
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

            if (aData['usedStatus'] == 0) {
                $('td:eq(6)', nRow).html("停止");
            }else if (aData['usedStatus'] == 1) {
                $('td:eq(6)', nRow).html("运行");
            }

            $('td:eq(7)', nRow).html(
                    "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#mailConfigInfoFormDlg\" onclick='editMailConfigInfo("
                    + aData['id'] + ");'>编辑</button>"
                    + "<button class='btn btn-info btn-xs' onclick='delMailConfigInfo("
                    + aData['id'] + ");'>删除</button>"
            );


        }

    });

}


//编辑邮箱配置信息
function editMailConfigInfo(id) { 
    $.ajax({
        url: 'mailConfigInfo.do?pageMailConfigInfoAjax&id='+id,
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
                $('#mailServer').val(result.aaData[0].mailServer);
                $('#mailSender').val(result.aaData[0].mailSender);
                $('#mailNickName').val(result.aaData[0].mailNickName);
                $('#mailUserName').val(result.aaData[0].mailUserName);
                $('#mailPassword').val(result.aaData[0].mailPassword);
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
function delMailConfigInfo(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'mailConfigInfo.do?deleteMailConfigInfo&id=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                	getMailConfigInfoList();
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
$("#mailConfigInfoAdd").click(function(){
	$('#id').val("");
    $('#mailServer').val("");
    $('#mailSender').val("");
    $('#mailNickName').val("");
    $('#mailUserName').val("");
    $('#mailPassword').val("");
    $('#usedStatus').val("0");
    
});

$('#mailConfigInfoTable').on('click', 'tbody tr', function () {
    // 设置选中样式
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        mailConfigInfoTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    var sTitle;
    var nTds = $('td', this);
    var groupId = $(nTds[0]).text();
//    getmailConfigInfoTable(groupId);
}); 

 


$(function () {
	getMailConfigInfoList();
})
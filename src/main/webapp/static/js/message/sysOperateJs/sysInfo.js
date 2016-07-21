var sysInfoTable;
var groupDlg;



//保存按钮
$('#btnSaveSysInfo').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);

    var reg = new RegExp("^[0-9]*$");
    if($('#sysCode').val()=='' || !reg.test($('#sysCode').val())){
        $("#sysCode").css("borderColor","#ebccd1");
        return false;
    }
    if($('#sysName').val()==''){
        $("#sysName").css("borderColor","#ebccd1");
        return false;
    }
    if($('#sysDesc').val()==''){
        $("#sysDesc").css("borderColor","#ebccd1");
        return false;
    }

    $.ajax({
    	 url:'sysOperate.do?addSysInfo',
    	 type:'post',    
    	 cache:false,
    	 async:false,
    	 timeout:1000*60,   
//        url: 'sysOperate.do?addSysInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(), 
        dataType: 'json',
        data: {
            "id": $('#id').val(),
            "sysCode": $('#sysCode').val(),
            "sysName": $('#sysName').val(),
            "sysDesc": $('#sysDesc').val()
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                $('#sysInfoFormDlg').modal('hide');
                getSysInfoList();
            } else {
                alert(result.desc);
            }
        }

    });
});

/**
 * 分页展示系统数据
 */
function getSysInfoList() {
    $('#sysInfo').dataTable().fnDestroy();
    sysInfoTable = $("#sysInfo").dataTable({
        "bDestory": true,
        "bFilter": false,

        "sAjaxSource": "sysOperate.do?pageSysInfoAjax",
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
                "bSortable": false
            },
            {
                "mDataProp": 'sysCode',
                "sTitle": "代码",
                "bSortable": false
            },
            {
                "mDataProp": 'sysName',
                "sTitle": "名称",
                "bSortable": false
            },
            {
                "mDataProp": 'sysDesc',
                "sTitle": "描述",
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
            $('td:eq(4)', nRow).html(
                    "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#sysInfoFormDlg\" onclick='editSysInfo("
                    + aData['id'] + ");'>编辑</button>"
                    + "<button class='btn btn-info btn-xs' onclick='delSysInfo("
                    + aData['id'] + ");'>删除</button>"
            );


        }

    });

}

//编辑系统
function editSysInfo(id) { 
    $.ajax({
        url: 'sysOperate.do?pageSysInfoAjax&id='+id,
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
                $('#sysCode').val(result.aaData[0].sysCode);
                $('#sysName').val(result.aaData[0].sysName);
                $('#sysDesc').val(result.aaData[0].sysDesc);
//                $('#userFromDlg').dialog('open');
                return false;
            } else {
                alert(result.aaData);
            }
        }

    });
}

//删除系统信息
function delSysInfo(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'sysOperate.do?deleteSysInfo&id=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                	getSysInfoList();
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
$("#sysInfoAdd").click(function(){
	$('#id').val("");
	$('#sysCode').val("");
    $('#sysName').val("");
    $('#sysDesc').val("");
    
});

$('#sysInfo').on('click', 'tbody tr', function () {
    // 设置选中样式
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        sysInfoTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    var sTitle;
    var nTds = $('td', this);
    var groupId = $(nTds[0]).text();
//    getsysInfoTable(groupId);
}); 

 


$(function () {
	getSysInfoList();
})
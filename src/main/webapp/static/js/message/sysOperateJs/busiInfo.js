var busiInfoTable;
var groupDlg;
var contextPath=$("#contextPath").val();



//保存按钮
$('#btnSaveBusiInfo').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);

    var reg = new RegExp("^[0-9]*$");

    if($('#busiCode').val()=='' || !reg.test($('#busiCode').val())){
        $("#busiCode").css("borderColor","#ebccd1");
        return false;
    }
    if($('#busiDesc').val()==''){
        $("#busiDesc").css("borderColor","#ebccd1");
        return false;
    }
    if($('#sysId').val()==''){
        $("#sysId").css("borderColor","#ebccd1");
        return false;
    }

    $.ajax({
        url:'busi.do?addBusiInfo',
        type:'post',
        cache:false,
        async:false,
        timeout:1000*60,
//        url: 'sysOperate.do?addBusiInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(),
        dataType: 'json',
        data: {
            "id": $('#id').val(),
            "busiCode": $('#busiCode').val(),
            "busiDesc": $('#busiDesc').val(),
            "sysId": $('#sysId').val()
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                $('#busiInfoFormDlg').modal('hide');
                getBusiInfoList();
            } else {
                alert(result.desc);
            }
        }

    });
});


function getBusiInfoList() {
    $('#busiInfo').dataTable().fnDestroy();
    busiInfoTable = $("#busiInfo").dataTable({
        "bDestory": true,
        "bFilter": false,

        "sAjaxSource": "busi.do?pageBusiInfoAjax",
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
                "mDataProp": 'busiCode',
                "sTitle": "代码",
                "bSortable": false
            },
            {
                "mDataProp": 'busiDesc',
                "sTitle": "名称",
                "bSortable": false
            },
            {
                "mDataProp": 'sysId',
                "sTitle": "系统",
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
                "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#busiInfoFormDlg\" onclick='editBusiInfo("
                + aData['id'] + ");'>编辑</button>"
                + "<button class='btn btn-info btn-xs' onclick='delBusiInfo("
                + aData['id'] + ");'>删除</button>"
            );
            var sysInfo=getSysInfoBySysId(aData['sysId']);
            $('td:eq(3)', nRow).html(sysInfo["sysName"]);
        }

    });

}

//编辑业务
function editBusiInfo(id) {
    $.ajax({
        url: 'busi.do?pageBusiInfoAjax&id='+id,
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
                $('#busiCode').val(result.aaData[0].busiCode);
                $('#busiDesc').val(result.aaData[0].busiDesc);
                busiInfoSysInfo(result.aaData[0].sysId);
//                $('#userFromDlg').dialog('open');
                return false;
            } else {
                alert(result.aaData);
            }
        }

    });
}

//删除业务信息
function delBusiInfo(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'busi.do?deleteBusiInfo&id=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getBusiInfoList();
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
$("#busiInfoAdd").click(function(){
    $('#id').val("");
    $('#busiCode').val("");
    $('#busiDesc').val("");
    busiInfoSysInfo(null);
});

$('#busiInfo').on('click', 'tbody tr', function () {
    // 设置选中样式
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        busiInfoTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    var sTitle;
    var nTds = $('td', this);
    var groupId = $(nTds[0]).text();
//    getBusiInfoTable(groupId);
});

/**
 * 展示系统信息的下拉框
 */
function busiInfoSysInfo(selectSysId){

    var selectHtml="";
    $.ajax({
        url: contextPath+'sysOperate.do?getSysInfoAjax',
        type: 'POST',
        dataType: 'json',
        async:false,
        error: function () {
            alert('根据系统id获取到系统信息失败！');
        },
        success: function (data) {
            if(data!=null && data.length>0){
                if(selectSysId!=null&&selectSysId!=''){
                    $.each(data,function(index,item){
                        if(selectSysId==item.id){
                            selectHtml+='<option value="'+item.id+'" selected="selected">'+item.sysName+'</option>';
                        }else{
                            selectHtml+='<option value="'+item.id+'">'+item.sysName+'</option>';
                        }
                    });
                }else{
                    selectHtml+='<option value="" selected>--请选择--</option>';
                    $.each(data,function(index,item){
                        selectHtml+='<option value="'+item.id+'">'+item.sysName+'</option>';
                    });
                }
            }
        }
    });
    $("#sysId").html(selectHtml);
}
/**
 * 根据系统编号获取到系统信息
 * @returns {___anonymous9140_9141}
 */
function getSysInfoBySysId(sysId){
    var sysInfo={};
    $.ajax({
        url: contextPath+'sysOperate.do?getSysInfoBySysId&id=' + sysId,
        type: 'POST',
        dataType: 'json',
        async:false,
        error: function () {
            alert('根据系统id获取到系统信息失败！');
        },
        success: function (result) {
            if(result!=null){
                sysInfo={"sysCode":result.sysCode,"sysDesc":result.sysDesc,"sysName":result.sysName};
            }
        }

    })
    return sysInfo;
}

$(function () {
    getBusiInfoList();
})
var earlyWarnInfoTable;
var groupDlg;
var contextPath=$("#contextPath").val();
$("#sysInfoAdd").click(function(){
	$('#id').val('');
    $('#description').val('');
    $('#flag').val('0');
//    $('#url').val('');
    $('#status').val('1');
    $('#warnCountMin').val('10');
    $('#warnCountMax').val('20');
    $('#errorCountMin').val('5');
    $('#errorCountMax').val('10');
    $('#sysChildActiveThresholdMinCount').val('1');
    $('#sysChildActiveThresholdMaxCount').val('1');
    $('#sysReqApdexThresholdMin').val('300');
    $('#sysReqApdexThresholdMax').val('700');
    $('#sysId').val('');
//    $('#busiId').val('');
    earlyWarnSysInfo(null);
}); 

//保存按钮
$('#btnSaveEarlyWarnInfo').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    var reg = new RegExp("^[0-9]*$");
    if($('#flag').val()==''){
        $("#flag").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#flag").removeClass("bor_reg_f_red");
    }

    if($('#status').val()==''){
        $("#status").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#status").removeClass("bor_reg_f_red");
    }

    if($('#warnCountMin').val()=='' || !reg.test($('#warnCountMin').val())){
        $("#warnCountMin").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#warnCountMin").removeClass("bor_reg_f_red");
    }

    if($('#warnCountMax').val()=='' || !reg.test($('#warnCountMax').val())){
        $("#warnCountMax").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#warnCountMax").removeClass("bor_reg_f_red");
    }

    if($('#errorCountMin').val()=='' || !reg.test($('#warnCountMax').val())){
        $("#errorCountMin").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#errorCountMin").removeClass("bor_reg_f_red");
    }

    if($('#errorCountMax').val()=='' || !reg.test($('#warnCountMax').val())){
        $("#errorCountMax").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#errorCountMax").removeClass("bor_reg_f_red");
    }

    if($('#sysChildActiveThresholdMinCount').val()=='' || !reg.test($('#sysChildActiveThresholdMinCount').val())){
        $("#sysChildActiveThresholdMinCount").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#sysChildActiveThresholdMinCount").removeClass("bor_reg_f_red");
    }

    if($('#sysChildActiveThresholdMaxCount').val()=='' || !reg.test($('#sysChildActiveThresholdMaxCount').val())){
        $("#sysChildActiveThresholdMaxCount").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#sysChildActiveThresholdMaxCount").removeClass("bor_reg_f_red");
    }

    if($('#sysReqApdexThresholdMin').val()=='' || !reg.test($('#sysReqApdexThresholdMin').val())){
        $("#sysReqApdexThresholdMin").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#sysReqApdexThresholdMin").removeClass("bor_reg_f_red");
    }

    if($('#sysReqApdexThresholdMax').val()=='' || !reg.test($('#sysReqApdexThresholdMax').val())){
        $("#sysReqApdexThresholdMax").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#sysReqApdexThresholdMax").removeClass("bor_reg_f_red");
    }

    if($('#sysId').val()==''){
        $("#sysId").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#sysId").removeClass("bor_reg_f_red");
    }

    if($('#description').val()==''){
        $("#description").addClass("bor_reg_f_red");
        return false;
    }else{
        $("#description").removeClass("bor_reg_f_red");
    }



    $.ajax({
    	 url:'mesEarlyWarn.do?insertMesEarlyWarn',
    	 type:'post',    
    	 cache:false,
    	 async:false,
    	 timeout:1000*60,   
//        url: 'mesEarlyWarn.do?addearlyWarnInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(), 
        dataType: 'json',
        data: {
            "id": $('#id').val(),
            "description": $('#description').val(),
//            "url": $('#url').val(),
            "flag": $('#flag').val(),
            "status": $('#status').val(),
            "warnCountMin": $('#warnCountMin').val(),
            "warnCountMax": $('#warnCountMax').val(),
            "errorCountMin": $('#errorCountMin').val(), 
            "errorCountMax": $('#errorCountMax').val(), 
            "sysChildActiveThresholdMinCount": $('#sysChildActiveThresholdMinCount').val(),
            "sysChildActiveThresholdMaxCount": $('#sysChildActiveThresholdMaxCount').val(),
            "sysReqApdexThresholdMin": $('#sysReqApdexThresholdMin').val(), 
            "sysReqApdexThresholdMax": $('#sysReqApdexThresholdMax').val(), 
            "busiId": $('#busiId').val(), 
            "sysId": $('#sysId').val() 
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                $('#earlyWarnInfoFormDlg').modal('hide');
                getEarlyWarnInfoList();
                
            } else {
                alert(result.desc);
            }
        }

    });
});
/**
 * 展示系统预警配置分页数据
 */
function getEarlyWarnInfoList() {
    $('#earlyWarnInfo').dataTable().fnDestroy();
    earlyWarnInfoTable = $("#earlyWarnInfo").dataTable({
        "bDestory": true,
        "bAutoWidth":false,
        "bFilter": false,
        "bJQueryUI": true,
        "sScrollY": "120%",
        "sScrollXInner": "120%",
        "sAjaxSource": "mesEarlyWarn.do?pageEarlyWarnInfoAjax",
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
                "sWidth":"20px",
                "mDataProp": 'id',
                "sTitle": "",
                "bSortable": false,

            },{
                "sWidth":"50px",
                "mDataProp": 'sysId',
                "sTitle": "系统名称",
                "bSortable": false
            },

//            {
//                "mDataProp": 'busiId',
//                "sTitle": "业务编号",
//                "bSortable": false
//            },
            {
                "sWidth":"50px",
                "mDataProp": 'warnCountMin',
                "sTitle": "警告阀值最小值",
                "bSortable": false
            },
            {
            	"mDataProp": 'warnCountMax',
            	"sTitle": "警告阀值最大值",
            	"bSortable": false
            },
            {
                "mDataProp": 'errorCountMin',
                "sTitle": "异常阀值最小值",
                "bSortable": false
            },
            {
            	"mDataProp": 'errorCountMax',
            	"sTitle": "异常阀值最大值",
            	"bSortable": false
            },
            {
            	"mDataProp": 'sysChildActiveThresholdMinCount',
            	"sTitle": "存活实例阀值最小值",
            	"bSortable": false
            },
            {
            	"mDataProp": 'sysChildActiveThresholdMaxCount',
            	"sTitle": "存活实例阀值最大值",
            	"bSortable": false
            },
            {
            	"mDataProp": 'sysReqApdexThresholdMin',
            	"sTitle": "Apdex阀值最小值",
            	"bSortable": false
            },
            {
            	"mDataProp": 'sysReqApdexThresholdMax',
            	"sTitle": "Apdex阀值最大值",
            	"bSortable": false
            },
            {
                "mDataProp": 'flag',
                "sTitle": "是否有效",
                "bSortable": false
            },
            {
                "mDataProp": 'status',
                "sTitle": "开启状态",
                "bSortable": false
            },
            {
                "mDataProp": 'description',
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
                target :["_all"]
            },

        ],
        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {

            $('td:eq(0)', nRow).html(iDisplayIndex+1);

        	var sysInfo=getSysInfoBySysId(aData['sysId']);
        	$('td:eq(1)', nRow).html(sysInfo["sysName"]);
        	 
        	if (aData['flag'] == 0) {
                $('td:eq(10)', nRow).html(
                    "有效");
            }else if (aData['flag'] == 1) {
                $('td:eq(10)', nRow).html(
                "无效");
            }
        	
        	if (aData['status'] == 0) {
                $('td:eq(11)', nRow).html(
                    " 开启 ");
            }else if (aData['status'] == 1) {
                $('td:eq(11)', nRow).html(
                " 停止 ");
            }
        	
//        	
        	
            $('td:eq(13)', nRow).html(
                    "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#earlyWarnInfoFormDlg\" onclick='editearlyWarnInfo("
                    + aData['id'] + ");'>编辑</button>"
                    + "<button class='btn btn-info btn-xs' onclick='delearlyWarnInfo("
                    + aData['id'] + ");'>删除</button>" 
            );
        }

    });

}

//编辑预警配置
function editearlyWarnInfo(id) {
    $.ajax({
        url: 'mesEarlyWarn.do?pageEarlyWarnInfoAjax&id='+id,
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
                $('#description').val(result.aaData[0].description);
//                $('#url').val(result.aaData[0].url);
                $('#flag').val(result.aaData[0].flag);
                $('#status').val(result.aaData[0].status);
                $('#warnCountMin').val(result.aaData[0].warnCountMin);
                $('#warnCountMax').val(result.aaData[0].warnCountMax);
                $('#errorCountMin').val(result.aaData[0].errorCountMin);
                $('#errorCountMax').val(result.aaData[0].errorCountMax);
                $('#sysChildActiveThresholdMinCount').val(result.aaData[0].sysChildActiveThresholdMinCount);
                $('#sysChildActiveThresholdMaxCount').val(result.aaData[0].sysChildActiveThresholdMaxCount);
                $('#sysReqApdexThresholdMin').val(result.aaData[0].sysReqApdexThresholdMin);
                $('#sysReqApdexThresholdMax').val(result.aaData[0].sysReqApdexThresholdMax);
                $('#sysId').val(result.aaData[0].sysId);
                $('#busiId').val(result.aaData[0].busiId);
                earlyWarnSysInfo(result.aaData[0].sysId);
                return false;
            } else {
                alert(result.aaData);
            }
        }

    });
}

//删除预警配置信息
function delearlyWarnInfo(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'mesEarlyWarn.do?deleteEarlyWarnInfo&id=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                	getEarlyWarnInfoList();
                } else {
                    alert(result.desc);
                }
            }

        })

    } else {

    }
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


$('#earlyWarnInfo').on('click', 'tbody tr', function () {
    // 设置选中样式
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
//        groupTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    var sTitle;
    var nTds = $('td', this);
    var groupId = $(nTds[0]).text();
});
/**
 * 展示系统信息的下拉框
 */
function earlyWarnSysInfo(selectSysId){



    var mesEarlyWarnAllSys=[];
    $.ajax({
        url: contextPath+'mesEarlyWarn.do?getAllEarlyWarnBySysIdAjax',
        type: 'POST',
        dataType: 'json',
        async:false,
        error: function () {
            alert('根据系统id获取到系统信息失败！');
        },
        success: function (data) {
            $.each(data,function(index,item){
                if(item.sysId==selectSysId){
                }else{
                    mesEarlyWarnAllSys.push(item.sysId);
                }
            });
        }
    });


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
                            var disabled="";
                            $.each(mesEarlyWarnAllSys, function (mesSysIndex, mesSysItem) {
                                if(item.id==mesSysItem){
                                    disabled="disabled";
                                }
                            });
                            selectHtml+='<option value="'+item.id+'" '+disabled+'>'+item.sysName+'</option>';
                        }
                    });
                }else{
                    selectHtml+='<option value="" selected>--请选择--</option>';
                    $.each(data,function(index,item){
                        var disabled="";
                        $.each(mesEarlyWarnAllSys, function (mesSysIndex, mesSysItem) {
                            if(item.id==mesSysItem){
                                disabled="disabled";
                            }
                        });
                        selectHtml+='<option value="'+item.id+'" '+disabled+'>'+item.sysName+'</option>';
                    });
                }
            }
        }
    });
    $("#sysId").html(selectHtml);
}
/**
 * 页面初始化加载预警列表
 */
$(function () {
	getEarlyWarnInfoList();
})
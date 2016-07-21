var mesProcessTrackTable;
var groupDlg;
var contextPath=$("#contextPath").val();

function getMesProcessTrackList() {
//	$('#mesProcessTrackListFormDlg').modal('show').css({
//        width: 'auto',
//        'margin-left': function () {
//            return -($(this).width() / 2);
//        }
//    });
    $('#mesProcessTrack').dataTable().fnDestroy();
    mesProcessTrackTable = $("#mesProcessTrack").dataTable({
        "bDestory": true,
        "bFilter": false,

        "sAjaxSource": "mesProcessTrack.do?getMesProcessTrackList",
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
                "sTitle": "系统",
                "bSortable": false
            },
            {
                "mDataProp": 'errId',
                "sTitle": "异常编号",
                "bSortable": false
            },
            {
                "mDataProp": 'userId',
                "sTitle": "用户",
                "bSortable": false
            },
            {
                "mDataProp": 'processStatus',
                "sTitle": "处理状态",
                "bSortable": false
            },
            {
                "mDataProp": 'description',
                "sTitle": "描述",
                "bSortable": false
            },
            {
                "mDataProp": 'createdTime',
                "sTitle": "创建日期",
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

            var sysInfo=getSysInfoBySysCode(aData.sysCode);
            $('td:eq(1)',nRow).html(sysInfo.sysName);

            var syUser=getSysInfoBySysId(aData["userId"]);
            $('td:eq(3)', nRow).html(syUser["name"]);

        	if (aData['processStatus'] == 2){
        		 $('td:eq(7)', nRow).html(
                         "<button class='btn btn-info btn-xs' onclick='showMesProcessTrackByErrId(\"" 
                         + aData['errId'] + "\") ' data-toggle=\"modal\"  >查看历史</button>"  
                 );
        	}else{
        		 $('td:eq(7)', nRow).html(
                         "<button class='btn btn-info btn-xs' data-toggle=\"modal\"  onclick='addNewMesProcessTrack(\""
                         + aData['errId'] + "\",\""+aData["userId"]+"\",\""+aData["processStatus"]+"\")' >处理完成</button>" 
                         + "<button class='btn btn-info btn-xs' onclick='showMesProcessTrackByErrId(\"" 
                         + aData['errId'] + "\") ' data-toggle=\"modal\"  >查看历史</button>"  
                 );
        	}
           
            if (aData['processStatus'] == 0) {
                $('td:eq(4)', nRow).html("<span class=\"label label-sm label-danger\"> 未处理 </span>");
            }else if (aData['processStatus'] == 1) {  
                $('td:eq(4)', nRow).html("<span class=\"label label-sm label-warning\"> 正在处理 </span>");
            }else if (aData['processStatus'] == 2) {    
                $('td:eq(4)', nRow).html("<span class=\"label label-sm label-success\"> 已处理</span>");
            }

        }

    }); 

}

function showMesProcessTrackByErrId(errId){
	
	$('#mesProcessTrackListFormDlg').modal('show');
	$('#mesProcessTrackByErrId').dataTable().fnDestroy();
    mesProcessTrackTable = $("#mesProcessTrackByErrId").dataTable({
        "bDestory": true,
        "bFilter": false,

        "sAjaxSource": "mesProcessTrack.do?getMesProcessTrackListByErrId&errId="+errId, 
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
                "mDataProp": 'errId',
                "sTitle": "异常编号",
                "bSortable": false
            },
            {
                "mDataProp": 'sysCode',
                "sTitle": "系统",
                "bSortable": false
            },
            {
                "mDataProp": 'userId',
                "sTitle": "用户",
                "bSortable": false
            },
            {
                "mDataProp": 'processStatus',
                "sTitle": "处理状态",
                "bSortable": false
            },
            {
                "mDataProp": 'description',
                "sTitle": "描述",
                "bSortable": false
            },
            {
                "mDataProp": 'createdTime',
                "sTitle": "创建日期",
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

            var sysInfo=getSysInfoBySysCode(aData.sysCode);
            $('td:eq(1)',nRow).html(sysInfo.sysName);

            var syUser=getSysInfoBySysId(aData["userId"]);
            $('td:eq(3)', nRow).html(syUser["name"]);
        	if (aData['processStatus'] == 0) {
                $('td:eq(4)', nRow).html("<span class=\"label label-sm label-danger\"> 未处理 </span>");
            }else if (aData['processStatus'] == 1) {  
                $('td:eq(4)', nRow).html("<span class=\"label label-sm label-warning\"> 正在处理 </span>");
            }else if (aData['processStatus'] == 2) {    
                $('td:eq(4)', nRow).html("<span class=\"label label-sm label-success\"> 已处理</span>");
            } 
        }
    });
}  

/**
 * 单击处理完成按钮,添加处理完成的处理轨迹
 * @param errId
 * @param userId
 * @param processStatus
 */
function addNewMesProcessTrack(errId,userId,processStatus){
	$("#errId").val(errId);
	$("#userId").val(userId);
	$("#processStatus").val('2');
	$("#mesProcessTrackFormDlg").modal("show");
	
}

//添加新的处理状态
$("#btnSaveMesProcessTrack").click(function(){
	var processStatus=$("#processStatus option:selected").val();
	var description=$("#description").val();
	var errId=$("#errId").val();
	var userId=$("#userId").val();
    $.ajax({
        url: 'mesProcessTrack.do?addProcessTrackView',
        type: 'POST',
        data:{
        	'errId':errId,
        	'userId':userId,
        	'description':description,
        	'processStatus':processStatus
        },
        dataType: 'json',
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
        	if (result.desc == null || result.desc == '') {
                $('#mesProcessTrackFormDlg').modal('hide');
                $("#errId").val('');
            	$("#userId").val('');
            	$("#processStatus").val('');
                getMesProcessTrackList();
            } else { 
                alert(result.desc);   
            }
        }

    });
});



/**
 * 根据userId获取到用户信息
 * @returns {___anonymous9140_9141}
 */
function getSysInfoBySysId(userId){
    var syUser={};
    $.ajax({
        url: contextPath+'userInfo.do?getUserByCondition&id=' + userId,
        type: 'POST',
        dataType: 'json',
        async:false,
        error: function () {
            alert('根据userid获取用户信息失败！');
        },
        success: function (result) {
            if(result!=null){
                syUser={"id":result.id,"name":result.name};
            }
        }

    })
    return syUser;
}


/**
 * 根据系统编号获取到系统信息
 * @returns {___anonymous9140_9141}
 */
function getSysInfoBySysCode(sysCode){
    var sysInfo={};
    $.ajax({
        url: contextPath+'sysOperate.do?getSysInfoBySysCode&sysCode=' + sysCode,
        type: 'POST',
        dataType: 'json',
        async:false,
        error: function () {
            alert('根据系统code获取到系统信息失败！');
        },
        success: function (result) {
            if(result!=null){
                sysInfo={"id":result.id,"sysCode":result.sysCode,"sysDesc":result.sysDesc,"sysName":result.sysName};
            }
        }

    });
    return sysInfo;
}

$('#mesProcessTrack').on('click', 'tbody tr', function () {
    // 设置选中样式
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        mesProcessTrackTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    var sTitle;
    var nTds = $('td', this);
    var groupId = $(nTds[0]).text();
//    getmesProcessTrackTable(groupId);
});


function deleteUser(id) {
    alert(id);
}


$(function () {
	getMesProcessTrackList();
})
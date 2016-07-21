var messageListTable;
var groupDlg;
var contextPath=$("#contextPath").val();


//保存按钮
$('#btnSaveSysInfo').click(function () {

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
            "sysDesc": $('#sysDesc').val() 
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                $('#sysInfoFormDlg').modal('hide');
                getMessageListNoPage();
            } else {
                alert(result.desc);
            }
        }

    });
});

///**
// * 获取到当前用户要展示的异常信息的列表
// */
//function getMessageList() {
//
//
//	var errLevel=$("#errLevel").val();
//	var processStatus=$("#processStatus").val();
//	var startRow=$("#startRow").val();
//    $('#messageList').dataTable().fnDestroy();
//    messageListTable = $("#messageList").dataTable({
//        "bDestory": true,
//        "bFilter": false,
//        "bInfo": false,
//        "bPaginate": true,
//        "sAjaxSource": "mesDatail.do?getUserMesDetailList&errLevel="+errLevel+"&processStatus="+processStatus+"&startRow="+$("#startRow").val(),
//        "bProcessing": true,
//        "searching": false, //去掉搜索框
//        "bLengthChange": false,// 是否允许自定义每页显示条数.
////        "aLengthMenu": [
////            [1, 5, 10, 25, 50, 100, -1],
////            [1, 5, 10, 25, 50, 100, "所有"]
////        ],
//        "bServerSide": true,
//        "iDisplayLength": 10,
//        "oLanguage": {//语言设置
//            "sLengthMenu": "每页显示  _MENU_ 条记录",
//            "sInfo": "",
//            "oPaginate": {
//                "sFirst": "首页",
//                "sPrevious": "前一页",
//                "sNext": "后一页",
//                "sLast": "尾页"
//            },
//            "sZeroRecords": "抱歉， 没有找到",
//            "sInfoEmpty": "没有数据"
//        },
////        "sAjaxDataProp": 'object',  //这个改写默认取值的JSON对象
//        "aoColumns": [
//            {
//                "mDataProp": null,
//                "sTitle": "选择",
//                "bSortable": false
//            },
//            {
//                "mDataProp": 'errId',
//                "sTitle": "编号",
//                "bSortable": false
//            },
//            {
//                "mDataProp": 'sysCode',
//                "sTitle": "系统编码",
//                "bSortable": false
//            },
//            {
//                "mDataProp": 'busiCode',
//                "sTitle": "业务编码",
//                "bSortable": false
//            },
//            {
//                "mDataProp": 'errCode',
//                "sTitle": "用户自定义异常编码",
//                "bSortable": false
//            },
//            {
//                "mDataProp": 'processStatus',
//                "sTitle": "处理状态",
//                "bSortable": false
//            },
//            {
//                "mDataProp": 'errLevel',
//                "sTitle": "异常等级",
//                "bSortable": false
//            },
//            {
//                "mDataProp": 'createDate',
//                "sTitle": "创建日期",
//                "bSortable": false
//            },
//            {
//                "mDataProp": null,
//                "sTitle": "操作",
//                "bSortable": false
//            }
//        ],
//        "aoColumnDefs": [
//            {
//                sDefaultContent: '',
//                aTargets: [ '_all' ]
//            }
//        ],
//        "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
//        	 if(aData['processStatus']==0){
//        		 $('td:eq(0)', nRow).html("<input name='errIds' type='checkbox' value='"+aData["errId"]+"' />");
//        	 }
//        	 $('td:eq(1)', nRow).html("<a style='color:000000' href='javascript:showMesDetail(\""+aData["errId"]+"\")' >"+aData["errId"]+" </a>");
//
//            var sysInfo=getSysInfoBySysCode(aData["sysCode"]);
//            $('td:eq(2)', nRow).html(sysInfo.sysName+"-"+sysInfo.sysCode);
//
//            var busiInfo=getBusiInfoBySysIdAndBusiCode(sysInfo.id,aData["busiCode"]);
//            $('td:eq(3)', nRow).html(busiInfo.busiDesc+"-"+busiInfo.busiCode);
//
//
//        	 if (aData['processStatus'] == 0) {
//                 $('td:eq(5)', nRow).css("vertical-align","middle").html("<span class=\"label label-sm label-danger\"> 未处理 </span>");
//             }else if (aData['processStatus'] == 1) {
//                 $('td:eq(5)', nRow).css("vertical-align","middle").html("<span class=\"label label-sm label-warning\"> 正在处理 </span>");
//             }else if (aData['processStatus'] == 2) {
//                 $('td:eq(5)', nRow).css("vertical-align","middle").html("<span class=\"label label-sm label-success\"> 已处理 </span>");
//             }
//
//        	 if (aData['errLevel'] == 1) {
//                 $('td:eq(6)', nRow).css("vertical-align","middle").html("<span class=\"label label-sm label-warning\"> 警告 </span>");
//             }else if (aData['errLevel'] == 2) {
//                 $('td:eq(6)', nRow).css("vertical-align","middle").html("<span class=\"label label-sm label-danger\"> 异常</span>");
//             }
//        	 if(aData['processStatus']==0){
//        		 $('td:eq(8)', nRow).css("vertical-align","middle").html("<button class='btn btn-info btn-xs' onclick='processMes(\""+ aData['errId'] + "\")'>处理</button>");
//
//        	 }
//        	 $("#startRow").val(aData['errId']);
//        }
//
//    });
//    var lastTd=$("#messageList tbody").html();
//}
/**
 *
 * @param pageTurnFlag  是查询前一页,还是查询后一页,previous前一页,next后一页
 */
function getMessageListNoPage(pageTurnFlag) {
    var errLevel=$("#errLevel").val();
    var processStatus=$("#processStatus").val();
    var startRow=$("#startRow").val();
    var startTime=$("#startTime").val();
    var endTime=$("#endTime").val();

    errLevel=(errLevel=='null'||errLevel==null)?'':errLevel;
    processStatus=(processStatus=='null'||processStatus==null)?'':processStatus;
    startRow=(startRow=='null'||startRow==null)?'':startRow;
    pageTurnFlag=(pageTurnFlag=='null'||pageTurnFlag==null)?'':pageTurnFlag;

    $.ajax({
        url:"mesDatail.do?getUserMesDetailList&errLevel="+errLevel+"&processStatus="+processStatus+"&startRow="+startRow+
                "&pageTurnFlag="+pageTurnFlag+"&startTime="+startTime+"&endTime="+endTime,
        type : 'post',
        cache : false,
        async : true,
        timeout : 1000 * 30,
        dataType : 'json',
        beforeSend:function(){
            $("#messageList_processing").css("display","block");
        },
        complete:function(){
            $("#messageList_processing").css("display","none");
        },
        error : function() {

            alert('网络失败！');
        },
        success : function(result) {
            var data=result.aaData;
            if(data!=null&&data.length>0){
                var tableObj=$("#messageList");
                var thead="<thead>"+$("#messageList thead").html()+"</thead>",tbody="";
                $("#startRow").val(result.startRow);    //设置hbase开始行的值

                /*修改上一页和下一页的禁用状态*/
                if(result.hasPrevious!=null && result.hasPrevious==true){
                    $("#messageList_previous").removeClass("disabled");
                }else{
                    $("#messageList_previous").addClass("disabled");
                }
                if(result.hasNext!=null && result.hasNext==true){
                    $("#messageList_next").removeClass("disabled");
                }else{
                    $("#messageList_next").addClass("disabled");
                }
                /*填充数据*/
                $.each(data, function(index, item) {
                    if (item != null && item != '') {
                        tbody+="<tr>";
                        /*多选框*/
                        if(item['processStatus']==0){
                            tbody+="<td><input name='errIds' type='checkbox' value='"+item["errId"]+"' /></td>";
                        }else{
                            tbody+="<td></td>";
                        }
                        /*异常编号*/
                        tbody+="<td><a style=\"color:#000000\" href='javascript:showMesDetail(\""+item.errId+"\")'>"+item.errId+" </a></td>";
                        /*系统*/
                        var sysInfo=getSysInfoBySysCode(item.sysCode);
                        tbody+="<td>"+sysInfo.sysName+"-"+sysInfo.sysCode+"</td>";
                        /*业务*/
                        var busiInfo=getBusiInfoBySysIdAndBusiCode(sysInfo.id,item.busiCode);
                        tbody+="<td>"+busiInfo.busiDesc+"-"+busiInfo.busiCode+"</td>";
                        tbody+="<td>"+item.errCode+"</td>";

                        /*处理状态*/
                        if (item['processStatus'] == 0) {
                            tbody+="<td style='vertical-align: middle;'><span class=\"label label-sm label-danger\"> 未处理 </span></td>";
                        }else if (item['processStatus'] == 1) {
                            tbody+="<td style='vertical-align: middle;'><span class=\"label label-sm label-warning\"> 正在处理 </span></td>";
                        }else if (item['processStatus'] == 2) {
                            tbody+="<td style='vertical-align: middle;'><span class=\"label label-sm label-success\"> 已处理 </span></td>";
                        }
                        /*异常等级*/
                        if (item['errLevel'] == 1) {
                            tbody+="<td style='vertical-align: middle;'><span class=\"label label-sm label-warning\"> 警告 </span></td>";
                        }else if (item['errLevel'] == 2) {
                            tbody+="<td style='vertical-align: middle;'><span class=\"label label-sm label-danger\"> 异常</span></td>";
                        }
                        /*创建日期*/
                        tbody+="<td>"+item.createDate+"</td>";
                        /*操作*/
                        if(item['processStatus']==0){
                            tbody+="<td style='vertical-align: middle;'><button class='btn btn-info btn-xs' onclick='processMes(\""+ item['errId'] + "\")'>处理</button></td>";
                        }else{
                            tbody+="<td></td>";
                        }
                        tbody+="</tr>"
                    }
                });
                tbody="<tbody>"+tbody+"</tbody>";
                tableObj.html(thead);
                tableObj.append(tbody);
                $("#messageList").parent().next().css("display","");
            }else{
                $("#messageList tbody").remove();
                $("#messageList thead").after('<tbody><tr class="odd"><td valign="top" colspan="9" class="dataTables_empty">抱歉， 没有找到</td></tr></tbody>');
                $("#messageList").parent().next().css("display","none");
            }
        }
    });
}
/**
 * 上一页单击
 */
$("#messageList_previous").children(":first").click(function(){

    if($("#messageList_previous").hasClass("disabled")){
    }else{
        //getMessageListNoPage("previous");

    }
});
/**
 * 下一页单击
 */
$("#messageList_next").children(":first").click(function(){
    if($("#messageList_next").hasClass("disabled")){
    }else{
        getMessageListNoPage("previous");
    }
});


/**
 * 处理单条信息按钮
 * @param errId
 */
function processMes(errId){  

		$.ajax({
		url : 'mesChart.do?updateMesOneStatus&errId=' + errId
				+ '&processStatus=1',  
		type : 'post',
		cache : false,
		async : false,
		timeout : 1000 * 60,
		// url:
		// 'sysOperate.do?addSysInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(),
		dataType : 'json',
		error : function() {
			alert('网络失败！');
		},
		success : function(result) {
			if (result.desc == null || result.desc == '') {
                getMessageListNoPage(null);
				alert("修改成功");
			} else {
				alert(result.desc);
			}
		}

	});
}
/**
 * 批量处理
 */  
function processMoreMes(){
	var errId=''; 
	$("input[name='errIds']:checkbox").each(function(){
		if($(this).attr("checked")){
			errId+=$(this).val()+","; 
		}
	}); 
	if(errId!=null&&errId.length>0){
		errId=errId.substr(0,errId.length-1);
		$.ajax({
			url : 'mesChart.do?updateMoreMesStatus&errId=' + errId + '&processStatus=1',
			type : 'post',
			cache : false,
			async : false,
			timeout : 1000 * 60,
			// url:
			// 'sysOperate.do?addSysInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(),
			dataType : 'json',
			error : function() {
				alert('网络失败！');
			},
			success : function(result) {
				if (result.desc == null || result.desc == '') {
                    getMessageListNoPage(null);
					alert("修改成功");
				} else {
					alert(result.desc);
				}
			}

		});
	}else{
		alert("请选择要处理的信息.");
	}
	  
}

/**
 * 显示单条信息的详细信息
 * @param errId
 */
function showMesDetail(errId){
//	var errId=$("#showMesDetail").html();    
		$.ajax({
			 url:'mesChart.do?findMessByErrId&errId='+errId,
	    	 cache:false,
	    	 async:false,
	    	 timeout:1000*60,  
	    	 success:function(item){
	    		 $("#detail_sysCode").html(item.sysCode);
	    		 $("#detail_busiCode").html(item.busiCode);
	    		 $("#detail_busiDesc").html(item.busiDesc);
	    		 $("#detail_errCode").html(item.errCode);
	    		 $("#detail_errDesc").html(item.errDesc);
	    		 $("#detail_sysErrCode").html(item.sysErrCode);
	    		 $("#detail_sysErrDesc").html(item.sysErrDesc);
	    		 $("#detail_throwableDesc").html(item.throwableDesc);
	    	 },
	    	 error:function(){
	    		 alert("查询失败!");
	    	 }
		}); 
		$("#mesDetail").modal("show"); 
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
/**
 * 根据系统id和业务code查询业务信息
 */
function getBusiInfoBySysIdAndBusiCode(sysId,busiCode){
    var busiInfo={};
    $.ajax({
        url: contextPath+'busi.do?findBusiInfoByCondition&sysId=' + sysId+"&busiCode="+busiCode,
        type: 'POST',
        dataType: 'json',
        async:false,
        error: function () {
            alert('根据条件查询业务信息失败！');
        },
        success: function (result) {
            if(result!=null){
                busiInfo={"id":result.id,"busiCode":result.busiCode,"busiDesc":result.busiDesc};
            }
        }

    });
    return busiInfo;
}


$('#sysInfo').on('click', 'tbody tr', function () {
    // 设置选中样式
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        messageListTable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    var sTitle;
    var nTds = $('td', this);
    var groupId = $(nTds[0]).text();
//    getmessageListTable(groupId);
});


function deleteUser(id) {
    alert(id);
}


$(function () {
    getMessageListNoPage(null);
});
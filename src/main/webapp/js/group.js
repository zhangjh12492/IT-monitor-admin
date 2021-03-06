var groupTable;
var usersTable;
var userEditTable;
var groupDlg;


$('#groupAdd').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    $('#id').val(null);
    $('#groupName').val('');
    $('#groupDesc').val('');
    $('#sendType').val(0);
//    $('#groupFrom').dialog('open');
//    return false;
});

//保存按钮
$('#btnSaveGroup').click(function () {
    var groupName = $('#groupName').val();
    if (groupName == '') {
        alert("用户组不能为空！")
        return false;
    }
    var groupDesc = $('#groupDesc').val();

//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    $.ajax({
        url: 'userGroup.do?saveOrUpdateGroup',
        type: 'POST',
        dataType: 'json',
        data: {
            "id": $('#id').val(),
            "groupName": groupName,
            "groupDesc": groupDesc
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

        "sAjaxSource": "userGroup.do?getUserGroupList",
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
                "mDataProp": 'groupName',
                "sTitle": "组名",
                "bSortable": false
            },
            {
                "mDataProp": 'groupDesc',
                "sTitle": "组名描述",
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
            $('td:eq(3)', nRow).html(
                    "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#groupFrom\" onclick='editGroup("
                    + aData['id'] + ");'>编辑</button>"
                    + "<button class='btn btn-info btn-xs' onclick='delGroup("
                    + aData['id'] + ");'>删除</button>"
            );
        }
    });
}

//编辑组
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
                $('#id').val(id);
                $('#groupName').val(result.aaData[0].groupName);
                $('#groupDesc').val(result.aaData[0].groupDesc);
//                $('#groupFrom').dialog('open');
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
            url: 'userGroup.do?delUserGroup&id=' + id,
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
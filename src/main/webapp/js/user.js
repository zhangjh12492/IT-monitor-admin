var usersTable;
var groupDlg;


$('#groupAdd').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    $('#id').val(null);
    $('#userCode').val('');
    $('#userName').val('');
    $('#tel').val('');
    $('#email').val('');
    $('#userFromDlg').dialog('open');
    return false;
});

//保存按钮
$('#btnSaveUser').click(function () {
//    $('#userEdit').dataTable().fnDestroy();
//    userEditTable(1);
    $.ajax({
        url: 'userInfo.do?saveOrUpdateUser',
        type: 'POST',
        dataType: 'json',
        data: {
            "id": $('#id').val(),
            "userCode": $('#userCode').val(),
            "userName": $('#userName').val(),
            "tel": $('#tel').val(),
            "email": $('#email').val()
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                $('#userFromDlg').modal('hide');
                getUserList();
            } else {
                alert(result.desc);
            }
        }

    })
})


function getUserList() {
    $('#user').dataTable().fnDestroy();
    usersTable = $("#user").dataTable({
        "bDestory": true,
        "bFilter": false,

        "sAjaxSource": "userInfo.do?getUserInfoList",
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
                "mDataProp": 'userCode',
                "sTitle": "代码",
                "bSortable": false
            },
            {
                "mDataProp": 'userName',
                "sTitle": "姓名",
                "bSortable": false
            },
            {
                "mDataProp": 'tel',
                "sTitle": "电话",
                "bSortable": false
            },
            {
                "mDataProp": 'email',
                "sTitle": "E-mail",
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
            $('td:eq(5)', nRow).html(
                    "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#userFromDlg\" onclick='editUserInfo("
                    + aData['id'] + ");'>编辑</button>"
                    + "<button class='btn btn-info btn-xs' onclick='delUserInfo("
                    + aData['id'] + ");'>删除</button>"
            );


        }

    });

}

//编辑组
function editUserInfo(id) {
    $.ajax({
        url: 'userInfo.do?getUserInfoList&id=' + id,
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
                $('#userCode').val(result.aaData[0].userCode);
                $('#userName').val(result.aaData[0].userName);
                $('#tel').val(result.aaData[0].tel);
                $('#email').val(result.aaData[0].email);
                $('#userFromDlg').dialog('open');
                return false;
            } else {
                alert(result.aaData);
            }
        }

    })
}

//删除人员
function delUserInfo(id) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'userInfo.do?delUserInfo&id=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    getUserList();
                } else {
                    alert(result.desc);
                }
            }

        })

    } else {

    }
}


$('#user').on('click', 'tbody tr', function () {
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
    getUserList();
})
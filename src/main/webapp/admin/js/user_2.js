var groupTable;
var usersTable;
var userEditTable;
var groupDlg;

var menuId_1 = '';
var menuHtml_1 = ''

//一级菜单增加
$('#menuAdd').click(function () {
    $('#id').val(null);
    $('#name').val('');
    $('#password').val('');
    $('#userName').val('');
    $('#email').val('');
    $('#tel').val('');
});

//取一级菜单列表
function getMenu_1() {
    menuHtml_1 = '';
    if (groupTable != null) {
        $("#group").dataTable().fnDestroy();
    }
    groupTable = $("#group").dataTable({
        "sAjaxSource": "userController.do?getUserList",
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
                "mDataProp": 'name',
                "sTitle": "账号",
                "bSortable": true
            },
            {
                "mDataProp": 'userName',
                "sTitle": "姓名",
                "bSortable": true
            },
            {
                "mDataProp": 'password',
                "sTitle": "密码",
                "bSortable": true
            },
            {
                "mDataProp": 'email',
                "sTitle": "邮件",
                "bSortable": true
            },
            {
                "mDataProp": 'tel',
                "sTitle": "电话",
                "bSortable": true
            },
            {
                "mDataProp": null,
                "sTitle": "操作",
                "bSortable": true
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
                    "<input style=\"display: none\" id=\"menu_1_" + aData['id']
                    + "\" value=\"" + aData['id'] + "\"/>"
                    + "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#groupFrom\" onclick='editMenu_1("
                    + aData['id'] + ");'>编辑</button>"
                    + "<button class='btn btn-info btn-xs' onclick='deleteMenu("
                    + aData['id'] + ",1);'>删除</button>"
            )
        }
    });
}

//编辑一级菜单
function editMenu_1(id) {
    $.ajax({
        url: 'userController.do?getUserById&id=' + id,
        type: 'POST',
        dataType: 'json',
        data: {
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result != '') {
                $('#id').val(id);
                $('#name').val(result.name);
                $('#userName').val(result.userName);
                $('#password').val(result.password);
                $('#email').val(result.email);
                $('#tel').val(result.tel);
            } else {
                alert(result);
            }
        }
    })
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
    var nTds = $('input', this);
    menuId_1 = $(nTds[0]).val();
//    getMenu_2(menuId_1);
});


//删除菜单
function deleteMenu(id, level) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'userController.do?delUserById&id=' + id,
            type: 'POST',
            dataType: 'json',
            data: {
            },
            error: function () {
                alert('网络失败！');
            },
            success: function (result) {
                if (result.desc == null || result.desc == '') {
                    if (level == 1) {
                        getMenu_1();
                    }
                } else {
                    alert(result.desc);
                }
            }

        })

    } else {

    }
}

//一级菜单保存
$('#btnSaveMenu_1').click(function () {
    var passwordText = $('#password').val();
    if(passwordText == ''){
        alert("密码不能为空！");
        return false;
    }
    var userNameText = $('#userName').val();
    if(userNameText == ''){
        alert("姓名不能为空！");
        return false;
    }
    $.ajax({
        url: 'userController.do?saveOrUpdateUser',
        type: 'POST',
        dataType: 'json',
        data: {
            id: $('#id').val(),
            password: passwordText,
            name: $('#name').val(),
            userName: userNameText,
            email: $('#email').val(),
            tel: $('#tel').val()
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                getMenu_1();
                $('#groupFrom').modal('hide');
            } else {
                alert(result.desc);
            }
        }
    })
});


$(function () {

    getMenu_1();
//    getUsersTable(-1);
})
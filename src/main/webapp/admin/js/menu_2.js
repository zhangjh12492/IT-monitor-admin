var groupTable;
var usersTable;
var userEditTable;
var groupDlg;

var menuId_1 = '';
var menuHtml_1 = ''

//一级菜单增加
$('#menuAdd').click(function () {
    $('#id').val(null);
    $('#text').val('');
    $('#seq').val(0);
    $('#pid').val(0);
});

//二级菜单增加
$('#userAdd').click(function () {
    if (menuId_1 == '') {
        alert("先选择再操作");
        return false;
    }

    $('#id_2').val(null);
    $('#text_2').val('');
    $('#src_2').val('');
    $('#seq_2').val(0);
    $('#pid_2').val(menuId_1);
});

//取一级菜单列表
function getMenu_1() {
    menuHtml_1 = '';
    if (groupTable != null) {
        $("#group").dataTable().fnDestroy();
    }
    groupTable = $("#group").dataTable({
        "sAjaxSource": "menuController.do?getMenuList&pid=0",
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
                "mDataProp": 'text',
                "sTitle": "菜单名称",
                "bSortable": true
            },
            {
                "mDataProp": 'seq',
                "sTitle": "排序",
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
            $('td:eq(2)', nRow).html(
                    "<input style=\"display: none\" id=\"menu_1_" + aData['id']
                    + "\" value=\"" + aData['id'] + "\"/>"
                    + "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#groupFrom\" onclick='editMenu_1(\""
                    + aData['id'] + "\");'>编辑</button>"
                    + "<button class='btn btn-info btn-xs' onclick='deleteMenu(\""
                    + aData['id'] + "\",1);'>删除</button>"
            )
        }
    });
}

//取二级菜单列表
function getMenu_2(pid) {
    if (pid == '') {
        pid = '';
    }
    if (usersTable != null) {
        $('#users').dataTable().fnDestroy();
    }
    usersTable = $("#users").dataTable({
        "bDestory": true,
        "bFilter": false,
        "bSort": true,
        "sAjaxSource": "menuController.do?getMenuList&pid=" + pid,
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
                "mDataProp": 'text',
                "sTitle": "菜单名称",
                "bSortable": true
            },
            {
                "mDataProp": 'src',
                "sTitle": "地址",
                "bSortable": true
            },
            {
                "mDataProp": 'seq',
                "sTitle": "排序",
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
            $('td:eq(3)', nRow).html(
                    "<button class='btn btn-info btn-xs' data-toggle=\"modal\" data-target=\"#menu_2From\" onclick='editMenu_2(\""
                    + aData['id'] + "\");'>编辑</button>"
                    + "<button class='btn btn-info btn-xs' onclick='deleteMenu(\""
                    + aData['id'] + "\",2);'>删除</button>"
            )

        }
    });
}


//编辑一级菜单
function editMenu_1(id) {
    $.ajax({
        url: 'menuController.do?getMenuById&id=' + id,
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
                $('#text').val(result.text);
                $('#seq').val(result.seq);
                $('#pid').val(result.pid);
            } else {
                alert(result);
            }
        }
    })
}

//编辑二级菜单
function editMenu_2(id) {
    $.ajax({
        url: 'menuController.do?getMenuById&id=' + id,
        type: 'POST',
        dataType: 'json',
        data: {
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result != '') {
                $('#id_2').val(id);
                $('#text_2').val(result.text);
                $('#seq_2').val(result.seq);
                $('#src_2').val(result.src);
                $('#pid_2').val(result.pid);
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
    getMenu_2(menuId_1);
});

function userEditForm(groupId) {

    if (userEditTable != null) {
//        usersTable.fnClearTable();
        $('#usersEdit').dataTable().fnDestroy();
    }
    userEditTable = $("#usersEdit").dataTable({
            "bDestory": true,
            "bFilter": false,
            "bSort": true,
            "sAjaxSource": "userGroup.do?getNotExistUser&userGroupId=" + groupId,
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
                    "mDataProp": null,
                    "sTitle": "",
                    "bSortable": false
                },
                {
                    "mDataProp": 'name',
                    "sTitle": "账号",
                    "bSortable": true
                },
                {
                    "mDataProp": 'userName',
                    "sTitle": "人员",
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
                $('td:eq(0)', nRow).html(
                        "<input type='checkbox' name='sysid' value='" + aData['id'] + "'  />");
            }
        }
    )
}


//删除菜单
function deleteMenu(id, level) {
    var se = confirm("确认要删除吗？");
    if (se == true) {
        $.ajax({
            url: 'menuController.do?delMenuById&id=' + id,
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
                    } else {
                        getMenu_2(menuId_1);
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
    var menuText = $('#text').val();
    var menuSeq = $('#seq').val();
    $.ajax({
        url: 'menuController.do?saveOrUpdateMenu',
        type: 'POST',
        dataType: 'json',
        data: {
            id: $('#id').val(),
            text: menuText,
            seq: menuSeq,
            pid: $('#pid').val()
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

//二级菜单保存
$('#btnSaveMenu_2').click(function () {
    var menuText = $('#text_2').val();
    var menuSeq = $('#seq_2').val();
    var src = $('#src_2').val();
    $.ajax({
        url: 'menuController.do?saveOrUpdateMenu',
        type: 'POST',
        dataType: 'json',
        data: {
            id: $('#id_2').val(),
            text: menuText,
            seq: menuSeq,
            src: src,
            pid: $('#pid_2').val()
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.desc == null || result.desc == '') {
                getMenu_2(menuId_1);
                $('#menu_2From').modal('hide');
            } else {
                alert(result.desc);
            }
        }
    })
});
function keyPress() {
    var keyCode = event.keyCode;
    if ((keyCode >= 48 && keyCode <= 57))
    {
        event.returnValue = true;
    } else {
        event.returnValue = false;
    }
}

$(function () {

    getMenu_1();
    getMenu_2('');
//    getUsersTable(-1);
})
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
    $('#descript').val('');
    $('#seq').val(0);
});

//取一级菜单列表
function getMenu_1() {
    menuHtml_1 = '';
    if (groupTable != null) {
        $("#group").dataTable().fnDestroy();
    }
    groupTable = $("#group").dataTable({
        "sAjaxSource": "roleController.do?getRoleList",
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
                "sTitle": "角色名称",
                "bSortable": true
            },
            {
                "mDataProp": 'descript',
                "sTitle": "描述",
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

//编辑一级菜单
function editMenu_1(id) {
    $.ajax({
        url: 'roleController.do?getRoleById&id=' + id,
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
                $('#descript').val(result.descript);
                $('#seq').val(result.seq);
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
            url: 'roleController.do?delRoleById&id=' + id,
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
    var menuText = $('#text').val();
    if(menuText == ''){
        alert("名称不能为空！");
        return false;
    }
    var menuSeq = $('#seq').val();
    if(menuSeq == ''){
        alert("排序不能为空！");
        return false;
    }
    $.ajax({
        url: 'roleController.do?saveOrUpdateRole',
        type: 'POST',
        dataType: 'json',
        data: {
            id: $('#id').val(),
            text: menuText,
            seq: menuSeq,
            descript: $('#descript').val()
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
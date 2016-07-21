/**
 * 配合loginAndReg.jsp使用的js
 */

var loginObject = $.extend({
    loginAndRegDialog:"",
    regDialog:"",
    loginTabs:"",
    loginInputForm:"",
    regForm:"",
    loginDatagridName:"",
    loginDatagridForm:"",
    loginComboboxForm:"",
    loginComboboxName:""
}, loginObject);

$(function () {
    /* 登陆框 */
    loginObject.loginInputForm = $('#loginInputForm').form({
        url:'userController.do?login',
        success:function (data) {
            var d = $.parseJSON(data);
            if (d.success) {
                loginObject.loginAndRegDialog.dialog('close');
                $('#indexLayout').layout('panel', 'center').panel('setTitle', sy.fs('[{0}]，欢迎您！[{1}]', d.obj.name, d.obj.ip));
                menuObject.refreshMenuTree();
                //add by GG,2013/3/12
                centerTabObject.clear();
            } else {
                loginObject.loginInputForm.find('input[name=password]').focus();
            }
            $.messager.show({
                msg:d.msg,
                title:'提示'
            });
        }
    });

    loginObject.loginInputForm.find('input').on('keyup', function (event) {/* 增加回车提交功能 */
        if (event.keyCode == '13') {
            loginObject.loginInputForm.submit();
        }
    });
    /* 注册框 */
    loginObject.regForm = $('#regForm').form({
        url:'userController.do?reg',
        success:function (data) {
            var d = $.parseJSON(data);
            if (d.success) {
                loginObject.regDialog.dialog('close');
                loginObject.loginInputForm.find('input[name=name]').val(loginObject.regForm.find('input[name=name]').val());
                loginObject.loginInputForm.find('input[name=password]').val(loginObject.regForm.find('input[name=password]').val());
                loginObject.loginInputForm.submit();
            } else {
                loginObject.regForm.find('input[name=name]').focus();
            }
            $.messager.show({
                msg:d.msg,
                title:'提示'
            });
        }
    });

    loginObject.regForm.find('input').on('keyup', function (event) {/* 增加回车提交功能 */
        if (event.keyCode == '13') {
            loginObject.regForm.submit();
        }
    });

    loginObject.regDialog = $('#regDialog').show().dialog({
        modal:true,
        title:'注册',
        closed:true,
        buttons:[
            {
                text:'注册',
                handler:function () {
                    loginObject.regForm.submit();
                }
            }
        ],
        onOpen:function () {
            setTimeout(function () {
                loginObject.regForm.find('input[name=name]').focus();
            }, 1);
        },
        onClose:function () {
            loginObject.loginTabs.tabs('select', 0);
        }
    });

    loginObject.loginDatagridForm = $('#loginDatagridForm').form({
        url:'userController.do?login',
        success:function (data) {
            var d = $.parseJSON(data);
            if (d.success) {
                loginObject.loginAndRegDialog.dialog('close');
                $('#indexLayout').layout('panel', 'center').panel('setTitle', sy.fs('[{0}]，欢迎您！[{1}]', d.obj.name, d.obj.ip));
            } else {
                loginObject.loginDatagridForm.find('input[name=password]').focus();
            }
            $.messager.show({
                msg:d.msg,
                title:'提示'
            });
        }
    });

    loginObject.loginDatagridName = $('#loginDatagridName').show().combogrid({
        loadMsg:'数据加载中....',
        panelWidth:440,
        panelHeight:180,
        required:true,
        fitColumns:true,
        value:'',
        idField:'name',
        textField:'name',
        mode:'remote',
        url:'userController.do?loginDatagrid',
        pagination:true,
        pageSize:5,
        pageList:[ 5, 10 ],
        columns:[
            [
                {
                    field:'id',
                    title:'编号',
                    width:60,
                    hidden:true
                },
                {
                    field:'name',
                    title:'登录名',
                    width:100
                },
                {
                    field:'createdatetime',
                    title:'创建时间',
                    width:150
                },
                {
                    field:'modifydatetime',
                    title:'最后修改时间',
                    width:150
                }
            ]
        ],
        delay:500,
        keyHandler:$.extend($.fn.combo.defaults.keyHandler, {
            query:function (q) {
                loginObject.loginDatagridName.combogrid('grid').datagrid('load', {
                    name:q
                });
            }
        })
    });

    loginObject.loginDatagridForm.find('input,.combo-text').on('keyup', function (event) {/* 增加回车提交功能 */
        if (event.keyCode == '13') {
            loginObject.loginDatagridForm.submit();
        }
    });

    loginObject.loginComboboxForm = $('#loginComboboxForm').form({
        url:'userController.do?login',
        success:function (data) {
            var d = $.parseJSON(data);
            if (d.success) {
                loginObject.loginAndRegDialog.dialog('close');
                $('#indexLayout').layout('panel', 'center').panel('setTitle', sy.fs('[{0}]，欢迎您！[{1}]', d.obj.name, d.obj.ip));
            } else {
                loginObject.loginComboboxForm.find('input[name=password]').focus();
            }
            $.messager.show({
                msg:d.msg,
                title:'提示'
            });
        }
    });

    loginObject.loginComboboxName = $('#loginComboboxName').show().combobox({
        required:true,
        url:'userController.do?loginCombobox',
        textField:'name',
        valueField:'name',
        mode:'remote',
        panelHeight:'auto',
        delay:500,
        value:''
    });

    loginObject.loginComboboxForm.find('[name=password],.combo-text').bind('keyup', function (event) {/* 增加回车提交功能 */
        if (event.keyCode == '13') {
            loginObject.loginComboboxForm.submit();
        }
    });

    loginObject.loginTabs = $('#loginTabs').tabs({
        fit:true,
        border:false,
        onSelect:function (title) {
            if (title == '输入方式') {
                setTimeout(function () {
                    loginObject.loginInputForm.find('input[name=name]').focus();
                }, 1);
            } else if (title == '表格方式') {
                setTimeout(function () {
                    loginObject.loginDatagridName.combogrid('textbox').focus();
                }, 1);
            } else if (title == '补全方式') {
                setTimeout(function () {
                    loginObject.loginComboboxName.combogrid('textbox').focus();
                }, 1);
            }

        }
    });
    /* 注册登陆框 */
    loginObject.loginAndRegDialog = $('#loginAndRegDialog').show().dialog({
        modal:true,
        title:'系统登录',
        closable:false,
        buttons:[
//            {
//                id:'btnReg',
//                text:'注册',
//                handler:function () {
//                    loginObject.regDialog.dialog('open');
//                }
//            },
            {
                id:'btnLogin',
                text:'登录',
                handler:function () {
                    var tab = loginObject.loginTabs.tabs('getSelected');
                    var f = tab.find('form');
                    f.submit();
                }
            }
        ]
    });

});
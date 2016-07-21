var menuObject = {};
var centerTabObject = {};
var userObject = {};

/* 定义菜单对象*/
menuObject = $.extend({tree: "", accordion: "",refresh:true}, menuObject);
menuObject.collapseAll = function () {
    var node = this.tree.tree('getSelected');
    if (node) {
        this.tree.tree('collapseAll', node.target);
    } else {
        this.tree.tree('collapseAll');
    }
};
menuObject.expandAll = function () {
    var node = this.tree.tree('getSelected');
    if (node) {
        this.tree.tree('expandAll', node.target);
    } else {
        this.tree.tree('expandAll');
    }
};
menuObject.reloadTree = function () {
    this.tree.tree('reload');
};

menuObject.refreshMenuTree = function () {
    if (this.refresh) {
        this.tree = $('#tree').tree({
            url: 'menuController.do?tree',
            animate: false,
            lines: !sy.isLessThanIe8(),
            onClick: function (node) {
                if (node.attributes && node.attributes.src && node.attributes.src != '') {
                    var href;
                    if (/^\//.test(node.attributes.src)) {/*以"/"符号开头的,说明是本项目地址*/
                        href = node.attributes.src.substr(1);
                        $.messager.progress({
                            text: '请求数据中....',
                            interval: 100
                        });
                    } else {
                        href = node.attributes.src;
                    }
                    centerTabObject.addTabFun({
                        src: href,
                        title: node.text
                    });
                }
            },
            onLoadSuccess: function (node, data) {
                var t = $(this);
                if (data) {
                    $(data).each(function (index, d) {
                        if (this.state == 'closed') {
                            t.tree('expandAll');
                        }
                    });
                }
            }
        });
    }
};


/* 定义右上角用户操作对象*/
userObject = $.extend({}, userObject);
//锁定
userObject.lock = function(){
    menuObject.refresh = false; //锁定则不刷新菜单
    centerTabObject.clearTab = false; //锁定则不清除tab标签
    loginObject.loginAndRegDialog.dialog('open');
};

//登出
userObject.logout = function (b) {
    $.post('userController.do?logout', function () {
        if (b) {
            if (sy.isLessThanIe8()) {
                loginObject.loginAndRegDialog.dialog('open');
            } else {
                location.replace(sy.bp());
            }
        } else {
            loginObject.loginAndRegDialog.dialog('open');
        }
    });
    menuObject.refresh = true; //刷新菜单
    centerTabObject.clearTab = true; //清除tab标签
};
userObject.userInfo = function () {
    centerTabObject.addTabFun({
        src: 'userController.do?userInfo',
        title: '个人信息'
    });
};

/* 定义中间Tab对象*/
centerTabObject = $.extend({centerTabs: "",clearTab:true}, centerTabObject);
centerTabObject.addTabFun = function (opts) {
    var options = $.extend({
        title: '',
        content: '<iframe src="' + opts.src + '" frameborder="0" style="border:0;width:100%;height:99.2%;"></iframe>',
        closable: true,
        iconCls: ''
    }, opts);
    if (this.centerTabs.tabs('exists', options.title)) {
        this.centerTabs.tabs('close', options.title);
    }
    this.centerTabs.tabs('add', options);
};
/* 清除tab标签*/
centerTabObject.clear = function () {
    if (this.clearTab) {
        var centerTabs = this.centerTabs;
        var tabs = this.centerTabs.tabs("tabs");
        var length = tabs.length;
        for (var i = 0; i < length; i++) {
            var onetab = tabs[0];
            var title = onetab.panel('options').tab.text();
            if (centerTabs.tabs('exists', title)) {
                centerTabs.tabs('close', title);
            }
        }
    }
};





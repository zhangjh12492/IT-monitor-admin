<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8">
    $(function () {
        //初始化centerTabs
        centerTabObject.centerTabs = $('#centerTabs').tabs({
            border:false,
            fit:true
        });
        //打开系统，在此默认进入home页
        setTimeout(function () {
            var src = 'userController.do?home';
            centerTabObject.centerTabs.tabs('add', {

                title:'首页',
                content:'<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:99.2%;"></iframe>',
                closable:true,
                iconCls:''
            });
        }, 0);
        
           //绑定tabs的右键菜单
       $("#centerTabs").tabs({
            onContextMenu : function (e, title) {
            e.preventDefault();
            $('#tabsMenu').menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data("tabTitle", title);
           }
        });
        
          //实例化menu的onClick事件
        $("#tabsMenu").menu({
            onClick : function (item) {
               CloseTab(this, item.name);
            } 
         });
         
         //几个关闭事件的实现
    function CloseTab(menu, type) {
        var curTabTitle = $(menu).data("tabTitle");
        var tabs = $("#centerTabs");         
        if (type === "close") {
            tabs.tabs("close", curTabTitle);
            return;
        }
        var allTabs = tabs.tabs("tabs");
        var closeTabsTitle = [];
        $.each(allTabs, function () {
            var opt = $(this).panel("options");
            if (opt.closable && opt.title != curTabTitle && type === "Other") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "All") {
                closeTabsTitle.push(opt.title);
            }
        });
        for (var i = 0; i < closeTabsTitle.length; i++) {
            tabs.tabs("close", closeTabsTitle[i]);
        }
     }
         
        
        
    });
</script>
<div id="centerTabs"></div>
<div id="tabsMenu" class="easyui-menu" style="width:120px;"> 

    <div name="close">关闭</div> 

    <div name="Other">关闭其他</div> 

    <div name="All">关闭所有</div>

  </div>
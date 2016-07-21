<%@ page language="java" pageEncoding="UTF-8" %>
<div class="easyui-panel" fit="true" border="false">
    <div id="accordion" class="easyui-accordion" fit="true" border="false">
        <div title="系统菜单" iconCls="icon-tip">
            <div class="easyui-layout" fit="true">
                <div region="north" border="false" style="overflow: hidden;">
                    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-redo"
                       onclick="menuObject.expandAll();">展开</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-undo"
                       onclick="menuObject.collapseAll();">折叠</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-reload"
                       onclick="menuObject.reloadTree()">刷新</a>
                    <hr style="border-color: #fff;"/>
                </div>
                <div region="center" border="false">
                    <ul id="tree" style="margin-top: 5px;"></ul>
                </div>
            </div>
        </div>
        <div title="个人信息" iconCls="icon-tip">
            <div class="easyui-panel" style="padding: 10px;font-size: 15px;" fit="true" border="false">
                <a href="javascript:userObject.userInfo();">个人信息</a>
                <hr style="border-color: #fff;"/>
            </div>
        </div>
    </div>
</div>
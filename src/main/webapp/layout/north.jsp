<%@ page language="java" pageEncoding="UTF-8" %>
<style type="text/css">


    h1 a {
        display: block;
        text-decoration: none;
        font: 150px Helvetica, Arial, Sans-Serif;
        letter-spacing: -5px;
        text-align: center;
        color: #999;
        text-shadow: 0px 3px 8px #2a2a2a;
    }

    h1 a:hover {
        color: #a0a0a0;
        text-shadow: 0px 5px 8px #2a2a2a;
    }

    h2 {
        font: bold 15px Tahoma, Helvetica, Arial, Sans-Serif ;
        text-shadow: 0px 2px 3px #555;
    }


</style>
<div>
    <%--<img src="images/st_img.gif"/>--%>
    <h2>赛特春天电子商务业务运营支撑系统</h2>

</div>
<div style="position: absolute; right: 0px; bottom: 0px; ">
    <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a><a
        href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back">注销</a>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
    <div onclick="userObject.userInfo();">个人信息</div>
    <div class="menu-sep"></div>
    <div>
        <span>更换主题</span>

        <div style="width: 100px;">
            <div onclick="sy.changeTheme('default');">default</div>
            <div onclick="sy.changeTheme('gray');">gray</div>
            <div onclick="sy.changeTheme('black');">black</div>
            <div onclick="sy.changeTheme('bootstrap');">bootstrap</div>
            <div onclick="sy.changeTheme('metro');">metro</div>
        </div>
    </div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
    <div onclick="userObject.lock();">锁定窗口</div>
    <div class="menu-sep"></div>
    <div onclick="userObject.logout();">重新登录</div>
    <div onclick="userObject.logout(true);">退出系统</div>
</div>
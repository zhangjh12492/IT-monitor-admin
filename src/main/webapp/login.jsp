<%@ page language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录注册</title>
    <link rel="stylesheet" type="text/css" href="./css/user.css">
</head>
<body>
<div class="posa login clearfix">
    <div class="posa logo"></div>
    <div class="left"></div>
    <div class="posr right">
        <h2 class="tit">欢迎使用后台管理系统</h2>

        <div class="posa wilcome"></div>
    </div>
    <div class="posa login_box">
        <h1 class="userlogin">用户登录</h1>

        <form id="loginForm" action="login.do?login" method="post">
            <div class="log_b">
                <ul class="login_list">
                    <li>
                        <label>用户名</label>
                        <input type="text" name="name"/>
                    </li>
                    <li>
                        <label>密&nbsp;&nbsp;&nbsp;码</label>
                        <input type="password" name="password">
                    </li>
                    <li class="last">
                        <label>&nbsp;</label>
                        <input type="submit" value="登录">
                    </li>
                </ul>
            </div>
        </form>
    </div>
</div>
</body>
</html>
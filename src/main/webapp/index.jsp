<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>IT管理平台</title>
</head>
<body>
<script>
    jQuery(document).ready(function () {
        $.ajax({
            url: '/login.do?getMenuList&userId=' + $('#userId').val(),
            type: 'POST',
            dataType: 'json',
            async: false,
            error: function () {

            },
            success: function (result) {
            }
        })
    })
</script>
</body>
</html>
$(function () {
$('#body').html('www.baidu.com');
    return;
    alert($('#userId').val());
    $.ajax({
        url: '/login.do?getMenuList&userId='+$('#userId').val(),
        type: 'POST',
        dataType: 'json',
        data: {
            iDisplayStart: 0,
            iDisplayLength: 1
        },
        error: function () {
            alert('网络失败！');
        },
        success: function (result) {
            if (result.aaData != '') {
                $('#id').val(id);
                $('#userCode').val(result.aaData[0].userCode);
                $('#userName').val(result.aaData[0].userName);
                $('#tel').val(result.aaData[0].tel);
                $('#email').val(result.aaData[0].email);
                $('#userFromDlg').dialog('open');
                return false;
            } else {
                alert(result.aaData);
            }
        }

    })
})
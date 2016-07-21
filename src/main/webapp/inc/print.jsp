<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gg
  Date: 13-1-23
  Time: 下午2:27
  系统打印控件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pcds.sysmanager.util.ResourceUtil" %>

<script type="text/javascript">
    var printPlugin = $.extend({}, printPlugin);
    //设计
    printPlugin.designReport = function (billNo, param) {
        printOCX.designReport(billNo, param);
    };
    //设置打印机
    printPlugin.setPrinter = function (billNo, param) {
        printOCX.setPrinter(billNo, param);
    };
    //预览
    printPlugin.preview = function (billNo, param) {
        printOCX.preview(billNo, param);
    };
    //打印
    printPlugin.print = function (billNo, param) {
        printOCX.print(billNo, param);
    };
    //更新模板
    printPlugin.update = function () {
        printOCX.update();
    };

     $(function () {
     //        alert('begin');
     try {
         printPlugin.update();
     } catch (e) {
         //console.log(e);
     } finally {

     }
     //        alert('end');
     });



</script>
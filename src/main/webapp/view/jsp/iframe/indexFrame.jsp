<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="kiben" content="no-cache">
    <title>Insert title here</title>
</head>
<frameset rows="50,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/admin/topMenu.do" name="topFrame" scrolling="No" noresize="noresize"
           id="topFrame" title="topFrame"/>
    <frameset cols="15%,80%" frameborder="no" border="0" framespacing="0">
        <frame src="<%=request.getContextPath()%>/login.do?leftMenu&userId=${userId}" scrolling="auto" noresize="noresize"
               id="leftFrame" title="leftFrame"/>
        <!--  <frame src="/left" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" /> -->
        <frame src="<%=request.getContextPath()%>/admin/intoIndex.do" name="mainFrame" id="mainFrame"
               title="mainFrame"/>    
    </frameset> 
</frameset> 
<noframes>
    <body>
    </body>
</noframes>
</html>
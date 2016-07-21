<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../initJs.jsp" %>
<%
    String pathVal = request.getContextPath();
    String basePathVal = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + pathVal;

%>
<html lang="en"><!-- class="no-js" -->
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="kiben" content="no-cache">
    <title>Metronic | Admin Dashboard Template</title>
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/> -->
</head>

<body class="page-header-fixed page-quick-sidebar-over-content page-style-square">

<input id="userId" type="text" style="display: none" value="${userId}">
<!-- BEGIN CONTAINER -->
<div class="page-container" style="width: 300px;">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse">
            <ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
                <li class="sidebar-toggler-wrapper">
                    <div class="sidebar-toggler">
                    </div>
                </li>
                <%--
                                <c:forEach items="${mainMenu}" var="menu">
                                    <li>
                                        <a href="javascript:;">
                                            <i class="icon-present"></i>
                                            <span class="title">${menu.text}</span>
                                            <span class="arrow"></span>
                                        </a>
                                        <ul class="sub-menu">
                                            <c:forEach items="${menu.children}" var="sub">
                                                <li>
                                                    <a href="${contextPath}${sub.src}" target="mainFrame">
                                                            ${sub.text}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:forEach>
                --%>
                <c:forEach items="${mainMenu}" var="menu" varStatus="vStatus">
                    <c:choose>
                        <c:when test="${vStatus.first}">
                            <li class="start active open">
                            <a href="javascript:;">
                                <i class="icon-home"></i>
                                <span class="title">${menu.text}</span>
                                <span class="selected"></span>
                                <span class="arrow open"></span>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <li>
                            <a href="javascript:;">
                                <i class="icon-home"></i>
                                <span class="title">${menu.text}</span>
                                <span class="arrow"></span>
                            </a>
                        </c:otherwise>
                    </c:choose>
                    <ul class="sub-menu">
                        <c:forEach items="${menu.children}" var="sub" varStatus="subStatus">
                            <c:choose>
                                <c:when test="${vStatus.first}&&${subStatus.first}">
                                    <li class="active">
                                </c:when>
                                <c:otherwise>
                                    <li>
                                </c:otherwise>
                            </c:choose>
                            <a href="<%=basePathVal%>${sub.src}" target="mainFrame">
                                <i class="icon-bar-chart"></i>
                                    ${sub.text}</a>
                            </li>
                        </c:forEach>
                    </ul>
                    </li>
                </c:forEach>
            </ul>
            <!-- END SIDEBAR MENU -->
        </div>
    </div>
</div>

<jsp:include page="../../common.jsp"></jsp:include>
<script type="text/javascript" src="./js/left.js"></script>
<script>
    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        QuickSidebar.init(); // init quick sidebar
        /*
         Demo.init(); // init demo features
         Index.init();
         Index.initDashboardDaterange();
         Index.initJQVMAP(); // init index page's custom scripts
         Index.initCalendar(); // init index page's custom scripts
         Index.initCharts(); // init index page's custom scripts
         Index.initChat();
         Index.initMiniCharts();
         Tasks.initDashboardWidget();
         */
    });
</script>
</body>

</html>


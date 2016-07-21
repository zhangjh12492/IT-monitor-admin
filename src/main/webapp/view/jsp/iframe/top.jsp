<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../initJs.jsp" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="kiben" content="no-cache">
<title>Top</title>

<link href="${contextPath }metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="${contextPath }metronic_v35/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="${contextPath }metronic_v35/theme/assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
<link href="${contextPath }metronic_v35/theme/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${contextPath }metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="${contextPath }css/home.css" rel="stylesheet" type="text/css">
<%--
    <script type="javascript">
        function logout(){
            window.parent.location.href='login.jsp';
        }
    </script>
--%>

</head>
<body>
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo" style="width: 280px;">
			<a href="javascript:void(0)">
				<div class="logo"></div>

			</a>
			<a style="color:#fff; height: 100%; line-height: 46px; margin-left: 10px; font-size: 19px;">王府井百货IT监控平台</a>
			<div class="menu-toggler sidebar-toggler hide">
				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		
		<div class="top-menu">
			<ul class="nav navbar-nav pull-right">
				<!-- BEGIN NOTIFICATION DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
					<a href="${contextPath}mesDatail.do?intoMesList&errLevel=1&processStatus=0" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" target="mainFrame" title="警告">
					<i class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></i>
					<span class="badge badge-default" id="top_user_show_warn_count">
					7 </span>
					</a>
					
				</li>
				<!-- END NOTIFICATION DROPDOWN -->
				<!-- BEGIN INBOX DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">
					<a href="${contextPath}mesDatail.do?intoMesList&errLevel=2&processStatus=0" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" target="mainFrame" title="异常">
					<i class="glyphicon glyphicon-ban-circle" aria-hidden="true"></i>
					<span class="badge badge-default" id="top_user_show_error_count">
					4 </span>
					</a>
					
				</li>
				
				<li class="dropdown dropdown-quick-sidebar-toggler">
					<a href="javascript:;" class="dropdown-toggle" id="js_exit" title="退出">
					<i class="icon-logout"></i>
					</a>
				</li>
				<!-- END QUICK SIDEBAR TOGGLER -->
			</ul>
		</div>
		
		<!-- END TOP NAVIGATION MENU -->
	</div>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div> 
<script type="javascript" src="${contextPath }js/top.js"></script>
<script src="${contextPath }metronic_v35/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">

initTopUserErrorWarnCount();
/**
 * 初始化页面头部右上角显示用户警告与异常的数量
 */
function initTopUserErrorWarnCount(){
	var url='${contextPath}login.do?getUserProcessMesCount'
	$.ajax({ 
		 url:url,
		 type:'post',    
		 cache:false,
		 async:false,
		 timeout:1000*60,   
	//   url: 'sysOperate.do?addSysInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(), 
	   dataType: 'json',
	   error: function () { 
	       alert('网络失败！');
	   },
	   success: function (result) {
	    	var error_count=0;
	    	var warn_count=0;
	       if(result[0]!=null){ 
	    	   $.each(result,function(index,item){
	    		   error_count+=item.errorCount;
	    		   warn_count+=item.warnCount;
	    		   
	    	   });
	       }
	    	$("#top_user_show_warn_count").html(warn_count);
	    	$("#top_user_show_error_count").html(error_count);
	   }
	
	});
}

/**
 * 用户
 */
function userShowCountClick(errLevel,processStatus){
	window.location.href="${contextPath}mesDatail.do?intoMesList&errLevel="+errLevel+"&processStatus="+processStatus;
}


	$(function(){
		$("#js_exit").click(function(){
			window.parent.location.href="${contextPath}login.jsp";
			console.log(window.location.href);
			
		})
	})

</script>
</body>
</html>
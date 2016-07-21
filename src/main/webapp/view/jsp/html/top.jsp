<div class="page-header navbar navbar-fixed-top clearfix">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="index.html">
			<div class="logo"></div>			
			</a>
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
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></i>
					<span class="badge badge-default">
					7 </span>
					</a>
					
				</li>
				<!-- END NOTIFICATION DROPDOWN -->
				<!-- BEGIN INBOX DROPDOWN -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<i class="glyphicon glyphicon-ban-circle" aria-hidden="true"></i>
					<span class="badge badge-default">
					4 </span>
					</a>
					
				</li>
				
				<li class="dropdown dropdown-quick-sidebar-toggler">
					<a href="javascript:;" class="dropdown-toggle">
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
<script type="javascript" src="${contextPath }js/top.js"></script>
<script src="${contextPath }metronic/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
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

</script>
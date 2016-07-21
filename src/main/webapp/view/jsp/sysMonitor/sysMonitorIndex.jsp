<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../initJs.jsp" %>
<html lang="en">
<head>
    <title>异常监控</title>
    <meta charset="UTF-8">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="kiben" content="no-cache">
    <link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/Scroller/css/dataTables.scroller.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/extensions/ColReorder/css/dataTables.colReorder.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/jquery-tags-input/jquery.tagsinput.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/typeahead/typeahead.css">
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="<%=request.getContextPath() %>/static/css/metronic/components.css" id="style_components" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/static/css/metronic/layout.css" rel="stylesheet" type="text/css"/>
    <link id="style_color" href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/metronic_v35/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}css/layout.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}static/js/message/sysMonitor/style.css" rel="stylesheet" type="text/css">
  
</head>
<body> 
<input type="hidden" name="code" id="code" value="${code }" /><!-- 系统编码 -->
<div class="conright">
    <div class="titbar">
         <div class="fl posr seleBox">
            <a class="posr ak" id="js_sele">
                <span></span>
                <b class="posa icon_1 seat1"></b>
            </a>
            <div class="posa chosen-drop" id="js_sel_box">
                <%--<div class="chosen-search">
                    <input type="text" autocomplete="off">
                </div>--%>
                <ul class="chosen-results" id="chosen_results_sys_select">
                    <!-- <li class="active-result highlighted"><a class="pl_15">PHP Application</a></li>
                    <li class="active-result"><a class="pl_15">java_demo</a></li>
                    <li class="active-result"><a class="pl_15">Welcome to Tomcat</a></li> -->

                </ul>

            </div>
        </div>
        <div class="fr timeMeun" id="js_timeMeun">
            <!-- <span>最近</span>
            <span>560分钟</span> -->
            <b class="icon_2 seat2"></b>
        </div>
    </div>
    <div class="main">
    <div class="fl concenter clearfix" id="js_center">
            <div class="clearfix">
                
                <div class="m_top10" style="margin:12px 0px 30px 0px;">   
                <table class="chart_table" border="0" cellspacing="0" cellpadding="0" id="table_sys_monitor_list">
                    <colgroup>
                        <col class="col_1">
                        <col class="col_2">
                        <col class="col_3">
                        <col class="col_4">
                        <col class="col_5">
                        <col class="col_6"> 
                        <col class="col_7">
                    </colgroup>
                    <tr class="table_head">
                        <th>名称</th>
                        <th>Apdex</th>
                        <th>响应时间(ms)</th>
                        <th>吞吐率(次/秒)</th>
                        <th>错误率(%)</th>
                        <th>CPU使用率(%)</th>
                        <th>内存使用量(MB)</th>
                    </tr>
                    
                </table>
            </div>
            <div class="item_1"> 
                    <div class="hd">
                        <div class="fl">
                            <b class="fl icon_t">?</b>
                            <span>应用服务器响应时间</span>
                        </div>
                        <div class="fr">
                            <div class="icon_add01"></div>
                        </div>
                    </div>
                    <div class="bd clearfix">
                        <div id="chart_2" class="chart" style="height: 200px;"></div>
                    </div>
                </div>
                
                <div class="mr_10 item_2">
                    <div class="hd">
                        <div class="fl">
                            <b class="fl icon_t">?</b>
                            <span>错误率</span>
                        </div>
                        <div class="fr">
                            <div class="icon_add01"></div>
                        </div>
                    </div>
                    <div class="bd clearfix">
                        <div id="chart_3" class="chart" style="height: 200px;"></div>
                    </div>
                </div>
                <div class="item_2">
                    <div class="hd">
                        <div class="fl">
                            <b class="fl icon_t">?</b>
                            <span>最耗时Web应用过程（Web Action）图表</span>
                        </div>
                        <div class="fr">
                            <div class="icon_add01"></div>
                        </div>
                    </div>
                    <div class="bd clearfix">
                        <div id="chart_4" class="chart" style="height: 200px;"></div>
                    </div>
                </div>
            </div>

            <div class="m_top10 right_gray">
                <div class="grayfont">
                    <b class="icon_cpu"></b>服务器资源
                </div>
            </div>
            <div class="clearfix">
                <div class="mr_10 item_2">
                    <div class="hd"> 
                        <div class="fl">
                            <b class="fl icon_t">?</b>
                            <span>CPU</span>
                        </div>
                        <div class="fr">
                            <div class="icon_add01"></div>
                        </div>
                    </div>
                    <div class="bd clearfix">
                        <div id="chart_5" class="chart" style="height: 200px;"></div>
                    </div>
                </div>
                <div class="item_2">
                    <div class="hd"> 
                        <div class="fl">
                            <b class="fl icon_t">?</b>
                            <span>物理内存</span>
                        </div>
                        <div class="fr">
                            <div class="icon_add01"></div>
                        </div>
                    </div>
                    <div class="bd clearfix">
                        <div id="chart_6" class="chart" style="height: 200px;"></div>
                    </div>
                </div>
            </div>
            
			 
        </div>
        <div class="fl conright_main clearfix" id="js_right">
            <div class="clearfix">
                <div class="item_1">
                    <div class="hd">
                        <div class="fl"> 
                            <b class="fl icon_t">?</b>
                            <span>Apdex指标</span>
                        </div>
                        <div class="fr">
                            <div class="icon_add01"></div>
                        </div>
                    </div>
                    <div class="bd clearfix">
                    <div id="chart_7" style="width: 100%; height: 400px;"></div>
                       
                    </div> 
                </div>
                <div class="item_1">
                    <div class="hd">
                        <div class="fl">
                            <b class="fl icon_t">?</b>
                            <span>吞吐率</span>  
                        </div>
                        <div class="fr">
                            <div class="icon_add01"></div>
                        </div>
                    </div>
                    <div class="bd clearfix">
                        <div id="chart_1" class="chart" style="height: 200px;"></div>
                    </div>
                </div>
            </div>
            <div class="m_top10 right_gray">
                <div class="grayfont">
                    <b class="icon_event"></b>最近事件
                </div>
            </div>
            <div class="m_top10 event_container clearfix">
                <div class="event_tab">
                    <ul id="event" class="pl_15 event"> 
                        <li class="on" onclick="selectMessageInfoForSysMonitorShow(this,'')"> 
                            <a href="javascript:void(0);">所有</a>
                        </li>
                        <li onclick="selectMessageInfoForSysMonitorShow(this,'1')">
                            <a href="javascript:void(0);">
                                <b class="eventtab_1"></b>
                            </a>
                        </li>
                        <li onclick="selectMessageInfoForSysMonitorShow(this,'2')"> 
                            <a href="javascript:void(0);">
                                <b class="eventtab_2"></b> 
                            </a>
                        </li> 
                       <!--  <li>
                            <a href="javascript:void(0);">
                                <b class="eventtab_3"></b>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);">
                                <b class="eventtab_4"></b>
                            </a>
                        </li> -->
                    </ul>
                </div>
                <div class="tab_content_body">
                    <div class="tit clearfix">
                        <b class="icon_add"></b>
                        今天
                    </div>
                    <ul class="e_list list_line dblist" id="js_list_1">
                        <%--<li>--%>
                            <%--<a href="javascript:void(0)" class="js_listMore fr tab_content_r">加载更多</a>--%>
                        <%--</li>--%>
                    </ul>
                    <div class="tit clearfix">
                        <b class="icon_add icon_plus"></b>
                        昨天
                    </div>
                    <ul class="e_list list_line" id="js_list_2">
                        <%--<li>--%>
                            <%--<a href="javascript:void(0)" class="js_listMore fr tab_content_r">加载更多</a>--%>
                        <%--</li>--%>
                    </ul>
                    <div class="tit clearfix">
                        <b class="icon_add icon_plus"></b>
                        更早
                    </div>
                    <ul class="e_list list_line" id="js_list_3">
                        <%--<li>--%>
                            <%--<a href="javascript:void(0)" class="js_listMore fr tab_content_r">加载更多</a>--%>
                        <%--</li>--%>
                    </ul>
                </div>
            </div>
        </div>
        
    </div>

</div>
<div id="mesDetail" class="modal fade" role="dialog" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">详细信息</h4>
            </div>
            <div class="modal-body form">
                <form action="#" class="form-horizontal form-row-seperated">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">系统码</label>
                        <div class="col-sm-8">
                            <p id="detail_sysCode" class="help-block">

                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">业务码</label>
                        <div class="col-sm-8">
                            <p id="detail_busiCode" class="help-block">

                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">业务描述</label>
                        <div class="col-sm-8">
                            <p id="detail_busiDesc" class="help-block">

                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">errCode</label>
                        <div class="col-sm-8">
                            <p id="detail_errCode" class="help-block">

                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">错误描述</label>
                        <div class="col-sm-8">
                            <p id="detail_errDesc" class="help-block">

                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">sysErrCode</label>
                        <div class="col-sm-8">
                            <p id="detail_sysErrCode" class="help-block">

                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">底层错误描述</label>
                        <div class="col-sm-8">
                            <p id="detail_sysErrDesc" class="help-block">

                            </p>
                        </div>
                    </div>
                    <div class="form-group last">
                        <label class="col-sm-4 control-label">异常详细信息</label>
                        <div class="col-sm-8">
                            <p id="detail_throwableDesc" class="help-block">

                            </p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <!-- <button type="button" class="btn btn-primary"><i class="fa fa-check"></i> Save changes</button> -->
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}metronic_v35/theme/assets/global/plugins/respond.min.js"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/excanvas.min.js"></script>

<script src="${contextPath}metronic_v35/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->

<script src="${contextPath}metronic_v35/theme/assets/global/plugins/amcharts/ammap/ammap.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/amcharts/ammap/maps/js/worldLow.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/amcharts/amstockcharts/amstock.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->  
<script src="${contextPath}metronic_v35/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="${contextPath}metronic_v35/theme/assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/metronic_v35/theme/assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>

<script src="${contextPath}static/js/message/sysMonitor/amcharts.js" type="text/javascript"></script>
<script src="${contextPath}static/js/message/sysMonitor/serial.js" type="text/javascript"></script> 
<!-- user js -->
<script src="${contextPath }static/js/message/sysMonitor/sysMonitorIndex.js" type="text/javascript"></script>

 

<script>

    /*页面功能*/
    jQuery(function(){   
        var $w=$(".conright").width()-10,k=($w/3*2); 
        $("#js_right").css({width:$w/3}); 
        $("#js_center").css({width:k-10});   
        $(".item_2").css({width:(k-10)/2-5});    


        $("#js_sele").click(function(){
            if($("#js_sel_box").css("display")=="block"){
                $("#js_sel_box").hide();
                $(this).removeClass("selbottom");
                return;
            }
            $(this).addClass("selbottom");
            $("#js_sel_box").show().find("li").hover(function(){
                $(this).addClass("highlighted");
            },function(){
                $(this).removeClass("highlighted");
            });

            

        });
        $("#js_sel_box").find("li").click(function(){
            $("#js_sele span").text($(this).text());
            $("#js_sele").removeClass("selbottom");
            $("#js_sel_box").hide(); 
            var clickLiText=$(this).text();
            //alert(clickLiText); 
            
            $("#code").val(clickLiText.substr(clickLiText.lastIndexOf("-")+1,2));
            initMonitorData();
        });

        var a=5;  //点击加载更多显示几条的值；
        $(".tit b").click(function(){
            var $obj=$(this).parent().next();
            if($obj.css("display")=="block"){
                $(this).addClass("icon_plus");
            }else{
                $(this).removeClass("icon_plus");
            }
            $obj.toggle();
            /*$obj.find("li").slice(0,5).show();
            $obj.find("li").slice(-1).show().find(".js_listMore").show();
            if($(this).hasClass("icon_plus")){
                $obj.find("li").hide();
                a=5;
            }*/
        });

//        listDisplay($("#js_list_1 li"));
//        listDisplay($("#js_list_2 li"));
//        listDisplay($("#js_list_3 li"));
//
//        function listDisplay(obj){
//                obj.slice(0,a).show();
//                obj.slice(-1).show();
//                obj.find(".js_listMore").click(function(){
//                    a+=5;
//                    obj.slice(0,a).show();
//                    if(a>obj.length || a ==obj.length){
//                        $(this).hide();
//                    }
//                });
//        }

    });
    initMomitor();	//init monitor page
    /**
     * 页面初始化信息的定时器，1分钟执行一次
     */
    $(document).ready(function(){
        setInterval(initMomitor, 1000*60)
    });
    
   
</script>

</body>
</html>
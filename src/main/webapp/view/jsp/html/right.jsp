
	<div class="conright">
	    <div class="titbar">
	         <div class="fl posr seleBox">
	            <a class="posr ak" id="js_sele">
	                <span></span>
	                <b class="posa icon_1 seat1"></b>
	            </a>
	            <div class="posa chosen-drop" id="js_sel_box"> 
	                <div class="chosen-search">
	                    <input type="text" autocomplete="off">
	                </div>
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
	                        <th>吞吐率(rpm)</th>
	                        <th>错误率(%)</th>
	                        <th>CPU使用率(%)</th>
	                        <th>内存使用量(MB)</th>
	                    </tr>
	                    
	                </table>
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
	                        <div id="chart_7" class="chart" style="height: 200px;"></div>
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
	                        <li>
	                            <a href="javascript:void(0)" class="js_listMore fr tab_content_r">加载更多</a>
	                        </li>
	                    </ul>
	                    <div class="tit clearfix">
	                        <b class="icon_add icon_plus"></b>
	                        昨天
	                    </div>
	                    <ul class="e_list list_line" id="js_list_2">
	                        <li>
	                            <a href="javascript:void(0)" class="js_listMore fr tab_content_r">加载更多</a>
	                        </li>
	                    </ul>
	                    <div class="tit clearfix">
	                        <b class="icon_add icon_plus"></b>
	                        更早
	                    </div>
	                    <ul class="e_list list_line" id="js_list_3">
	                        <li>
	                            <a href="javascript:void(0)" class="js_listMore fr tab_content_r">加载更多</a>
	                        </li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	        
	    </div>
	</div> 



/*
    Validform datatype extension
	By sean during December 8, 2012 - February 20, 2013
	For more information, please visit http://validform.rjboy.cn
	
	扩展以下类型：
	date：匹配日期
	zh：匹配中文字符
	dword：匹配双字节字符
	money：匹配货币类型
	ipv4：匹配ipv4地址
	ipv6：匹配ipv6地址
	num：匹配数值型
	qq：匹配qq号码
	unequal：当前值不能等于被检测的值，如可以用来检测新密码不能与旧密码一样
	notvalued：当前值不能包含指定值，如密码不能包含用户名等的检测
	min：多选框最少选择多少项
	max：多选框最多不能超过多少项
	byterange:判断字符长度，中文算两个字符
	numrange：判断数值范围，如小于100大于10之间的数
	daterange：判断日期范围
	idcard：对身份证号码进行严格验证
*/

(function(){
	if($.Datatype){
		$.extend($.Tipmsg.w,{
			"date":"请填写日期！",
			"zh":"请填写中文！",
			"dword":"请填写双字节字符！",
			"money":"请填写货币值！",
			"ipv4":"请填写ip地址！",
			"ipv6":"请填写IPv6地址！",
			"num":"请填写数值！",
			"qq":"请填写QQ号码！",
			"unequal":"值不能相等！",
			"notvalued":"不能含有特定值！",
			"idcard":"身份证号码不对！",
			"cron":"计划时间格式不对！"
		});
		
		$.extend($.Datatype,{
			/*
				reference http://blog.csdn.net/lxcnn/article/details/4362500;
				
				日期格式可以是：20120102 / 2012.01.02 / 2012/01/02 / 2012-01-02
				时间格式可以是：10:01:10 / 02:10
				如 2012-01-02 02:10
				   2012-01-02
			*/
			"date":/^(?:(?:1[6-9]|[2-9][0-9])[0-9]{2}([-/.]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:(?:1[6-9]|[2-9][0-9])(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)([-/.]?)0?2\2(?:29))(\s+([01][0-9]:|2[0-3]:)?[0-5][0-9]:[0-5][0-9])?$/,
			
			//匹配中文字符;
			"zh":/^[\u4e00-\u9fa5]+$/,
			
			//匹配双字节字符;
			"dword":/^[^\x00-\xff]+$/,
			
			//货币类型;
			"money":/^([\u0024\u00A2\u00A3\u00A4\u20AC\u00A5\u20B1\20B9\uFFE5]\s*)(\d+,?)+\.?\d*\s*$/,
			
			//匹配ipv4地址;
			"ipv4":/^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})$/,
			
			/*
				匹配ipv6地址;
				reference http://forums.intermapper.com/viewtopic.php?t=452;
			*/
			"ipv6":/^\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:)))(%.+)?\s*$/,
			
			
			//数值型;
			"num":/^(\d+[\s,]*)+\.?\d*$/,
			
			
			//QQ号码;
			"qq":/^[1-9][0-9]{4,}$/,
			
			
			/*
			  参数gets是获取到的表单元素值，
			  obj为当前表单元素，
			  curform为当前验证的表单，
			  datatype为内置的一些正则表达式的引用。
			*/
			"unequal":function(gets,obj,curform,datatype){
				/*
					当前值不能与指定表单元素的值一样，如新密码不能与旧密码一样，密码不能设置为用户名等
					注意需要通过绑定with属性来指定要比较的表单元素，可以是clas，id或者是name属性值

					eg.  <input type="text" name="name" id="name" class="name" />
					eg1. <input type="text" name="test" datatype="unequal" with="name" />
					eg2. <input type="text" name="test" datatype="unequal" with=".name" />
					eg3. <input type="text" name="test" datatype="unequal" with="#name" />
					
					也可以用来验证不能与with指定的值相等
					当上面根据class，id和name都查找不到对象时，会直接跟with的值比较
					eg4. <input type="text" name="test" datatype="num unequal" with="100" />
					该文本框的值不能等于100
				*/
				var withele=$.trim(obj.attr("with"));
				var val=curform.find(withele+",[name='"+withele+"']").val() || withele;

				if(gets==$.trim(val)){
					return false;
				}
			},
			
			
			"notvalued":function(gets,obj,curform,datatype){
				/*
					当前文本框的值不能含有指定文本框的值，如注册时设置的密码里不能包含用户名
					注意需要给表单元素绑定with属性来指定要比较的表单元素，可以是clas，id或者是name属性值
					<input type="text" name="username" id="name" class="name" />
					eg. <input type="password" name="test" datatype="notvalued" with=".name" />
					
					也可以用来验证不能包含with指定的值
					当上面根据class，id和name都查找不到对象时，会直接跟with的值比较
					eg2. <input type="password" name="test" datatype="notvalued" with="validform" />
					要求不能含有"validform"字符
				*/
				var withele=$.trim(obj.attr("with"));
				var val=curform.find(withele+",[name='"+withele+"']").val() || withele;

				if(gets.indexOf($.trim(val))!=-1){
					return false;
				}
			},
			
			
			"min":function(gets,obj,curform,datatype){
				/*
					checkbox最少选择n项
					注意需要给表单元素绑定min属性来指定是至少需要选择几项，没有绑定的话使用默认值
					eg. <input type="checkbox" name="test" datatype="min" min="3" />
				*/
				
				var minim=~~obj.attr("min") || 2,
					numselected=curform.find("input[name='"+obj.attr("name")+"']:checked").length;
				return  numselected >= minim ? true : "请至少选择"+minim+"项！";
			},
			
			
			"max":function(gets,obj,curform,datatype){
				/*
					checkbox最多选择n项
					注意需要给表单元素绑定max属性来指定是最多需要选择几项，没有绑定的话使用默认值
					eg. <input type="checkbox" name="test" datatype="max" max="3" />
				*/
				
				var atmax=~~obj.attr("max") || 2,
					numselected=curform.find("input[name='"+obj.attr("name")+"']:checked").length;
					
				if(numselected==0){
					return false;
				}else if(numselected>atmax){
					return "最多只能选择"+atmax+"项！";
				}
				return  true;
			},
			
			
			"byterange":function(gets,obj,curform,datatype){
				/*
					判断字符长度，中文算两个字符
					注意需要给表单元素绑定max,min属性来指定最大或最小允许的字符长度，没有绑定的话使用默认值
				*/
				var dregx=/[^\x00-\xff]/g;
				var maxim=~~obj.attr("max") || 100000000,
					minim=~~obj.attr("min") || 0;
					
				getslen=gets.replace(dregx,"00").length;
				
				if(getslen>maxim){
					return "输入字符不能多于"+maxim+"个，中文算两个字符！";
				}
				
				if(getslen<minim){
					return "输入字符不能少于"+minim+"个，中文算两个字符！";
				}
				
				return true;
			},
			
			
			"numrange":function(gets,obj,curform,datatype){
				/*
					判断数值范围
					注意需要给表单元素绑定max,min属性来指定最大或最小可输入的值，没有绑定的话使用默认值
				*/
				
				var maxim=~~obj.attr("max") || 100000000,
					minim=~~obj.attr("min") || 0;
				
				gets=gets.replace(/\s*/g,"").replace(/,/g,"");
				if(!/^\d+\.?\d*$/.test(gets)){
					return "只能输入数字！";
				}
				
				if(gets<minim){
					return "值不能小于"+minim+"！";
				}else if(gets>maxim){
					return "值不能大于"+maxim+"！";
				}
				return  true;
			},
			
			"numrange_integer":function(gets,obj,curform,datatype){
				/*
					判断数值范围
					注意需要给表单元素绑定max,min属性来指定最大或最小可输入的值，没有绑定的话使用默认值
				*/
				
				var maxim=~~obj.attr("max") || 100000000,
					minim=~~obj.attr("min") || 0;
				
				gets=gets.replace(/\s*/g,"").replace(/,/g,"");
				if(!/^\d+$/.test(gets)){
					return "只能输入整数！";
				}
				
				if(gets<minim){
					return "值不能小于"+minim+"！";
				}else if(gets>maxim){
					return "值不能大于"+maxim+"！";
				}
				return  true;
			},
			"cron_second":function(gets,obj,curform,datatype){
		       var value = gets; //传递过来真正要验证的字符串
		       if(value!=""){
		            value=value.replace(/(^\s*)|(\s*$)/g, ""); //去掉前后空白
	                // 为了清晰起见，我将规则拆分来写
	                var reg1=/^([0-5]?\d)([\/\-][0-5]?\d)?$/;			// 形如23 23/34 45-59
	                var reg2=/^[0-5]?\d(,[0-5]?\d)*$/ ;         		// 形如 12,43,56  
	                var reg3=/^([0-5]?\d)(\-[0-5]?\d)(\/[0-5]?\d)?$/;	// 12-25/2
	                var reg4=/^(\*)(\/[0-5]?\d)?$/; 					// */8
	                var reg5=/^\*$/;                            		// 匹配 *
	                if(!(reg1.test(value)||reg2.test(value)||reg3.test(value)||reg4.test(value)||reg5.test(value))){
	                    return "格式错误";
	                }
	                return true;
		        }else{
		            return false;
		        }
			},
			"cron_minute":function(gets,obj,curform,datatype){
		       var value = gets; //传递过来真正要验证的字符串
		       if(value!=""){
		            value=value.replace(/(^\s*)|(\s*$)/g, ""); //去掉前后空白
	                // 为了清晰起见，我将规则拆分来写
	                var reg1=/^([0-5]?\d)([\/\-][0-5]?\d)?$/;			// 形如23 23/34 45-59
	                var reg2=/^[0-5]?\d(,[0-5]?\d)*$/ ;         		// 形如 12,43,56  
	                var reg3=/^([0-5]?\d)(\-[0-5]?\d)(\/[0-5]?\d)?$/;	// 12-25/2
	                var reg4=/^(\*)(\/[0-5]?\d)?$/; 					// */8
	                var reg5=/^\*$/;                            		// 匹配 *
	                if(!(reg1.test(value)||reg2.test(value)||reg3.test(value)||reg4.test(value)||reg5.test(value))){
	                    return "格式错误";
	                }
	                return true;
		        }else{
		            return false;
		        }
			},
			
			"cron_hour":function(gets,obj,curform,datatype){
			       var value = gets; //传递过来真正要验证的字符串
			       if(value!=""){
			            value=value.replace(/(^\s*)|(\s*$)/g, ""); //去掉前后空白
		                // 为了清晰起见，我将规则拆分来写
			            var reg1=/^(([0-1]?\d)|(2[0-3]))([\/\-](([0-1]?\d)|(2[0-3])))?$/;  //形如23 12/18 7-19
			            var reg2=/^(([0-1]?\d)|(2[0-3]))(,(([0-1]?\d)|(2[0-3])))*$/;       //形如12,15,20
			            var reg3=/^(([0-1]?\d)|(2[0-3]))(\-(([0-1]?\d)|(2[0-3])))(\/(([0-1]?\d)|(2[0-3])))?$/;       
			            var reg4=/^(\*)(\/(([0-1]?\d)|(2[0-3])))?$/;
		                var reg5=/^\*$/;                            		// 匹配 *
		                if(!(reg1.test(value)||reg2.test(value)||reg3.test(value)||reg4.test(value)||reg5.test(value))){
		                    return "格式错误";
		                }
		                return true;
			        }else{
			            return false;
			        }
				},
			"cron_day":function(gets,obj,curform,datatype){
			       var value = gets; //传递过来真正要验证的字符串
			       if(value!=""){
			            value=value.replace(/(^\s*)|(\s*$)/g, ""); //去掉前后空白
		                // 为了清晰起见，我将规则拆分来写
			            var reg1=/^(([1-9])|([12]\d)|(3[01]))([\/\-](([1-9])|([12]\d)|(3[01])))?$/;  //形如1 12/18 7-26
			            var reg2=/^(([1-9])|([12]\d)|(3[01]))(,(([1-9])|([12]\d)|(3[01])))*$/;       //形如23,25,30
		                var reg3=/^(([1-9])|([12]\d)|(3[01]))(\-(([1-9])|([12]\d)|(3[01])))(\/(([1-9])|([12]\d)|(3[01])))?$/;       
		                var reg4=/^(\*)(\/(([1-9])|([12]\d)|(3[01])))?$/;
		                var reg5=/^\*$/;                            		// 匹配 *
		                var reg6=/^((1st)|(2nd)|(3rd)|(4th)|(5th)|(last))([ ]((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun)))?$/i;
		                if(!(reg1.test(value)||reg2.test(value)||reg3.test(value)||reg4.test(value)
		                		||reg5.test(value) ||reg6.test(value))){
		                    return "格式错误";
		                }
		                return true;
			        }else{
			            return false;
			        }
				},
			"cron_week":function(gets,obj,curform,datatype){
			       var value = gets; //传递过来真正要验证的字符串
			       if(value!=""){
			            value=value.replace(/(^\s*)|(\s*$)/g, ""); //去掉前后空白
		                // 为了清晰起见，我将规则拆分来写
			            var reg1=/^([0-6])([\/\-]([0-6]))?$/;  //形如1 12/18 7-26
			            var reg2=/^([0-6])(,([0-6]))*$/;       //形如23,25,30
			            var reg3=/^([0-6])(\-([0-6]))(\/([0-6]))?$/;      
			            var reg4=/^(\*)(\/([0-6]))?$/;
			            var reg5=/^\*$/;                            		// 匹配 *
		                var reg6=/^((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun))([\/\-]((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun)))?$/i;
		                var reg7=/^((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun))(,((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun)))*$/i;
		                
		                if(!(reg1.test(value)||reg2.test(value)||reg3.test(value)||reg4.test(value)
		                		||reg5.test(value)||reg6.test(value)||reg7.test(value))){
		                    return "格式错误";
		                } 
		                return true;
			        }else{
			            return false;
			        }
				},
			"cron_weeks":function(gets,obj,curform,datatype){
			       var value = gets; //传递过来真正要验证的字符串
			       if(value!=""){
			            value=value.replace(/(^\s*)|(\s*$)/g, ""); //去掉前后空白
		                // 为了清晰起见，我将规则拆分来写
			            var reg1=/^(([1-9])|([1234]\d)|(5[0123]))([\/\-](([1-9])|([1234]\d)|(5[0123])))?$/;  //形如1 12/18 7-26
			            var reg2=/^(([1-9])|([1234]\d)|(5[0123]))(,(([1-9])|([1234]\d)|(5[0123])))*$/;       //形如23,25,30
			            var reg3=/^(([1-9])|([1234]\d)|(5[0123]))(\-(([1-9])|([1234]\d)|(5[0123])))(\/(([1-9])|([1234]\d)|(5[0123])))?$/;       
			            var reg4=/^(\*)(\/(([1-9])|([1234]\d)|(5[0123])))?$/;
		                var reg5=/^\*$/;                            		// 匹配 *
		                if(!(reg1.test(value)||reg2.test(value)||reg3.test(value)||reg4.test(value)||reg5.test(value))){
		                    return "格式错误";
		                }
		                return true;
			        }else{
			            return false;
			        }
				},
			"cron_month":function(gets,obj,curform,datatype){
			       var value = gets; //传递过来真正要验证的字符串
			       if(value!=""){
			            value=value.replace(/(^\s*)|(\s*$)/g, ""); //去掉前后空白
		                // 为了清晰起见，我将规则拆分来写
			            var reg1=/^(([1-9])|(1[0-2]))([\/\-](([1-9])|(1[0-2])))?$/;  //形如23 12/18 7-19
			            var reg2=/^(([1-9])|(1[0-2]))(,(([1-9])|(1[0-2])))*$/;       //形如12,15,20
			            var reg3=/^(([1-9])|(1[0-2]))(\-(([1-9])|(1[0-2])))(\/(([1-9])|(1[0-2])))?$/;       
			            var reg4=/^(\*)(\/(([1-9])|(1[0-2])))?$/;
		                var reg5=/^\*$/;                            		// 匹配 *
		                if(!(reg1.test(value)||reg2.test(value)||reg3.test(value)||reg4.test(value)||reg5.test(value))){
		                    return "格式错误";
		                }
		                return true;
			        }else{
			            return false;
			        }
				},
			"cron_year":function(gets,obj,curform,datatype){
			       var value = gets; //传递过来真正要验证的字符串
			       if(value!=""){
			            value=value.replace(/(^\s*)|(\s*$)/g, ""); //去掉前后空白
		                // 为了清晰起见，我将规则拆分来写
			            var reg1=/^((19[7-9]\d)|(20\d\d))([\/\-]((19[7-9]\d)|(20\d\d)))?$/; 
			            var reg2=/^((19[7-9]\d)|(20\d\d))(,((19[7-9]\d)|(20\d\d)))*$/; 
	                    var reg3=/^(\*)(\/((19[7-9]\d)|(20\d\d)))?$/;
		                var reg4=/^\*$/;                            		// 匹配 *
		                if(!(reg1.test(value)||reg2.test(value)||reg3.test(value)||reg4.test(value))){
		                    return "格式错误";
		                }
		                return true;
			        }else{
			            return false;
			        }
				},
			"cron":function(gets,obj,curform,datatype){
				
		        var value = gets; //传递过来真正要验证的字符串
		        
		       if(value!=""){
		            value=value.replace(/(^\s*)|(\s*$)/g, ""); //去掉前后空白
		            var arr=value.split("|"); //用空白分割
		            
		          if(arr.length!=8){
		                return "表达式必须是由7个 | 隔开，如 *|*|0-3|3rd fri|*|*|6-8,11-12|* ";
		            }else{
		                // 为了清晰起见，我将规则拆分来写
		                var reg1=/^([0-5]?\d)([\/\-][0-5]?\d)?$/;			// 形如23 23/34 45-59
		                var reg2=/^[0-5]?\d(,[0-5]?\d)*$/ ;         		// 形如 12,43,56  
		                var reg3=/^([0-5]?\d)(\-[0-5]?\d)(\/[0-5]?\d)?$/;	// 12-25/2
		                var reg4=/^(\*)(\/[0-5]?\d)?$/; 					// */8
		                var reg5=/^\*$/;                            		// 匹配 *
		                
		                if(!(reg1.test(arr[0])||reg2.test(arr[0])||reg3.test(arr[0])||reg4.test(arr[0])||reg5.test(arr[0]))){
		                    return "第1位是秒，允许的值（0-59 ,*）如 （2,47,23-45,5/6,0-23/2）";
		                }
		                if(!(reg1.test(arr[1])||reg2.test(arr[1])||reg3.test(arr[1])||reg4.test(arr[1])||reg5.test(arr[1]))){
		                    return "第2位是分，允许的值（0-59 ,*）如 （2,47,23-45,5/6,0-23/2）";
		                }
		               
		                reg1=/^(([0-1]?\d)|(2[0-3]))([\/\-](([0-1]?\d)|(2[0-3])))?$/;  //形如23 12/18 7-19
		                reg2=/^(([0-1]?\d)|(2[0-3]))(,(([0-1]?\d)|(2[0-3])))*$/;       //形如12,15,20
		                reg3=/^(([0-1]?\d)|(2[0-3]))(\-(([0-1]?\d)|(2[0-3])))(\/(([0-1]?\d)|(2[0-3])))?$/;       
		                reg4=/^(\*)(\/(([0-1]?\d)|(2[0-3])))?$/;
		                
		                if(!(reg1.test(arr[2])||reg2.test(arr[2])||reg3.test(arr[2])||reg4.test(arr[2])||reg5.test(arr[2]))){
		                    return "第3位是小时，允许的值（0-23 ,*）如 （3,8,21-23,4/7,*）";
		                }
		                
		                reg1=/^(([1-9])|([12]\d)|(3[01]))([\/\-](([1-9])|([12]\d)|(3[01])))?$/;  //形如1 12/18 7-26
		                reg2=/^(([1-9])|([12]\d)|(3[01]))(,(([1-9])|([12]\d)|(3[01])))*$/;       //形如23,25,30
		                reg3=/^(([1-9])|([12]\d)|(3[01]))(\-(([1-9])|([12]\d)|(3[01])))(\/(([1-9])|([12]\d)|(3[01])))?$/;       
		                reg4=/^(\*)(\/(([1-9])|([12]\d)|(3[01])))?$/;
		                
		                if(!(reg1.test(arr[3])||reg2.test(arr[3])||reg3.test(arr[3])||reg4.test(arr[3])||reg5.test(arr[3]))){
		                    return "第4位是日，允许的值（1-31 ,*）如 （1,20,4-8,3/5等）";
		                }
		                
		                reg1=/^([0-6])([\/\-]([0-6]))?$/;  //形如1 12/18 7-26
		                reg2=/^([0-6])(,([0-6]))*$/;       //形如23,25,30
		                reg3=/^([0-6])(\-([0-6]))(\/([0-6]))?$/;      
		                reg4=/^(\*)(\/([0-6]))?$/;
		                var reg6=/^((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun))([\/\-]((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun)))?$/i;
		                var reg7=/^((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun))(,((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun)))*$/i;
		                
		                if(!(reg1.test(arr[4])||reg2.test(arr[4])||reg3.test(arr[4])||reg4.test(arr[4])
		                		||reg5.test(arr[4])||reg6.test(arr[4])||reg7.test(arr[4]))){
		                    return "第5位是星期，允许的值（0-6 ,*）如 （1,3,2-6,3/5等）";
		                } 
		                
		                reg1=/^(([1-9])|([1234]\d)|(5[0123]))([\/\-](([1-9])|([1234]\d)|(5[0123])))?$/;  //形如1 12/18 7-26
		                reg2=/^(([1-9])|([1234]\d)|(5[0123]))(,(([1-9])|([1234]\d)|(5[0123])))*$/;       //形如23,25,30
		                reg3=/^(([1-9])|([1234]\d)|(5[0123]))(\-(([1-9])|([1234]\d)|(5[0123])))(\/(([1-9])|([1234]\d)|(5[0123])))?$/;       
		                reg4=/^(\*)(\/(([1-9])|([1234]\d)|(5[0123])))?$/;
		                
		                if(!(reg1.test(arr[5])||reg2.test(arr[5])||reg3.test(arr[5])||reg4.test(arr[5])||reg5.test(arr[5]))){
		                    return "第6位是周，允许的值（1-53 ,*）如 （2,47,23-45,5/6,1-23/2）";
		                }
		                
		                reg1=/^(([1-9])|(1[0-2]))([\/\-](([1-9])|(1[0-2])))?$/;  //形如23 12/18 7-19
		                reg2=/^(([1-9])|(1[0-2]))(,(([1-9])|(1[0-2])))*$/;       //形如12,15,20
		                reg3=/^(([1-9])|(1[0-2]))(\-(([1-9])|(1[0-2])))(\/(([1-9])|(1[0-2])))?$/;       
		                reg4=/^(\*)(\/(([1-9])|(1[0-2])))?$/;
		                
		                if(!(reg1.test(arr[6])||reg2.test(arr[6])||reg3.test(arr[6])||reg4.test(arr[6])||reg5.test(arr[6]))){
		                    return "第7位是月，允许的值（1-12 ,*）如 （3,8,2-8,4/7,*）";
		                }
		                
	                    reg1=/^((19[7-9]\d)|(20\d\d))([\/\-]((19[7-9]\d)|(20\d\d)))?$/; 
	                    reg2=/^((19[7-9]\d)|(20\d\d))(,((19[7-9]\d)|(20\d\d)))*$/; 
	                    reg3=/^(\*)(\/((19[7-9]\d)|(20\d\d)))?$/;
	                    if(!(reg1.test(arr[7])||reg2.test(arr[7])||reg3.test(arr[7])||reg5.test(arr[7]))){
	                        return "第8位是年(可选字段)，允许的值（1970-2099 ,*）如 (2013,2012,2013 2012-2013等)";
	                    }
		                return true;
		           }    
		        }else{
		            return false;
		        }
				return false;
			},
		
			
			"daterange":function(gets,obj,curform,datatype){
				/*
					判断日期范围
					注意需要给表单元素绑定max或min属性，或两个同时绑定来指定最大或最小可输入的日期
					日期格式：2012/12/29 或 2012-12-29 或 2012.12.29 或 2012,12,29
				*/
				var maxim=new Date(obj.attr("max").replace(/[-\.,]/g,"/")),
					minim=new Date(obj.attr("min").replace(/[-\.,]/g,"/")),
					gets=new Date(gets.replace(/[-\.,]/g,"/"));

				if(!gets.getDate()){
					return "日期格式不对！";
				}
				
				if(gets>maxim){
					return "日期不能大于"+obj.attr("max");	
				}
				
				if(gets<minim){
					return "日期不能小于"+obj.attr("min");
				}
				
				return true;
			},
			
			
			"idcard":function(gets,obj,curform,datatype){
				/*
					该方法由网友提供;
					对身份证进行严格验证;
				*/
			
				var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
				var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;
			
				if (gets.length == 15) {   
					return isValidityBrithBy15IdCard(gets);   
				}else if (gets.length == 18){   
					var a_idCard = gets.split("");// 得到身份证数组   
					if (isValidityBrithBy18IdCard(gets)&&isTrueValidateCodeBy18IdCard(a_idCard)) {   
						return true;   
					}   
					return false;
				}
				return false;
				
				function isTrueValidateCodeBy18IdCard(a_idCard) {   
					var sum = 0; // 声明加权求和变量   
					if (a_idCard[17].toLowerCase() == 'x') {   
						a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
					}   
					for ( var i = 0; i < 17; i++) {   
						sum += Wi[i] * a_idCard[i];// 加权求和   
					}   
					valCodePosition = sum % 11;// 得到验证码所位置   
					if (a_idCard[17] == ValideCode[valCodePosition]) {   
						return true;   
					}
					return false;   
				}
				
				function isValidityBrithBy18IdCard(idCard18){   
					var year = idCard18.substring(6,10);   
					var month = idCard18.substring(10,12);   
					var day = idCard18.substring(12,14);   
					var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
					// 这里用getFullYear()获取年份，避免千年虫问题   
					if(temp_date.getFullYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){   
						return false;   
					}
					return true;   
				}
				
				function isValidityBrithBy15IdCard(idCard15){   
					var year =  idCard15.substring(6,8);   
					var month = idCard15.substring(8,10);   
					var day = idCard15.substring(10,12);
					var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
					// 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
					if(temp_date.getYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){   
						return false;   
					}
					return true;
				}
				
			}
		});
	}else{
		setTimeout(arguments.callee,10);
	}
})();
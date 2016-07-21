/**
 * 初始化系统的展示信息div
 */
//function initSysShowDiv(){
//	var divHtml="";
//	
//	$.ajax({
//		url : 'mesChart.do?updateMesOneStatus&errId=' + errId + '&processStatus=1',  
//		type : 'post',
//		cache : false,
//		async : false,
//		timeout : 1000 * 60,
//		// url:
//		// 'sysOperate.do?addSysInfo&id='+$('#id').val()+"&sysCode="+$('#sysCode').val()+"&sysDesc="+$('#sysDesc').val(),
//		dataType : 'json',
//		error : function() {
//			alert('网络失败！');
//		},
//		success : function(result) {
//			if (result.desc == null || result.desc == '') {
//				getMessageList();
//				alert("修改成功");
//			} else {
//				alert(result.desc);
//			}
//		}
//	});
//}

function Map() {
	var struct = function(key, value) {
		this.key = key;
		this.value = value;
	};

	var put = function(key, value) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				this.arr[i].value = value;
				return;
			}
		}
		this.arr[this.arr.length] = new struct(key, value);
	};

	var get = function(key) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				return this.arr[i].value;
			}
		}
		return null;
	};

	var remove = function(key) {
		var v;
		for ( var i = 0; i < this.arr.length; i++) {
			v = this.arr.pop();
			if (v.key === key) {
				continue;
			}
			this.arr.unshift(v);
		}
	};

	var size = function() {
		return this.arr.length;
	};

	var isEmpty = function() {
		return this.arr.length <= 0;
	};
	this.arr = new Array();
	this.get = get;
	this.put = put;
	this.remove = remove;
	this.size = size;
	this.isEmpty = isEmpty;
}  




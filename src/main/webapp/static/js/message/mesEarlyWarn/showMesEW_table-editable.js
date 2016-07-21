
var TableEditable = function () {

    var handleTable = function () {

        function restoreRow(oTable, nRow) {
        	
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);

            for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                oTable.fnUpdate(aData[i], nRow, i, false);
            }

            oTable.fnDraw();
        }

        function editRow(oTable, nRow) {
        	
        	
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);
            jqTds[0].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[0] + '" readonly>';
            jqTds[1].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[1] + '">';
            jqTds[2].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[2] + '">';
            jqTds[3].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[3] + '" readonly>';
            jqTds[4].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[4] + '">';
            jqTds[5].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[5] + '">';
            jqTds[6].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[6] + '">';
            jqTds[7].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[7] + '">';
            jqTds[8].innerHTML = '<a class="edit" href="">Save</a>';
            jqTds[9].innerHTML = '<a class="cancel" href="">Cancel</a>';
            
        }
        
        function saveRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            var id=jqInputs[0].value;
            var description=jqInputs[1].value;
            var url=jqInputs[2].value;
            var flag='0';
            var status='1';
            var count=jqInputs[5].value;
            var errLevel="1";	//预警等级
            var sendType='2';
            
            if(id!=null&&id!=''){	//修改
            	$.ajax({
    	        	url:'updateSysInfo.do',
    	   			 type:'post',   
    	   			 data:{sysCode:sysCode,sysDesc:sysDesc,id:id},
    	   			 cache:false,
    	   			 async:false,
    	   			 timeout:1000*60,   
    	   			 success:function(data){
    	   				 if(data!=null && data!='' && data=='true'){
    	   		            oTable.fnUpdate(description, nRow, 1, false);
    	   		            oTable.fnUpdate(url, nRow, 2, false);
    	   		            oTable.fnUpdate('有效', nRow, 3, false);
    	   		            oTable.fnUpdate('停止', nRow, 4, false);
    	   		            oTable.fnUpdate(count, nRow, 5, false);
    	   		            oTable.fnUpdate('警告', nRow, 6, false);
    	   		            oTable.fnUpdate('邮件', nRow, 7, false);
    	   		            oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 8, false);
    	   		            oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 9, false);
    	   		            oTable.fnDraw();
    	   				 }else{
    	   					 alert("修改失败,返回值错误!");
    	   				 }
    	   			 },error:function(e){
    	   				 alert("修改失败!");
    	   			 }	 
    	        });    
            }else{
            	$.ajax({
    	        	url:'insertMesEarlyWarn.do',
    	   			 type:'post',   
    	   			 data:{description:description,url:url,flag:flag,status:status,count:count,sendType:sendType},
    	   			 cache:false,
    	   			 async:false,
    	   			 timeout:1000*60,   
    	   			 success:function(data){
    	   				 if(data!=null && data!=''){
    	   					oTable.fnUpdate(data, nRow, 0, false);
    	   					oTable.fnUpdate(description, nRow, 1, false);
     	   		            oTable.fnUpdate(url, nRow, 2, false);
     	   		            oTable.fnUpdate('有效', nRow, 3, false);
     	   		            oTable.fnUpdate('停止', nRow, 4, false);
     	   		            oTable.fnUpdate(count, nRow, 5, false);
     	   		            oTable.fnUpdate(errLevel, nRow, 6, false);
     	   		            oTable.fnUpdate('邮件', nRow, 7, false);
     	   		            oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 8, false);
     	   		            oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 9, false);
    	   				 }else{
    	   					 alert("插入失败,id为空!");
    	   				 }
    	   			 },error:function(e){
    	   				 alert("插入失败!");
    	   			 }	 
    	        });    
            }
	        
            
        }

        function cancelEditRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
            oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
            oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
            oTable.fnUpdate(jqInputs[5].value, nRow, 5, false);
            oTable.fnUpdate(jqInputs[6].value, nRow, 6, false);
            oTable.fnUpdate(jqInputs[7].value, nRow, 7, false);
            oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 8, false);
            oTable.fnDraw();
        }
        
        function deleteRow(oTable,nRow){
        	var jqTd = $('td:eq(0)', nRow);
            var id=jqTd.html();
             $.ajax({
 	        	url:'deleteSysInfo.do',
 	   			 type:'post',   
 	   			 data:{id:id},
 	   			 cache:false,
 	   			 async:false,
 	   			 timeout:1000*60,   
 	   			 success:function(data){
 	   				 if(data!=null && data!='' && data=='true'){
 	   					oTable.fnDeleteRow(nRow);
 	   				 }else{
 	   					 alert("删除失败,返回数据错误!");
 	   				 }
 	   			 },error:function(e){
 	   				 alert("删除失败!");
 	   			 }	 
 	        });    
        }

        var table = $('#sample_editable_1');

        var oTable = table.dataTable({

            // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
            // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
            // So when dropdowns used the scrollable div should be removed. 
            //"dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",

            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 10,
            "bProcessing" : true, // 开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
//			"bServerSide" : true, // 开启服务器模式，使用服务器端处理配置datatable。注意：sAjaxSource参数也必须被给予为了给datatable源代码来获取所需的数据对于每个画。
//            "sAjaxSource":'/pageSysInfoAjax.do?sysCode=',
           
            "language": {
                "lengthMenu": " _MENU_ records"
            },
            "columnDefs": [{ // set default column settings
                'orderable': true,
                'targets': [0]
            }, {
                "searchable": true,
                "targets": [0]
            }]
//            ,
//            "aoColumns": [
//                          { "mData": "id" },
//                          { "mData": "sysCode" },
//                          { "mData": "sysDesc" },
//                          { "sDefaultContent" : ''},
//                          { "sDefaultContent" : ''}
//                      ],
//            "columnDefs": [{
//                          "targets": -2,//编辑
//                          "data": null,
//                          "defaultContent": '<a class="edit" href="">Edit</a>'
//                  },{
//                          "targets": -1,//删除
//                          "data": null,
//                          "defaultContent": '<a class="edit" href="">Delete</a>'
//                  }
//                  ],       
//            "order": [
//                [0, "asc"]
//            ]  // set first column as a default sort by asc
           
        });

        var tableWrapper = $("#sample_editable_1_wrapper");

        tableWrapper.find(".dataTables_length select").select2({
            showSearchInput: false //hide search box with special css class
        }); // initialize select2 dropdown

        var nEditing = null;
        var nNew = false;

        $('#sample_editable_1_new').click(function (e) {
            e.preventDefault();

            if (nNew && nEditing) {
                if (confirm("Previose row not saved. Do you want to save it ?")) {
                    saveRow(oTable, nEditing); // save
                    $(nEditing).find("td:first").html("Untitled");
                    nEditing = null;
                    nNew = false;

                } else {
                    oTable.fnDeleteRow(nEditing); // cancel
                    nEditing = null;
                    nNew = false;
                    
                    return;
                }
            }

            var aiNew = oTable.fnAddData(['', '', '', '', '','','','','','']);
            var nRow = oTable.fnGetNodes(aiNew[0]);
            editRow(oTable, nRow);
            nEditing = nRow;
            nNew = true;
        });

        table.on('click', '.delete', function (e) {
            e.preventDefault();

            if (confirm("Are you sure to delete this row ?") == false) {
                return;
            }

            var nRow = $(this).parents('tr')[0];
            deleteRow(oTable,nRow);
            
        });

        table.on('click', '.cancel', function (e) {
            e.preventDefault();
            if (nNew) {
                oTable.fnDeleteRow(nEditing);
                nEditing = null;
                nNew = false;
            } else {
                restoreRow(oTable, nEditing);
                nEditing = null;
            }
        });

        table.on('click', '.edit', function (e) {
            e.preventDefault();

            /* Get the row as a parent of the link that was clicked on */
            var nRow = $(this).parents('tr')[0];

            if (nEditing !== null && nEditing != nRow) {
                /* Currently editing - but not this row - restore the old before continuing to edit mode */
                restoreRow(oTable, nEditing);
                editRow(oTable, nRow);
                nEditing = nRow;
            } else if (nEditing == nRow && this.innerHTML == "Save") {
                /* Editing this row and want to save it */
                saveRow(oTable, nEditing);
                nEditing = null;
            } else {
                /* No edit in progress - let's start one */
                editRow(oTable, nRow);
                nEditing = nRow;
            }
        });
    }

    return {

        //main function to initiate the module
        init: function () {
            handleTable();
        }

    };

}();
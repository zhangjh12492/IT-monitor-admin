<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/meta.jsp"></jsp:include>
<jsp:include page="../inc/easyui.jsp"></jsp:include>
<script type="text/javascript" charset="UTF-8">
var datagrid;
var brandInfoForm;

function uploadPic() {
    var filePath = $('#pic').val();
    var spell = $('#spell').val();
    if (spell == '') {
        $.messager.alert('提示', '请选择要上传LOGO的品牌！', 'error');
        return;
    }
    if (filePath == '') {
        $.messager.alert('提示', '请选择要上传的LOGO！', 'error');
        return;
    }
    $('#logoForm').form('submit', {
        url:'brandInfoController.do?uploadPic',
        success:function (data) {
            $.messager.alert('提示', '成功！');
            var sp = $('#spell').val();
            var src1 = 'http://img03-saite-com.pcds.cn/brands/' + sp + '/logo.gif';
            $('#img').attr("src", src1);
        },
        error:function (_data) {
            $.messager.alert('提示', '失败！');

        }
    });

}

function exportExcel() {
    var ids = [];
    var rows = $('#dataGrid').datagrid('getSelections');
    if (rows.length > 0) {
        for (var i = 0; i < rows.length; i++) {
            ids.push(rows[i].BRAND_SID);
        }
        sids = ids.join(',');
        $('#sids').val(sids);
        /*
         $.ajax({
         method:'post',
         async:false,
         url:'brandInfoController.do?exportExcel',
         success:function () {
         alert("保存成功！");
         },
         error:function () {
         alert("保存失败！")
         }
         })
         */
        $('#grd').form('submit', {
            url:'brandInfoController.do?exportExcel',
            success:function (data) {
            }
        });

    } else {
        $.messager.alert('提示', '请选择要导出的记录！', 'error');
    }
}


function clearBrandInfoFrom() {
    $('#b_country').val('');
    $('#b_founder').val('');
    $('#b_found_date').val('');
    $('#b_stylist').val('');
    $('#b_content').val('');
}

$(function () {
    brandInfoForm = $('#brandInfoForm').form({
        onLoadSuccess:function (_data) {
            var tt = _data;
            $('#b_country').val(tt.B_COUNTRY);
            $('#b_founder').val(tt.B_FOUNDER);
            $('#b_found_date').val(tt.B_FOUND_DATE);
            $('#b_stylist').val(tt.B_STYLIST);
            $('#b_content').val(tt.B_CONTENT);
            $('#sid').val(tt.SID);
        }
    });
    /*
     $('#brand').combobox({
     url:'brandInfoController.do?getBrand',
     valueField:'SID',
     textField:'BRAND_NAME',
     editable:true,
     onSelect:function (_data) {
     var key = _data['SID'];
     var value = _data['SPELL'].toLocaleLowerCase();
     $.ajax({
     url:'brandInfoController.do?getBrandBySID&b_sid=' + key,
     async:true,
     success:function (_data) {
     //                            var tt = eval("(" + _data + ")");
     var tt = _data;
     $('#b_country').val(tt.B_COUNTRY);
     $('#b_founder').val(tt.B_FOUNDER);
     $('#b_found_date').val(tt.B_FOUND_DATE);
     $('#b_stylist').val(tt.B_STYLIST);
     $('#b_content').val(tt.B_CONTENT);
     $('#spell').val(tt.SPELL.toLowerCase());
     $('#sid').val(tt.SID);
     //品牌LOGO
    <%--http://img03-saite-com.pcds.cn/brands/fancl/logo.gif--%>
     var src = 'http://img03-saite-com.pcds.cn/brands/' + value.toLowerCase() + '/logo.gif';
     $('#img').attr("src", src);
     }
     });
     }
     });
     */
    $('#classType').combotree({
        multiple:true,
        url:'brandInfoController.do?getClassJson'
    });
    datagrid = $('#dataGrid').datagrid({
//                url:'brandInfoController.do?getBrandInfoList',
        toolbar:'#dbar',
        title:'',
        iconCls:'icon-save',
        method:"post",
        pagination:true,
        remoteSort:false,
        singleSelect:false,
        pageSize:10,
        pageList:[ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
        fit:true,
        fitColumns:false,
        nowrap:false,
        border:false,
        frozenColumns:[
            [
                {
                    title:'BRAND_SID',
                    field:'BRAND_SID',
                    width:50,
                    checkbox:true
                }
            ]
        ],
        columns:[
            [
                {
                    title:'中文品牌名',
                    field:'BRAND_NAME_SECOND',
                    width:120
                },
                {
                    field:'BRAND_NAME',
                    title:'英文品牌名',
                    width:100,
                    sortable:false
                },
                {
                    field:'B_COUNTRY',
                    title:'国家',
                    width:60,
                    sortable:false
                },
                {
                    field:'B_FOUNDER',
                    title:'创始人',
                    width:100
                },
                {
                    field:'B_FOUND_DATE',
                    title:'创建时间',
                    width:60,
                    sortable:false
                },
                {
                    field:'B_STYLIST',
                    title:'现任设计师',
                    width:100,
                    sortable:false
                }
                ,
                {
                    field:'SPELL',
                    title:'',
                    width:100,
                    sortable:false,
                    hidden:true
                }
            ]
        ],
        onClickRow:function (rowIndex, rowData) {
            $('#brand_name').val(rowData["BRAND_NAME"]);
            $('#brand_name_second').val(rowData["BRAND_NAME_SECOND"]);
            var key = rowData["BRAND_SID"];
            var value = rowData["SPELL"].toLowerCase();
            $('#spell').val(value);
            var src = 'http://img03-saite-com.pcds.cn/brands/' + value.toLowerCase() + '/logo.gif';
            $('#img').attr("src", src);
            $('#brandInfoForm').form('load','brandInfoController.do?getBrandBySID&b_sid=' + key);
            <%--var src = 'http://img03-saite-com.pcds.cn/brands/' + value.toLowerCase() + '/logo.gif';--%>
            <%--$('#img').attr("src", src);--%>
            <%--$.ajax({--%>
                <%--url:'brandInfoController.do?getBrandBySID&b_sid=' + key,--%>
                <%--success:function (_data) {--%>
<%--//                            var tt = eval("(" + _data + ")");--%>
                    <%--var tt = _data;--%>
                    <%--$('#b_country').val(tt.B_COUNTRY);--%>
                    <%--$('#b_founder').val(tt.B_FOUNDER);--%>
                    <%--$('#b_found_date').val(tt.B_FOUND_DATE);--%>
                    <%--$('#b_stylist').val(tt.B_STYLIST);--%>
                    <%--$('#b_content').val(tt.B_CONTENT);--%>
                    <%--$('#sid').val(tt.SID);--%>

                    <%--//品牌LOGO--%>
                    <%--&lt;%&ndash;http://img03-saite-com.pcds.cn/brands/fancl/logo.gif&ndash;%&gt;--%>

                <%--}--%>
            <%--});--%>
        }
    });
});

function uploadPicture() {
    alert('abc');
}

function qryBrandInfoList() {
//            $('#dataGrid').attr('url','brandInfoController.do?getBrandInfoList');
    var param = $('#sids').val("");
    var param = $('#grd').serialize();
    $('#dataGrid').datagrid({
        url:'brandInfoController.do?getBrandInfoList.json&' + param
    });
//            $('#dataGrid').datagrid('load',{});
}

function updateBrandInfo() {
    var zh_brand = $('#brand_name').val();
    if(zh_brand==''){
        alert('先选择品牌！');
        return;
    }

    var param = $('#brandInfoForm').serialize();
    /*
     $.ajax({
     method:'post',
     //                async:false,
     url:'brandInfoController.do?updateBrandInfo&' + param,
     success:function () {
     alert("保存成功！");
     },
     error:function () {
     alert("保存失败！")
     }
     })
     */

    brandInfoForm.form('submit', {
        url:'brandInfoController.do?updateBrandInfo',
        success:function (data) {
            try {
                var d = $.parseJSON(data);
                if (d) {
                    $.messager.alert("提示", "详情保存成功！");
                }
//                        datagrid.datagrid('reload');
                $('#dataGrid').datagrid('reload');
            } catch (e) {
                $.messager.show({
                    msg:e.message + ":" + data,
                    title:'提示'
                });
            }
        }
    });
}


</script>
</head>
<body class="easyui-layout" fit="true">
<%
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date currentTime = new java.util.Date();//得到当前系统时间
    Long dt = currentTime.getTime();
    dt = dt - 1000 * 60 * 60 * 24 * 2;
    java.util.Date cur = new Date(dt);
//    String str_date1 = formatter.format(currentTime); //将日期时间格式化
    String str_date1 = formatter.format(cur); //将日期时间格式化
    String str_date2 = formatter.format(currentTime); //将Date型日期时间转换成字符串形式
%>
<div region="center" border="false" style="overflow: hidden; height: 100%">
<%--<div id="toolbar" class="datagrid-toolbar" style="height: 100%;">--%>
<div id="toolbar" style="height: 100%;">
<table>
<tr>
    <td style="width: 600px; height: 100%">
        <div id="dbar" class="datagrid-toolbar" style="height: 60px;">
            <%--<form id="grd">--%>
            <table>
                <tr>
                    <td>
                        <form id="grd" method="post">
                            <table>
                                <tr>
                                    <th>品牌</th>
                                    <td>
                                        <input id="brandName" name="brandName" style="width: 80px;"/>
                                        <input id="sids" name="sids" style="display: none;"/>
                                    </td>
                                    <th>分类</th>
                                    <td>
                                        <select id="classType" name="classType" style="width: 180px;"
                                                class="easyui-combotree"/>
                                        <%--<input id="classType" />--%>
                                    </td>
                                </tr>
                                <tr>
                                    <th>网站状态</th>
                                    <td>
                                        <select id="display_bit" name="display_bit" style="width: 80px;">
                                            <option value="" selected="selected">全部</option>
                                            <option value="1">显示</option>
                                            <option value="0">不显示</option>
                                        </select>
                                    </td>
                                    <th>单品状态</th>
                                    <td>
                                        <select id="brand_active_bit" name="brand_active_bit" style="width: 80px;">
                                            <option value="" selected="selected">全部</option>
                                            <option value="1">启用</option>
                                            <option value="0">禁用</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td>
                                    <input id="btnQry" type="button" style="width: 60px;" value="查询"
                                           onclick="qryBrandInfoList()"/>
                                </td>
                                <td>
                                    <input id="btnExp" type="button" style="width: 60px;" value="导出"
                                           onclick="exportExcel()"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <%--</form>--%>
        </div>
        <table id="dataGrid"></table>
    </td>
    <td style=" border-right: 1px; border-right-style: solid;">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    <td>
        <fieldset style="display: none;">
            <legend>第一步：选择品牌信息</legend>
            <form id="batchForm" method="post">
                <table class="tableForm">
                    <tr>
                        <th>品牌</th>
                        <td>
                            <input id="brand" name="brand" style="width: 300px;" required="true"/>
                        </td>
                    </tr>
                </table>
            </form>
        </fieldset>
        <%--<fieldset>--%>
        <%--<legend>品牌LOGO编辑</legend>--%>
        <table class="tableForm">
            <tr>
                <form id="logoForm" method="post" enctype="multipart/form-data">
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>
                        <input id="spell" name="spell" style="display: none;">
                    </td>
                    <td>
                        <th>LOGO：</th>
                    </td>
                    <td>
                        <fieldset style="height: 100px;width: 100px; border: 0">
                            <%--<img src="file:\\\\E:\\1.jpg" style="width: 100px;height: 100px;"/>--%>
                            <img id="img" name="img" border="1"
                            <%--src="http://img01-saite-com.pcds.cn/prod/images/29258/29258_ou_m2.jpg"--%>
                                 style="width: 100px;height: 100px;"/>
                        </fieldset>
                    </td>
                    <td style="width: 220px;">
                        <input id="pic" name="pic" type="file"/>
                    </td>
                </form>
                <%--<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>--%>
                <td>
                    <%--<a class="easyui-linkbutton" onclick="" plain="true" href="javascript:void(0);">上传图片</a>--%>
                    <button id="uploadPIC" name="uploadPIC" onclick="uploadPic()">上传图片</button>
                </td>
            </tr>
        </table>
        <%--</fieldset>--%>
        <%--<fieldset>--%>
        <%--<legend>品牌资料编辑</legend>--%>
        <form id="brandInfoForm" method="post">
            <table class="tableForm">
                <tr>
                    <th>中文品牌名：</th>
                    <td>
                        <input id="brand_name" style="width: 200px;" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th>英文品牌名：</th>
                    <td>
                        <input id="brand_name_second" style="width: 200px;" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th>国家：</th>
                    <td>
                        <input id="b_country" name="b_country" style="width: 200px;"/>
                    </td>
                </tr>
                <tr>
                    <th>创始人：</th>
                    <td>
                        <input id="b_founder" name="b_founder" style="width: 200px;"/>
                    </td>
                </tr>
                <tr>
                    <th>创建时间：</th>
                    <td>
                        <input id="b_found_date" name="b_found_date" style="width: 200px;"/>
                    </td>
                </tr>
                <tr>
                    <th>现任设计师：</th>
                    <td>
                        <input id="b_stylist" name="b_stylist" style="width: 200px;"/>
                    </td>
                    <td>
                        <input id="sid" name="sid" style="display: none;"/>
                    </td>
                </tr>
                <%--<tr>
                </tr>--%>
                <tr>
                    <th>品牌介绍：</th>
                    <td colspan=8>
                        <textarea id="b_content" name="b_content" style="width: 300%;"
                                  rows="10"></textarea>
                    </td>
                </tr>
            </table>
        </form>
        <table>
            <tr>
                <td style="width: 100%" colspan="3">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button id="btnContent" align="center"
                            onclick="updateBrandInfo()">保存品牌信息
                    </button>
                    <button id="btnClear" align="center"
                            onclick="clearBrandInfoFrom()">重置
                    </button>
                </td>
            </tr>
        </table>
        <%--</fieldset>--%>
        <fieldset style="display: none;">
            <%--<legend>品牌尺码表编辑</legend>--%>
            <table class="tableForm">
                <tr>
                    <form id="brandSizeTableForm" method="post">
                        <th>尺码表导入</th>
                        <td style="width: 220px;">
                            <input id="sizeTable" name="SizeTable" type="file" style=""/>
                        </td>
                    </form>
                    <td>
                        <button id="btnUpload" name="btnUpload">上传</button>
                    </td>
                    <td>
                        <button id="btnPreview" name="btnPreview">预览</button>
                    </td>
                    <td>
                        <button id="btnDownload" name="btnDownload">下载模板</button>
                    </td>
                </tr>
            </table>
        </fieldset>
    </td>
</tr>
</table>
</div>
</div>
</div>
</body>
</html>
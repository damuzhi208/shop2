/**
 * 查询
 */
function doSearch(){
	var params = {};
	$("#toolbar").find("input[name],select[name]").each(function(i){
		params[this.name] = this.value;
	});
	$("#datagrid").datagrid('load',params);
}

/**
 * 规格formatter
 * @param value
 * @param row
 * @param index
 */
function guigeFormatter(value,row,index){
	var str = "【"+row.guige+"】厚度["+row.houdu+"]"+"系数["+row.xishu+"]"+"吨位价["+row.dwj+"]"+"单价["+row.danjia+"]";
	return str;
}

function YMDDateFormatter(value,row,index){
	if(value){
		return value.substring(0, 11);
	}
}
/**
 * 操作formatter
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value,row,index){
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modStockQj(\''+row.id+'\', \''+row.name+'\')">修改</a>';
}

/**
 * 修改
 * @param pId
 * @param name
 * @returns {Boolean}
 */
function modStockQj(pId, name){
	var url = "stock/modstockQj";
	if(pId){
		url += "?id=" + pId;
	}
	modelDialog = sy.iframeDialog({
		"href" : url,
		"height" : 210,
		"width" : 580,
		"title" : "【入库编辑】",
		"buttons": [
              {
            	  text : '确定',handler : function() {
            		  var _body = modelDialog.find('iframe').get(0).contentWindow;
            		  _body.$('#ff').form('submit',{
	                    success: function(result) {
	                    	result = JSON.parse(result);
	                    	$.messager.alert('提示', result.msg);
	                    	if(result.success){
	                    		modelDialog.dialog('close');
    	                        $("#datagrid").datagrid('reload');
	                    	}
	                    }
                	});
      			}
              },{
            	  text: '取消', handler: function() {
            		  modelDialog.dialog('destroy');
                	  $("#datagrid").datagrid('reload');
            	  }
              }
        ]
	});
	return false;
}

/**
 * 新增
 */
function addBtnClick(){
	modStockQj(null, null);
}
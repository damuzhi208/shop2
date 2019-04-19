
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
 * 状态formatter
 * @param value
 * @param row
 * @param index
 */
function stateFormatter(value, row, index){
	if(value) return '有效';
	return '无效';
}

/**
 * 操作formatter
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value, row, index){
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modRole(\''+row.id+'\')">修改</a>'
		+'<a href="javascript:void(0)" class="easyui-delbtn" onclick="delBtnClick(\''+row.id+'\')">删除</a>';
}

function delBtnClick(id){
	$.messager.confirm("操作提示", "数据删除后无法恢复，确定删除？", function(data) {
		if (data) {
			$.post("order/deleteLine?id="+id , function(js){ 
				$.messager.alert('提示', js.msg);
				if(js.success){
					doSearch();
				}
			},'json');
		}
	});
}

function modRole(id){
	addBtnRole(id);
}

function addBtnRole(id){
	var url = "role/modRole";
	if(id){
		url += "?id="+id;
	}
	modelDialog = sy.iframeDialog({
		"href" : url,
		"height" : 340,
		"width" : 640,
		"title" : "角色编辑",
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

function delBtnClick(id){
	$.messager.confirm("操作提示", "数据删除后无法恢复，确定删除？", function(data) {
		if (data) {
			$.post("role/deleRole?id="+id , function(js){ 
				$.messager.alert('提示', js.msg);
				if(js.success){
					doSearch();
				}
			},'json');
		}
	});
}
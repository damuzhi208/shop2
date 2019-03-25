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
 * 类型
 * @param value
 * @param row
 * @param index
 */
function statusFormatter(value,row,index){
	if(row.status == 1) return '有效';
	return '<span style="color:red;">无效</span>';
}

/**
 * 操作
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value,row,index){
	return '<a href="javascript:void(0)" onclick="modUnit(\''+row.id+'\', \''+row.name+'\')">修改</a>';
}

function modUnit(pId, name){
	var url = "baseUnit/modUnit";
	if(pId){
		url += "?id="+pId;
	}
	modelDialog = sy.iframeDialog({
		"href" : url,
		"height" : $('body', document).height() * 0.5,
		"width" : $('body', document).width() * 0.4,
		"title" : '单位信息',
		"buttons": [
              {
            	  text : '确定',handler : function() {
            		  var _body = modelDialog.find('iframe').get(0).contentWindow;
            		  _body.$('#ff').form('submit',{
	                    success: function(result) {
	                    	result = JSON.parse(result);
	                    	$.messager.alert('提示', result.msg);
	                    	if(result.success){
    	                        $("#datagrid").datagrid('reload');
    	                        modelDialog.dialog('close');
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

function addBtnClick(){
	modUnit(null, "");
}


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
 * 顾客类别
 * @param value
 * @param row
 * @param index
 */
function cusTypeFormatter(value,row,index){
	if(value == 1){
		return '顾客';
	}else if(value == 2){
		return '同行';
	}
	return '未知';
}
/**
 * 操作
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value,row,index){
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modCustomer(\''+row.id+'\', \''+row.name+'\')">修改</a>'
		+'<a href="javascript:void(0)" class="easyui-delbtn" onclick="delBtnClick(\''+row.id+'\')">删除</a>';
}

function modCustomer(pId, name){
	var url = "customer/modCustomer";
	if(pId){
		url += "?id="+pId;
	}
	modelDialog = sy.iframeDialog({
		"href" : url,
		"height" : 300,
		"width" : 640,
		"title" : '客户信息【' + name + '】 编辑',
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
	modCustomer(null, "");
}

/**
 * 删除
 */
function delBtnClick(id){
	$.messager.confirm("操作提示", "数据删除后无法恢复，确定删除？", function(data) {
		if (data) {
			$.post("customer/delCustomer?id="+id , function(js){ 
				$.messager.alert('提示', js.msg);
				if(js.success){
					doSearch();
				}
			},'json');
		}
	});
}

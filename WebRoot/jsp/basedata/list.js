/**
 * 查询
 */
function doSearch(){
	var params = {};
	$("#toolbar").find("input[name],select[name]").each(function(i){
		params[this.name] = this.value;
	});
	console.log("params"+JSON.stringify(params));
	$("#datagrid").datagrid('load',params);
}

/**
 * 喷塑桥架/镀锌桥架
 * @param value
 * @param row
 * @param index
 */
function mTypeFormatter(value,row,index){
	if(row.mType==1) return '喷塑桥架';
	if(row.mType==2) return '镀锌桥架';
	return '其他';
}

/**
 * 桥架/盖板
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function typeFormatter(value,row,index){
	if(row.type==1) return '桥架';
	if(row.type==2) return '盖板';
	return '其他';
}

/**
 * 操作
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value,row,index){
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modBaseQj(\''+row.id+'\', \''+row.guige+'\', \''+row.type+'\')">修改</a>'
		+'<a href="javascript:void(0)" class="easyui-delbtn" onclick="delBtnClick(\''+row.id+'\')">删除</a>';
}

function modBaseQj(pId, guige, type){
	var url = "baseqj/modBaseQj";
	if(pId){
		url += "?id="+pId;
	}
	modelDialog = sy.iframeDialog({
		"href" : url,
		"height" : 320,
		"width" : 630,
		"title" : "【桥架/盖板】编辑",
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
            	  text: '关闭', handler: function() {
            		  modelDialog.dialog('destroy');
                	  $("#datagrid").datagrid('reload');
            	  }
              }
        ]
	});
	return false;
}

function addBtnClick(){
	modBaseQj(null, null, null);
}

/**
 * 刪除
 * @param id
 */
function delBtnClick(id){
	$.messager.confirm("操作提示", "数据删除后无法恢复，确定删除？", function(data) {
		if (data) {
			$.post("lineTube/delLineTube?id="+id , function(js){ 
				$.messager.alert('提示', js.msg);
				if(js.success){
					doSearch();
				}
			},'json');
		}
	});
}

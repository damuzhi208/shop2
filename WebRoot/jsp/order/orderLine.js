
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
var mTypeJson = {'1':'金属软管','2':'线管'};
function guigeFormatter(value, row, index){
	if(row.guige == '合计') return '合计';
	return '【'+row.guige+'】'+mTypeJson[row.mType]+'[单价['+row.danjia+']单位['+row.danweis+']';
}

function easyuiMoneyFormatter1(value, row, index){
	if(row.guige == '合计') return;
	if(!value) return;
	return value.toFixed(2);
}

/**
 * 操作formatter
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value, row, index){
	if(row.guige == '合计') return;
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modLine(\''+row.id+'\')">修改</a>'
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

function modLine(id){
	var mType = $("#mType").val();
	addBtnQj(mType, id);
}

function addBtnQj(mType, id){
	var orderId = parent.isSaved();
	if(!orderId){
		$.messager.alert('提示', "请先保存订单!");
		return false;
	}
	var url = "order/modLine?mType="+mType+"&orderId="+orderId+"&id="+id;
	modelDialog = parent.sy.iframeDialog({
		"href" : url,
		"height" : 340,
		"width" : 640,
		"title" : "线管/软管订单编辑",
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
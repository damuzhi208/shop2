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
var typeJson = {'1':'金属软管','2':'线管'};
function lxFormatter(value, row, index){
	return typeJson[value];
}
function guigeFormatter(value, row, index){
	if(row.guige == '合计') return '合计';
	return "【" + row.guige + "】单位[" + row.danweis + "]单价[" + row.danjia + "]";
}

/**
 * YMD  formatter
 * @param value
 * @param row
 * @param index
 * @returns
 */
function YMDDateFormatter(value,row,index){
	if(value){
		return value.substring(0, 11);
	}
}

/**
 * 利润formatter
 * @param value
 * @param row
 * @param index
 */
function profitFormatter(value,row,index){
	if(row.shopName == '合计') return ;
	if(row.nums && row.cost && row.salePrice){
		return parseFloat((Number(row.salePrice) - Number(row.cost)) * Number(row.nums)).toFixed(2);
	}
}
/**
 * 操作formatter
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value, row, index){
	if(row.guige == '合计') return ;
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modStockLine(\''+row.id+'\')">修改</a>';
		//+'<a href="javascript:void(0)" class="easyui-delbtn" onclick="delBtnClick(\''+row.id+'\')">删除</a>';
}

/**
 * 修改
 * @param pId
 * @param name
 * @returns {Boolean}
 */
function modStockLine(pId, name){
	var url = "stockLine/modStockLine";
	if(pId){
		url += "?id=" + pId;
	}
	modelDialog = sy.iframeDialog({
		"href" : url,
		"height" : 320,
		"width" : 600,
		"title" : "金属软管/线管库存编辑",
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
 * 增加
 */
function addBtnClick(){
	modStockLine(null, null);
}

/**
 * 删除
 * @param id
 */
function delBtnClick(id){
	$.messager.confirm("操作提示", "数据删除后无法恢复，确定删除？", function(data) {
		if (data) {
			$.post("stockLine/delTransRecord?id="+id , function(js){ 
				$.messager.alert('提示', js.msg);
				if(js.success){
					doSearch();
				}
			},'json');
		}
	});
}

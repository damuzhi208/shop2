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
 * 调动类型
 * @param value
 * @param row
 * @param index
 */
function transTypeFormatter(value, row, index){
	if(value == 1){
		return "调入";
	}else if(value == 2){
		return "调出";
	}
}

/**
 * 进货价格formatter
 * @param value
 * @param row
 * @param index
 * @returns
 */
function costFormatter(value, row, index){
	if(row.shopName == '合计') return ;
	return value;
}

/**
 * 交易价格formatter
 * @param value
 * @param row
 * @param index
 * @returns
 */
function salePriceFormatter(value, row, index){
	if(row.shopName == '合计') return ;
	return value;
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
	if(row.shopName == '合计') return ;
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modLine(\''+row.id+'\', \''+row.name+'\')">修改</a>'
		+'<a href="javascript:void(0)" class="easyui-delbtn" onclick="delBtnClick(\''+row.id+'\')">删除</a>';
}

/**
 * 新增、修改调动记录
 * @param pId
 * @param name
 * @returns {Boolean}
 */
function modLine(pId, name){
	var url = "transfer/modTransfer";
	if(pId){
		url += "?id=" + pId;
	}
	modelDialog = sy.iframeDialog({
		"href" : url,
		"height" : 340,
		"width" : 620,
		"title" : "【商品调动】修改",
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
	modLine(null, null);
}

/**
 * 删除
 * @param id
 */
function delBtnClick(id){
	$.messager.confirm("操作提示", "数据删除后无法恢复，确定删除？", function(data) {
		if (data) {
			$.post("transfer/delTransRecord?id="+id , function(js){ 
				$.messager.alert('提示', js.msg);
				if(js.success){
					doSearch();
				}
			},'json');
		}
	});
}

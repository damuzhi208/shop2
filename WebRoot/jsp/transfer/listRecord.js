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
	return '<a href="javascript:void(0)" onclick="modLine(\''+row.id+'\', \''+row.name+'\')">修改</a>';
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
		"height" : $('body', document).height() * 0.65,
		"width" : $('body', document).width() * 0.4,
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

function addBtnClick(){
	modLine(null, null);
}

function editBtnClick(){
	var row = $("#datagrid").datagrid('getSelected');
	if(!row){
		showInfo({msg:''});
		return;
	}
	top.openTab(row.infoName,basePath+'transfer/modPositive?id='+row.id);
}
function delBtnClick(){
	var row = $("#datagrid").datagrid('getSelected');
	if(!row){
		showInfo({msg:'请选择记录'});
		return;
	}
	showConfirm('提示','确定？',function(){
		var url = basePath + 'singleTable/jsonDelete?entityClass=HrPositive&id='+row.id;
		$.post(url,function(js){
			showInfo({msg:js.msg});
			if(js.success)
				$("#datagrid").datagrid('deleteRow',$("#datagrid").datagrid('getRowIndex',row));
		},'json');
	});
}

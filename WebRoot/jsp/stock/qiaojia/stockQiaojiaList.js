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
	return '<a href="javascript:void(0)" onclick="modLine(\''+row.id+'\', \''+row.name+'\')">修改</a>';
}

function modLine(pId, name){
	modelDialog = sy.iframeDialog({
		"href" : "customer/modCustomer?id="+pId,
		"height" : $('body', document).height() * 0.5,
		"width" : $('body', document).width() * 0.4,
		"title" : name,
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
	top.openTab('',basePath+'hr/modPositive');
}

function editBtnClick(){
	var row = $("#datagrid").datagrid('getSelected');
	if(!row){
		showInfo({msg:''});
		return;
	}
	top.openTab(row.infoName,basePath+'hr/modPositive?id='+row.id);
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

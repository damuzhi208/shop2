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
 * 喷塑桥架/镀锌桥架
 * @param value
 * @param row
 * @param index
 */
function mTypeFormatter(value,row,index){
	if(row.mType==1) return '金属软管';
	if(row.mType==2) return '线管';
	return '其他';
}

/**
 * 操作
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value,row,index){
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modLine(\''+row.id+'\', \''+row.guige+'\', \''+row.type+'\')">修改</a>';
}

function modLine(pId, guige, type){
	var title = (type == 1) ? "软管" : "线管"; 
	modelDialog = sy.iframeDialog({
		"href" : "baseqj/modLine?id="+pId,
		"height" : $('body', document).height() * 0.68,
		"width" : $('body', document).width() * 0.4,
		"title" : guige +'【' + title + '】修改',
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
		showInfo({msg:'？？？？？？'});
		return;
	}
	showConfirm('提示','确定删除吗？',function(){
		var url = basePath + 'singleTable/jsonDelete?entityClass=HrPositive&id='+row.id;
		$.post(url,function(js){
			showInfo({msg:js.msg});
			if(js.success)
				$("#datagrid").datagrid('deleteRow',$("#datagrid").datagrid('getRowIndex',row));
		},'json');
	});
}

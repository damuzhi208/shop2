/**
 * 查询
 */
function doSearch(){
	var params = {};
	$("#toolbar").find("input[name]").each(function(i){
		params[this.name] = this.value;
	});
	$("#datagrid").datagrid('load',params);
}

function typeFormatter(value,row,index){
	if(row.type==1) return '桥架';
	if(row.type==2) return '盖板';
	return '3';
}

/**
 * 操作
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value,row,index){
	return '<a href="javascript:void(0)" onclick="modBaseQj(\''+row.id+'\', \''+row.guige+'\', \''+row.type+'\')">修改</a>';
}

function modBaseQj(pId, guige, type){
	var title = (type == 1) ? "桥架" : "盖板"; 
	modelDialog = sy.iframeDialog({
		"href" : "baseqj/modBaseQj?id="+pId,
		"height" : $('body', document).height() * 0.68,
		"width" : $('body', document).width() * 0.4,
		"title" : guige +'【' + title + '】修改',
		"buttons": [
              {
            	  text : '确定',handler : function() {
            		  var _body = modelDialog.find('iframe').get(0).contentWindow;
            		  //alert(_body.$('#guige').val());
            		  _body.$('#ff').form('submit',{
	                    success: function(result) {
	                    	result = JSON.parse(result);
	                    	showMsg(result.msg);
	                    	if(result.success){
	                    		modelDialog.dialog('close');
    	                        $("#datagrid").datagrid('reload');
	                    	}
	                    }
                	});
      			}
              },{
            	  text: '关闭', handler: function() {
            		  modBaseQj.dialog('destroy');
                	  //$("#datagrid").datagrid('reload');
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
		showInfo({msg:'��ѡ��һ����¼��'});
		return;
	}
	showConfirm('ȷ��','ȷ��ɾ��ü�¼��',function(){
		var url = basePath + 'singleTable/jsonDelete?entityClass=HrPositive&id='+row.id;
		$.post(url,function(js){
			showInfo({msg:js.msg});
			if(js.success)
				$("#datagrid").datagrid('deleteRow',$("#datagrid").datagrid('getRowIndex',row));
		},'json');
	});
}

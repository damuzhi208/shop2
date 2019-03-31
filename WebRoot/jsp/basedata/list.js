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
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modBaseQj(\''+row.id+'\', \''+row.guige+'\', \''+row.type+'\')">修改</a>';
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

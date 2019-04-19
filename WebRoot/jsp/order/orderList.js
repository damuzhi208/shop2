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
 * 订单日期
 * @param value
 * @param row
 * @param index
 */
function orderDateFormatter(value, row, index){
	if(value) return value.substring(0, 11);
}

/**
 * 操作formatter
 * @param value
 * @param row
 * @param index
 */
function opFormatter(value, row, index){
	if(row.customerName == '合计' || row.customerName == '') return ;
	return '<a href="javascript:void(0)" class="easyui-editbtn" onclick="modOrder(\''+row.orderId+'\',\''+row.customerName+'\',\''+row.opTime+'\')">修改</a>'
	    +'<a href="javascript:void(0)" class="easyui-viewbtn" onclick="viewRecord(\''+row.customerId+'\')">查看交易记录</a>'
		+'<a href="javascript:void(0)" class="easyui-delbtn" onclick="delBtnClick(\''+row.orderId+'\')">删除</a>';
}

function delBtnClick(id){
	$.messager.confirm("操作提示", "数据删除后无法恢复，确定删除？", function(data) {
		if (data) {
			$.post("order/delete?id="+id , function(js){ 
				$.messager.alert('提示', js.msg);
				if(js.success){
					doSearch();
				}
			},'json');
		}
	});
}

function viewRecord(customerId){
	var flag = $("#flag").val();
	var url = "order/viewRecord?customerId="+customerId+"&flag="+flag;
	modelDialog = sy.iframeDialog({
		"href" : url,
		"height" : '85%',
		"width" : '95%',
		"title" : "查看交易记录",
		"buttons": [
             {
            	  text: '取消', handler: function() {
            		  modelDialog.dialog('destroy');
                	  $("#datagrid").datagrid('reload');
            	  }
              }
        ]
	});
	return false;
}

function modOrder(orderId,customerName,opTime){
	top.$('#tabs').tabs("add", {
        title: '修改【'+customerName+'】订单['+opTime+']',
        closable: true,
        content: "<iframe scrolling='auto' frameborder='0' src='order/orderTabs?orderId="+orderId+"' style='width:100%;height:98%;'></iframe>"
    });
    return false;
}

function addBtnClick(){
	top.$('#tabs').tabs("add", {
        title: '新建订单',
        closable: true,
        content: "<iframe scrolling='auto' frameborder='0' src='order/orderTabs' style='width:100%;height:98%;'></iframe>"
    });
    return false;
}
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>订单管理tabs</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
	<div style="height: 20%;width: 98%;">
		<form id="form" action="order/save">
			<table>
				<tr>
					<td>客户姓名：</td>
					<td>
						<input type="hidden" id="orderId" name="orderId" value="${record.orderId }"/>
						<input class="easyui-combobox" type="text" id="customerId" name="customerId" value="${record.customerId }" panelHeight="100" data-options="valueField:'id',textField:'name',url:'customer/select'"/>
					</td>
					<td>订单日期</td>
					<td>
						<input  class="easyui-datebox" id="orderDate" type="text" name="orderDate" value="${record.orderDate }" required="required">
					</td>
					<td colspan="2" align="right" style="width: 30%;">
						<span class="easyui-searchbtn" onclick="doSave();">保存订单</span>
					</td>
				</tr>
				<tr>
					<td>客户公司：</td>
					<td colspan="3">
						<input type="text" class="easyui-textbox" id="companyName" value="${cus.companyName }" name="companyName" readonly="readonly" style="width: 100%;"/>
					</td>
				</tr>
				<tr>
					<td>客户地址：</td>
					<td>
						<input type="text" class="easyui-textbox" id="address" value="${cus.address }" name="address" readonly="readonly"/>
					</td>
					<td>联系电话：</td>
					<td>
						<input type="text" class="easyui-textbox" id="telephone" value="${cus.telephone }" name="telephone" readonly="readonly"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="tt" class="easyui-tabs" style="width:98%;height:78%;">
        <div title="其他" data-options="closable:false" style="padding:20px;display:none;">
    		<iframe scrolling='auto' frameborder='0' src='order/orderOther?orderId=${record.orderId }' style='width:100%;height:98%;'></iframe>
        </div>
        <div title="喷塑桥架" style="padding:20px;display:none;">
    		<iframe scrolling='auto' frameborder='0' src='order/orderQiaojia?type=1&mType=1&orderId=${record.orderId }' style='width:100%;height:98%;'></iframe>
        </div>
        <div title="喷塑盖板" data-options="closable:false" style="overflow:auto;padding:20px;display:none;">
    		<iframe scrolling='auto' frameborder='0' src='order/orderQiaojia?type=2&mType=1&orderId=${record.orderId }' style='width:100%;height:98%;'></iframe>
        </div>
        <div title="镀锌桥架" data-options="closable:false" style="padding:20px;display:none;">
    		<iframe scrolling='auto' frameborder='0' src='order/orderQiaojia?type=1&mType=2&orderId=${record.orderId }' style='width:100%;height:98%;'></iframe>
        </div>
        <div title="镀锌盖板" data-options="closable:false" style="padding:20px;display:none;">
    		<iframe scrolling='auto' frameborder='0' src='order/orderQiaojia?type=2&mType=2&orderId=${record.orderId }' style='width:100%;height:98%;'></iframe>
        </div>
        <div title="金属软管" data-options="closable:false" style="padding:20px;display:none;">
    		<iframe scrolling='auto' frameborder='0' src='order/orderLine?mType=1&orderId=${record.orderId }' style='width:100%;height:98%;'></iframe>
        </div>
        <div title="线管" data-options="closable:false" style="padding:20px;display:none;">
    		<iframe scrolling='auto' frameborder='0' src='order/orderLine?mType=2&orderId=${record.orderId }' style='width:100%;height:98%;'></iframe>
        </div>
    </div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#customerId").combobox({
			onChange : function(n, o) {
				setCustomer(n);
			}
		});
	});
	function setCustomer(id){
		$.ajax({
			url : "customer/getCustomer?id="+id,
			dataType : "json",
			success : function(data){
				$("#companyName").textbox('setValue',data.companyName);
				$("#address").textbox('setValue',data.address);
				$("#telephone").textbox('setValue',data.telephone);
			}
		});
	}
	function isSaved(){
		var orderId = $("#orderId").val();
		if(orderId) return orderId;
	}
	function doSave(){
		var customerId = $("#customerId").combobox('getValue');
		var orderDate = $("#orderDate").val();
		if(!customerId){
			$.messager.alert('提示', "请先选择客户");
			return false;
		}
		if(!orderDate){
			$.messager.alert('提示', "请填写订单日期");
			return false;
		}
		var url = $("#form").attr("action");
		var data = $("#form").serialize();
		$.ajax({
			url : url,
			data : data,
			dataType : "json",
			success : function(data){
				if(data.success){
					window.location.href = "order/orderTabs?orderId="+data.data;
				}
			}
		});
	}
</script>
</html>

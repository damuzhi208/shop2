<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'modOther.jsp' starting page</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-panel" style="width:96%">
	    <form id="ff" method="post" action="order/updateOther" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>规格</td>
	    			<td colspan="3">
	    				<input type="hidden" name="id" value="${other.id }">
	    				<input type="hidden" name="orderId" value="${other.orderId }">
	    				<input class="easyui-textbox" type="text" id="guige" name="guige" value="${other.guige }" style="width: 100%;" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>单位:</td>
	    			<td>
	    				<input class="easyui-combobox" value="${other.unit }" type="text" id="unit" name="unit" panelHeight="300" style="width:100%;" 
	    					data-options="required:true,valueField:'id',textField:'name',url:'baseUnit/select'"/>
	    			</td>
	    			<td>数量:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${other.orderNums }" type="text" id="orderNums" name="orderNums" 
	    					data-options="required:true,min:0,precision:2,onChange:changeProfit"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>成本价:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${other.costPrice }" type="text" id="danjia" name="costPrice" 
	    					data-options="onChange:changeProfit,required:true,min:0,precision:2"/>
	    			</td>
	    			<td>交易价格:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${other.salePrice }" type="text" id="salePrice" name="salePrice" 
	    					data-options="required:true,min:0,precision:2,onChange:changeProfit"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>利润</td>
	    			<td>
	    				<input class="easyui-numberbox" readonly="readonly" value="${other.profit }" type="text" id="profit" name="profit" 
	    					data-options="min:0,precision:2"/>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
<script type="text/javascript">
	function changeProfit() {
		var orderNums = $("#orderNums").val();
		var danjia = $("#danjia").val();
		var salePrice = $("#salePrice").val();
		if (orderNums && danjia && salePrice) {
			$("#profit").numberbox('setValue', parseFloat((Number(salePrice) - Number(danjia)) * Number(orderNums)).toFixed(2));
		}else{
			$("#profit").numberbox('setValue', parseFloat((Number(salePrice) - Number(danjia)) * Number(orderNums)).toFixed(2));
		}
	}
</script>
</body>
</html>

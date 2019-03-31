<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'modTransfer.jsp' starting page</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-panel" style="width:96%">
	    <form id="ff" method="post" action="transfer/updateTransFer" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>商品名称:</td>
	    			<td colspan="3">
	    				<input type="hidden" name="id" value="${record.id }">
	    				<input type="hidden" value='<fmt:formatDate value="${record.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' name="createTime">
	    				<input class="easyui-textbox" style="width:80%;" value="${record.shopName }" id="shopName" type="text" name="shopName" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>调动类型:</td>
	    			<td>
    				    <input class="easyui-combobox" value="${record.transType }" type="text" name="transType" panelHeight="100" data-options="required:true,valueField:'id',textField:'name',url:'transfer/getTransType'"/>
	    			</td>
	    			<td>客户名称:</td>
	    			<td>
	    				<input class="easyui-combobox" value="${record.customerId }" type="text" name="customerId" panelHeight="100" data-options="required:true,valueField:'id',textField:'name',url:'customer/select'"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>数量:</td>
	    			<td><input class="easyui-numberbox" value="${record.nums }" type="text" id="nums" name="nums" data-options="required:true,min:0,precision:2,onChange:changeProfit"/></td>
	    			<td>单位:</td>
	    			<td>
	    				<input class="easyui-combobox" value="${record.unit }" type="text" name="unit" panelHeight="100" data-options="required:true,valueField:'id',textField:'name',url:'baseUnit/select'"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>成本价:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${record.cost }" type="text" id="cost" name="cost" data-options="required:true,min:0,precision:2,onChange:changeProfit"/>
	    			</td>
	    			<td>交易价格:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${record.salePrice }" type="text" id="salePrice" name="salePrice" data-options="required:true,min:0,precision:2,onChange:changeProfit"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>调动日期:</td>
	    			<td>
	    				<input  class="easyui-datebox" id="transDate" type="text" name="transDate" value="${record.transDate }" required="required">
	    			</td>
	    			<td>利润:</td>
	    			<td>
	    				<input  class="easyui-numberbox" id="profit" type="text" name="profit" value="${record.profit }" readonly="readonly" data-options="min:0,precision:2">
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
<script type="text/javascript">
function changeProfit(){
	var nums = $("#nums").val();
	var cost = $("#cost").val();
	var salePrice = $("#salePrice").val();
	if(nums && cost && salePrice){
		$("#profit").numberbox('setValue', parseFloat((Number(salePrice) - Number(cost)) * Number(nums)).toFixed(2));
	}
}
</script>
</body>
</html>

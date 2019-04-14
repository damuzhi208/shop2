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
<title>My JSP 'modQiaojia.jsp' starting page</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-panel" style="width:96%">
	    <form id="ff" method="post" action="order/saveQj" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>规格:</td>
	    			<td colspan="3">
	    				<input type="hidden" name="id" value="${qj.id }">
	    				<input type="hidden" name="orderId" value="${qj.orderId }">
	    				<input class="easyui-combobox" value="${qj.baseQjId }" type="text" id="baseQjId" name="baseQjId" panelHeight="200" style="width:100%;" data-options="onChange:changeProfit,required:true,valueField:'id',textField:'name',url:'baseqj/getQjSelect?type=${qj.type }&mType=${qj.mType }'"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>成本价:</td>
	    			<td>
	    				<input class="easyui-numberbox" type="text" name="danjia" id="danjia" value="${qj.danjia }" data-options="required:true,min:0,precision:2"/>
	    				<input class="easyui-numberbox" type="hidden" name="houdu" id="houdu" value="${qj.houdu }"/>
	    				<input class="easyui-numberbox" type="hidden" name="xishu" id="xishu" value="${qj.xishu }"/>
	    				<input class="easyui-numberbox" type="hidden" name="dwj" id="dwj" value="${qj.dwj }"/>
	    			</td>
	    			<td>数量:</td>
	    			<td><input class="easyui-numberbox" value="${qj.orderNums }" type="text" id="orderNums" name="orderNums" data-options="required:true,min:0,precision:2,onChange:changeProfit"/></td>
	    		</tr>
	    		<tr>
	    			<td>交易价格:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${qj.salePrice }" type="text" id="salePrice" name="salePrice" data-options="required:true,min:0,precision:2,onChange:changeProfit"/>
	    			</td>
	    			<td>利润</td>
	    			<td>
	    				<input class="easyui-numberbox" readonly="readonly" value="${qj.profit }" type="text" id="profit" name="profit" data-options="required:true,min:0,precision:2"/>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
<script type="text/javascript">
	$('#baseQjId').combobox({
		onChange : function(n, o) {
			setBaseQj(n);
		}
	});
	
	function setBaseQj(id){
		$.ajax({
			url : "baseqj/getBaseQj?id="+id,
			dataType : "json",
			success : function(data){
				$("#danjia").textbox('setValue',data.danjia);
				$("#houdu").textbox('setValue',data.houdu);
				$("#xishu").textbox('setValue',data.xishu);
				$("#dwj").textbox('setValue',data.dwj);
			}
		});
	}
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

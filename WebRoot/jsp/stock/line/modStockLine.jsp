<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	    <form id="ff" method="post" action="stockLine/updateStockLine" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>规格:</td>
	    			<td colspan="3">
	    				<input type="hidden" name="id" value="${line.id }">
	    				<input class="easyui-combobox" value="${line.lineId }" type="text" name="lineId" panelHeight="100" style="width: 100%;"
	    					data-options="required:true,valueField:'id',textField:'name',url:'lineTube/select'"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>入库时间:</td>
	    			<td>
	    				<input class="easyui-datebox" value="${line.transDate }" type="text" name="transDate" data-options="required:true"/>
	    			</td>
	    			<td>入库数量:</td>
	    			<td>
    				    <input class="easyui-numberbox" value="${line.stockNum }" type="text" name="stockNum" panelHeight="100" data-options="required:true"/>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</body>
</html>

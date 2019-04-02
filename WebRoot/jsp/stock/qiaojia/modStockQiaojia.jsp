<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>修改桥架库存</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-panel" style="width:96%">
	    <form id="ff" method="post" action="stock/updateStockQj" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>桥架型号:</td>
	    			<td colspan="3">
	    				<input class="easyui-combobox" type="text" value="${stock.qiaojiaId }" name="qiaojiaId" panelHeight="180" style="width: 100%;"
	    				 data-options="required:true,valueField:'id',textField:'name',url:'baseqj/getQjSelect?mType=${mType }&type=${type }'"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>数量:</td>
	    			<td>
	    				<input type="hidden" name="id" value="${stock.id }">
	    				<input class="easyui-numberbox" value="${stock.stockNum }" id="stockNum" type="text" name="stockNum" data-options="required:true"></input>
	    			</td>
	    			<td>入库时间:</td>
	    			<td>
	    				<input  class="easyui-datebox" id="transDate" type="text" name="transDate" value="${stock.transDate }" required="required">
	    				<input type="hidden" value='<fmt:formatDate value="${stock.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' name="createTime">
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</body>
</html>

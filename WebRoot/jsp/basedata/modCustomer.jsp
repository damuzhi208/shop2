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
<title>修改客户资料</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>

<body>
	<div class="easyui-panel" style="width:96%">
	    <form id="ff" method="post" action="customer/updateCustomer" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>顾客类别:</td>
	    			<td>
	    				<input class="easyui-combobox" value="${customer.cusType }" type="text" name="unit" panelHeight="100"
	    				 data-options="required:true,valueField:'id',textField:'name',url:'customer/cusType'"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>姓名:</td>
	    			<td>
	    				<input type="hidden" name="id" value="${customer.id }">
	    				<input class="easyui-textbox" value="${customer.name }" id="name" type="text" name="name" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>公司名称:</td>
	    			<td><input class="easyui-textbox" value="${customer.companyName }" type="text" name="companyName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>地址:</td>
	    			<td><input class="easyui-textbox" value="${customer.address }" type="text" name="address" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>联系电话:</td>
	    			<td><input class="easyui-textbox" value="${customer.telephone }" type="text" name="telephone" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</body>
</html>

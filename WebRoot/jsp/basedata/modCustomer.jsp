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
<link rel="stylesheet" type="text/css" href="js/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="js/themes/mobile.css">
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.mobile.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jeasyui.extensions.validatebox.js"></script>
<script type="text/javascript" src="js/syUtils.js"></script>

</head>

<body>
	<div class="easyui-panel" style="width:96%">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" action="customer/updateCustomer" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>姓名:</td>
	    			<td>
	    				<input type="hidden" name="id" value="${customer.id }">
	    				<input class="easyui-textbox" value="${customer.name }" id="guige" type="text" name="guige" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>公司名称:</td>
	    			<td><input class="easyui-textbox" value="${customer.companyName }" type="text" name="houdu" data-options="required:true,min:0,precision:2"></input></td>
	    		</tr>
	    		<tr>
	    			<td>地址:</td>
	    			<td><input class="easyui-textbox" value="${customer.address }" type="text" name="xishu" data-options="required:true,min:0,precision:2"></input></td>
	    		</tr>
	    		<tr>
	    			<td>联系电话:</td>
	    			<td><input class="easyui-textbox" value="${customer.telephone }" type="text" name="dwj" data-options="required:true,min:0,precision:2"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
</body>
</html>

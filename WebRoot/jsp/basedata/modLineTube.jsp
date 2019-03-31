<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>修改线管资料</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>

<body>
	<div class="easyui-panel" style="width:96%">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" action="lineTube/update" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>规格:</td>
	    			<td>
	    				<input type="hidden" name="id" value="${line.id }">
	    				<input class="easyui-textbox" value="${line.guige }" id="guige" type="text" name="guige" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>软管/线管:</td>
	    			<td>
	    				<select name="mType" class="easyui-combobox" panelHeight="100" style="width: 172px;">
	    					<option value="1" <c:if test="${line.mType eq 2 }">selected="selected"</c:if>>金属软管</option>
	    					<option value="2" <c:if test="${line.mType eq 2 }">selected="selected"</c:if>>线管</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>单位:</td>
	    			<td>
	    				<input class="easyui-combobox" value="${line.danwei }" type="text" name="danwei" panelHeight="100" data-options="required:true,valueField:'id',textField:'name',url:'baseUnit/select'"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>单价:</td>
	    			<td><input class="easyui-numberbox" value="${line.danjia }" type="text" name="danjia" data-options="required:true,min:0,precision:2"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
</body>
</html>

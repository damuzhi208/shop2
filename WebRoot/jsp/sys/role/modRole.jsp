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
<title>My JSP 'modRole.jsp' starting page</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>

<body>
	<div class="easyui-panel" style="width:96%">
	    <form id="ff" method="post" action="role/updateRole" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>角色名称:</td>
	    			<td>
	    				<input type="hidden" name="id" value="${role.id }">
	    				<input class="easyui-textbox" type="text" name="roleName" value="${role.roleName }" id="roleName" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>状态:</td>
	    			<td>
	    				<input class="easyui-combobox" value="${role.state }" type="text" id="state" name="state" panelHeight="66" style="width:100%;"
	    				 data-options="required:true,valueField:'id',textField:'name',url:'role/select'"/>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</body>
</html>

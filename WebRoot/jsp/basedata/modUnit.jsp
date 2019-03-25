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
<title>修改单位信息</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>

<body>
	<div class="easyui-panel" style="width:96%">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" action="baseUnit/updateUnit" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>单位名称:</td>
	    			<td>
	    				<input type="hidden" name="id" value="${unit.id }">
	    				<input class="easyui-textbox" value="${unit.name }" id="name" type="text" name="name" data-options="required:true"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>状态:</td>
	    			<td>
	                    <label class="radio inline"><input type="radio" name="status" value="1" <c:if test="${unit.status eq 1}">checked='checked'</c:if> ></input>有效</label>
                		<label class="radio inline"><input type="radio" name="status" value="0" <c:if test="${unit.status ne 1}">checked='checked'</c:if> ></input>无效</label>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	</div>
</body>
</html>

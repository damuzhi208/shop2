<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'mldBaseQj.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<jsp:include page="/inc.jsp"></jsp:include>
  </head>
  
  <body>
	<div class="easyui-panel" style="width:96%">
	    <form id="ff" method="post" action="baseqj/updateBaseQj" >
	    	<table cellpadding="5" align="center">
	    		<tr>
	    			<td>喷塑/镀锌:</td>
	    			<td>
	    				<select class="easyui-combobox" name="mType" style="width: 172px;" panelHeight="100">
	    					<option value="1" <c:if test="${qj.mType eq 1}">selected="selected"</c:if>>喷塑桥架</option>
	    					<option value="2" <c:if test="${qj.mType eq 2}">selected="selected"</c:if>>镀锌桥架</option>
	    				</select>
	    			</td>
	    			<td>桥架/盖板:</td>
	    			<td>
	    				<select class="easyui-combobox" name="type" style="width: 172px;" panelHeight="100">
	    					<option value="1" <c:if test="${qj.type eq 1}">selected="selected"</c:if>>桥架</option>
	    					<option value="2" <c:if test="${qj.type eq 2}">selected="selected"</c:if>>盖板</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>规格:</td>
	    			<td colspan="3">
	    				<input type="hidden" name="id" value="${qj.id }">
	    				<input class="easyui-textbox" value="${qj.guige }" id="guige" type="text" name="guige" style="width: 100%;" data-options="required:true"></input>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>厚度:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${qj.houdu }" type="text" name="houdu" data-options="required:true,min:0,precision:2"/>
	    			</td>
	    			<td>系数:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${qj.xishu }" type="text" name="xishu" data-options="required:true,min:0,precision:2"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>吨位价:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${qj.dwj }" type="text" name="dwj" data-options="required:true,min:0,precision:2"/>
	    			</td>
	    			<td>单价:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${qj.danjia }" type="text" name="danjia" data-options="required:true,min:0,precision:2"/>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
  </body>
</html>

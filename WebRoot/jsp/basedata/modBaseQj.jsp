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
	    				<select class="easyui-combobox" id="type" name="type" style="width: 172px;" panelHeight="100">
	    					<option value="1" <c:if test="${qj.type eq 1}">selected="selected"</c:if>>桥架</option>
	    					<option value="2" <c:if test="${qj.type eq 2}">selected="selected"</c:if>>盖板</option>
	    				</select>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>规格(宽*高):</td>
	    			<td colspan="3">
	    				<input type="hidden" name="id" value="${qj.id }">
	    				<input class="easyui-numberbox" id="widths" type="text" style="width: 80px" value="${qj.widths }" name="widths" 
	    					data-options="required:true,min:0,precision:2,onChange:calculateDanjia"/>*
	    				<input class="easyui-numberbox" id="heights" type="text" style="width: 80px" value="${qj.heights }" name="heights" 
	    					data-options="required:true,min:0,precision:2,onChange:calculateDanjia"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>厚度:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${qj.houdu }" type="text" id="houdu" name="houdu" data-options="required:true,min:0,precision:2,onChange:calculateDanjia"/>
	    			</td>
	    			<td>系数:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${qj.xishu }" type="text" id="xishu" name="xishu" data-options="required:true,min:0,precision:2,onChange:calculateDanjia"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>吨位价:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${qj.dwj }" type="text" id="dwj" name="dwj" data-options="required:true,min:0,precision:2,onChange:calculateDanjia"/>
	    			</td>
	    			<td>单价:</td>
	    			<td>
	    				<input class="easyui-numberbox" value="${qj.danjia }" type="text" id="danjia" name="danjia" readonly="readonly" data-options="min:0,precision:2"/>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
  </body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#type").combobox({
			onChange : function(n, o) {
				calculateDanjia();
			}
		});
	});
	function calculateDanjia() {
		var type = $("#type").val();
		var widths = $("#widths").numberbox('getValue');
		var heights = $("#heights").numberbox('getValue');
		var houdu = $("#houdu").numberbox('getValue');
		var xishu = $("#xishu").numberbox('getValue');
		var dwj = $("#dwj").numberbox('getValue');
		var danjia = 0;
		if (type == 1) {//桥架
			if (widths && heights && houdu && xishu && dwj) {
				danjia = (Number(widths) + 2 * Number(heights) + Number(20)) / Number(1000) * Number(houdu) * Number(xishu) * Number(dwj);
			}
		} else if (type == 2) {//盖板
			if (widths && houdu && xishu && dwj) {
				danjia = (Number(widths) + Number(20)) / Number(1000) * Number(houdu) * Number(xishu) * Number(dwj);
			}
		}
		$("#danjia").numberbox('setValue', danjia);
	}
</script>
</html>

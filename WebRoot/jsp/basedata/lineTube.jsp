<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<title>桥架基础数据</title>
<meta charset="utf-8" />
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
  <div class="easyui-layout" fit="true" style="border:none;">
		<div id="toolbar" style="height: 44px;padding:7px 2px">
			规格：<input type="text" name="guige" />
			软管/线管：<select name="mType" class="easyui-combobox" panelHeight="100" style="width: 172px;">
						<option value="">全部</option>
						<option value="1">软管</option>
						<option value="2">线管</option>
					  </select>
			<span class="easyui-searchbtn" onclick="doSearch();">搜索</span>
			<span class="easyui-addbtn" onclick="addBtnClick();">新增</span>
		</div>  		
		<div data-options="region:'center',border:false" style=" border:none">
			<table id="datagrid" class="easyui-datagrid"  fit="true" 
				data-options="
						rownumbers:true,
						pagination:true,
						toolbar:'#toolbar',
						pageSize:15,
						nowrap:false,
						singleSelect:true,
						pageList:[10,15,20,25,30,35,50,100],
						url:'<%=basePath %>lineTube/listData'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'1',align:'center',width:160,formatter:mTypeFormatter">金属软管/线管</th>
		    			<th data-options="field:'guige',align:'center',width:160">规格</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'danweiStr',align:'center',width:100">单位</th>
		    			<th data-options="field:'danjia',align:'center',width:100,formatter:easyuiMoneyFormatter">单价</th>
		    			<th data-options="field:'2',align:'center',width:100,formatter:opFormatter">操作</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/basedata/lineTube.js"></script>
  </body>
</html>

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
			桥架类型：<select name="mType" class="easyui-combobox" panelHeight="100" style="width: 172px;">
						<option value="">全部</option>
						<option value="1">喷塑桥架</option>
						<option value="2">镀锌桥架</option>
					</select>
			桥架/盖板：<select name="type" class="easyui-combobox" panelHeight="100" style="width: 172px;">
						<option value="">全部</option>
						<option value="1">桥架</option>
						<option value="2">盖板</option>
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
						url:'<%=basePath %>baseqj/listData'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'0',align:'center',width:100,formatter:mTypeFormatter">喷塑/镀锌</th>
		    			<th data-options="field:'1',align:'center',width:100,formatter:typeFormatter">桥架/盖板</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'3',align:'left',width:200,formatter:guigeFormatter">规格</th>
		    			<th data-options="field:'widths',align:'center',width:100">宽度</th>
		    			<th data-options="field:'heights',align:'center',width:100">高度</th>
		    			<th data-options="field:'houdu',align:'center',width:100,formatter:easyuiMoneyFormatter">厚度</th>
		    			<th data-options="field:'xishu',align:'center',width:100,formatter:easyuiMoneyFormatter">系数</th>
		    			<th data-options="field:'dwj',align:'center',width:100,formatter:easyuiMoneyFormatter">吨位价</th>
		    			<th data-options="field:'danjia',align:'center',width:100,formatter:easyuiMoneyFormatter">单价</th>
		    			<th data-options="field:'2',align:'center',width:100,formatter:opFormatter">操作</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/basedata/list.js"></script>
  </body>
</html>

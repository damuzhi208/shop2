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
<title>订单管理</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" fit="true" style="border:none;">
		<div id="toolbar" style="height: 44px;padding:7px 2px">
			角色名称：<input type="text" name="roleName"/>
			<span class="easyui-searchbtn" onclick="doSearch();">搜索</span>
			<span class="easyui-addbtn" onclick="addBtnRole()">新增</span>
		</div>  		
		<div data-options="region:'center',border:false" style=" border:none">
			<table id="datagrid" class="easyui-datagrid"  fit="true" 
				data-options="
						rownumbers:true,
						pagination:true,
						showFooter:true,
						toolbar:'#toolbar',
						pageSize:15,
						nowrap:false,
						singleSelect:true,
						pageList:[10,15,20,25,30,35,50,100],
						url:'<%=basePath %>role/listData'"
				>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'roleName',align:'center',width:120">角色名称</th>
		    			<th data-options="field:'state',align:'center',width:120,formatter:stateFormatter">状态</th>
		    			<th data-options="field:'2',align:'center',width:120,formatter:opFormatter">操作</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/sys/role/listRole.js"></script>
  </body>
</html>

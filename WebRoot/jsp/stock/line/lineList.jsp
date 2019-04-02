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
<title>软管库存</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>

<body>
	<div class="easyui-layout" fit="true" style="border:none;">
		<div id="toolbar" style="height: 44px;padding:7px 2px">
			规格：<input type="text" name="guige" />
			<!-- 软管/线管：
			<select class="easyui-combobox" panelHeight="100" style="width: 172px;" name="mType">
				<option value="">全部</option>
				<option value="1">软管</option>
				<option value="2">线管</option>
			</select> -->
			<input type="hidden" name="mType" value="${mType }"/>
			<span class="easyui-searchbtn" onclick="doSearch();">搜索</span>
			<span class="easyui-addbtn" onclick="addBtnClick();">新增</span>
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
						url:'<%=basePath %>stockLine/lineData?mType=${mType }'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'mType',align:'center',width:100,formatter:lxFormatter">规格</th>
		    			<th data-options="field:'guige',align:'left',width:380,formatter:guigeFormatter">规格</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'stockNum',align:'center',width:100">入库数量</th>
		    			<th data-options="field:'transDate',align:'center',width:100,formatter:YMDDateFormatter">入库日期</th>
		    			<th data-options="field:'createTime',align:'center',width:160">操作时间</th>
		    			<th data-options="field:'2',align:'center',width:100,formatter:opFormatter">操作</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/stock/line/lineList.js"></script>
  </body>
</html>

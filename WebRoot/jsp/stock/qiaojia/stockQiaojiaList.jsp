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
<title>客户关系基础数据</title>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="js/themes/metro/easyui.css">  
<link rel="stylesheet" type="text/css" href="js/themes/mobile.css">  
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">  
<script type="text/javascript" src="js/jquery.min.js"></script>  
<script type="text/javascript" src="js/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="js/jquery.easyui.mobile.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/syUtils.js"></script> 
</head>
<body>
  <div class="easyui-layout" fit="true" style="border:none;">
		<div id="toolbar" style="height: 30px;">
			喷塑/镀锌：<select name="type">
						<option value="">==全部==</option>
						<option value="1">喷塑</option>
						<option value="2">镀锌</option>
					  </select>
			桥架/盖板：<select name="mType">
						<option value="">==全部==</option>
						<option value="1">桥架</option>
						<option value="2">盖板</option>
					  </select>
			<span class="btn pull-right" onclick="addBtnClick();">新增</span>
			<span class="btn pull-right" onclick="doSearch();">搜索</span>
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
						url:'<%=basePath %>stock/qiaojiaData'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'1',align:'center',width:400,formatter:guigeFormatter">规格</th>
		    			<th data-options="field:'stockNum',align:'center',width:100">入库数量</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'transDate',align:'center',width:120,formatter:YMDDateFormatter">入库库日期</th>
		    			<th data-options="field:'createTime',align:'center',width:160">操作时间</th>
		    			<th data-options="field:'2',align:'center',width:100,formatter:opFormatter">操作</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/stock/qiaojia/stockQiaojiaList.js"></script>
  </body>
</html>

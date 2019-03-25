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
<title>调动记录</title>
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
			客户：<input type="text" name="customerName" />
			调动类型：<select name="mType">
						<option value="">==全部==</option>
						<option value="1">调入</option>
						<option value="2">调出</option>
					  </select>
			<span class="btn pull-right" onclick="addBtnClick();">新增</span>
			<span class="btn pull-right" onclick="doSearch();">搜索</span>
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
						url:'<%=basePath %>transfer/listData'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'customerName',align:'center',width:100">客户姓名</th>
		    			<th data-options="field:'companyName',align:'center',width:180">客户公司</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'transType',align:'center',width:100,formatter:transTypeFormatter">调动类型</th>
		    			<th data-options="field:'shopName',align:'center',width:120">商品名称</th>
		    			<th data-options="field:'nums',align:'center',width:100">调动数量</th>
		    			<th data-options="field:'unit',align:'center',width:80">单位</th>
		    			<th data-options="field:'cost',align:'center',width:100">成本价格</th>
		    			<th data-options="field:'salePrice',align:'center',width:100">成交价格</th>
		    			<th data-options="field:'1',align:'center',width:80,formatter:profitFormatter">利润</th>
		    			<th data-options="field:'transDate',align:'center',width:120,formatter:YMDDateFormatter">调动日期</th>
		    			<th data-options="field:'createTime',align:'center',width:160">操作时间</th>
		    			<th data-options="field:'2',align:'center',width:100,formatter:opFormatter">操作</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/transfer/listRecord.js"></script>
  </body>
</html>

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
<title>桥架库存</title>
<meta charset="utf-8" />
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
  <div class="easyui-layout" fit="true" style="border:none;">
		<div id="toolbar" style="height: 44px;padding:7px 2px">
			规格：<input type="text" name="guige"/>
			单位：<input type="text" class="easyui-combobox"	 name="danweis" panelHeight="300" data-options="valueField:'id',textField:'name',url:'baseUnit/select'"/>
			<input type="hidden" id="type" name="type" value="${type }"/>
			<input type="hidden" id="mType" name="mType" value="${mType }"/>
			<span class="easyui-searchbtn" onclick="doSearch();">搜索</span>
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
						url:'<%=basePath %>kucun/lineData?mType=${mType}'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'0',align:'left',width:100,formatter:lxFormatter">类型</th>
		    			<th data-options="field:'1',align:'left',width:300,formatter:guigeFormatter">规格</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'danweis',align:'center',width:100">单位</th>
		    			<th data-options="field:'danjia',align:'center',width:100,formatter:easyuiMoneyFormatter">单价</th>
		    			<th data-options="field:'total',align:'center',width:100,formatter:easyuiMoneyFormatter">总数</th>
		    			<th data-options="field:'sales',align:'center',width:100,formatter:easyuiMoneyFormatter">交易数量</th>
		    			<th data-options="field:'leaves',align:'center',width:100,formatter:easyuiMoneyFormatter">库存</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/kucun/line.js"></script>
  </body>
</html>

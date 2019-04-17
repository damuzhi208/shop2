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
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
  <div class="easyui-layout" fit="true" style="border:none;">
		<div id="toolbar" style="height: 44px;padding:7px 2px">
			客户：<input type="text" name="customerName" />
			电话号码：<input type="text" name="telephone" />
			调动类型：
			<select class="easyui-combobox" panelHeight="100" style="width: 172px;" name="transType">
				<option value="">全部</option>
				<option value="1">调入</option>
				<option value="2">调出</option>
			</select>
<!-- 			<input class="easyui-combobox" type="text" name="transType" panelHeight="100" data-options="valueField:'id',textField:'name',url:'transfer/getTransType'"/> -->
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
		    			<th data-options="field:'telephone',align:'left',width:120">电话号码</th>
		    			<th data-options="field:'shopName',align:'left',width:260">商品名称</th>
		    			<th data-options="field:'nums',align:'center',width:100">调动数量</th>
		    			<th data-options="field:'unitStr',align:'center',width:80">单位</th>
		    			<th data-options="field:'cost',align:'center',width:100,formatter:costFormatter">成本价格</th>
		    			<th data-options="field:'salePrice',align:'center',width:100,formatter:salePriceFormatter">成交价格</th>
		    			<th data-options="field:'liushui',align:'center',width:100">流水</th>
		    			<th data-options="field:'profit',align:'center',width:160">利润<br>(成交价-成本价)*数量</th>
		    			<th data-options="field:'transType',align:'center',width:100,formatter:transTypeFormatter">调动类型</th>
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>订单记录</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" fit="true" style="border:none;">
		<div id="toolbar" style="height: 44px;padding:7px 2px">
			<input type="hidden" name="customerId" value="${customerId }"/>
			名称：<input  class="easyui-textbox" id="shopName" type="text" name="shopName" />
			订单开始日期：<input  class="easyui-datebox" id="orderDate" type="text" name="orderDate" />
			结束日期：<input  class="easyui-datebox" id="endDate" type="text" name="endDate" />
			<span class="easyui-searchbtn" onclick="doSearch();">搜索</span>
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
						url:'<%=basePath %>order/recordData?customerId=${customerId }'"
				>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'1',align:'center',width:100,formatter:shopNameFormatter">类别</th>
		    			<th data-options="field:'shopName',align:'left',width:360">名称</th>
		    			<th data-options="field:'orderNums',align:'center',width:100,formatter:easyuiMoneyFormatter">数量</th>
		    			<c:if test="${flag }">
			    			<th data-options="field:'costPrice',align:'center',width:100,formatter:easyuiMoneyFormatter">成本单价</th>
		    			</c:if>
		    			<th data-options="field:'salePrice',align:'center',width:100,formatter:easyuiMoneyFormatter">交易价格</th>
		    			<th data-options="field:'liushui',align:'center',width:100,formatter:easyuiMoneyFormatter">流水</th>
		    			<c:if test="${flag }">
			    			<th data-options="field:'profit',align:'center',width:100,formatter:easyuiMoneyFormatter">利润</th>
		    			</c:if>
		    			<th data-options="field:'orderDate',align:'center',width:100">订单时间</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/order/viewRecord.js"></script>
  </body>
</html>

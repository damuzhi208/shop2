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
<title>订单管理</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-layout" fit="true" style="border:none;">
		<div id="toolbar" style="height: 44px;padding:7px 2px">
			客户：<input type="text" name="customerName" />
			电话号码：<input type="text" name="telephone" />
			订单开始日期：<input  class="easyui-datebox" id="beginDate" type="text" name="beginDate" />
			结束日期：<input  class="easyui-datebox" id="endDate" type="text" name="endDate" />
			<input type="hidden" name="flag" id="flag" value="${flag }">
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
						url:'<%=basePath %>order/listData'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'customerName',align:'center',width:100">客户姓名</th>
		    			<th data-options="field:'companyName',align:'center',width:180">客户公司</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'telephone',align:'center',width:120">电话号码</th>
		    			<th data-options="field:'liushui',align:'center',width:160,formatter:easyuiMoneyFormatter">流水额</th>
		    			<c:if test="${flag }">
			    			<th data-options="field:'profit',align:'center',width:160,formatter:easyuiMoneyFormatter">订单利润</th>
		    			</c:if>
		    			<th data-options="field:'orderDate',align:'center',width:160,formatter:orderDateFormatter">订单时间</th>
		    			<th data-options="field:'opTime',align:'center',width:160">操作时间</th>
		    			<th data-options="field:'2',align:'center',width:260,formatter:opFormatter">操作</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/order/orderList.js"></script>
  </body>
</html>

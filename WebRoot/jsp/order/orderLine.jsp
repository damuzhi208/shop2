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
			<input name="mType" value="${line.mType }" id="mType" type="hidden">
			<span class="easyui-addbtn" onclick="addBtnQj('${line.mType }','');">新增</span>
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
						url:'<%=basePath %>order/lineData?orderId=${line.orderId }&mType=${line.mType }'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'guige',align:'left',width:360,formatter:guigeFormatter">线管规格</th>
		    			<th data-options="field:'danjia',align:'center',width:100,formatter:easyuiMoneyFormatter1">成本价</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'salePrice',align:'center',width:100,formatter:easyuiMoneyFormatter1">成交单价</th>
		    			<th data-options="field:'orderNums',align:'center',width:100,formatter:easyuiMoneyFormatter1">订单数量</th>
		    			<th data-options="field:'liushui',align:'center',width:100,formatter:easyuiMoneyFormatter">流水金额</th>
		    			<th data-options="field:'profit',align:'center',width:100,formatter:easyuiMoneyFormatter">利润</th>
		    			<th data-options="field:'2',align:'center',width:100,formatter:opFormatter">操作</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/order/orderLine.js"></script>
  </body>
</html>

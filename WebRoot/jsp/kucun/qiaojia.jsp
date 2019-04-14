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
			规格(宽或高)：<input type="text" name="guige"/>
			系数：<input type="text" name="xishu"/>
			吨位价：<input type="text" name="dwj"/>
			单价：<input type="text" name="danjia"/>
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
						url:'<%=basePath %>kucun/qjData?type=${type}&mType=${mType}'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'0',align:'left',width:100,formatter:lxFormatter">类型</th>
		    			<th data-options="field:'1',align:'left',width:600,formatter:guigeFormatter">规格</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'total',align:'center',width:100,formatter:easyuiMoneyFormatter">总数</th>
		    			<th data-options="field:'saleNums',align:'center',width:100,formatter:easyuiMoneyFormatter">交易数量</th>
		    			<th data-options="field:'leaveNums',align:'center',width:100,formatter:easyuiMoneyFormatter">库存</th>
		    		</tr>
		    	</thead>
			</table>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath %>jsp/kucun/qiaojia.js"></script>
  </body>
</html>

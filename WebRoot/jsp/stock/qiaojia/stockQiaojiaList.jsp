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
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
  <div class="easyui-layout" fit="true" style="border:none;">
		<div id="toolbar" style="height: 44px;padding:7px 2px">
			<!-- 喷塑/镀锌：<select name="type" class="easyui-combobox" panelHeight="100" style="width: 172px;">
						<option value="">全部</option>
						<option value="1">喷塑</option>
						<option value="2">镀锌</option>
					</select>
			桥架/盖板：<select name="mType" class="easyui-combobox" panelHeight="100" style="width: 172px;">
						<option value="">全部</option>
						<option value="1">桥架</option>
						<option value="2">盖板</option>
					</select> -->
			规格：<input type="text" name="widths"/>
			系数：<input type="text" name="xishu"/>
			吨位价：<input type="text" name="dwj"/>
			<input type="hidden" id="type" name="type" value="${type }"/>
			<input type="hidden" id="mType" name="mType" value="${mType }"/>
			<span class="easyui-searchbtn" onclick="doSearch();">搜索</span>
			<span class="easyui-addbtn" onclick="addBtnClick();">新增</span>
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
						url:'<%=basePath %>stock/qiaojiaData?type=${type}&mType=${mType}'"
				>
				<thead data-options="frozen:true">
		    		<tr>
		    			<th data-options="field:'0',align:'left',width:100,formatter:lxFormatter">类型</th>
		    			<th data-options="field:'1',align:'left',width:600,formatter:guigeFormatter">规格</th>
		    		</tr>
		    	</thead>
		    	<thead>
		    		<tr>
		    			<th data-options="field:'stockNum',align:'center',width:100">入库数量</th>
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

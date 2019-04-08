<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<link rel="stylesheet" type="text/css" href="js/themes/metro/easyui.css">  
<link rel="stylesheet" type="text/css" href="js/themes/mobile.css">  
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">  
<script type="text/javascript" src="js/jquery.min.js"></script>  
<script type="text/javascript" src="js/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="js/jquery.easyui.mobile.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/syUtils.js"></script> 
<script type="text/javascript">
	$(function(){
		$('.easyui-combobox').combobox({
			required:true,
			multiple:false,
			editable:false 
		});
	});
	function easyuiMoneyFormatter(value, row, index){
		if(value){
			return value.toFixed(2);
		}
		return '0.00';
	}
</script>


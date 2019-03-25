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

<title>系统登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" rel="external nofollow" >  
 <link rel="stylesheet" type="text/css" href="js/themes/default/panel.css" rel="external nofollow" >  
 <link rel="stylesheet" type="text/css" href="js/themes/icon.css" rel="external nofollow" > 
 <script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
 <script type="text/javascript" src="js/jeasyui.extensions.validatebox.js"></script>
<script type="text/javascript">
$('#p').panel('move',{
    left:100,
    top:100
}); 
</script>
<style type="text/css">
*{margin:0;padding:0;}
a{
  text-decoration:none;
}
body{
  margin:0px;
}
.header{
  height:50px;  
  position:absolute;
  left:480px;
  top:220px;
}
.header .logo{
	float:left;	
}
.header logo img{
	 width:50px;
	 height:50px;
}
.header h1{	
  	font-size: 24px;
  	line-height:50px;
  	color:#fff;
}
#picture{
  width:100%;
  height:90%;
}
.panel-title {
 text-align: center;
 font-size: 16px;
}
#bootom{
  width:100%;
  height:10%;
  background: #eaf2ff;
  text-align: center;
  font-size:0.8em; 
  overflow:hidden;
}
p{
  line-height:20px; 
}
</style>
</head>

<body>	
	<div id="picture" style="background:url(images/timg.jpg) no-repeat; background-size: cover;">
		<div class="header">
			<div class="logo"></div>
		    <h1>信息系统</h1>				
		</div>
		<div data-options=" region:'east',split:true,style:{position:'absolute',right:280,top:180}" class="easyui-panel " title="用户登录" style="width:300px;text-align: center;">
			<div style="padding:10px 60px 20px 60px">
        	<form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
          		<table cellpadding="5">
            		<tr>
              			<td><input class="easyui-textbox" value="admin" name="account" data-options="required:true,prompt:'账号'" iconCls="icon-man" iconAlign=left style="width:100%;height:32px"/></td>
		            </tr>
		            <tr>
              			<td><input class="easyui-textbox" value="123456" name="passWord" data-options="required:true,prompt:'密码',validType:'password'" iconCls="icon-lock" iconAlign=left style="width:100%;height:32px"></input></td>
            		</tr>
          		</table>
        	</form>
			<div style="text-align:center;padding:5px; ">
				<a href="javascript:void(0)" id="logBtn" rel="external nofollow" rel="external nofollow" class="easyui-linkbutton" style="width:45%;height:32px">登录</a>    
        	</div>
			</div>
		</div>
	</div>
	<div id="bootom">   
		<p><strong>关于我们      法律声明      服务条款     联系我们</strong></p>
		<p>地址：江西省南昌市经济开发区天祥大道    邮箱：330000  Copyright © 2017 - 2018    hacker_pangdaxing@qq.com 版权所有</p>
		<p>建议使用IE8以上版本浏览器    E-mail：hacker_pandaxing@qq.com</p>    
	</div>
</body>
<script>
	$("#logBtn").click(function(){
		var data = $("#ff").serialize();
		$.post("login", data,
			function(data,status){
				if(data.success){
					window.location.href = "index";
				}else{
					$.messager.alert('提示',data.msg,'info',function () {});
					//$.messager.alert('提示',data.msg);
				}
			},"json"
		);
	});
	function submitForm() {
		alert("123");
		$('#ff').form('submit', {
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			}
		});
	}
	function clearForm() {
		$('#ff').form('clear');
	}
</script>
</html>

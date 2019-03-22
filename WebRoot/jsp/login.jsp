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
 <link rel="stylesheet" type="text/css" href="css/index.css" rel="external nofollow" > 
 <script type="text/javascript" src="js/jquery.min.js"></script>
 <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
console.info("==================")
  $('#p').panel('move',{
    left:100,
    top:100
  }); 
</script>
<style type="text/css">
a{
  text-decoration:none;
}
body{
  margin:0px;
}
.header{
  width:100%;
  height:30px;
  background-color:#E0EFFF;
  padding-bottom: 10px;
  position: relative;
}
.header .logo{
  margin-left: 50px;
  font-size: 24px;
  font-family: 微软雅黑;position: absolute;margin:auto; top: 0;left: 0;right: 0;bottom: 0;
}
#picture{
  width:100%;
  height:584px;
}
.panel-title {
 text-align: center;
 font-size: 16px;
}
#bootom{
  width:100%;
  height:100px;
  background: #eaf2ff;
}
#bootom_content{
  margin-left:100px;
  width:80%;
  text-align: center;
  font-size:0.8em; 
}
p{
  line-height:20px; 
}
</style>
</head>

<body>
	<div class="header" >
	    <div class="logo" onclick="javascript:void(0)">
			<strong>信息系统</strong>
	    </div>
	</div>
  <div id="picture" style="background:url(images/timg.jpg) no-repeat; background-size: cover;">
  <div data-options=" region:'east',split:true,style:{position:'absolute',right:280,top:180}" class="easyui-panel " title="用户登录" style="width:300px;text-align: center;">
      <div style="padding:10px 60px 20px 60px">
        <form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
          <table cellpadding="5">
            <tr>
              <td><input class="easyui-textbox" data-options="prompt:'账号',validType:'name'" iconCls="icon-man" iconAlign=left style="width:100%;height:32px"/></td>
            </tr>
            <tr>
              <td><input class="easyui-textbox" data-options="prompt:'密码',validType:'password'" iconCls="icon-lock" iconAlign=left style="width:100%;height:32px"></input></td>
            </tr>
          </table>
        </form>
        <div style="text-align:center;padding:5px; ">
          <a href="#" rel="external nofollow" rel="external nofollow" class="easyui-linkbutton" style="width:45%;height:32px">登录</a>    
        </div>
      </div>
    </div>
  </div>
  <div id="bootom">
    <div id="bootom_content">
      <p><strong>关于我们      法律声明      服务条款     联系我们</strong></p>
      <p>地址：江西省南昌市经济开发区天祥大道    邮箱：330000  Copyright © 2017 - 2018    hacker_pangdaxing@qq.com 版权所有</p>
      <p>建议使用IE8以上版本浏览器    E-mail：hacker_pandaxing@qq.com</p>
    </div>
  </div>
</body>
<script>
	function submitForm() {
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

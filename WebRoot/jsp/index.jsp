<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.company.hxs.common.sys.SysConstant" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>系统首页</title>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.1.2.2.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
<%if (session.getAttribute(SysConstant.SESSION_USER) == null) {%>
function toLoginPage(obj){
	if(obj.frameElement!=null){
		toLoginPage(obj.parent);
	}else if(obj.opener!=null){
		window.close();
		toLoginPage(window.opener);
	}else {
		obj.location.href='/login';
	}
}
toLoginPage(window);
<%}%>
</script>
<script type="text/javascript">
	var _menus = { "menus" : JSON.parse('${menus}') };
	/*var _menus = null;
	$.ajaxSettings.async = false;
	$.get("jsp/menus.json", function(result) {
		_menus = { "menus" : result };
		console.log(_menus);
	});
	$.ajaxSettings.async = true;*/
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	//关闭登录窗口
	function closePwd() {
		$('#w').window('close');
	}

	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');
		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}

		$.post('/updatePass?passWord=' + $newpass.val()+'&id=${currentUser.id }', function(msg) {
			msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg.data, 'info');
			//$newpass.val('');
			//$rePass.val('');
			if(msg.success){
				$('#w').window('close');
			}
		},'json');

	}

	$(function() {
		openPwd();
		$('#editpass').click(function() {
			$('#w').window('open');
		});
		$('#btnEp').click(function() {
			serverLogin();
		});
		$('#btnCancel').click(function() {
			closePwd();
		});
		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
				if (r) {
					location.href = '/login';
				}
			});
		})
	});
</script>
<script type="text/javascript" src='js/outlook2.js'></script>
</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false" style="overflow: hidden; background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float:right; padding-right:20px;padding-top:12px" class="head">欢迎 ${currentUser.userName } <a href="javascript:void(0)" id="editpass">修改密码</a> <a href="javascript:void(0)" id="loginOut">安全退出</a>
		</span> <span style="padding-left:10px; font-size: 16px; ">
		<img src="images/blocks.gif" width="20" height="50" align="absmiddle" /> jQuery.EasyUI- 1.2.2 应用实例</span>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2; ">
		<div class="footer">By 猪肉炖粉条(QQ:735173617) jQuery.Easy-UI</div>
	</div>
	<div region="west" hide="true" split="true" title="导航菜单"
		style="width:180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->
		</div>
	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; ">
				<h1 style="font-size:24px;">* 作者：猪肉炖粉条 QQ：735173617</h1>
				<h1 style="font-size:24px;">
					* BLOG: <a style="font-size:24px;color:green;" href="javascript:void(0)">猪肉炖粉条的博客</a>
				</h1>
				<!-- <h1 style="font-size:24px;">*
					讨论群：112044258、32994605、36534121、56271061</h1>
				<h1 style="font-size:24px;">*
					广告：本人承接各类大中小型管理系统的软件的设计与开发，有需要的朋友联系我啦~~~~</h1> -->
			</div>
		</div>
	</div>

	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px;  background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)"> 确定</a> 
				<a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
</html>

/**
 * 包含easyui的扩展和常用的方法
 */

var sy = $.extend({}, sy);/* 全局对象 */
var returnAllData=false;
 // 全局变量为了调节listForSelect.jsp页面表格最后一列宽度问题
/*
$.parser.auto = false;
$(function() {
	$.messager.progress({
		text : '页面加载中,请稍后....',
		interval : 100
	});
	$.parser.parse(window.document);
	window.setTimeout(function() {
		$.messager.progress('close');
		if (self != parent) {
			window.setTimeout(function() {
				
				$.messager.progress('close');
			}, 500);
		}
	}, 1);
	$.parser.auto = true;
});
*/
$.fn.panel.defaults.onBeforeDestroy = function() {/* tab关闭时回收内存 */
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			frame[0].contentWindow.document.write('');
			frame[0].contentWindow.close();
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		} else {
			$(this).find('.combo-f').each(function() {
				var panel = $(this).data().combo.panel;
				panel.panel('destroy');
			});
		}
	} catch (e) {
	}
};

$.fn.panel.defaults.loadingMessage = '数据加载中，请稍候....';
$.fn.datagrid.defaults.loadMsg = '数据加载中，请稍候....';

var easyuiErrorFunction = function(XMLHttpRequest) {
	/* $.messager.progress('close'); */
	/* alert(XMLHttpRequest.responseText.split('<script')[0]);*/ 
	//$.messager.alert('错误', XMLHttpRequest.responseText.split('<script')[0]);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

var easyuiPanelOnMove = function(left, top) {/* 防止超出浏览器边界 */
	if (left < 0) {
		$(this).window('move', {
			left : 1
		});
	}
	if (top < 0) {
		$(this).window('move', {
			top : 1
		});
	}
};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;

$.extend($.fn.validatebox.defaults.rules, {
	eqPassword : {/* 扩展验证两次密码 */
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

$.extend($.fn.datagrid.defaults.editors, {
	combocheckboxtree : {
		init : function(container, options) {
			var editor = $('<input/>').appendTo(container);
			options.multiple = true;
			editor.combotree(options);
			return editor;
		},
		destroy : function(target) {
			$(target).combotree('destroy');
		},
		getValue : function(target) {
			return $(target).combotree('getValues').join(',');
		},
		setValue : function(target, value) {
			$(target).combotree('setValues', sy.getList(value));
		},
		resize : function(target, width) {
			$(target).combotree('resize', width);
		}
	}
});

/**
 * 获得项目根路径
 * 
 * 使用方法：sy.bp();
 */
sy.bp = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};

/**
 * 增加formatString功能
 * 
 * 使用方法：sy.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 */
sy.fs = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

sy.formatString = sy.fs;

/**
 * 增加命名空间功能
 * 
 * 使用方法：sy.ns('jQuery.bbb.ccc','jQuery.eee.fff');
 */
sy.ns = function() {
	var o = {}, d;
	for ( var i = 0; i < arguments.length; i++) {
		d = arguments[i].split(".");
		o = window[d[0]] = window[d[0]] || {};
		for ( var k = 0; k < d.slice(1).length; k++) {
			o = o[d[k + 1]] = o[d[k + 1]] || {};
		}
	}
	return o;
};


/**
 * 创建一个模式化的dialog，实现全屏遮罩
 * 
 * @requires jQuery,EasyUI
 * 
 */
sy.modalDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 480,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	
	return $('<div/>').dialog(opts);
};

/**
 * 创建一个模式化的dialog，实现全屏遮罩(iframe方式)
 * 
 * @requires jQuery,EasyUI
 * 
 */
sy.iframeDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 480,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	var href=opts.href;
	opts.href="";
	var dialog=$('<div style="overflow:hidden;" ><iframe style="width:100%;height:100%;border: 0;overflow: hidden;" ></iframe></div>').dialog(opts);
	dialog.find("iframe").attr("src",href);
	return dialog;
};

/**
 * 生成UUID
 */
sy.random4 = function() {
	return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
};
sy.UUID = function() {
	return (sy.random4() + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + sy.random4() + sy.random4());
};

/**
 * 获得URL参数
 */
sy.getUrlParam = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};

sy.getList = function(value) {
	if (value) {
		var values = [];
		var t = value.split(',');
		for ( var i = 0; i < t.length; i++) {
			values.push('' + t[i]);/* 避免他将ID当成数字 */
		}
		return values;
	} else {
		return [];
	}
};

sy.png = function() {
	var imgArr = document.getElementsByTagName("IMG");
	for ( var i = 0; i < imgArr.length; i++) {
		if (imgArr[i].src.toLowerCase().lastIndexOf(".png") != -1) {
			imgArr[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + imgArr[i].src + "', sizingMethod='auto')";
			imgArr[i].src = "images/blank.gif";
		}
		if (imgArr[i].currentStyle.backgroundImage.lastIndexOf(".png") != -1) {
			var img = imgArr[i].currentStyle.backgroundImage.substring(5, imgArr[i].currentStyle.backgroundImage.length - 2);
			imgArr[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img + "', sizingMethod='crop')";
			imgArr[i].style.backgroundImage = "url('images/blank.gif')";
		}
	}
};
sy.bgPng = function(bgElements) {
	for ( var i = 0; i < bgElements.length; i++) {
		if (bgElements[i].currentStyle.backgroundImage.lastIndexOf(".png") != -1) {
			var img = bgElements[i].currentStyle.backgroundImage.substring(5, bgElements[i].currentStyle.backgroundImage.length - 2);
			bgElements[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img + "', sizingMethod='crop')";
			bgElements[i].style.backgroundImage = "url('images/blank.gif')";
		}
	}
};

sy.isLessThanIe8 = function() {/* 判断浏览器是否是IE并且版本小于8 */
	return ($.browser.msie && $.browser.version < 8);
};

//拓展验证
$.extend($.fn.validatebox.defaults.rules, {
    
	//校验输入的最大字符数
	//使用：validType:'maxLength[8]',invalidMessage:'最多允许输入8个字符'(自定义提示)
	maxLength: {
        validator: function(value, param){
            return value.length <= param[0];
        },
        message: '输入的字符超出了预定长度.'
    },
    // 验证身份证
    idcard: {
        validator: function (value, param) {
            return idCard(value);
        },
        message:'请输入正确的身份证号码'
    },
    // 验证手机号码
    mobile: {
        validator: function (value, param) {
            return /^1[3,5,8]{1}[0-9]{1}[0-9]{8}$/.test(value);
        },
        message: '手机号码不正确'
    },
    //验证电子邮件
    email:{
        validator : function(value){
        return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
    },
    message : '请输入有效的电子邮件账号'    
    },
    //验证邮政编码
    zip:{
        validator : function(value) {
            return /^[1-9]\d{5}$/.test(value);
        },
        message : '邮政编码格式不正确'
    }
	
});

//验证身份证
var idCard = function (value) {
    if (value.length == 18 && 18 != value.length) return false;
    var number = value.toLowerCase();
    var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
    if (re == null || a.indexOf(re[1]) < 0) return false;
    if (re[2].length == 9) {
        number = number.substr(0, 6) + '19' + number.substr(6);
        d = ['19' + re[4], re[5], re[6]].join('-');
    } else d = [re[9], re[10], re[11]].join('-');
    if (!isDateTime.call(d, 'yyyy-MM-dd')) return false;
    for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
    return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
};

var isDateTime = function (format, reObj) {
    format = format || 'yyyy-MM-dd';
    var input = this, o = {}, d = new Date();
    var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
    var len = f1.length, len1 = f3.length;
    if (len != f2.length || len1 != f4.length) return false;
    for (var i = 0; i < len1; i++) if (f3[i] != f4[i]) return false;
    for (var i = 0; i < len; i++) o[f1[i]] = f2[i];
    o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
    o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
    o.dd = s(o.dd, o.d, d.getDate(), 31);
    o.hh = s(o.hh, o.h, d.getHours(), 24);
    o.mm = s(o.mm, o.m, d.getMinutes());
    o.ss = s(o.ss, o.s, d.getSeconds());
    o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
    if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) return false;
    if (o.yyyy < 100) o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
    d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
    var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
    return reVal && reObj ? d : reVal;
    function s(s1, s2, s3, s4, s5) {
        s4 = s4 || 60, s5 = s5 || 2;
        var reVal = s3;
        if (s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
        if (s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
        return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
    }
};

var gongsi ="";
var nameIdVal = "";

function chooseEntUser(entfsn,callBack){
  var _selStaff = null;
  var _success = false;
  var _chooseStaffWindow = null;
  _chooseStaffWindow = top.sy.modalDialog({
	id:'_chooseStaffWindow',
	title : '选择中介人员',
	width : 635,
	height : 570,
	href : 'common/js/Component/chooseEntUser.jsp?entfsn='+entfsn
	,onDestroy : function() {
	  if (_success) {
		callBack(_selStaff);  
	  }
	}
	,buttons : [
	  {'text':'确定', 'handler' : function() {
	    var _body = _chooseStaffWindow.dialog('body');
	    var tab = _body.find('#_choose_user').tabs('getSelected');
	    var chks = tab.find('input:checkbox:checked');
		if (0 == chks.length) {
		  top.$.messager.alert('提示', '请选择一个人员再进行确定！', 'error');
		  _selStaff = null;
		} else {
		  _selStaff = {'id': chks.val(), 'name': chks.next().text(),'fphone':chks.next().next().text()}
		  _success = true;
		  _chooseStaffWindow && _chooseStaffWindow.dialog('destroy');
		}
	  }}
	  ,{'text':'关闭', 'handler' : function() {
		_chooseStaffWindow && _chooseStaffWindow.dialog('destroy');
	  }}
	]
  });    	
}

function winModalFullScreen(strURL,title)
{
  var sheight = screen.availHeight;
  var swidth = screen.availWidth;
 var winoption ="dialogHeight:"+sheight+"px;dialogWidth:"+ swidth +"px;status:yes;scroll:yes;resizable:yes;center:yes;fullscreen=yes";
 var tmp=window.open(strURL,title,winoption);
 if(tmp){
	 self.blur();
	 tmp.focus();
	 tmp.resizeTo(screen.availWidth, screen.availHeight);
 }
 return tmp;
}

function saveDatagridTemp(datagrid,status){
	var url = basePath + 'datagrid/saveDatagrid';
	var frozenColumns = datagrid.datagrid('getColumns',true);
	var columns = datagrid.datagrid('getColumns');
	var options = datagrid.datagrid('options');
	if(!options.location) return;
	var data = {
			location : options.location,
			options : JSON.stringify(options),
			frozenColumns : JSON.stringify(options.frozenColumns),
			columns : JSON.stringify(options.columns)
	}
	$.post(url,data,function(js){
		if(!status)
			showMsg(js.msg);
	},'json');
}
/**从数据库加载保存的表格列属性**/
function loadMyDatagrid(datagrid,datagridUrl1){
	var options = datagrid.datagrid('options');
	var location = options.location;
	var url = basePath + 'datagrid/getMyDatagrid/'+location;
	$.post(url,function(js){
		if(js.status){
			var frozenColumns = $.parseJSON(js.datagrid.frozenColumns);
			var columns = $.parseJSON(js.datagrid.columns);
			var saveOptions = $.parseJSON(js.datagrid.options);
			if(saveOptions.sortName)
				options.sortName = saveOptions.sortName;
			if(saveOptions.sortOrder)
				options.sortOrder = saveOptions.sortOrder;
			for(var i=0;i<frozenColumns.length;i++){
				if(!frozenColumns[i])
					continue;
				for(var j=0;j<frozenColumns[i].length;j++){
					if(!frozenColumns[i][j]) continue;
					var columnOption = datagrid.datagrid('getColumnOption',frozenColumns[i][j].field);
					if(!columnOption) continue;
					if(columnOption.formatter)
						frozenColumns[i][j].formatter = columnOption.formatter;
					if(columnOption.styler)
						frozenColumns[i][j].styler = columnOption.styler;
					frozenColumns[i][j].field = columnOption.field;
				}
			}
			var tempColumns = new Array();
			for(var i=0;i<columns.length;i++){
				if(!columns[i]) continue;
				var tempColumns2 = new Array();
				for(var j=0;j<columns[i].length;j++){
					if(!columns[i][j]) continue;
					if(!columns[i][j].field){
						tempColumns2.push(columns[i][j]);
					}else{
						var columnOption = datagrid.datagrid('getColumnOption',columns[i][j].field);
						if(!columnOption) continue;
						if(columns[i][j].width)
							columnOption.width = columns[i][j].width;
						columnOption.hidden = columns[i][j].hidden;
						tempColumns2.push(columnOption);
					}
				}
				tempColumns.push(tempColumns2);
			}
			options.frozenColumns = frozenColumns;
			options.columns = tempColumns;
		}else{
			saveDatagridTemp(datagrid,1);
		}
		options.url = datagridUrl1;
		datagrid.datagrid(options).datagrid('columnMoving');
		if(returnAllData=true){
            setTimeout(function () {
                $("#accountChooseId").find(".datagrid-header-row ").find(".datagrid-cell").eq(5).css({'width':200});
                $("td[field='faccountNo']").find('div').css({'width':200});
            },50);
		}


	},'json');
}
function delMyDatagrid(t){
	var cur_options = t.datagrid('options');
    if(cur_options.location){
    	$.post(basePath + 'datagrid/delMyDatagrid/'+cur_options.location,function(js){
    		showMsg(js.msg);
    	},'json');
    }
}
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
//人员选择
function chooseStaff(scope, chooseStaffCallback) {
  var _selStaff = null;
  var _success = false;
  var _chooseStaffWindow = null;
  var onHeight=top.$('body').height()*0.75;//控制弹出框高度大小
  _chooseStaffWindow = scope.sy.modalDialog({
    id:'_chooseStaffWindow',
    title : '人员选择',
    width : 700,
    height : onHeight,
    minHeight : 400,
	  href :basePath+ 'staff/selectStaffPage.do'
    ,onDestroy : function() {
      if (_success) {
        chooseStaffCallback(_selStaff);

		  gongsi =_selStaff.orgId;
		  nameIdVal = _selStaff.name;
      }
    }
  	,buttons : [
  	  {'text':'确定', 'handler' : function() {
        var _body = _chooseStaffWindow.dialog('body');
        var chks = _body.find('#_staffList').find('input:checkbox:checked');   //本公司选择框
        var chks2 = _body.find('#_crossstaffList').find('input:checkbox:checked'); //跨公司选择框
        var chks3 = _body.find('#_rankStaffList').find('input:checkbox:checked'); //按行政岗位
    	if (1 != chks.length && 1 != chks2.length && 1 != chks3.length) {
    	  top.$.messager.alert('提示', '请选择一个用户再进行确定！', 'error');
    	  _selStaff = null;
    	} else {
    		if(1==chks.length){
    			_selStaff = {'id' : chks.val(),
    					'name' : chks.next().text(),
    					'orgId':chks.next().attr('orgid'),
    					'orgName':chks.next().attr('orgname'),
    					'phone':chks.next().attr('phone')};
    			_success = true;
    			_chooseStaffWindow && _chooseStaffWindow.dialog('close');
    		}else if(1==chks3.length){
    			_selStaff = {'id' : chks3.val(),
    					'name' : chks3.next().text(),
    					'orgId':chks3.next().attr('orgid'),
    					'orgName':chks3.next().attr('orgname'),
    					'phone':chks.next().attr('phone')};
    			_success = true;
    			_chooseStaffWindow && _chooseStaffWindow.dialog('close');
    		}else{
    			_selStaff = {'id' : chks2.val(),
    					'name' : chks2.next().text(),
    					'orgId':chks2.next().attr('orgid'),
    					'orgName':chks2.next().attr('orgname'),
    					'phone':chks.next().attr('phone')};
    			_success = true;
    			_chooseStaffWindow && _chooseStaffWindow.dialog('close');
    		}
    	}
  	  }}
  	  ,{'text':'关闭', 'handler' : function() {
  		_chooseStaffWindow && _chooseStaffWindow.dialog('close');
  	  }}
  	]
  });
}

/**
 * 人员在线情况统计(只供展示，暂时隐藏确定按钮)
 */
function chooseOnlineStaff(scope, chooseStaffCallback) {
  var _selStaff = null;
  var _success = false;
  var _chooseStaffWindow = null;
  _chooseStaffWindow = scope.sy.modalDialog({
    id:'_chooseStaffWindow',
    title : '人员选择',
    width : 700,
    height : 700,
	  href :basePath+ 'staff/selectOnlineStaffPage.do'
    ,onDestroy : function() {
      if (_success) {
        chooseStaffCallback(_selStaff);

		  gongsi =_selStaff.orgId;
		  nameIdVal = _selStaff.name;
      }
    }
  	,buttons : [
  	  /*{'text':'确定', 'handler' : function() {
        var _body = _chooseStaffWindow.dialog('body');
        var chks = _body.find('#_staffList').find('input:checkbox:checked');   //本公司选择框
        var chks2 = _body.find('#_crossstaffList').find('input:checkbox:checked'); //跨公司选择框
        var chks3 = _body.find('#_rankStaffList').find('input:checkbox:checked'); //按行政岗位
    	if (1 != chks.length && 1 != chks2.length && 1 != chks3.length) {
    	  top.$.messager.alert('提示', '请选择一个用户再进行确定！', 'error');
    	  _selStaff = null;
    	} else {
    		if(1==chks.length){
    			_selStaff = {'id' : chks.val(),
    					'name' : chks.next().text(),
    					'orgId':chks.next().attr('orgid'),
    					'orgName':chks.next().attr('orgname'),
    					'phone':chks.next().attr('phone')};
    			_success = true;
    			_chooseStaffWindow && _chooseStaffWindow.dialog('close');
    		}else if(1==chks3.length){
    			_selStaff = {'id' : chks3.val(),
    					'name' : chks3.next().text(),
    					'orgId':chks3.next().attr('orgid'),
    					'orgName':chks3.next().attr('orgname'),
    					'phone':chks.next().attr('phone')};
    			_success = true;
    			_chooseStaffWindow && _chooseStaffWindow.dialog('close');
    		}else{
    			_selStaff = {'id' : chks2.val(),
    					'name' : chks2.next().text(),
    					'orgId':chks2.next().attr('orgid'),
    					'orgName':chks2.next().attr('orgname'),
    					'phone':chks.next().attr('phone')};
    			_success = true;
    			_chooseStaffWindow && _chooseStaffWindow.dialog('close');
    		}
    	}
  	  }}
  	  ,*/{'text':'关闭', 'handler' : function() {
  		_chooseStaffWindow && _chooseStaffWindow.dialog('close');
  	  }}
  	]
  });
}
/**
 * 人员多选
 * @param scope
 * @param chooseStaffCallback
 */
function chooseStaff2(scope, chooseStaffCallback) {
	top.zTreeName = "";

	var event = arguments[2] || event;
    if(event && event.target){
        var $ele = $(event.target).prev('input');
        if($ele){
            top.zTreeName = $ele.val();
        }
    }
  var _selStaff = null;
  var _success = false;
  var _chooseStaffWindow = null;
  var onHeight=top.$('body').height()*0.8;//控制弹出框高度大小
  _chooseStaffWindow = scope.sy.modalDialog({
    id:'_chooseStaffWindow2',
    title : '人员选择',
    width :700,
    height : onHeight,
	minHeight : 400,
    href :basePath+ 'staff/selectStaffPage2.do'
    ,onDestroy : function() {
      if (_success) {
        chooseStaffCallback(_selStaff);
      }
    }
  	,buttons : [
  	  {'text':'确定', 'handler' : function() {

        var _body = _chooseStaffWindow.dialog('body');
        var chks = _body.find('._staffList').find('input:checkbox:checked');
        var chks2 = _body.find('._crossStaffList').find('input:checkbox:checked');
        var chks3 = _body.find('._rankStaffList').find('input:checkbox:checked');
          var _selStaffOneName = [];

    	if (0 == chks.length && 0 == chks2.length && 0 == chks3.length) {
    	  top.$.messager.alert('提示', '请选择一个用户再进行确定！', 'error');
    	  _selStaff = null;
    	} else {
    	  _selStaff = [];
    	  var _set = {};
    	  if(chks.length!=0){
    		  $.each(chks, function() {
    			  id = this.value;
    			  name = $(this).next().text();
    			  if (!_set[id]) {
    				  _selStaff.push({id:id, name:name});
    				  _set[id] = name;
    			  }
    		  });
    	  }
    	  if(chks2.length!=0){
    		  $.each(chks2, function() {
    			  id = this.value;
    			  name = $(this).next().text();
    			  if (!_set[id]) {
    				  _selStaff.push({id:id, name:name});
    				  _set[id] = name;
    			  }
    		  });
    	  }
    	  if(chks3.length!=0){
    		  $.each(chks3, function() {
    			  id = this.value;
    			  name = $(this).next().text();
    			  if (!_set[id]) {
    				  _selStaff.push({id:id, name:name});
    				  _set[id] = name;
    			  }
    		  });
    	  }
    	  //console.log(_selStaff);
            for(var i =0;i<_selStaff.length;i++){
                _selStaffOneName.push(_selStaff[i].name)
            }
            //window.localStorage.setItem("_selStaff",_selStaffOneName);
            //window.localStorage.getItem("_selStaff");
            //console.log(window.localStorage.getItem("_selStaff"))
    	  _success = true;
    	  _chooseStaffWindow && _chooseStaffWindow.dialog('close');
    	}
  	  }}
  	  ,{'text':'关闭', 'handler' : function() {
  		_chooseStaffWindow && _chooseStaffWindow.dialog('close');
  	  }}
  	]
  });
}

//有人员部门的全选功能
function chooseStaff3(scope, chooseStaffCallback) {
  var _selStaff = null;
  var _success = false;
  var _chooseStaffWindow = null;
  _chooseStaffWindow = scope.sy.modalDialog({
    id:'_chooseStaffWindow3',
    title : '人员选择',
    width : 630,
    height : 550,
	  href :basePath+ 'staff/selectStaffPage3.do'
    ,onDestroy : function() {
      if (_success) {
        chooseStaffCallback(_selStaff);  
      }
    }
  	,buttons : [
  	  {'text':'确定', 'handler' : function() {
  		  
        var _body = _chooseStaffWindow.dialog('body');
        var chks = _body.find('#_staffList').find('input:checkbox:checked');
        console.info(_body.find("#_orgTree").tree('getSelected'));
        
        
     
    	if (0 == chks.length) {
    	  top.$.messager.alert('提示', '请选择一个用户再进行确定！', 'error');
    	  _selStaff = null;
    	} else {
    	  _selStaff = [];
    	  var _set = {};
    	  $.each(chks, function() {
    		  id = this.value;
    		  name = $(this).next().text();
    		  if (!_set[id]) {
    			_selStaff.push({id:id, name:name});
    		    _set[id] = name;
    		  }
    	  });
    	  _success = true;
    	  _chooseStaffWindow && _chooseStaffWindow.dialog('close');
    	}
  	  }}
  	  ,{'text':'关闭', 'handler' : function() {
  		_chooseStaffWindow && _chooseStaffWindow.dialog('close');
  	  }}
  	]
  });    	
}




//协助人员选择
function chooseHelpStaff(scope, chooseStaffCallback) {
  var _selStaff = null;
  var _success = false;
  var _chooseStaffWindow = null;
  _chooseStaffWindow = scope.sy.modalDialog({
    id:'_chooseStaffWindow',
    title : '选择协助人员',
    width : 630,
    height : 550,
    href : 'core/account/selectStaffPage.jsp'
    ,onDestroy : function() {
      if (_success) {
        chooseStaffCallback(_selStaff);  
      }
    }
  	,buttons : [
  	  {'text':'确定', 'handler' : function() {
        var _body = _chooseStaffWindow.dialog('body');
        var chks = _body.find('#_staffList').find('input:checkbox:checked');
    	if (1 != chks.length) {
    	  top.$.messager.alert('提示', '请选择一个用户再进行确定！', 'error');
    	  _selStaff = null;
    	} else {
    	  _selStaff = {'id' : chks.val(), 'name' : chks.next().text()};
    	  _success = true;
    	  _chooseStaffWindow && _chooseStaffWindow.dialog('close');
    	}
  	  }}
  	  ,{'text':'关闭', 'handler' : function() {
  		_chooseStaffWindow && _chooseStaffWindow.dialog('close');
  	  }}
  	]
  });    	
}

function chooseDept(scope, chooseDeptCallback) {
  var _selDept = null;
  var _selDeptSuccess = false;
  var _chooseDeptWindow = null;
  var onHeight=top.$('body').height()*0.566;//控制弹出框高度大小
  _chooseDeptWindow = scope.sy.modalDialog({
    id:'_chooseDeptWindow',
    title : '部门选择',
    width : 630,
    height : onHeight,
    minHeight : 400,
    href : 'core/org/selectDeptPage.jsp'
    ,onDestroy : function() {
      if (_selDeptSuccess) {
        chooseDeptCallback(_selDept);  
      }
    }
  	,buttons : [
  	  {'text':'确定', 'handler' : function() {
        var _body = _chooseDeptWindow.dialog('body');
        var chks = _body.find('#_deptList').find('input:checkbox:checked');
    	if (1 != chks.length) {
    	  top.$.messager.alert('提示', '请在右边列表中选择一个部门再进行确定！', 'error');
    	  _selDept = null;
    	  _selDeptSuccess = false;
    	} else {
    	  _selDept = {'id': chks.val(), 'name': chks.next().text(), 'fullName': chks.attr('alt')};
    	  _selDeptSuccess = true;
    	  if (_chooseDeptWindow) {
    	    _chooseDeptWindow.dialog('close');
    	  }
    	}
  	  }}
  	  , {'text':'关闭', 'handler' : function() {
  		_selDeptSuccess = false;
  		_selDept = null;
  		if (_chooseDeptWindow) {
    	  _chooseDeptWindow.dialog('close');
        }
  	  }}
  	]
  });    	
}







function chooseCompanyDept(scope, chooseDeptCallback) {
  var _selDept = null;
  var _selDeptSuccess = false;
  var _chooseDeptWindow = null;
  _chooseDeptWindow = scope.sy.modalDialog({
    id:'_chooseDeptWindow',
    title : '主体选择',
    width : 630,
    height : 550,
    href : 'core/org/selectCompanyDeptPage.jsp'
    ,onDestroy : function() {
      if (_selDeptSuccess) {
        chooseDeptCallback(_selDept);  
      }
    }
  	,buttons : [
  	  {'text':'确定', 'handler' : function() {
        var _body = _chooseDeptWindow.dialog('body');
        var chks = _body.find('#_deptList').find('input:checkbox:checked');
    	if (1 != chks.length) {
    	  top.$.messager.alert('提示', '请在右边列表中选择一个部门再进行确定！', 'error');
    	  _selDept = null;
    	  _selDeptSuccess = false;
    	} else {
    	  _selDept = {'id': chks.val(), 'name': chks.next().text(), 'fullName': chks.attr('alt')};
    	  _selDeptSuccess = true;
    	  if (_chooseDeptWindow) {
    	    _chooseDeptWindow.dialog('close');
    	  }
    	}
  	  }}
  	  , {'text':'关闭', 'handler' : function() {
  		_selDeptSuccess = false;
  		_selDept = null;
  		if (_chooseDeptWindow) {
    	  _chooseDeptWindow.dialog('close');
        }
  	  }}
  	]
  });    	
}
function chooseCompanyDept2(scope, chooseDeptCallback) {
	var _selDept = null;
	var _selDeptSuccess = false;
	var _chooseDeptWindow = null;
	_chooseDeptWindow = scope.sy.modalDialog({
		id:'_chooseDeptWindow',
		title : '主体选择',
		width : 630,
		height : 550,
		href : 'core/org/selectCompanyDeptPage2.jsp'
			,onDestroy : function() {
				if (_selDeptSuccess) {
					chooseDeptCallback(_selDept);  
				}
			}
	,buttons : [
	            {'text':'确定', 'handler' : function() {
	            	var _body = _chooseDeptWindow.dialog('body');
	            	var chks = _body.find('#_deptList').find('input:checkbox:checked');
	            	if (1 != chks.length) {
	            		top.$.messager.alert('提示', '请在右边列表中选择一个部门再进行确定！', 'error');
	            		_selDept = null;
	            		_selDeptSuccess = false;
	            	} else {
	            		_selDept = {'id': chks.val(), 'name': chks.next().text(), 'fullName': chks.attr('alt')};
	            		_selDeptSuccess = true;
	            		if (_chooseDeptWindow) {
	            			_chooseDeptWindow.dialog('close');
	            		}
	            	}
	            }}
	            , {'text':'关闭', 'handler' : function() {
	            	_selDeptSuccess = false;
	            	_selDept = null;
	            	if (_chooseDeptWindow) {
	            		_chooseDeptWindow.dialog('close');
	            	}
	            }}
	            ]
	});    	
}










function chooseFile(scope, chooseDeptCallback) {
	  var _selDept = null;
	  var _selDeptSuccess = false;
	  var _chooseDeptWindow = null;
	  _chooseDeptWindow = scope.sy.modalDialog({
	    id:'_chooseDeptWindow',
	    title : '档案柜或文件夹选择',
	    width : 630,
	    height : 550,
	    href : 'bs/general/filemanager/selectFileManager.jsp'
	    ,onDestroy : function() {
	      if (_selDeptSuccess) {
	        chooseDeptCallback(_selDept);  
	      }
	    }
	  	,buttons : [
	  	  {'text':'确定', 'handler' : function() {
	        var _body = _chooseDeptWindow.dialog('body');
	        var chks = _body.find('#_deptList').find('input:checkbox:checked');
	    	if (1 != chks.length) {
	    	  top.$.messager.alert('提示', '请在右边列表中选择一个档案柜或文件夹！', 'error');
	    	  _selDept = null;
	    	  _selDeptSuccess = false;
	    	} else {
	    	  _selDept = {'id': chks.val(), 'name': chks.next().text(), 'fullName': chks.attr('alt')};
	    	  _selDeptSuccess = true;
	    	  if (_chooseDeptWindow) {
	    	    _chooseDeptWindow.dialog('close');
	    	  }
	    	}
	  	  }}
	  	  , {'text':'关闭', 'handler' : function() {
	  		_selDeptSuccess = false;
	  		_selDept = null;
	  		if (_chooseDeptWindow) {
	    	  _chooseDeptWindow.dialog('close');
	        }
	  	  }}
	  	]
	  });    	
	}




//合同选择，经营项目专用
function chooseContractPT(callBack,projectTrackingId) {
	var contractBusinessId = null;
	if (0 != $("#contractBusinessId").length) {
		contractBusinessId = $("#contractBusinessId").val();
	}
	var chooseWindow = null;
	chooseWindow = top.sy.modalDialog({
		id:'contractChooseId',
		href : 'contract/chooseContractPT.do?contractBusinessId='
		+ contractBusinessId,
		title : "合同选择",
		width : 880,
		height: 550,
		buttons : [ {
			'text' : '确定',
			'handler' : function() {
				var body = chooseWindow.dialog('body');
				var temp = body.find('#contractList');
				var row = temp.datagrid('getSelected');
				if ( !row) {
					top.$.messager.alert("请选择一条记录");
					return;
				}
				row.id = row.contractId;
				callBack && callBack(row);
				chooseWindow.dialog('destroy');
			}
		}]
	});
};




//合同选择
function chooseContract(callBack,singleSelect) {
	var contractBusinessId = null;
	if (0 != $("#contractBusinessId").length) {
		contractBusinessId = $("#contractBusinessId").val();
	}
	var chooseWindow = null;
	chooseWindow = top.sy.modalDialog({
		id:'contractChooseId',
		href : 'contract/chooseContract.do?contractBusinessId='
				+ contractBusinessId + '&singleSelect='+(singleSelect?true:false),
		title : "合同选择",
		width : 880,
		height: 550,
		buttons : [ {
			'text' : '确定',
			'handler' : function() {
				var body = chooseWindow.dialog('body');
				var temp = body.find('#contractList');
				if (temp.datagrid('getSelected') == null) {
					top.$.messager.alert("请选择一条记录");
					return;
				}
				var selIdNo = body.find('#selectedContract').val();
				if (selIdNo != '') {
					var rets = selIdNo.split('=');
					callBack && callBack({
						'id' : rets[0]
						,'contractNo' : rets[1]
						,'contractPrice' : rets[2]
						,'signDate' : rets[3]
					    ,'businessName':rets[4]
					});
					chooseWindow.dialog('destroy');
				}
			}
		}]
	});
};





//投标业务合同选择，多选
function chooseContract3(callBack) {
	var contractBusinessId = null;
	if (0 != $("#contractBusinessId").length) {
		contractBusinessId = $("#contractBusinessId").val();
	}
	var chooseWindow = null;
	chooseWindow = top.sy.modalDialog({
		href : 'contract/chooseContracts.do?contractBusinessId='
		+ contractBusinessId,
		title : "合同选择",
		width : 880,
		height: 550,
		buttons : [ {
			'text' : '确定',
			'handler' : function() {
				var body = chooseWindow.dialog('body');
				var temp = body.find('#contractList');
				if (temp.datagrid('getSelected') == null) {
					top.$.messager.alert("请至少选择一条记录！");
					return;
				}
				var ids = body.find('#selectedContract').val();
				if (ids != '') {
					callBack && callBack({
						'id' :ids
					});
					chooseWindow.dialog('destroy');
				}
			}
		}]
	});
};



//驾驶员选择
function chooseDriver(callBack) {
	var chooseWindow = null;
	chooseWindow = top.sy.modalDialog({
		href : 'carmanager/chooseDriver.do',
		title : "驾驶员选择",
		width : 700,
		height: 550,
		buttons : [ {
			'text' : '确定',
			'handler' : function() {
				var body = chooseWindow.dialog('body');
				var temp = body.find('#carDriverManager');
				if (temp.datagrid('getSelected') == null) {
					top.$.messager.alert("请选择一条记录");
					return;
				}
				var selIdNo = body.find('#selectedDriver').val();
				if (selIdNo != '') {
					var rets = selIdNo.split('=');
					callBack && callBack({
						'id' : rets[0]
						,'driver' : rets[1]
					});
					chooseWindow.dialog('destroy');
				}
			}
		}]
	});
};
//车辆选择
function chooseCar(callBack) {
	var chooseWindow = null;
	chooseWindow = top.sy.modalDialog({
		href : 'carmanager/chooseCar.do',
		title : "车辆选择",
		width : 700,
		height: 550,
		buttons : [ {
			'text' : '确定',
			'handler' : function() {
				var body = chooseWindow.dialog('body');
				var temp = body.find('#carManager');
				if (temp.datagrid('getSelected') == null) {
					top.$.messager.alert("请选择一条记录");
					return;
				}
				var selIdNo = body.find('#selectedCar').val();
				if (selIdNo != '') {
					var rets = selIdNo.split('=');
					callBack && callBack({
						'id' : rets[0]
						,'licenseNo' : rets[1]
					});
					chooseWindow.dialog('destroy');
				}
			}
		}]
	});
};
function chooseContract2(domId) {
	chooseContract(function(c){
		$('#' + domId).val(c.contractNo);
	});
}

//费用计算工具
function openFeeTool(scope,taskId,feeToolCallback,view){
	var w=scope.$(scope).width()*0.9;
	var h=scope.$(scope).height()*0.9;
	 var _chooseFeeToolWindow = scope.sy.iframeDialog({
	    id:'_chooseFeeToolWindow',
	    title : '费用计算工具',
	    width : w,
	    height : h,
	    href : 'feetool/feeDetailmain.do?taskId='+taskId+"&view="+(view||false),
	    onDestroy : function() {
	    	
	     }
	  	,buttons : [
	  	  {'text':'确定', 'handler' : function() {
	        var win = _chooseFeeToolWindow.find('iframe').get(0).contentWindow;
	        var foot=win.$('#datagrid2').datagrid('getFooterRows')[0];
	        var baseFee=foot.total-foot.total2;
	        var	benefitFee=foot.total2;
	        feeToolCallback({"baseFee":baseFee,"benefitFee":benefitFee});
	        _chooseFeeToolWindow.dialog('close');
	  	  }}
	  	  ,{'text':'关闭', 'handler' : function() {
	  		_chooseFeeToolWindow.dialog('close');
	  	  }}
	  	]
	  });
}
//数据权限设置窗口
function dataPermission(scope,dataId,callback){
	var w=scope.$(scope).width()*0.8;
	var h=scope.$(scope).height()*0.8;
	w=700;h=500;
	 var _dataPermissionWindow = scope.sy.iframeDialog({
	    id:'_dataPermissionWindow',
	    title : '数据授权(对角色)',
	    width : w,
	    height : h,
	    href : 'datapermission?dataId=='+dataId,
	    onDestroy : function() {
	    	callback&&callback();
	     }
	  });
	 return _dataPermissionWindow;
}

function openFeeToolyh(scope,taskId,feeToolCallback,view){
	var w=scope.$(scope).width()*0.9;
	var h=scope.$(scope).height()*0.9;
	 var _chooseFeeToolWindow = scope.sy.iframeDialog({
	    id:'_chooseFeeToolWindow',
	    title : '费用计算工具',
	    width : w,
	    height : h,
	    href : 'feetool/feeDetailmainyh.do?taskId='+taskId+"&view="+(view||false),
	    onDestroy : function() {
	    	
	    }
	  	,buttons : [
	  	  {'text':'确定', 'handler' : function() {
	        var win = _chooseFeeToolWindow.find('iframe').get(0).contentWindow;
	        var foot=win.$('#datagrid2').datagrid('getFooterRows')[0];
	        var baseFee=foot.total-foot.total2;
	        var	benefitFee=foot.total2;
	        feeToolCallback({"baseFee":baseFee,"benefitFee":benefitFee});
	        _chooseFeeToolWindow.dialog('close');
	  	  }}
	  	  ,{'text':'关闭', 'handler' : function() {
	  		_chooseFeeToolWindow.dialog('close');
	  	  }}
	  	]
	  });
	 return _chooseFeeToolWindow;
}
//简易计费工具
function openFeeToolSimple(scope,taskId,feeToolCallback,view){
	var w=scope.$(scope).width()*0.8;
	var h=scope.$(scope).height()*0.8;
	 var _chooseFeeToolWindow = scope.sy.iframeDialog({
	    id:'_chooseFeeToolWindow',
	    title : '费用计算工具',
	    width : w,
	    height : h,
	    href : 'feetool/feeCalculateSimple.do?taskId='+taskId+"&fs.id="+taskId+"&view="+(view||false),
	    onDestroy : function() {
	    	
	    }
	  	,buttons : [
	  	  {'text':'确定', 'handler' : function() {
	        var win = _chooseFeeToolWindow.find('iframe').get(0).contentWindow;
	        var total=win.$('#fs_total').numberbox("getValue");
	        feeToolCallback(total);
	        _chooseFeeToolWindow.dialog('close');
	  	  }}
	  	  ,{'text':'关闭', 'handler' : function() {
	  		_chooseFeeToolWindow.dialog('close');
	  	  }}
	  	]
	  });
	 return _chooseFeeToolWindow;
}
//系统统一上传接口
function ydUpload(title,refModule,refRecordId,callback){
	var url='file/fileUploadPage.do?refModule='+refModule+'&refRecordId=' + refRecordId;
	var opition={
			id:'commonFileUploadPage',
			title:title,
			width:650,
			height:330,
			href:url,
			onDestroy : function() {
		    	callback&&callback();
		     }
	};
	sy.iframeDialog(opition);
}

//系统统一上传接口，但是不显示下载，删除，的操作列
function ydFindFileView(title,refModule,refRecordId,callback){
	var url='file/fileListPageView.do?refModule='+refModule+'&refRecordId=' + refRecordId;
	var opition={title:title,
			width:1000,
			height:600,
			href:url,
			onDestroy : function() {
		    	callback&&callback();
		     }
	};
	sy.iframeDialog(opition);
}



//系统统一查看附件接口
function ydFindFile(title,refModule,refRecordId,callback){
	var url='file/fileListPage.do?refModule='+refModule+'&refRecordId=' + refRecordId;
	var opition={title:title,
			width:1000,
			height:600,
			href:url,
			onDestroy : function() {
		    	callback&&callback();
		     }
	};
	sy.iframeDialog(opition);
}



//显示是否优先
function chooseUser(callBack,showFirse){
  var _selStaff = null;
  var _success = false;
  var _chooseStaffWindow = null;
  showFirse=showFirse||false;
  _chooseStaffWindow = top.sy.modalDialog({
	id:'_chooseStaffWindow',
	title : '选择中介单位/中心科室',
	width : 635,
	height : 570,
	href : 'common/js/Component/chooseUser.jsp?showFirse='+showFirse
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
		  $.messager.alert('提示', '请选择一个人员再进行确定！', 'error');
		  _selStaff = null;
		} else {
		  //选中的企业
		  var _orgFullName1="";
		  _orgFullName1=_chooseStaffWindow&&_chooseStaffWindow.find("#_orgFullName1").text();
		  var _orgFullId=_chooseStaffWindow&&_chooseStaffWindow.find("#_orgFullId").val();
		  _selStaff = {'id': chks.val(), 'name': chks.next().text(),"orgName":_orgFullName1,"orgId":_orgFullId};
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

function chooseOrg(chooseDeptCallback) {
  var _selDept = null;
  var _selDeptSuccess = false;
  var _chooseDeptWindow = null;
  _chooseDeptWindow = top.sy.modalDialog({
    id:'_chooseDeptWindow',
    title : '组织机构选择',
    width : 630,
    height : 570,
    href : 'common/js/Component/chooseOrg.jsp'
    ,onDestroy : function() {
      if (_selDeptSuccess) {
        chooseDeptCallback(_selDept);  
      }
    }
  	,buttons : [
  	  {'text':'确定', 'handler' : function() {
        var _body = _chooseDeptWindow.dialog('body');
        var tab = _body.find('#_choose_org').tabs('getSelected');
        var chks = tab.find('input:checkbox:checked');
    	if (1 != chks.length) {
    	  top.$.messager.alert('提示', '请在右边列表中选择一个部门再进行确定！', 'error');
    	  _selDept = null;
    	  _selDeptSuccess = false;
    	} else {
    	  _selDept = {'id': chks.val(), 'name': chks.next().text(), 'fullName': chks.attr('alt')};
    	  _selDeptSuccess = true;
    	  if (_chooseDeptWindow) {
    	    _chooseDeptWindow.dialog('close');
    	  }
    	}
  	  }}
  	  , {'text':'关闭', 'handler' : function() {
  		_selDeptSuccess = false;
  		_selDept = null;
  		if (_chooseDeptWindow) {
    	  _chooseDeptWindow.dialog('close');
        }
  	  }}
  	]
  });    	
}

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

function chooseOrgUser(orgId, callBack){
  var _selStaff = null;
  var _success = false;
  var _chooseStaffWindow = null;
  _chooseStaffWindow = top.sy.modalDialog({
	id:'_chooseStaffWindow',
	title : '选择科室人员',
	width : 635,
	height : 570,
	href : 'common/js/Component/chooseOrgUser.jsp?orgId='+orgId
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


function chooseDrawEnterprise(taskId, chooseDeptCallback) {
  var _selDept = null;
  var _selDeptSuccess = false;
  var _chooseDeptWindow = null;
  _chooseDeptWindow = top.sy.modalDialog({
    id:'_chooseDeptWindow',
    title : '组织机构选择',
    width : 630,
    height : 550,
    href : 'budget/chooseDrawEnterprise?taskId=' + taskId
    ,onDestroy : function() {
      if (_selDeptSuccess) {
        chooseDeptCallback(_selDept);  
      }
    }
  	,buttons : [
  	  {'text':'确定', 'handler' : function() {
        var _body = _chooseDeptWindow.dialog('body');
        var tab = _body.find('#_choose_org').tabs('getSelected');
        var chks = tab.find('input:checkbox:checked');
    	if (1 != chks.length) {
    	  top.$.messager.alert('提示', '请在右边列表中选择一个部门再进行确定！', 'error');
    	  _selDept = null;
    	  _selDeptSuccess = false;
    	} else {
    	  _selDept = {'id': chks.val(), 'name': chks.next().text(), 'fullName': chks.attr('alt')};
    	  _selDeptSuccess = true;
    	  if (_chooseDeptWindow) {
    	    _chooseDeptWindow.dialog('close');
    	  }
    	}
  	  }}
  	  , {'text':'关闭', 'handler' : function() {
  		_selDeptSuccess = false;
  		_selDept = null;
  		if (_chooseDeptWindow) {
    	  _chooseDeptWindow.dialog('close');
        }
  	  }}
  	]
  });    	
}

function chooseDrawEmployee(taskId, callBack){
  var _selStaff = null;
  var _success = false;
  var _chooseStaffWindow = null;
  _chooseStaffWindow = top.sy.modalDialog({
	id:'_chooseStaffWindow',
	title : '选择中介单位/中心科室',
	width : 635,
	height : 570,
	href : 'budget/chooseDrawEmployee?taskId=' + taskId
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
		  $.messager.alert('提示', '请选择一个人员再进行确定！', 'error');
		  _selStaff = null;
		} else {
		  _selStaff = {'id': chks.val(), 'name': chks.next().text()}
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

function _singleChoose(options, callback) {
  var _singleChooseDialog = null;
  var suffix = new Date().getTime()
  var params = $.extend({}, options, {
    "id": '_singleChooseDialog'  
    , "buttons": [
      {"text": '确定', "handler": function() {
    	  var frameBody = _singleChooseDialog.dialog('body');
    	  var selTab = frameBody.find('#_choose_tabs' + "_" + suffix).tabs('getSelected');
    	  var selDatas = frameBody.find('#_list_' + selTab.attr('index') + "_" + suffix).datagrid('getSelections');
          if (1 != selDatas.length) {
            top.$.messager.alert('提示', '请在列表中选择一条记录再进行确定！', 'info');
          } else {
            if (callback) callback.call(_singleChooseDialog, selDatas[0]);
            _singleChooseDialog.dialog('destroy'); 
          }
        }
      }
      , {"text": '关闭', "handler": function() {_singleChooseDialog.dialog('destroy');}}
    ]
  });
  var url = params.href;
  if (-1 < url.indexOf('?')) {
	params.href = url + '&suffix=' + suffix;  
  } else {
	params.href = url + '?suffix=' + suffix;  
  }
  _singleChooseDialog = top.sy.modalDialog(params);
}

/*
function singleChoose() {
  var p = {"width": 620, "height": 550, "href": "chooseUserInMyOrg"}
  _singleChoose(p, function(data) {
	console.info(this);
	alert(data.name);
  });
}*/

function chooseWorkPartner(taskId, callBack) {
  var _chooseWorkPartner = null;
  _chooseWorkPartner = top.sy.modalDialog({
    id:'_chooseWorkPartner',
    "title": '项目成员选择',
    "width": 630,
    "height": 550,
    "href": 'budget/chooseWorkPartner?taskId=' + taskId
    , "onClose" : function() {
        var _body = _chooseWorkPartner.dialog('body');
        if ('' != _body.find('[name="_id"]').val()) {
          var returnData = {
        	"id": _body.find('[name="_id"]').val()
        	, "name": _body.find('[name="_name"]').val()
        	, "type": _body.find('[name="_type"]').val()
          }
          callBack(returnData);  
        }
        _chooseWorkPartner.dialog('destroy');
    }
  }); 
}
function chooseCostWorkPartner(taskId, callBack) {
	  var _chooseWorkPartner = null;
      top.$('#_chooseCostWorkPartner').dialog('destroy');
	  _chooseWorkPartner = top.sy.modalDialog({
	    id:'_chooseCostWorkPartner',
	    "title": '项目成员选择',
	    "width": 830,
	    "height": 250,
	    "href": 'cost1/chooseCostWorkPartner?taskId=' + taskId
	    , "onClose" : function() {
	        var _body = _chooseWorkPartner.dialog('body');
	        if ('' != _body.find('[name="_id"]').val()) {
	          var returnData = {
	        	"id": _body.find('[name="_id"]').val()
	        	, "name": _body.find('[name="_name"]').val()
	        	, "type": _body.find('[name="_type"]').val()
	          }
	          callBack(returnData);  
	        }
	        _chooseWorkPartner.dialog('destroy');
	    }
	  }); 
	}

function chooseUserInCompany(callback) {
  var p = {"width": 620, "height": 550, "href": "chooseUserInCompany"}
  _singleChoose(p, callback);
}

function chooseDepartment(callback) {
  var p = {"width": 620, "height": 550, "href": "chooseDepartment"}
  _singleChoose(p, callback);
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

function chooseContractByTkProjectId(taskId, callBack) {
	var _tkContractWin = null;
	_tkContractWin = top.sy.iframeDialog({
		id:'_chooseWorkPartner',
		"title": '投控合同项目选择',
		"width": $(top).width() * 0.9,
		"height": $(top).height() * 0.9,
		"href": 'showContractByTkProjectId?projectId=' + taskId
		, "buttons" : [{'text':'确定', 'handler': function() {
			var _body = _tkContractWin.find('iframe').get(0).contentWindow;
			var treegrid = _body.$('#treegrid');
			var row = treegrid.treegrid('getSelected');
			if (row) {
				callBack(row);
			} else {
				top.showAlert("请先选择一条记录再进行操作。");
			}
			_tkContractWin.dialog('destroy');
		}}, {'text': '取消', 'handler': function() {_tkContractWin.dialog('close')}}]
	});
}

//部门选择多选
function chooseDepts(scope, chooseDeptCallback) {
	var _selDept = null;
	var _selDeptSuccess = false;
	var _chooseDeptWindow = null;
	_chooseDeptWindow = scope.sy.modalDialog({
		id:'_chooseDeptWindow2',
		title : '部门选择',
		width : 630,
		height : 550,
		href : 'core/org/selectDeptsPage.jsp'
		,onDestroy : function() {
			if (_selDeptSuccess) {
				chooseDeptCallback(_selDept);
			}
		}
		,buttons : [
			{'text':'确定', 'handler' : function() {
				var _body = _chooseDeptWindow.dialog('body');
				var chks = _body.find('#_deptList').find('input:checkbox:checked');
				if (0 == chks.length) {
					top.$.messager.alert('提示', '请在右边列表中选择一个部门再进行确定！', 'error');
					_selDept = null;
					_selDeptSuccess = false;
				} else {
				/*	for(var i=0;i<chks.length;i++){
						if(_selDept==null){
							_selDept = {'id': $(chks[i]).val(), 'name': $(chks[i]).next().text(), 'fullName': $(chks[i]).attr('alt')};
						}else{
							_selDept += {'id': $(chks[i]).val(), 'name': $(chks[i]).next().text(), 'fullName': $(chks[i]).attr('alt')};
						}
					}*/
					_selDept = [];
					var _set = {};
					if(chks.length!=0){
						$.each(chks, function() {
							id = this.value;
							name = $(this).next().text();
							if (!_set[id]) {
								_selDept.push({id:id, name:name});
								_set[id] = name;
							}
						});
					}
					_selDeptSuccess = true;
					if (_chooseDeptWindow) {
						_chooseDeptWindow.dialog('close');
					}
				}
			}}
			, {'text':'关闭', 'handler' : function() {
				_selDeptSuccess = false;
				_selDept = null;
				if (_chooseDeptWindow) {
					_chooseDeptWindow.dialog('close');
				}
			}}
		]
	});
}
function uploadFiles(id,model,ondestroy){
	 var uploadDialog = top.sy.modalDialog({
	      id: 'commonFileUploadPage'
	      , iconCls:'icon-note_add'
	  	  , title: '上传附件'
	  	  , width: width*0.8
	  	  , height: height*0.8
	  	  , content: '<iframe style="width:98%;height:95%;border:none;" frameborder="none" src="' 
	  		  			+ basePath + 'file/html5FileUploadPage.do'
	  					+ '?refModule='+model
	  					+ '&refRecordId=' + id
	  					+ '"></iframe>'
	  	  , onDestroy: function() {
	  		  if(ondestroy)
	  			ondestroy();
	      }
	    });
		return false;
}
function uploadFile(id,model,ondestroy){
	var uploadDialog = top.sy.modalDialog({
		id: 'commonFileUploadPage'
		, iconCls:'icon-note_add'
		, title: '上传附件'
		, width: width*0.8
		, height: height*0.8
		, content: '<iframe style="width:98%;height:95%;border:none;" frameborder="none" src="' 
			+ basePath + 'file/html5FileUploadPage.do'
			+ '?refModule='+model
			+ '&refRecordId=' + id
			+ '&multi=0' 
			+ '"></iframe>'
		, onDestroy: function() {
			if(ondestroy)
				ondestroy();
		}
	});
	return false;
}
function showAttClick(model,id){
	top.ydFindFile("管理附件", model, id, function() {});
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
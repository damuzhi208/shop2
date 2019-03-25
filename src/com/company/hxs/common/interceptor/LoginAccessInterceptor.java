package com.company.hxs.common.interceptor;

import java.util.Set;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.company.hxs.common.sys.SysConstant;
import com.company.hxs.common.util.CTools;

@SuppressWarnings({"unchecked", "serial", "rawtypes"})
public class LoginAccessInterceptor implements HandlerInterceptor  {

	private static Set<String> UN_LOGIN_EXPOSE_METHODS = new HashSet() {};
	
	//private static List<Class<?>> TEMP_USER_EXPOSE_ACTIONS = new ArrayList() {};
	
	//private static List<String> TEMP_USER_EXPOSE_METHODS = new ArrayList() {};
	
	static {
		UN_LOGIN_EXPOSE_METHODS.add("login");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//请求的路径
        String contextPath = request.getContextPath();
        String url = request.getServletPath().toString();
        System.out.println("url===================="+url);
        HttpSession session = request.getSession();
        if(UN_LOGIN_EXPOSE_METHODS.contains(url)){
        	return true;
        }
        String user = (String) session.getAttribute(SysConstant.SESSION_USER);
        
        //这里可以根据session的用户来判断角色的权限，根据权限来重定向不同的页面，简单起见，这里只是做了一个重定向
        if (!CTools.isNotEmpty(user)) {
            //被拦截，重定向到login界面
            response.sendRedirect(contextPath + "/login");
            return false;
        }
        return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		
	}

}

package com.uic.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 职工url拦截器
 */
public class WorkerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        if (url.indexOf("/worker/") >= 0) {
            HttpSession session = httpServletRequest.getSession();
            String userRole = (String) session.getAttribute("userRole");
            if (userRole.equals("职工") && userRole.equals("管理员")) {
                return true;
            } else {
                httpServletRequest.getRequestDispatcher("/unauthorized.jsp").forward(httpServletRequest, httpServletResponse);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

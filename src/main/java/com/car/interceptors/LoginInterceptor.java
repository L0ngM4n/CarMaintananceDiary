package com.car.interceptors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession httpSession = httpServletRequest.getSession();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(httpSession.getAttribute("userId") == null){
//            httpSession.setAttribute("userId", ((User) principal).getId());
        }

        return true;
    }


}

package com.car.interceptors;

import com.car.areas.user.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpSession httpSession = request.getSession();

        if(httpSession.getAttribute("userId") == null){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            httpSession.setAttribute("userId", ((User) principal).getId());

        }
    }
}

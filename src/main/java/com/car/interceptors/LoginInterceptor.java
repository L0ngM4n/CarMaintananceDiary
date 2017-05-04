package com.car.interceptors;

import com.car.areas.user.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession httpSession = request.getSession();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(httpSession.getAttribute("userId") == null){
            httpSession.setAttribute("userId", ((User) principal).getId());
        }
    }
}

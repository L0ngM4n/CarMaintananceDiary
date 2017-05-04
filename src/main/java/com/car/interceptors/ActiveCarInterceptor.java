package com.car.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ActiveCarInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        HttpSession httpSession = request.getSession();

        if(httpSession.getAttribute("activeCar") == null){
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return true;
    }
}

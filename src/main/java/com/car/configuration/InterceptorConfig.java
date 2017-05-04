package com.car.configuration;

import com.car.interceptors.CounterInterceptor;
import com.car.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/");
        registry.addInterceptor(new CounterInterceptor()).addPathPatterns("/");
//        registry.addInterceptor(new ActiveCarInterceptor()).addPathPatterns("");
    }


}

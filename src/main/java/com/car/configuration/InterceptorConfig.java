package com.car.configuration;

import com.car.interceptors.CounterInterceptor;
import com.car.interceptors.IpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new IpInterceptor()).addPathPatterns("/viruses/*").addPathPatterns("/map").addPathPatterns("/");
        registry.addInterceptor(new CounterInterceptor()).addPathPatterns("/");
    }


}

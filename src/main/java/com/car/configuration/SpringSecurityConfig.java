package com.car.configuration;

import com.car.areas.user.services.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 03/04/2017
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicUserService basicUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.basicUserService).passwordEncoder(this.bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/connect/**", "/", "/register/**", "/bootstrap/**", "/jquery/**", "/js/**", "/css/*").permitAll()
//                .antMatchers("/cars/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        .and()
                .formLogin().loginPage("/login").permitAll()
                .passwordParameter("password")
                .usernameParameter("username")
        .and()
                .rememberMe()
                .rememberMeCookieName("RememberMeFromLecture")
                .rememberMeParameter("rememberMe").key("SecretKey").tokenValiditySeconds(1000)
        .and()
                .logout().logoutSuccessUrl("/login?logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
        .and()
                .exceptionHandling().accessDeniedPage("/unauthorized")
        .and()
                .csrf()
                .csrfTokenRepository(csrfTokenRepository());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public CsrfTokenRepository csrfTokenRepository(){
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}























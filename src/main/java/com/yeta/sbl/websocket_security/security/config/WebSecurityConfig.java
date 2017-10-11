package com.yeta.sbl.websocket_security.security.config;

import com.yeta.sbl.websocket_security.security.filter.MyUsernamePasswordAuthenticationFilter;
import com.yeta.sbl.websocket_security.security.service.SysUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.*;

/**
 * Created by Administrator on 2017-9-27.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注册CustomUserService的bean
     * @return
     */
    @Bean
    public UserDetailsService customUserService(){
        return new SysUserService();
    }

    /**
     * 登陆验证成功回调方法
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new SimpleUrlAuthenticationSuccessHandler("/login_success");
    }

    /**
     * 登陆验证失败回调方法
     * @return
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login_failure");
    }

    /**
     * 增加验证码验证
     * @return
     * @throws Exception
     */
    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception{
        MyUsernamePasswordAuthenticationFilter myFilter = new MyUsernamePasswordAuthenticationFilter();
        myFilter.setAuthenticationManager(authenticationManagerBean());
        myFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        myFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return myFilter;
    }

    /**
     * 添加自定义的userDetailService认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new Md5PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                    .authenticated()       //所有请求需要认证
                .and()
                //添加验证码验证
                .addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login_page"))
                .and()
                .formLogin()
                    .loginPage("/login_page")       //登陆页面的请求路径
                    .loginProcessingUrl("/login")       //登陆处理路径
                    .defaultSuccessUrl("/login_success", true)      //登陆验证成功路径
                    //.successHandler(authenticationSuccessHandler())     //登陆验证成功回调
                    .failureUrl("/login_failure")       //登陆验证失败路径
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/logout")       //退出登陆的请求路径
                    .logoutSuccessUrl("/login_page")        //退出登陆成功后路径
                    .permitAll();
        http.csrf()
                .disable();
    }

}

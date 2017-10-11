package com.yeta.sbl.websocket_security.security.filter;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017-9-28.
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //参数中的验证码
        String requestCode = "";
        Object requestCodeObject = request.getParameter("code");
        if(requestCodeObject != null){
            requestCode = requestCodeObject.toString();
        }
        //session中的验证码
        String sessionCode = "";
        Object sessionCodeObject = request.getSession().getAttribute("code");
        if(sessionCodeObject != null){
            sessionCode = sessionCodeObject.toString();
        }
        sessionCode = "1234";
        //验证
        if(requestCode == null || sessionCode == null || !requestCode.equals(sessionCode)){
            throw new AuthenticationServiceException("验证码错误！");
        }

        return super.attemptAuthentication(request, response);
    }

}

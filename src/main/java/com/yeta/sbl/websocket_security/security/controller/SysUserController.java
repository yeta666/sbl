package com.yeta.sbl.websocket_security.security.controller;

import com.yeta.sbl.websocket_security.security.model.Message;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆控制器
 * Created by Administrator on 2017-9-28.
 */
@RestController
public class SysUserController {

    /**
     * 访问登陆页面路径
     * @return
     */
    @GetMapping(value = "/login_page")
    public ModelAndView login_page(){
        return new ModelAndView("websocket_security/security/login.html");
    }

    /**
     * 访问主页面路径
     * @return
     */
    @GetMapping(value = "/index")
    public ModelAndView index(){
        return new ModelAndView("websocket_security/security/index.html");
    }

    /**
     * 登陆验证成功返回
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login_success", method = RequestMethod.GET)
    public Message login_success(HttpServletRequest request, HttpServletResponse response) {
        Message message = new Message();
        message.setSuccess(true);
        message.setMessage("登陆验证通过，即将跳转到主页！");
        message.setUrl("/index");
        return message;
    }

    /**
     * 登陆验证失败返回
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login_failure", method = RequestMethod.GET)
    public Message login_failure(HttpServletRequest request, HttpServletResponse response) {
        AuthenticationException authenticationException = (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        Message message = new Message();
        message.setSuccess(false);
        message.setMessage(authenticationException.getMessage());
        return message;
    }
}

package com.yeta.sbl.websocket_security.security.service;

import com.yeta.sbl.websocket_security.security.model.SysUser;
import com.yeta.sbl.websocket_security.security.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Administrator on 2017-9-27.
 */
public class SysUserService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser sysUser = sysUserRepository.findByUsername(username);
        if(sysUser == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return sysUser;
    }
}

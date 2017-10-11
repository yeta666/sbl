package com.yeta.sbl.websocket_security.security.repository;

import com.yeta.sbl.websocket_security.security.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017-9-27.
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);
}

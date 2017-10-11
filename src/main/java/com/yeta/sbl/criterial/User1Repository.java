package com.yeta.sbl.criterial;

import com.yeta.sbl.rest.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 基于准则的查询，即Criteria查询
 * Created by Administrator on 2017-9-18.
 */
public interface User1Repository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

}

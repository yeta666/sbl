package com.yeta.sbl.criterial;

import com.yeta.sbl.rest.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.yeta.sbl.criterial.UserSpecs.*;

/**
 * 测试使用
 * Created by Administrator on 2017-9-18.
 */
public class TestUserSpecs {

    @Autowired
    private User1Repository user1Repository;

    List<User> users = user1Repository.findAll(userIsMen());
}

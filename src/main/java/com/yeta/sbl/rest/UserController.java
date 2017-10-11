package com.yeta.sbl.rest;

import com.yeta.sbl.rest.User;
import com.yeta.sbl.rest.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017-9-18.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/findBySexSort")
    public List<User> findBySexSort(@RequestParam(value = "sex") String sex,
                                    @RequestParam(value = "sortMethod") String sortMethod){
        return userService.findBySexSort(sex, sortMethod);
    }

    @GetMapping(value = "/findBySexPage")
    public List<User> findBySexPage(@RequestParam(value = "sex") String sex,
                                    @RequestParam(value = "pageNum") Integer pageNum,
                                    @RequestParam(value = "pageSize") Integer pageSize){
        return userService.findBySexPage(sex, pageNum, pageSize);
    }

    @GetMapping(value = "/setName")
    public int setName(@RequestParam(value = "name") String name,
                       @RequestParam(value = "id") Long id){
        return userService.setName(name, id);
    }

    @GetMapping(value = "/findByAge")
    public List<User> findByAge(@RequestParam(value = "age") Integer age){
        return userService.findByAge(age);
    }

    @GetMapping(value = "/findByName")
    public List<User> findByName(@RequestParam(value = "name") String name){
        return userService.findByName(name);
    }

    @GetMapping(value = "/findByAgeAndSex")
    public List<User> findByAgeAndSex(@RequestParam(value = "age") Integer age,
                                      @RequestParam(value = "sex") String sex){
        return userService.findByAgeAndSex(age, sex);
    }

    @GetMapping(value = "/findTop10BySex")
    public List<User> findTop10BySex(@RequestParam(value = "sex") String sex){
        return userService.findTop10BySex(sex);
    }

    @GetMapping(value = "/findFirst10BySex")
    public List<User> findFirst10BySex(@RequestParam(value = "sex") String sex){
        return userService.findFirst10BySex(sex);
    }

    @GetMapping(value = "/save")
    public User save(@RequestParam(value = "age") Integer age,
                     @RequestParam(value = "sex") String sex,
                     @RequestParam(value = "name") String name){
        return userService.save(new User(name, age, sex));
    }

    @GetMapping(value = "/findOne")
    public User findOne(@RequestParam(value = "id") Long id){
        return userService.findOne(new User(id));
    }

    @GetMapping(value = "/remove")
    public void remove(@RequestParam(value = "id") Long id){
        userService.remove(id);
    }
}

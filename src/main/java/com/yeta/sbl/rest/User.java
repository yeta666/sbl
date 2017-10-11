package com.yeta.sbl.rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Created by Administrator on 2017-9-18.
 */
@Entity
@NamedQuery(name = "User.findByAgeAndSex", query = "select u from User u where u.age = ?1 and u.sex = ?2")
public class User {
    //
    @Id
    @GeneratedValue
    private Long id;
    //
    private String name;
    //
    private Integer age;
    //
    private String sex;

    public User() {
    }

    public User(Long id){
        this.id = id;
    }

    public User(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

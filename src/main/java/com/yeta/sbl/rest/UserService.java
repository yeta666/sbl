package com.yeta.sbl.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017-9-18.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据性别查询，结果根据年龄排序
     * @param sex
     * @param sortMethod
     * @return
     */
    public List<User> findBySexSort(String sex, String sortMethod) {

        List<User> userList;

        if (sortMethod.equals("ASC")) {
            userList = userRepository.findBySex(sex, new Sort(Sort.Direction.ASC, "age"));
        } else if (sortMethod.equals("DESC")) {
            userList = userRepository.findBySex(sex, new Sort(Sort.Direction.DESC, "age"));
        } else {
            userList = null;
        }

        return userList;
    }

    /**
     * 根据性别查询，结果分页
     * @param sex
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<User> findBySexPage(String sex, Integer pageNum, Integer pageSize){
        return userRepository.findBySex(sex, new PageRequest(pageNum, pageSize));
    }

    /**
     * 根据id更新name
     * @param name
     * @param id
     * @return
     */
    public int setName(String name, Long id){
        return userRepository.setName(name, id);
    }

    /**
     * 根据age查询
     * @param age
     * @return
     */
    public List<User> findByAge(Integer age){
        return userRepository.findByAge(age);
    }

    /**
     * 根据name查询
     * @param name
     * @return
     */
    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }

    /**
     * 根据age和sex查询
     * @param age
     * @param sex
     * @return
     */
    public List<User> findByAgeAndSex(Integer age, String sex){
        return userRepository.findByAgeAndSex(age, sex);
    }

    /**
     * 根据sex查询，结果前10条数据
     * @param sex
     * @return
     */
    public List<User> findTop10BySex(String sex){
        return userRepository.findTop10BySex(sex);
    }

    /**
     * 根据sex查询，结果前10条数据
     * @param sex
     * @return
     */
    public List<User> findFirst10BySex(String sex){
        return userRepository.findFirst10BySex(sex);
    }

    /**
     * 如果数据库中已存在新增用户，则回滚
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public User saveWithRollBack(User user){

        User newUser = userRepository.save(user);

        String name = user.getName();

        List<User> userList = userRepository.findByName(name);
        if(userList.size() >= 2){
            throw new IllegalArgumentException("姓名为：" + name + "的用户已存在！数据将回滚！");
        }

        return user;
    }

    /**
     * 将新增或更新的数据放到缓存
     * @param user
     * @return
     */
    @CachePut(value = "user", key = "#user.id")
    public User save(User user){
        User newUser = userRepository.save(user);
        System.out.println("为id为：" + newUser.getId() + "的数据做了缓存！");
        return newUser;
    }

    /**
     * 从缓存中删除，若没有指定key，则key为方法参数
     * @param id
     */
    @CacheEvict(value = "user")
    public void remove(Long id){
        userRepository.delete(id);
        System.out.println("删除了id为:" + id + "的数据缓存！");
    }

    /**
     * 将查询的数据放到缓存
     * @param user
     * @return
     */
    @Cacheable(value = "user", key = "#user.id")
    public User findOne(User user){
        User newUser = userRepository.findOne(user.getId());
        System.out.println("为id为：" + newUser.getId() + "的数据做了缓存！");
        return newUser;
    }

}

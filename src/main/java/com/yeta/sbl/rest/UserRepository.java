package com.yeta.sbl.rest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017-9-18.
 */
@RepositoryRestResource(path = "users")     //定制节点路径
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 获取符合条件的前10条数据
     * @param sex
     * @return
     */
    List<User> findFirst10BySex(String sex);
    List<User> findTop10BySex(String sex);

    /**
     * 使用namedQuery里面定义的查询，而不是根据方法名查询
     * @param age
     * @param sex
     * @return
     */
    List<User> findByAgeAndSex(Integer age, String sex);

    /**
     * 使用参数索引的@Query查询
     * @param name
     * @return
     */
    @Query("select u from User u where u.name = ?1")
    List<User> findByName(String name);

    /**
     * 使用命名参数的@Query查询
     * @param age
     * @return
     */
    @Query("select u from User u where u.age = :age")
    List<User> findByAge(@Param("age") Integer age);

    /**
     * 更新
     * @param name
     * @return
     */
    @Modifying
    @Transactional
    @Query("update User u set u.name = ?1 where u.id = ?2")
    int setName(String name, Long id);

    /**
     * 排序和分页
     * @param sex
     * @param sort
     * @return
     */
    List<User> findBySex(String sex, Sort sort);
    List<User> findBySex(String sex, Pageable pageable);

    /**
     *
     * @param id
     * @return
     */
    @RestResource(path = "findById", rel = "findById")
    List<User> findById(@Param("id") Long id);
}

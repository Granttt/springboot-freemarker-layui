package com.example.service;

import com.example.domain.Person;
import com.example.domain.User;
import com.example.domain.repository.TargetDataSource;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther: 高希阳
 * @Date: 2018/12/27 15:26
 * @Description: redis test
 * SpringBoot 使用 Redis 缓存(3)
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(String keyRedis){

        return userMapper.findById(keyRedis);
    }
    public List<User> findUserList(){

        return userMapper.findUserList();
    }
    /**
     * 指定数据源
     * @return
     */
    @TargetDataSource("ds1")
    public List<User> getListByDs1(){
        return userMapper.getListByDs1();
    }
    public int addUser(String name,String age){

        return userMapper.addUser(name,age);

    }
    public void updataById(String id, String name){

        userMapper.updataById(id,name);

    }
    public void deleteById(String keyRedis){

        userMapper.deleteById(keyRedis);

    }

    /**
     * 指定数据源
     * @return
     */
    @TargetDataSource("ds2")
    public List<Person> getListByDs2() {
        return userMapper.getListByDs2();
    }

    @TargetDataSource("ds3")
    public List<Person> getListByDs3() {
        return userMapper.getListByDs2();
    }

    /**
     * 方法上加@Transactional，声明一个事物
     */
    @TargetDataSource("ds2")
    @Transactional(rollbackFor = Exception.class)
    public void insertBack() {
        User user = new User();
        user.setId("3");
        user.setAge("11");
        user.setName("hsfsf");
        userMapper.insertUser(user);
        throw new RuntimeException();
    }
}

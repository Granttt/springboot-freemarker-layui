package com.example;

import com.example.domain.Person;
import com.example.domain.User;
import com.example.mapper.HotelDtoMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: gaoxi
 * @CreateDate: 2019/10/12 11:39
 * @Version: 1.0
 */
@RestController
public class DynamicDataSourceController {
    @Resource
    UserService userService;
    @Autowired
    HotelDtoMapper hotelDtoMapper;

    @RequestMapping("/ds1")
    public List<User> getUserList(){
        // 处理"/swagger/"的GET请求，用来获取用户列表
        List<User> userList = userService.getListByDs1();
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        return userList;
    }
    //测试mybaits
    @RequestMapping("/q6")
    public List<Person> q6(){
        return hotelDtoMapper.findListDs2();
    }
    @RequestMapping("/q7")
    public List<Person> q7(){
        return userService.getListByDs2();
    }
    @RequestMapping("/q8")
    public List<Person> q8(){
        return userService.getListByDs3();
    }

    /**
     * 分布式事务测试
     * https://blog.csdn.net/Coder_Qiang/article/details/80205784
     */
    @RequestMapping("/q9")
    public void insertBack(){
         userService.insertBack();
    }
}
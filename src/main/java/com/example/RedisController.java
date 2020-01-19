package com.example;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: 高希阳
 * @Date: 2018/12/27 15:42
 * @Description:redis test 实例
 * http://note.youdao.com/noteshare?id=31b68c9e157092ef2fddfe2513c0e435&sub=0B32FC8E708943A88F16676E127AC9FB
 * SpringBoot 使用 Redis 缓存(4)
 */
@RestController
public class RedisController {

    @Autowired
    private UserService userService;

    @RequestMapping("/adduser")
    public int addUser(@RequestParam("name")String name, @RequestParam("age")String age){

        return userService.addUser(name, age);

    }
    @RequestMapping("/findUser")
    public User findUser(@RequestParam("id") String id){

        return userService.findById(id);

    }

    @RequestMapping("/updataById")
    public String updataById(@RequestParam("id") String id,@RequestParam("name") String name){

        try {
            userService.updataById(id,name);

        } catch (Exception e) {

            return "error";

        }

        return "success";

    }



    @RequestMapping("/deleteById")
    public String deleteById(@RequestParam("id") String id){

        try {
            userService.deleteById(id);

        } catch (Exception e) {

            return "error";

        }

        return "success";

    }

}

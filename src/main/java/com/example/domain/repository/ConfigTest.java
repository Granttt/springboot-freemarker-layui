package com.example.domain.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: 高希阳
 * @Date: 2019/3/1 16:18
 * @Description:配置类，不做任何操作，只用于读取配置文件内容
 * https://blog.csdn.net/qq_31351071/article/details/81006760
 */
@RestController
@PropertySource(value = "classpath:diy.properties",encoding = "utf-8")
public class ConfigTest {

    @Value("${donghao1.name}")
    public String name;

    @Value("${donghao1.age}")
    public int age;

    @Value("${donghao1.sex}")
    public String sex;

    @RequestMapping("/configTest")
    public String print(){
        return "name:"+name+",age:"+age+".sex:"+sex;
//        System.out.println("name:"+name+",age:"+age+".sex:"+sex);
    }
}

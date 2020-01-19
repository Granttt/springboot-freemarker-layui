package com.example.springbootsamplehelloworld;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.Person;
import com.example.domain.User;
import com.example.mapper.RedisDao;
import com.example.service.ICacheService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @auther: 高希阳
 * @Date: 2018/12/26 13:38
 * @Description:
 * https://blog.csdn.net/forezp/article/details/70991675
 * SpringBoot非官方教程 | 第九篇： springboot整合Redis(2)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ListOperations<String, Object> listOperations;
    /**
     * jedis测试
     * -4
     */
    @Autowired
    private ICacheService iCacheService;

    public static Logger logger= LoggerFactory.getLogger(SpringbootRedisTests.class);

    private JSONObject json = new JSONObject();

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    @Autowired
    RedisDao redisDao;
    //redis测试
    //https://www.cnblogs.com/superfj/p/9232482.html
    @Test
    public void testRedis(){
        redisDao.setKey("name","forezp");
        redisDao.setKey("age","11");
        logger.info(redisDao.getValue("name"));
        logger.info(redisDao.getValue("age"));


    }

    /**
     * redis 存取list
     * @throws Exception
     * https://blog.csdn.net/weixin_37490221/article/details/78134748
     * https://blog.csdn.net/qq_31024823/article/details/81233752
     */
    @Test
    public void  ListOperations() throws Exception{
        User userVo = new User();
        userVo.setId("1");
        userVo.setName("jantent");
        userVo.setAge("23");
        User userVo1 = new User();
        userVo1.setId("2");
        userVo1.setName("高");
        userVo1.setAge("15");
        User userVo2 = new User();
        userVo2.setId("3");
        userVo2.setName("希阳");
        userVo2.setAge("34");

        //清空
        while (listOperations.size("ooo:user") > 0){
            listOperations.leftPop("ooo:user");
        }
        //存储
        listOperations.leftPush("ooo:user",userVo);
        listOperations.leftPush("ooo:user",userVo1);
        listOperations.leftPush("ooo:user",userVo2);

//        System.out.println(listOperations.leftPop("list:user"));
        // pop之后 值会消失
//        System.out.println(listOperations.leftPop("list:user"));

        //取出,获取列表指定范围内的元素range(start开始位置, 0是开始位置，end 结束位置, -1返回所有)
        List<User> oowwoo = redisTemplate.opsForList().range("ooo:user", 0, -1);
//        Iterator<User> it = oowwoo.iterator();
//        while (it.hasNext()) {
//            User next = it.next();
//            logger.info("User = {}", next.toString());
//        }
        for (User user : oowwoo) {
            logger.info("User = {}", user.getName());
        }
    }
    @Test
    public void redisSaveList() {
        List<Person> list = getPersonList();
        //清空
        while (redisTemplate.opsForList().size("oowwoo") > 0){
            redisTemplate.opsForList().leftPop("oowwoo");
        }
        //存储
        redisTemplate.opsForList().rightPushAll("oowwoo", list);

        //取出
        List<Person> oowwoo = redisTemplate.opsForList().range("oowwoo", 0, -1);
        logger.info(">>>>>>>>>>>>>>>list = {}", oowwoo.toString());
        Iterator<Person> it = oowwoo.iterator();
        while(it.hasNext()){
            Person p = it.next();
            logger.info("person = {}", p.toString());
        }
    }

    private List<Person> getPersonList() {
        Person p1 = new Person();
        p1.setId(1);
        p1.setName("张一");
        p1.setAge(11);

        Person p2 = new Person();
        p2.setId(2);
        p2.setName("张二");
        p2.setAge(22);

        Person p3 = new Person();
        p3.setId(3);
        p3.setName("张三");
        p3.setAge(33);

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        return list;
    }


    /**
     * 插入字符串
     */
    @Test
    public void setString() {
        iCacheService.set("redis_string_test", "springboot redis test");
    }

    /**
     * 获取字符串
     */
    @Test
    public void getString() {
        String result = iCacheService.get("redis_string_test");
        System.out.println(result);
    }
    /**
     * 插入对象
     */
    @Test
    public void setObject() {
        Person person = new Person(1,"嘻嘻",25,"山峰上发送进来几个零负担",null);
        iCacheService.set("redis_obj_test", json.toJSONString(person));
    }
    /**
     * 插入对象List
     */
    @Test
    public void setList() {
        Person person1 = new Person(1,"嘻嘻1",25,"山峰上",null);
        Person person2 = new Person(1,"嘻嘻2",26,"发送进来几个零负担",null);
        Person person3 = new Person(1,"嘻嘻3",27,"你我推心置腹，岂能相负",null);
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        iCacheService.set("redis_list_test", json.toJSONString(list));
    }
    /**
     * 获取list
     */
    @Test
    public void getList() {
        String result = iCacheService.get("redis_list_test");
        List<String> list = json.parseArray(result, String.class);
        System.out.println(list);
    }
    /**
     * 获取Object
     */
    @Test
    public void getObject() {
        Object result = iCacheService.get("redis_obj_test");
        JSONObject.toJavaObject((JSON) result,Person.class);
    }
    @Test
    public void remove() {
        iCacheService.remove("redis_test");
    }

}
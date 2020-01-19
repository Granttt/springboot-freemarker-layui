package com.testclass;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.Dog;
import com.example.domain.School;
import com.example.domain.UserDog;
import lombok.Data;
import lombok.var;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/6 19:53
 */
public class Java8Test {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        /**
         * spring BeanUtils.copyProperties("要转换的类","转换后的类")
         * 前赋值后：UserDog  -> dog
         * https://www.cnblogs.com/lilala-world/p/10443597.html
         */
        UserDog user = new UserDog();
        user.setName("人");
        user.setAge("23");
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        user.setList(list);

        List<School> schools = new ArrayList<>();
        School school = new School();
        school.setSchoolId("1");
        school.setSchoolName("北大");
        schools.add(school);
        School school1 = new School();
        school1.setSchoolId("2");
        school1.setSchoolName("清华");
        schools.add(school1);
        School school2 = new School();
        school2.setSchoolId("3");
        school2.setSchoolName("北航");
        schools.add(school);
        user.setSchools(schools);

        List<School> sdc = new ArrayList<>();
        Dog dog = new Dog();
//        dog.setName("狗");
//        dog.setAge("2");
        BeanUtils.copyProperties(user,dog);
        System.out.println(JSONObject.toJSON(dog));

//        BeanUtils.copyProperties(schools,sdc);
//        System.out.println(JSONObject.toJSON(sdc));

        /**
         * apache BeanUtils.copyProperties("转换后的类","要转换的类")
         * 后赋值前：dog -> UserDog
         * Spring与Apache中BeanUtils的区别
         * https://www.jianshu.com/p/581f831e0ffe
         */
//        org.apache.commons.beanutils.BeanUtils.copyProperties(dog,user);
//        System.out.println(JSONObject.toJSON(dog));
    }
}
class Main{
    public static void main(String[] args) {
        String[] names = {"Bob", "Alice", "Grace"};
        var sb = new StringBuilder();
        sb.append("Hello ");
        for (String name : names) {
            sb.append(name).append(", ");
        }
        //注意去掉最后的", ":
        sb.delete(sb.length() - 2,sb.length());
        sb.append("!");

        System.out.println(sb.toString());


        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        boolean aa = strs.stream().anyMatch(str -> str.equals("a"));
        boolean bb = strs.stream().allMatch(str -> str.equals("a"));
        boolean cc = strs.stream().noneMatch(str -> str.equals("a"));
        long count = strs.stream().filter(str -> str.equals("a")).count();
        System.out.println(aa);// TRUE
        System.out.println(bb);// FALSE
        System.out.println(cc);// FALSE
        System.out.println(count);// 4
    }

}

/**
 * Java 8 Optional类 菜鸟教程
 * https://www.runoob.com/java/java8-optional-class.html
 */
class OptionalTest{
    public static void main(String[] args) {

        OptionalTest java8Tester = new OptionalTest();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(java8Tester.sum(a,b));



        List<TaskProductOut> goods = new ArrayList<>();
        OptionalTest.TaskProductOut out1 =new OptionalTest.TaskProductOut();
        out1.setKid(1);
        out1.setPid(2);
        goods.add(out1);
        OptionalTest.TaskProductOut out2 =new OptionalTest.TaskProductOut();
        out2.setKid(3);
        out2.setPid(4);
        goods.add(out2);
        OptionalTest.TaskProductOut out3 =new OptionalTest.TaskProductOut();
        out3.setKid(5);
        out3.setPid(6);
        goods.add(out3);
        System.out.println(JSON.toJSONString(goods));

        /**
         * 将List集合用字符串，逗号隔开进行拼接
         * https://blog.csdn.net/albb_/article/details/83012139
         */
        //第一种方法(灵活，可以用逗号隔开，用|线隔开)
        String str = StringUtils.join(goods, "/");
        System.out.println("第一种方法" + str);

        /**
         * 如何使用Stream流操作将list中所有map的某个值拼接成字符串？
         * https://segmentfault.com/q/1010000019999754
         */
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> m = new HashMap();
        m.put("name", "樟树街");

        Map<String, String> m1 = new HashMap();
        m1.put("name", "樟树街1");

        Map<String, String> m2 = new HashMap();
        m2.put("name", "樟树街2");

        list.add(m);
        list.add(m2);
        list.add(m1);

        String s = goods.stream().map(p -> p.kid.toString()).collect(Collectors.joining("###"));
        String name = list.stream().map(p -> p.get("name")).collect(Collectors.joining(","));
        System.out.println(s);
        System.out.println(name);


        /**
         * 使用Java8的Stream对两个 List 遍历匹配数据的优化处理
         * https://segmentfault.com/q/1010000011689711
         */
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.add("f");
        Long count = 0L;
        count = list1.stream()
                .filter(string -> list2.stream().anyMatch(string2 -> string.equals(string2))).count();
//                .forEach(string -> {
//                    // to do sth
//                    System.out.println("------");
//                });
        System.out.println(count);

    }
    public Integer sum(Optional<Integer> a, Optional<Integer> b){
        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;


    }



    @Data
    static class TaskProductOut {
        // skuid
        private Integer kid;
        //spuid
        private Integer pid;
    }
}

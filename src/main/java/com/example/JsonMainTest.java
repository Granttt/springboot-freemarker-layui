package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.Emp;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gaoxi
 * @CreateDate: 2019/10/24 10:26
 * @Description:
 */
public class JsonMainTest {
    public static void main(String[] args) {
        Emp emp=new Emp();
        emp.setId(1);
        emp.setName("张三");
        emp.setGender("男");
        emp.setDptNo(001);
        emp.setDuty("职员");

        //Java对象转化为JSON对象
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(emp);
        System.out.println("Java对象转化为JSON对象\n" + jsonObject);

        //Java对象转换成JSON字符串
        String stuString = JSONObject.toJSONString(emp);
        System.out.println("Java对象转换成JSON字符串\n" + stuString);

        JSON.toJSONString("sdfs");

        int a = 123456;
        Integer b = 123456;
        System.out.println(a == b);
        System.out.println(b.equals(a));

        List<TaskProductOut> goods = new ArrayList<>();
        TaskProductOut out1 =new TaskProductOut();
        out1.setKid(1);
        out1.setPid(2);
        goods.add(out1);
        TaskProductOut out2 =new TaskProductOut();
        out2.setKid(3);
        out2.setPid(4);
        goods.add(out2);
        TaskProductOut out3 =new TaskProductOut();
        out3.setKid(5);
        out3.setPid(6);
        goods.add(out3);
        System.out.println(JSON.toJSONString(goods));


    }
@Data
static class TaskProductOut {
        // skuid
        private Integer kid;
        //spuid
        private Integer pid;
    }
}
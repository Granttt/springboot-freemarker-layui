package com.testclass;

import com.alibaba.fastjson.JSON;
import com.example.domain.School;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: gxy
 * @CreateDate: 2020/1/3 10:11
 * @Description:java8 stream流练习
 */
public class StreamTest {

    public static void main(String[] args) {
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
        schools.add(school2);

        System.out.println(JSON.toJSONString(schools));
        //stream().sorted()进行排序默认升序，reversed()为降序
        List<School> schoolAsc = schools.stream().sorted(Comparator.comparing(School::getSchoolId).reversed()).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(schoolAsc));

        long count = schools.stream().filter(school3 -> school3.getSchoolId().equals("10")).count();
        System.out.println(count);
    }
}
package com.example.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/23 20:13
 * @Description:
 */
@Data
public class Dog {
    private String name;
    private String age;
    private List<String> list;

    private List<School> schools;
}
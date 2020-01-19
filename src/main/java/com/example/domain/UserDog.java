package com.example.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/28 10:58
 * @Description:
 */
@Data
public class UserDog {
    private String id;
    private String name;
    private String age;

    private List<String> list;

    private List<School> schools;
}
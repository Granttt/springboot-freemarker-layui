package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @auther: 高希阳
 * @Date: 2018/12/27 15:21
 * @Description:
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 4845958743208947556L;
    @Id
    private String id;
    private String name;
    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/6/10 23:28
 * @Version: 1.0
 */
@Entity
@Table(name = "t_user")
public class TUser {
    private String id;
    @Column(name = "user_name")
    private String userName;
    private String password;

    @Id
    @Column(name = "id",unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
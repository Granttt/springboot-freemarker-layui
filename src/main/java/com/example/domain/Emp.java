package com.example.domain;

/**
 * @Author: gaoxi
 * @CreateDate: 2019/10/24 10:26
 * @Description:
 */
public class Emp {
    private Integer id;
    private String name;
    private Integer dptNo;
    private String gender;
    private String duty;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getDptNo() {
        return dptNo;
    }
    public void setDptNo(Integer dptNo) {
        this.dptNo = dptNo;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDuty() {
        return duty;
    }
    public void setDuty(String duty) {
        this.duty = duty;
    }

}
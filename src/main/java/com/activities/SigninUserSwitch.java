package com.activities;
import java.util.Date;
import java.sql.*;
import lombok.Data;

/**
 * @文件名称：SigninUserSwitch.java
 * @创建时间：2019-12-19 15:00:03
 * @创  建  人：gxy 
 * @文件描述：signin_user_switch 实体类
 * @文件版本：V0.01 
 */@Data
public class SigninUserSwitch{
	private Long id;	//主键id
	private Long userId;	//用户id
	private Date createTime;	//数据创建时间
	private Long createUser;	//数据创建人
	private Date updateTime;	//最后一次修改时间
	private Long updateUser;	//最后修改人
	private String tagStr1;	//
	private String tagStr2;	//
	private String tagStr3;	//
	private Short signinSwitch;	//签到开关:1 和 0,true和false
}


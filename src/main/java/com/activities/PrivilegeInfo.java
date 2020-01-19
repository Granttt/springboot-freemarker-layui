package com.activities;
import java.util.Date;
import java.sql.*;
import lombok.Data;

/**
 * @文件名称：PrivilegeInfo.java
 * @创建时间：2019-11-05 15:03:52
 * @创  建  人：gxy 
 * @文件描述：privilege_info 实体类
 * @文件版本：V0.01 
 */@Data
public class PrivilegeInfo{
	private Integer id;	//主键id
	private String name;	//特权名称
	private String showName;	//显示名称
	private Integer rank;	//等级
	private Long sellingPrice;	//售价
	private Integer indate;	//有效期 单位天 0为无限制(永久)
	private Date createTime;	//数据创建时间
	private Long createUser;	//数据创建人
	private Date updateTime;	//最后修改时间
	private Long updateUser;	//最后修改人
}


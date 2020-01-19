package com.activities;
import java.util.Date;
import java.sql.*;
import lombok.Data;

/**
 * @文件名称：PrivilegeRecord.java
 * @创建时间：2019-11-05 17:49:06
 * @创  建  人：gxy 
 * @文件描述：privilege_record 实体类
 * @文件版本：V0.01 
 */@Data
public class PrivilegeRecord{
	private Long id;	//主键id
	private Long userId;	//用户id
	private Integer privilegeId;	//特权id
	private Date expiryDate;	//过期时间
	private Integer rank;	//等级
	private Long sellingPrice;	//售价
	private Integer indate;	//有效期 单位天 0为无限制(永久)
	private String privilegeName;	//特权名称
	private String showName;	//显示名称
	private Date createTime;	//数据创建时间
	private Long createUser;	//数据创建人
	private Date updateTime;	//最后一次修改时间
	private Long updateUser;	//最后修改人
}


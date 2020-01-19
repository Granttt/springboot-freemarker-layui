package com.activities;
import java.util.Date;
import java.sql.*;
import lombok.Data;

/**
 * @文件名称：IntegralLotteryRecord.java
 * @创建时间：2019-11-20 16:05:45
 * @创  建  人：gxy 
 * @文件描述：integral_lottery_record 实体类
 * @文件版本：V0.01 
 */@Data
public class IntegralLotteryRecord{
	private Long id;	//主键id
	private Long userId;	//用户id
	private Integer lotteryLevel;	//奖项
	private Date lotteryTime;	//抽奖时间
	private Long consumePoints;	//消耗的积分
	private String creatUser;	//数据创建人
	private Date createTime;	//数据创建时间
	private Long createUser;	//数据创建人
	private Date updateTime;	//最后一次修改时间
	private Long updateUser;	//最后修改人
	private String ref1;	//
	private String ref2;	//
}


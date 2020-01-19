package com.activities;
import java.sql.*;
import lombok.Data;

   /**
    * @文件名称：ActSeckillTime.java
    * @创建时间：2019-10-29 14:05:04
    * @创  建  人：gxy 
    * @文件描述：act_seckill_time 实体类
    * @文件版本：V0.01 
    */ 


@Data
public class ActSeckillTime{
	private Integer id;	//时间段id
	private String aid;	//appid
	private String seckillTimeNo;	//时间段标号
	private String seckillTimeName;	//时间段名称
	private Date startTime;	//每日开始时间
	private Date endTime;	//每日结束时间
	private Integer seckillStatus;	//开启状态  0 未开始 1进行中 2 已结束 3已关闭
	private String note;	//备注
}


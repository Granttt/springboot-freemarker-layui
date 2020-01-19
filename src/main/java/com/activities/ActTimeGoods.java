package com.activities;
import lombok.Data;

import java.util.Date;

/**
 * @文件名称：ActTimeGoods.java
 * @创建时间：2019-11-01 10:19:35
 * @创  建  人：gxy 
 * @文件描述：act_time_goods 实体类
 * @文件版本：V0.01 
 */
@Data
public class ActTimeGoods{
	private Integer id;	//
	private String aid;	//appid
	private Integer actPeriodId;	//活动时间段关联表id
	private Integer goodsId;	//商品id
	private Date createTime;	//创建时间
	private Date updateTime;	//修改时间
}


package com.activities;
import lombok.Data;

import java.sql.Date;

   /**
    * @文件名称：ActUser.java
    * @创建时间：2019-10-28 19:32:24
    * @创  建  人：act_ambry 
    * @文件描述：act_user 实体类
    * @文件版本：V0.01 
    */

@Data
public class ActUser{
	private Integer id;	//id
	private String aid;	//
	private Integer actId;	//活动id
	private String userId;	//用户id
	private Integer isRemind;	//是否完成提醒
	private Integer needRemind;	//是否需要提醒
	private Date remindTime;	//提醒时间
	private Date sendTime;	//发送提醒时间
	private Integer goodsId;	//商品id
}


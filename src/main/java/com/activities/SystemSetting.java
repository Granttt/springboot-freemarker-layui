package com.activities;
import java.util.Date;
import lombok.Data;

/**
 * @文件名称：SystemSetting.java
 * @创建时间：2020-01-06 10:56:41
 * @创  建  人：gxy 
 * @文件描述：系统配置表实体类
 * @文件版本：V0.01 
 */@Data
public class SystemSetting{
	private Long id;
	/**
	 * 系统代码（不用）
	 */
	private String systemCode;
	/**
	 * 描述
	 */
	private String name;
	/**
	 * h5地址
	 */
	private String h5Www;
	/**
	 * oss地址
	 */
	private String ossPicWww;
	/**
	 * 注销开关:false-关闭，true-开启
	 */
	private String cancelSwitch;
	private String updateUser;
	private Date updateTime;
	private String tagStr1;
	private String tagStr2;
	private String tagStr3;
}


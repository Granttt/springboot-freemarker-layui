package com.example.domain;
import java.sql.*;

   /**
    * @文件名称：t_goods_act.java
    * @创建时间：2019-10-17 17:10:45
    * @创  建  人：Zero 
    * @文件描述：t_goods_act 实体类
    * @文件版本：V0.01 
    */ 


public class T_goods_act{
	private String goods_act_id;
	private String goods_id;
	private String act_id;
	private String goods_name;
	private String show_no;
	private String goods_no;
	private String real_price;
	private String act_price;
	private Date remind_time;
	private Integer is_remind;
	private Long to_limit;
	private Integer order;
	private Long goods_num;
	private Long left_num;
	private String ambry_id;
	private String rules_id;
	private String label;
	private String note;
	public void setGoods_act_id(String goods_act_id){
	this.goods_act_id=goods_act_id;
	}
	public String getGoods_act_id(){
		return goods_act_id;
	}
	public void setGoods_id(String goods_id){
	this.goods_id=goods_id;
	}
	public String getGoods_id(){
		return goods_id;
	}
	public void setAct_id(String act_id){
	this.act_id=act_id;
	}
	public String getAct_id(){
		return act_id;
	}
	public void setGoods_name(String goods_name){
	this.goods_name=goods_name;
	}
	public String getGoods_name(){
		return goods_name;
	}
	public void setShow_no(String show_no){
	this.show_no=show_no;
	}
	public String getShow_no(){
		return show_no;
	}
	public void setGoods_no(String goods_no){
	this.goods_no=goods_no;
	}
	public String getGoods_no(){
		return goods_no;
	}
	public void setReal_price(String real_price){
	this.real_price=real_price;
	}
	public String getReal_price(){
		return real_price;
	}
	public void setAct_price(String act_price){
	this.act_price=act_price;
	}
	public String getAct_price(){
		return act_price;
	}
	public void setRemind_time(Date remind_time){
	this.remind_time=remind_time;
	}
	public Date getRemind_time(){
		return remind_time;
	}
	public void setIs_remind(Integer is_remind){
	this.is_remind=is_remind;
	}
	public Integer getIs_remind(){
		return is_remind;
	}
	public void setTo_limit(Long to_limit){
	this.to_limit=to_limit;
	}
	public Long getTo_limit(){
		return to_limit;
	}
	public void setOrder(Integer order){
	this.order=order;
	}
	public Integer getOrder(){
		return order;
	}
	public void setGoods_num(Long goods_num){
	this.goods_num=goods_num;
	}
	public Long getGoods_num(){
		return goods_num;
	}
	public void setLeft_num(Long left_num){
	this.left_num=left_num;
	}
	public Long getLeft_num(){
		return left_num;
	}
	public void setAmbry_id(String ambry_id){
	this.ambry_id=ambry_id;
	}
	public String getAmbry_id(){
		return ambry_id;
	}
	public void setRules_id(String rules_id){
	this.rules_id=rules_id;
	}
	public String getRules_id(){
		return rules_id;
	}
	public void setLabel(String label){
	this.label=label;
	}
	public String getLabel(){
		return label;
	}
	public void setNote(String note){
	this.note=note;
	}
	public String getNote(){
		return note;
	}
}


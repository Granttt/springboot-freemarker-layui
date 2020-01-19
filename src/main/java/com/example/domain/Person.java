/**
 * 
 */
package com.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:Person 
 * @author 高希阳
 * @date 2018-7-3 上午10:51:54   
 * @version 1.0.0 
 * @Description: https://blog.csdn.net/ZChangfeng/article/details/50146187
 */
@Entity
@SequenceGenerator(name="SeqGen1",sequenceName="id")
public class Person {

	@Id
	@GeneratedValue(generator="SeqGen1")
	private Integer id;
	private String name;
	private String cityName;
	private Integer age;
	private String address;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	@Column(name = "school_id")
	private String schoolId;
	@ManyToOne
	@JoinColumn(name="school_id",insertable = false, updatable = false)
	private School school;
	
	public Person() {
		super();
	}
 
	public Person(Integer id, String name, Integer age, String address,String schoolId) {
		super();
		this.id = id;
		this.name = name;
		this.cityName = address;
		this.age = age;
		this.address = address;
	}

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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public School getSchool() {
		return school;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", cityName=" + cityName
				+ ", age=" + age + ", address=" + address + ", schoolId="
				+ schoolId + ", school=" + school + "]";
	}

}

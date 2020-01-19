/**
 * 
 */
package com.example.domain;

/**
 * @Project:spring-boot-sample-helloworld
 * @Class:Params
 * @author 高希阳
 * @date 2018-8-24 下午2:16:00   
 * @version 1.0.0
 * @Description: 封装查询条件的类
 */
public class Params {
	private String userId;
    private String userName;
    private String age;


    private String schoolName;
    private String schoolId;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	public String getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}


}

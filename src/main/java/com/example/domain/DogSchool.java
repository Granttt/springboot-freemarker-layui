/**
 * 
 */
package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:School 
 * @author 高希阳
 * @date 2018-8-23 下午3:13:39   
 * @version 1.0.0 
 * @Description: 
 */
@Entity
public class DogSchool {
	@Id
	private String schoolId;
	private String schoolName;
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	
}

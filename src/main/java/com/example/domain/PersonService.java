/**
 * 
 */
package com.example.domain;

import org.springframework.data.domain.Page;

import java.util.List;


/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:PersonService 
 * @author 高希阳
 * @date 2018-8-23 下午4:52:52   
 * @version 1.0.0 
 * @Description: 
 */
public interface PersonService {
	List<Person> findAll();
	
	List<Person> findAll(int pageNum, int pageSize,Person user);

	Page<Person> findAll(int pageNum, int pageSize, Params params);

	Person add(Person person);
}

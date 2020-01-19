/**
 * 
 */
package com.example;

import com.example.domain.Person;
import com.example.mapper.HotelDtoMapper;
import com.example.domain.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:DataController 
 * @author 高希阳
 * @date 2018-7-3 上午11:04:07   
 * @version 1.0.0 
 * @Description: 测试操作数据库
 */
@RestController
public class DataController {

	protected static Logger logger=LoggerFactory.getLogger(DataController.class);

	@Autowired
	PersonRepository personRepository;
	@Autowired
	HotelDtoMapper hotelDtoMapper;
	
	@RequestMapping("/save")
	public Person save(String name,String address,Integer age){
		logger.debug("save 开始");
		
		Person p=personRepository.save(new Person(null,name,age,address,null));
		logger.debug("save 结束");
		return p;
		
	}
	@RequestMapping("/q1")
	public List<Person> q1(String address){
		logger.debug("q1 开始");
		logger.debug("q1 接收参数address={}",address);
		List<Person> persons=personRepository.findByAddress(address);
		return persons;
	}
	@RequestMapping("/q2")
	public List<Person> q2(String name,String address){
		logger.debug("q2 开始");
		logger.debug("q2接收参数name={},address={}",name,address);
		return personRepository.findByNameAndAddress(name, address);
	}
	@RequestMapping("/q3")
	public List<Person> q3(String name,String address){
		logger.debug("q3 开始");
		logger.debug("q3接收参数name={},address={}",name,address);
		return personRepository.withNameAndAddressQuery(name, address);
	}
	@RequestMapping("/q4")
	public List<Person> q4(String name,String address){
		logger.debug("q4 开始");
		logger.debug("q4接收参数name={},address={}",name,address);
		Person person = new Person();
		person.setName(name);
		person.setAddress(address);
		return personRepository.withNameAndAddressQuery(person);
	}
	//测试mybaits
	@RequestMapping("/q5")
	public List<Person> q2(String name){
		logger.debug("q5 开始");
		logger.debug("q5接收参数name={}",name);
		return hotelDtoMapper.findByName(name);
	}
	//排序
	@RequestMapping("/sort")
	public List<Person> sort(){
		logger.debug("sort 开始");
		List<Person> people=personRepository.findAll(new Sort(Direction.ASC,"age"));
		return people;
	}
	//分页
	@RequestMapping("/page")
	public Page<Person> page(){
		logger.debug("page 开始");
		Page<Person> people=personRepository.findAll(new PageRequest(0,4));//PageRequest(页码,每页显示的条数)
		return people;
	}
}

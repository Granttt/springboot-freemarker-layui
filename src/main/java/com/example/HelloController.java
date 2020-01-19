/**
 * 
 */
package com.example;
import com.example.domain.School;

import com.example.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:HelloController 
 * @author 高希阳
 * @date 2017-9-28 上午9:49:19   
 * @version 1.0.0 
 */
@RestController
public class HelloController {
	
	protected static Logger logger=LoggerFactory.getLogger(HelloController.class);
	
	//获取实体的第一种写法：配置文件读取
	@Value("${cupSize}")
	private String cupSize;
	@Value("${age}")
	private Integer age;
	@Value("${content}")
	private String content;
	//获取实体第二种写法：实体注解映射
	@Autowired
	private BoyInfo boy;
	
	@RequestMapping("/")  
    public String helloworld(){  
		logger.debug("访问hello");
        return "Hello world GXY!";  
    }  

    @RequestMapping("/hello/{Name}")  
    public String hellName(@PathVariable("Name") String myName){  
    	logger.debug("访问helloName,Name={}",myName);
        return "Hello "+myName;  
    }

	/**
	 * 功能描述：多个url映射
	 * 用户hello或者hi任何一个访问都能够访问到这个方法
	 * @author gxy
	 * @date 2019/1/23 15:02
	 * @param
	 * @return
	 */
    @RequestMapping(value={"/hello","/hi"},method=RequestMethod.POST)
    public String gril(){
//		return content;
//		return cupSize+age;

		Person person =new Person();
		person.setId(0);
		person.setName("");
		person.setCityName("");
		person.setAge(0);
		person.setAddress("");
		person.setSchoolId("");
		person.setSchool(new School());



    	return boy.getName();

    }
}

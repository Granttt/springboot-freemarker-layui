/**
 *
 */
package com.example;

import com.example.domain.Params;
import com.example.domain.Person;
import com.example.domain.PersonService;
import com.example.domain.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project:spring-boot-sample-helloworld
 * @Class:PersonController
 * @author 高希阳
 * @date 2018-8-23 下午1:42:47
 * @version 1.0.0
 * @Description:
 */
@RestController
@RequestMapping(value = "person")
public class PersonController {
	protected static Logger logger=LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonRepository personRepository;
	@Autowired
	PersonService personService;

	/**
     * 查
     * @return
     */
    @GetMapping(value = "/list")
    public List<Person> getUserList(){
        return personRepository.findAll();
    }

    /**
     * 增
     * @author GXY
     * @param username
     * @param userage
     * @param useraddress
     * @param cname
     * @return
     */
    @PostMapping(value="/add")
    public Person addUser(@RequestParam("name")String username,@RequestParam("age")int userage,
    		@RequestParam("address")String useraddress,@RequestParam("cityname")String cname,
    		@RequestParam("schoolId")String schoolId){

    	Person person=new Person();
    	person.setName(username);
    	person.setAge(userage);
    	person.setAddress(useraddress);
    	person.setCityName(cname);
    	person.setSchoolId(schoolId);
    	return personRepository.save(person);
    }

    /**
     * 改
     * @author GXY
     * @param id
     * @param username
     * @param userage
     * @param useraddress
     * @param cname
     * @return
     */
    @PutMapping(value="update/{id}.html")
    public Person updUser(@PathVariable("id") Integer id,@RequestParam("name")String username,
    		@RequestParam("age")int userage,@RequestParam("schoolId")String sId,
    		@RequestParam("address")String useraddress,@RequestParam("cityname")String cname){

    	Person person=new Person();
    	System.out.println(person);
    	person.setId(id);

    	person.setName(username);
    	person.setAge(userage);
    	person.setAddress(useraddress);
    	person.setCityName(cname);
    	person.setSchoolId(sId);
    	return personRepository.save(person);
    }

    /**
     * 删
     * @author GXY
     * @param id
     */
    @DeleteMapping(value = "delete/{id}")
    public void delUser(@PathVariable("id") Integer id){
    	Person person=new Person();
    	person.setId(id);
    	personRepository.delete(person);
    }

    //查询User，单表，多条件
    @PostMapping(value = "/getUser/{pageNum}/{pageSize}")
    public List<Person> getUser(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize,
                               @RequestBody Person user) {
        List<Person> uList = personService.findAll(pageNum, pageSize, user);
        return uList;
    }

    //查询User，多表，多条件
    @PostMapping(value = "/getUser2/{pageNum}/{pageSize}")
    public List<Person> getUser2(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize,
                               @RequestBody Params params) {
        List<Person> uList = personService.findAll(pageNum, pageSize, params).getContent();
        return uList;
    }
    //查询User，多表，单条件
    @PostMapping(value = "/getUser3")
    public List<Person> getUser3(@RequestParam("schoolId")String cname) {
    	List<Person> uList = personRepository.findPersonList(cname);
    	return uList;
    }

    public static void main(String[] args) {
        String str="ruoyi/20190228/997c9df348454701bd3d9feb3532ef11.jpg";
        String subStr = str.substring(str.indexOf("{"),str.indexOf("}")+1);
        System.out.println(subStr);
//        System.out.println(str.substring(str.indexOf("/")));
    }
}

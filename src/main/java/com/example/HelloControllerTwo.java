/**
 * 
 */
package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:HelloControllerTwo 
 * @author 高希阳
 * @date 2018-7-2 上午11:00:40   
 * @version 1.0.0 
 * @Description: 
 */
@Controller
public class HelloControllerTwo {

	@RequestMapping(value = {"","/home"},method = RequestMethod.GET)
    public String home(Model model){
		List<String> list=new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		model.addAttribute("yu","Hello world Thymeleaf");
		model.addAttribute("list",list);
		
		Map user= new HashMap();  
        user.put("name", "姓名");  
        user.put("age", "年龄");  
        user.put("sex", "性别");  
        user.put("birthday", "生日");  
        user.put("phonenumber", "手机");  
        model.addAttribute("userhead", user);  
        List userinfo =new ArrayList();  
        userinfo.add("周美玲");  
        userinfo.add("32");  
        userinfo.add("女");  
        userinfo.add("1985");  
        userinfo.add("19850014665");  
        model.addAttribute("userinfo", userinfo); 
        
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("name", "name1");
        m.put("age", "1");
        list1.add(m);
         
        m = new HashMap<String, Object>();
        m.put("name", "name2");
        m.put("age", "2");
        list1.add(m);
         
        m = new HashMap<String, Object>();
        m.put("name", "name3");
        m.put("age", "3");
        list1.add(m);
         
        m = new HashMap<String, Object>();
        m.put("name", "name4");
        m.put("age", "4");
        list1.add(m);
         
        m = new HashMap<String, Object>();
        m.put("name", "name5");
        m.put("age", "5");
        list1.add(m);
        model.addAttribute("list1", list1);

		
        return "index"; //当浏览器输入/home时，会返回 /templates/index.html页面
    }
}

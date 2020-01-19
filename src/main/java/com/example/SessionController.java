/**
 * 
 */
package com.example;

import java.util.Arrays;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:SessionController 
 * @author 高希阳
 * @date 2018-8-30 上午11:37:32   
 * @version 1.0.0 session 用法
 * @Description: https://www.cnblogs.com/caoyc/p/5635914.html
 */
@Controller
@RequestMapping(value = "session")
@SessionAttributes(value={"names"},types={Integer.class})
public class SessionController {
	@RequestMapping("/test")
    public String test(Map<String,Object> map){
        map.put("names", Arrays.asList("caoyc","zhh","cjx"));
        map.put("age", 18);
        return "hello";
    }
}

/**
 * 
 */
package com.example;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:TestPerson 
 * @author 高希阳
 * @date 2017-10-26 下午12:13:32   
 * @version 1.0.0 
 */
@RestController
public class TestPerson {

	//绑定PersonalValidator
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
	    webDataBinder.addValidators(new PersonalValidto());
	}

	//直接返回对象
	@RequestMapping(value = "testPersonalValidtor.do")
	@ResponseBody
	public Object testPersonalValidtor(@Valid BoyInfo personScope, BindingResult bindingResult){
	    if(bindingResult.hasErrors()){
	        StringBuffer sb = new StringBuffer();
	        for(ObjectError objectError : bindingResult.getAllErrors()){
	            sb.append(((FieldError)objectError).getField() +" : ").append(objectError.getDefaultMessage());
	        }
	        return sb.toString();
	    }else{
	        return personScope;
	    }
	}

	public static void main(String[] args) {
		//运算时已经超过int的取值范围,没到10L时还是int类型，所以已经溢出了，
		long b =1000000000*3*10L;
		System.out.println(b);

		long c =1000000000L*3*10;
		System.out.println(c);
	}
}

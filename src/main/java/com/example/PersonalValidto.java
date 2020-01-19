/**
 * 
 */
package com.example;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:PersonalValidto 
 * @author 高希阳
 * @date 2017-10-26 下午12:01:43   
 * @version 1.0.0 
 */

public class PersonalValidto implements Validator{

	 /** 
     * 判断支持的JavaBean类型 
     * @param aClass 
     * @return 
     */  
	@Override
	public boolean supports(Class<?> aClass) {
		// TODO Auto-generated method stub
		return BoyInfo.class.equals(aClass);
	}

	/** 
     * 实现Validator中的validate接口 
     * @param obj 
     * @param errors 
     */  
	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		//把校验信息注册到Error的实现类里  
        ValidationUtils.rejectIfEmpty(errors,"name",null,"姓名不能为空!");  
        BoyInfo boyInfo=(BoyInfo) obj;
        if(StringUtils.isEmpty(boyInfo.getAge())){  
            errors.rejectValue("age",null,"年龄不能为空!!!!");  
        }  
	}

}

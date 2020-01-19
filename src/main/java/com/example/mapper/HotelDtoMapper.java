/**
 * 
 */
package com.example.mapper;

import com.example.domain.Person;
import com.example.domain.repository.TargetDataSource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:HotelDtoMapper 
 * @author 高希阳
 * @date 2018-7-3 下午1:49:11   
 * @version 1.0.0 
 * @Description: 
 */
@Mapper
public interface HotelDtoMapper {
	
	public List<Person> findByName(String country);

	@TargetDataSource("ds2")
	public List<Person> findListDs2();

}

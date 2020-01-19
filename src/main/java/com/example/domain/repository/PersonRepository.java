/**
 * 
 */
package com.example.domain.repository;

import com.example.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:PersonRepository 
 * @author 高希阳
 * @date 2018-7-3 上午10:59:21   
 * @version 1.0.0 
 * @Description: spring data jpa这里继承了JpaRepository类，其封装了一些对数据库操作的基本方法
 * http://www.cnblogs.com/javazhiyin/p/9297743.html
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	List<Person> findByName(String name);
	
	List<Person> findByAddress(String address);
	
	List<Person> findByNameAndAddress(String name,String address);
	
	@Query("select p from Person p where p.name=:name and p.address=:address")
	List<Person> withNameAndAddressQuery(@Param("name")String Name,@Param("address")String address);
	
	Page<Person> findAll(Specification<Person> spc, Pageable pageable);
	
	/**
	 * 在hql中p.schoolId这里schoolId写实体映射中的字段而不是数据库里的字段
	 * @author GXY
	 * @param Name
	 * @return
	 */
	@Query("select p from Person p inner join p.school where p.schoolId =:sclId")
	public List<Person> findPersonList(@Param("sclId")String Name);

	@Query("select p from Person p where p.name = :#{#person.name} and p.address = :#{#person.address}")
	List<Person> withNameAndAddressQuery(@Param("person")Person person);
}

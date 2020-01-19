/**
 * 
 */
package com.example.domain;

import com.example.domain.repository.PersonRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:PersonServiceImpl 
 * @author 高希阳
 * @date 2018-8-23 下午4:55:18   
 * @version 1.0.0 
 * @Description: 
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Resource
	private PersonRepository personRepository;
	
	/* (non-Javadoc)
	 * @see com.example.domain.PersonService#findAll()
	 */
	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	//查询User，单表，多条件
	@Override
	public List<Person> findAll(int pageNum, int pageSize, final Person user) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(pageNum, pageSize);
        List<Person> uList = personRepository.findAll(new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (user.getId() != null && !user.getId().equals("")) {
                    predicates.add(cb.like(root.get("id").as(String.class), "%" + user.getId() + "%"));
                }
                if (user.getName() != null && !user.getName().equals("")) {
                	//模糊查询
                    //predicates.add(cb.like(root.get("name").as(String.class), "%" + user.getName() + "%"));
                	//精准查询
                    //predicates.add(cb.equal(root.get("name"), user.getName()));
                    //将字符串转换成大写 upperCase
                	String flag=StringUtils.upperCase(StringUtils.trim(user.getName()));
                    predicates.add(cb.like(cb.upper(root.get("name").as(String.class)),  "%" +flag+ "%"));
                }
                if (user.getAge() != null && !user.getAge().equals("")) {
                    //查询大于输入年龄的数据
                    predicates.add(cb.gt(root.get("age").as(Integer.class), user.getAge()));
                   
                }
                Predicate[] pre = new Predicate[predicates.size()];
                criteriaQuery.where(predicates.toArray(pre));
                return cb.and(predicates.toArray(pre));
            }
        }, pageable).getContent();

        return uList;
	}

	/* (non-Javadoc)
	 * @see com.example.domain.PersonService#findAll(int, int, com.mysql.fabric.xmlrpc.base.Params)
	 * 查询person，多表，多条件
	 */
	@Override
	public Page<Person> findAll(int pageNum, int pageSize, final Params params) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(pageNum, pageSize);

		Page<Person> uList = personRepository.findAll(new Specification<Person>() {
	            @Override
	            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
	                List<Predicate> list = new ArrayList<>();
	                //根据userId 查询user
	                if (StringUtils.isNotBlank(params.getUserId())) {
	                    list.add(cb.equal(root.get("id").as(String.class), params.getUserId()));
	                }
	                //根据userName 模糊查询user
	                if (StringUtils.isNotBlank(params.getUserName())) {
	                    list.add(cb.like(root.get("name").as(String.class), "%" + params.getUserName() + "%"));
	                }
	                //根据age>? 查询user
	                if (StringUtils.isNotBlank(params.getAge())) {
	                    list.add(cb.gt(root.get("age").as(Integer.class), Integer.valueOf(params.getAge())));
	                }
	                //根据schoolId 查询user
	                if (StringUtils.isNotBlank(params.getSchoolId())) {
	                	//LEFT JOIN关联
	                    Join<School, Person> join = root.join("school", JoinType.LEFT);
	                    list.add(cb.equal(join.get("schoolId"), params.getSchoolId()));
	                }
	                if (StringUtils.isNotBlank(params.getSchoolName())) {
	                	//LEFT JOIN关联
	                    Join<School, Person> join = root.join("school", JoinType.LEFT);
	                    list.add(cb.like(join.get("schoolName"), "%" + params.getSchoolName() + "%"));
	                }
	                Predicate[] pre = new Predicate[list.size()];
	                criteriaQuery.where(list.toArray(pre));
	                return cb.and(list.toArray(pre));
	            }
	        }, pageable);
		
		return uList;
	}

	@Override
	public Person add(Person person) {
		if (StringUtils.isNotBlank(String.valueOf(person.getId()))){
			System.out.println("修改的id为："+String.valueOf(person.getId()));
		}
		return personRepository.save(person);
	}

}

package com.example.mapper;

import com.example.domain.TUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/6/10 23:33
 * @Version: 1.0
 */
/**
 * mapper的具体表达式
 * 不加注解会报错：
 * Unsatisfied dependency expressed through field 'tUserDao'; nested exception is org.springframework.beans.factory.BeanCreationException:
 * Error creating bean with name 'TUserDao': Invocation of init method failed; nested exception is java.util.NoSuchElementException
 */
@Repository
public interface TUserDao extends JpaRepository<TUser,String> {

    /**
     * 登录
     * @param user
     * @return
     * https://blog.csdn.net/byteArr/article/details/80955703
     * JpaRepository查询
     * https://blog.csdn.net/ming070423/article/details/22086169
     */
//    @Query(value = "select u.id from t_user u where u.username = #{username} and password = #{password}")
    @Query(value = "select u from TUser u where u.userName = :#{#user.userName} and u.password = :#{#user.password}")
    List<TUser> findByUserNameAndPassword(@Param("user")TUser user);

    @Query(value = "select u.id from t_user u where u.userName = ?1 and u.password = ?2",nativeQuery = true)
    String findByUsernameAndPassword(String username,String password);





    //jpa 示范
//    @Query("select u from User u where u.age = ?#{[0]}")
//    List<User> findUsersByAge(int age);
//   https://spring.io/blog/2014/07/15/spel-support-in-spring-data-jpa-query-definitions
//    @Query("select u from User u where u.firstname = :#{#customer.firstname}")
//    List<User> findUsersByCustomersFirstname(@Param("customer") Customer customer);

    //报错：t_user is not mapped [from
    //原因：hibernate 使用的是hql语句而不是sql语句，from t_user中的t_user应该是实体类而不是数据库中的表。我把t_user更改成写的实体类User就不在报错了。

    //报错：nvalidDataAccessApiUsageException: Name for parameter binding must not be null or empty! On JDKs < 8,you need to use @Param for named parameters
    //原因： @Query中若使用":参数"的形式，必须搭配@Param使用
//    @Query(value = "select u.id from t_user u where u.username = :username and u.password = :password",nativeQuery = true)
//    String findByUsernameAndPassword(@Param("username") String username,(@Param("password")String password);
}

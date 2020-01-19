package com.example;

import com.example.domain.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @auther: 高希阳
 * @Date: 2019/3/5 10:47
 * @Description:springboot2.x整合swagger2
 * https://blog.csdn.net/qq_38157516/article/details/82378638
 * https://blog.csdn.net/ysk_xh_521/article/details/80633141
 * 访问路径：
 *
 * http://localhost:端口号/swagger-ui.html
 */
@Api(value="/test1", tags="测试接口模块")
@RestController
@RequestMapping("swagger")
public class Swagger2TestController {

    @Autowired
    UserService userService;

    //创建线程安全的Map
    static Map<String, User> users = Collections.synchronizedMap(new HashMap<String, User>());


    @ApiOperation(value = "获取用户列表",notes = "方法的备注说明")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User>getUserList(){
        // 处理"/swagger/"的GET请求，用来获取用户列表
        List<User> userList = userService.findUserList();
        for (User user : userList) {
            users.put(user.getId(),user);
        }
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<>(users.values());
        return r;
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable String id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息

        // url中的id可通过@PathVariable绑定到函数的参数中
        return (User)users.get(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新的对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "String"),
            @ApiImplicitParam(name = "user",value = "用户详细实体user",required = true,dataType = "User")
    })
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String putUser(@PathVariable String id, @ModelAttribute User user){
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);





    Map map = new HashMap();
    map.put("user","/uploadImg/图片名");





        return "success";
    }
}

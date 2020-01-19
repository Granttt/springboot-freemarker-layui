/**
 * 
 */
package com.example.springbootsamplehelloworld;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.SpringBootSampleHelloworldApplication;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:ApplicationTests 
 * @author 高希阳
 * @date 2017-12-11 下午12:18:29   
 * @version 1.0.0
 * 使用MockMvc对controller进行测试
 * https://www.cnblogs.com/qlong8807/p/7121522.html
 * https://blog.csdn.net/sun_t89/article/details/52185952
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = MockServletContext.class)//这个测试单个controller，不建议使用
@SpringBootTest(classes = SpringBootSampleHelloworldApplication.class)//这里的Application是springboot的启动类名。
@WebAppConfiguration
public class ApplicationTests {
  @Autowired
  private WebApplicationContext context;
  private MockMvc mvc;
  
  @Before
  public void setUp() throws Exception {
//       mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
      mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
  }
  @Test
  public void test1() throws Exception {
    /**
     * 1、mockMvc.perform执行一个请求。
     * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
     * 3、ResultActions.param添加请求传值
     * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
     * 5、ResultActions.andExpect添加执行完成后的断言。
     * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
     *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
     * 5、ResultActions.andReturn表示执行完成后返回相应的结果。
     */

    mvc.perform(MockMvcRequestBuilders.get("/data/getMarkers") //调用接口
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .param("lat", "123.123").param("lon", "456.456")//传入添加的用户参数
              .accept(MediaType.APPLICATION_JSON))//接收的类型
              .andExpect(MockMvcResultMatchers.status().isOk())//判断接收到的状态是否是200
              .andDo(MockMvcResultHandlers.print())//打印内容
              .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));//匹配返回值中的内容
      
  }
}

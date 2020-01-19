package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.domain.PageDTO;
import com.example.domain.Params;
import com.example.domain.Person;
import com.example.domain.PersonService;
import com.example.domain.repository.PersonRepository;
import com.example.domain.repository.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: gxy
 * @CreateDate: 2020/1/9 15:31
 * @Description:
 */
@Controller
@RequestMapping("/fm")
public class FreemarkerController {

    @Autowired
    PersonService personService;
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/test")
    public String testFreemarker(ModelMap modelMap){
        modelMap.addAttribute("msg", "Hello gaoxiyang , this is freemarker");
        return "indexftl";
    }
    @GetMapping("/list")
    @ResponseBody
    public PageDTO listUI(HttpServletRequest request, HttpServletResponse response,Params params){
        int page = Integer.parseInt(request.getParameter("curr"));
        int limit = Integer.parseInt(request.getParameter("nums"));
        List<Person> uList0 = personService.findAll();

        Page<Person> pageList = personService.findAll(page-1,limit,params);
        List<Person> uList = pageList.getContent();
        PageDTO pageDTO = new PageDTO();
        try{
            pageDTO.setCode(0);
            pageDTO.setCount((int) pageList.getTotalElements());
            pageDTO.setData(uList);
        }catch(Exception e){
            pageDTO.setCode(201);
            pageDTO.setMsg("系统异常");
        }
        return pageDTO;
    }
    @RequestMapping("/demo2")
    public String listUI2(ModelMap modelMap){

//        List<Person> uList = personService.findAll();
//        modelMap.addAttribute("list",uList);
        return "ftl/layuidemo";
    }
    /**
     * @MethodName addUI
     * @Description  编辑弹框
     * @param modelAndView
     * @date 2019/4/26 15:12
     * @return org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/editUI")
    public ModelAndView updateUI(@RequestParam("id") String id,ModelAndView modelAndView) {
        Person person = personRepository.findOne(Integer.valueOf(id));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("person",person);
        modelAndView.addObject("person",person);
        modelAndView.setViewName("ftl/adddemo");
        return modelAndView;
    }
    /**
     * @MethodName addUI
     * @Description  编辑弹框
     * @param modelAndView
     * @date 2019/4/26 15:12
     * @return org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/editUI2")
    public ModelAndView updateUI2(@RequestParam("id") String id,ModelAndView modelAndView) {
        Person person = personRepository.findOne(Integer.valueOf(id));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("person",person);
        modelAndView.addObject("person",person);
        modelAndView.setViewName("ftl/editdemo");
        return modelAndView;
    }

    @GetMapping("/addUI")
    public ModelAndView addUI(ModelAndView modelAndView) {
        modelAndView.setViewName("ftl/adddemo");
        return modelAndView;
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(HttpServletRequest request,Person person) {
        System.out.println("添加");
        Person person1 = personService.add(person);
        System.out.println(JSON.toJSONString(person1));
        Result pageRel = new Result();
        pageRel.setCode(1001);
        pageRel.setSuccess(true);
        pageRel.setMsg("添加操作成功！");
        return pageRel;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("id") String id, HttpServletRequest request) {
        System.out.println(id);
//        personRepository.delete(Integer.valueOf(id));
        Result pageRel = new Result();
        pageRel.setSuccess(true);
        pageRel.setMsg("删除操作成功！");
        return pageRel;
    }
}
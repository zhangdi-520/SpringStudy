package com.example.springboot.tacos;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.querydsl.service.DslUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @program: Spring
 * @description: 一个控制类的小案例
 * @author: Mr.Zhang
 * @create: 2020-05-13 11:13
 **/
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    DslUserService dslUserService;

    @GetMapping("/")
    public String home(){
        dslUserService.test();
        System.out.println("aaa");
        return "home";
    }


    /**
     * @Description:远程调用方法测试
     * @Param: [request]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: Mr.Wang
     * @Date: 2020/5/15
     */
     @RequestMapping(value = "data", method = { RequestMethod.GET,RequestMethod.POST })
     @ResponseBody
     public JSONObject test(HttpServletRequest request)throws Exception {
             JSONObject jsonObj = new JSONObject();
             Map<String,Object> map = new HashMap<String,Object>();
             map.put("firstName", "jack");
             map.put("isexist", "1");
             jsonObj.putAll(map);
             return jsonObj;
     }



}

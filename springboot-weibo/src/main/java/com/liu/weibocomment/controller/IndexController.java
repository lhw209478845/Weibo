package com.liu.weibocomment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/toUserInfo")
    public String toUserInfo(){
        return "user/userInfo";
    }



}

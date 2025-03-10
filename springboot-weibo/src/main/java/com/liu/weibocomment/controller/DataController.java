package com.liu.weibocomment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data")
public class DataController {

    @RequestMapping("/data")
    public String toData(){
        return "data/data";
    }


}

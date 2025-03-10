package com.liu.weibocomment.controller;

import com.liu.weibocomment.entity.PhoneAge;
import com.liu.weibocomment.entity.PhoneBrand;
import com.liu.weibocomment.service.PhoneBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/brand")
public class PhoneBrandController {
    @Autowired
    private PhoneBrandService phoneBrandService;

    @RequestMapping("/queryBrand")
    @ResponseBody
    private List<PhoneBrand> queryBrand(){
        List<PhoneBrand> phoneBrandList = new ArrayList<>();
        phoneBrandList = phoneBrandService.list();
        return phoneBrandList;
    }

    @RequestMapping("/queryNameValue")
    @ResponseBody
    private List<Map<String,String>> queryNameValue(){
        List<PhoneBrand> phoneBrandList = new ArrayList<>();
        phoneBrandList = phoneBrandService.list();
        List<Map<String,String>> mapList = new ArrayList<>();
        for(int i = 0; i < phoneBrandList.size(); i++){
            PhoneBrand phoneBrand = new PhoneBrand();
            phoneBrand = phoneBrandList.get(i);
            Map<String,String> map = new HashMap<>();
            map.put("name",phoneBrand.getName());
            map.put("value", String.valueOf(phoneBrand.getValue()));
            mapList.add(map);
        }
        return mapList;
    }

    @RequestMapping("/queryAge")
    @ResponseBody
    private List<PhoneBrand> querySex(){
        List<PhoneBrand> phoneSexList = new ArrayList<>();
        phoneSexList = phoneBrandService.list();
        return phoneSexList;
    }
}

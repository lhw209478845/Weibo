package com.liu.weibocomment.controller;

import com.liu.weibocomment.entity.PhoneProvince;
import com.liu.weibocomment.entity.PhoneProvinceValue;
import com.liu.weibocomment.service.PhoneProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/province")
public class PhoneProvinceController {

    @Autowired
    private PhoneProvinceService phoneProvinceService;


    @RequestMapping("/query")
    @ResponseBody
    private List<Map<String,String>> queryProvince(){
        List<Map<String,String>> mapList = new ArrayList<>();
        List<PhoneProvince> phoneProvinceList = new ArrayList<>();
        phoneProvinceList = phoneProvinceService.list();
        for(int i = 0; i < phoneProvinceList.size(); i++){
            PhoneProvince phoneProvince = phoneProvinceList.get(i);
            Map<String,String> map = new HashMap<>();
            map.put("name",phoneProvince.getName());
            map.put("value", String.valueOf(phoneProvince.getValue()));
            mapList.add(map);
        }
        return mapList;
    }

    @RequestMapping("/queryProvinceValue")
    @ResponseBody
    private List<PhoneProvinceValue> queryProvinceValue(){
        List<PhoneProvince> phoneProvinceList = new ArrayList<>();
        phoneProvinceList = phoneProvinceService.listOrderById10();
        List<PhoneProvinceValue> mapList = new ArrayList<>();
        for(int i = 0; i < phoneProvinceList.size(); i++){
            PhoneProvince phoneProvince = phoneProvinceList.get(i);
            PhoneProvinceValue phoneProvinceValue = new PhoneProvinceValue();
            phoneProvinceValue.setName(phoneProvince.getName());
            phoneProvinceValue.setValue(String.valueOf(phoneProvince.getValue()));
            mapList.add(phoneProvinceValue);
        }
        return mapList;
    }
}

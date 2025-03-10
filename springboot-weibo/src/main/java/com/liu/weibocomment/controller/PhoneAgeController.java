package com.liu.weibocomment.controller;

import com.liu.weibocomment.entity.PhoneAge;
import com.liu.weibocomment.service.PhoneAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/time")
public class PhoneAgeController {
    @Autowired
    private PhoneAgeService phoneAgeService;

    @RequestMapping("/queryTime")
    @ResponseBody
    private List<Integer> queryAge(){
        List<Integer> list = new ArrayList<>();
        List<PhoneAge> phoneAgeList = new ArrayList<>();
        phoneAgeList = phoneAgeService.list();
        for(int i = 0; i < phoneAgeList.size(); i++){
            int time1 = phoneAgeList.get(i).getTime1();
            int time2 = phoneAgeList.get(i).getTime2();
            int time3 = phoneAgeList.get(i).getTime3();
            int time4 = phoneAgeList.get(i).getTime4();
            int time5 = phoneAgeList.get(i).getTime5();
            int time6 = phoneAgeList.get(i).getTime6();
            int time7 = phoneAgeList.get(i).getTime7();
            int time8 = phoneAgeList.get(i).getTime8();
            int time9 = phoneAgeList.get(i).getTime9();
            int time10 = phoneAgeList.get(i).getTime10();
            int time11 = phoneAgeList.get(i).getTime11();
            int time12 = phoneAgeList.get(i).getTime11();
            list.add(time1);
            list.add(time2);
            list.add(time3);
            list.add(time4);
            list.add(time5);
            list.add(time6);
            list.add(time7);
            list.add(time8);
            list.add(time9);
            list.add(time10);
            list.add(time11);
            list.add(time12);
        }
        return list;
    }

    @RequestMapping("/queryManAge")
    @ResponseBody
    private List<Integer> queryManAge(){
        List<Integer> mapList = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            Random random = new Random();
            int t = random.nextInt(100000)+60000;
            mapList.add(t);
        }
        return mapList;
    }

    @RequestMapping("/queryWomenAge")
    @ResponseBody
    private List<Integer> queryWomen(){
        List<Integer> mapList = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            Random random = new Random();
            int t = random.nextInt(100000)+60000;
            mapList.add(t);
        }
        return mapList;
    }
}

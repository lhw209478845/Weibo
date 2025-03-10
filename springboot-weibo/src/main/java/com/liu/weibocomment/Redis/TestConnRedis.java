package com.liu.weibocomment.Redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class TestConnRedis {
    public static void main(String[] args) {
        //连接本地的Redis服务
        Jedis jedis = new Jedis("localhost");

        //如果Redis服务设置了密码。需要下面这行，没有就不需要
        //jedis.auth("123456")
//
//        System.out.println("连接成功");
//        System.out.println("服务正在运行：" + jedis.ping());

        //1、String类型[确诊：22]
        jedis.set("nocv","Coding");
        System.out.println(jedis.get("nocv"));

        //2、List集合【新闻列表】
        //存储数据到列表中
        jedis.lpush("siteList","Runoob");
        jedis.lpush("siteList","Google");
        jedis.lpush("siteList","Taobao");
        //获取存储的数据并输出
        List<String> siteList = jedis.lrange("siteList",0,2);
        for(int i = 0; i < siteList.size();i++){
            System.out.println("列表项为：" + siteList.get(i));
        }

        //3、无序集合
        jedis.sadd("nocvSet","111");
        jedis.sadd("nocvSet","222");
        jedis.sadd("nocvSet","333");
        jedis.sadd("nocvSet","111");
        jedis.sadd("nocvSet","111");
        Set<String> nocvSet = jedis.smembers("nocvSet");
        for(String s:nocvSet){
            System.out.println(s);
        }

        //4、有序集合
        jedis.zadd("nocvZet",21,"1111");
        jedis.zadd("nocvZet",45,"2222");
        jedis.zadd("nocvZet",64,"3333");
        jedis.zadd("nocvZet",78,"4444");
        jedis.zadd("nocvZet",95,"5555");
        Set<String> nocvZet = jedis.zrange("nocvZet", 0, -1);
        for(String s:nocvZet){
            System.out.println(s);
        }

        Long nocvZet1 = jedis.zrank("nocvZet", "3333");
        System.out.println(nocvZet1);

        System.out.println("========================");
        //返回分数区间里的数
        Long nocvZet2 = jedis.zremrangeByScore("nocvZet", 60, 100);
        System.out.println(nocvZet2);

        System.out.println("========================");
        //返回有序集中，成员的分数值
        Double nocvZet3 = jedis.zscore("nocvZet", "2222");
        System.out.println(nocvZet3);
    }
}

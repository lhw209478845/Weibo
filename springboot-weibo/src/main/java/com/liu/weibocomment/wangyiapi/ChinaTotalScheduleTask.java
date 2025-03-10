package com.liu.weibocomment.wangyiapi;

import org.springframework.stereotype.Component;

@Component
public class ChinaTotalScheduleTask {

    /**
     * 每小时执行一次
     * @throws Exception
     */
//    @Scheduled(fixedDelay = 1000000) //1000s执行一次
    public void updateChinaTotalToDB() throws Exception{
        HttpUtils httpUtils = new HttpUtils();
        String string = httpUtils.getData();
        System.out.println(string);
//        //1、所有数据的alibaba格式
//        JSONObject jsonObject = JSONObject.parseObject(string);
//        Object data = jsonObject.get("data");
//
//        //2、data
//        JSONObject jsonObjectData = jsonObject.parseObject(data.toString());
//        Object chinaTotal = jsonObjectData.get("chinaTotal");
//        Object lastUpdateTime = jsonObjectData.get("overseaLastUpdateTime");
//
//        //3、total 全中国整体疫情数据
//        JSONObject jsonObjectTotal = JSONObject.parseObject(chinaTotal.toString());
//        Object total = jsonObjectTotal.get("total");
//
//        //4、全国数据total
//        JSONObject totalData = JSONObject.parseObject(total.toString());
//        Object confirm = totalData.get("confirm");
//        Object input = totalData.get("input");
//        Object severe = totalData.get("severe");
//        Object heal = totalData.get("heal");
//        Object dead = totalData.get("dead");
//        Object suspect = totalData.get("suspect");
//
//        ChinaTotal dataEntity = new ChinaTotal();
//        dataEntity.setConfirm((Integer) confirm);
//        dataEntity.setInput((Integer) input);
//        dataEntity.setSevere((Integer) severe);
//        dataEntity.setHeal((Integer) heal);
//        dataEntity.setDead((Integer) dead);
//        dataEntity.setSuspect((Integer) suspect);
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        dataEntity.setUpdateTime(format.parse(String.valueOf(lastUpdateTime)));
//
//        //6、插入数据库
//        chinaTotalService.save(dataEntity);
//
//        7、删除缓存【非常重要】mysql -- redis 数据一致性的套路
//        Jedis jedis = new Jedis("localhost");
//        if(jedis!=null){
//            jedis.flushDB();
//        }
    }

    public static void main(String[] args) throws Exception {
        ChinaTotalScheduleTask chinaTotalScheduleTask = new ChinaTotalScheduleTask();
        chinaTotalScheduleTask.updateChinaTotalToDB();
    }

}

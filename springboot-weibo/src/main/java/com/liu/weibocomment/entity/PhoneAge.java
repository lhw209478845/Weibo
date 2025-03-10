package com.liu.weibocomment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("weibo_time")
public class PhoneAge {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer time1;
    private Integer time2;
    private Integer time3;
    private Integer time4;
    private Integer time5;
    private Integer time6;
    private Integer time7;
    private Integer time8;
    private Integer time9;
    private Integer time10;
    private Integer time11;
    private Integer time12;
}

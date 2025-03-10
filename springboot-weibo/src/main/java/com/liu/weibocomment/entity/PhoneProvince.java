package com.liu.weibocomment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("weibo_province")
public class PhoneProvince {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer value;
    private String num1;
    private String num2;
    private String num3;
    private String num4;
    private String num5;
}

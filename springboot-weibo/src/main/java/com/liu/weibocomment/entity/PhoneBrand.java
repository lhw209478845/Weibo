package com.liu.weibocomment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("phone_brand")
public class PhoneBrand {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer value;
    private String comment;
    private Integer man;
    private Integer women;
}

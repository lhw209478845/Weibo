package com.liu.weibocomment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("word")
public class Word {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer value;
    private String link;
    private String img;
}

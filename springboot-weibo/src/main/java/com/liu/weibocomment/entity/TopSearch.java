package com.liu.weibocomment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("top_search")
public class TopSearch {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String content;
    private Integer contentHeat;
    private String appear;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}

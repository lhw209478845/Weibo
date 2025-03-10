package com.liu.weibocomment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.weibocomment.entity.Word;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface WordMapper extends BaseMapper<Word> {

    @Select("select * from word order by value DESC LIMIT 50")
    List<Word> listLimit50();

    @Select("select * from word order by value DESC")
    List<Word> listOrderByValue();

    @Select("select * from word order by value DESC LIMIT 5")
    List<Word> listOrderByValue5();

    @Select("select * from word order by value DESC LIMIT 20")
    List<Word> listOrderByValue10();
}

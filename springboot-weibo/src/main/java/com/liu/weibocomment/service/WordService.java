package com.liu.weibocomment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.weibocomment.entity.Word;

import java.util.List;
import java.util.Map;

public interface WordService extends IService<Word> {
    List<Word> listLimit50();

    List<Word> listOrderByValue();

    List<Word> listOrderByValue5();

    List<Word> listOrderByValue10();
}

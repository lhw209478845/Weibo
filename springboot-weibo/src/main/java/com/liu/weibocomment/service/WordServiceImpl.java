package com.liu.weibocomment.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.weibocomment.entity.Word;
import com.liu.weibocomment.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WordServiceImpl extends ServiceImpl<WordMapper, Word> implements WordService{

    @Autowired
    private WordMapper wordMapper;

    @Override
    public List<Word> listLimit50() {
        return wordMapper.listLimit50();
    }

    @Override
    public List<Word> listOrderByValue() {
        return wordMapper.listOrderByValue();
    }

    @Override
    public List<Word> listOrderByValue5() {
        return wordMapper.listOrderByValue5();
    }

    @Override
    public List<Word> listOrderByValue10() {
        return wordMapper.listOrderByValue10();
    }
}

package com.liu.weibocomment.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.weibocomment.entity.TopSearch;
import com.liu.weibocomment.mapper.TopSearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopSearchServiceImpl extends ServiceImpl<TopSearchMapper, TopSearch> implements TopSearchService{
    @Autowired
    private TopSearchMapper topSearchMapper;
    @Override
    public List<TopSearch> listTopSearchsLimit10() {
        return topSearchMapper.listTopSearchsLimit10();
    }

    @Override
    public List<TopSearch> listTopSearchsLimit30() {
        return topSearchMapper.listTopSearchsLimit30();
    }
}

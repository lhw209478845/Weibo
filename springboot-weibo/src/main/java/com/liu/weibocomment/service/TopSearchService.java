package com.liu.weibocomment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.weibocomment.entity.TopSearch;

import java.util.List;

public interface TopSearchService extends IService<TopSearch> {
    List<TopSearch> listTopSearchsLimit10();

    List<TopSearch> listTopSearchsLimit30();
}

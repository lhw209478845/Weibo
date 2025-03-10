package com.liu.weibocomment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.weibocomment.entity.TopSearch;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TopSearchMapper extends BaseMapper<TopSearch> {
    @Select("select * from top_search order by create_time desc, id asc limit 10")
    List<TopSearch> listTopSearchsLimit10();

    @Select("select * from top_search order by create_time desc, id asc limit 100")
    List<TopSearch> listTopSearchsLimit30();
}

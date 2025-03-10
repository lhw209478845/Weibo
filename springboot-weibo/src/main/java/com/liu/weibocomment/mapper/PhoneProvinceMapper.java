package com.liu.weibocomment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.weibocomment.entity.PhoneProvince;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PhoneProvinceMapper extends BaseMapper<PhoneProvince> {
    @Select("SELECT * FROM weibo_province WHERE id BETWEEN 6010 AND 6043 ORDER BY VALUE DESC ")
    List<PhoneProvince> listOrderById34();

    @Select("SELECT * FROM weibo_province WHERE id BETWEEN 6010 AND 6043 ORDER BY VALUE DESC ")
    List<PhoneProvince> listOrderById10();
}

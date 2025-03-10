package com.liu.weibocomment.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.weibocomment.entity.PhoneProvince;
import com.liu.weibocomment.mapper.PhoneProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneProvinceServiceImpl extends ServiceImpl<PhoneProvinceMapper, PhoneProvince> implements PhoneProvinceService{
    @Autowired
    private PhoneProvinceMapper phoneProvinceMapper;
    @Override
    public List<PhoneProvince> listOrderById34() {
        return phoneProvinceMapper.listOrderById34();
    }

    @Override
    public List<PhoneProvince> listOrderById10() {
        return phoneProvinceMapper.listOrderById10();
    }
}

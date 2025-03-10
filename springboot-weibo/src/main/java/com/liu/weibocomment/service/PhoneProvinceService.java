package com.liu.weibocomment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.weibocomment.entity.PhoneProvince;

import java.util.List;

public interface PhoneProvinceService extends IService<PhoneProvince> {
    List<PhoneProvince> listOrderById34();

    List<PhoneProvince> listOrderById10();
}

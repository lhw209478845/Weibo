package com.liu.weibocomment.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.weibocomment.entity.PhoneBrand;
import com.liu.weibocomment.mapper.PhoneBrandMapper;
import org.springframework.stereotype.Service;

@Service
public class PhoneBrandServiceImpl extends ServiceImpl<PhoneBrandMapper, PhoneBrand> implements PhoneBrandService {
}

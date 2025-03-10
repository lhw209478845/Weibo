package com.liu.weibocomment.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.weibocomment.entity.PhoneAge;
import com.liu.weibocomment.mapper.PhoneAgeMapper;
import org.springframework.stereotype.Service;

@Service
public class PhoneAgeServiceImpl extends ServiceImpl<PhoneAgeMapper, PhoneAge> implements PhoneAgeService{
}

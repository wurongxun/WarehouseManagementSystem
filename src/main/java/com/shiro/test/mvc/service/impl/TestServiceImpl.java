package com.shiro.test.mvc.service.impl;

import com.shiro.test.mvc.dao.testTimeMapper;
import com.shiro.test.mvc.pojo.testTime;
import com.shiro.test.mvc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    com.shiro.test.mvc.dao.testTimeMapper testTimeMapper;

    @Override
    public List<testTime> queryTime() {
        return testTimeMapper.queryTime() ;
    }
}

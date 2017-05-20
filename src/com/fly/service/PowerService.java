package com.fly.service;

import java.util.List;

import com.fly.model.Power;

public interface PowerService {
    int deleteByPrimaryKey(String powerId);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(String powerId);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);
    
    int updateByPowerList(List<Power> record);
}

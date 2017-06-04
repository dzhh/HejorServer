package com.fly.service;

import com.fly.model.Paylist;

public interface PaylistService {

    int deleteByPrimaryKey(String transactionId);

    int insert(Paylist record);

    int insertSelective(Paylist record);

    Paylist selectByPrimaryKey(String transactionId);

    int updateByPrimaryKeySelective(Paylist record);

    int updateByPrimaryKey(Paylist record);
}

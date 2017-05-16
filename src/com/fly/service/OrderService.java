package com.fly.service;

import java.util.List;

import com.fly.model.Order;

public interface OrderService {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    Order selectByPowerId(String powerId);

    List<Order> selectUnfinishedByUserId(String userId);
    Order selectUnPayByUserId(String userId);

}

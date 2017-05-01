package com.fly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.mapping.OrderMapper;
import com.fly.model.Order;
import com.fly.service.OrderService;

@Service
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class OrderServiceImpl implements OrderService{
	
	@Resource
	private OrderMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String orderId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Order record) {
		// TODO Auto-generated method stub
		int s = mapper.insert(record);
		return s;
	}

	@Override
	public int insertSelective(Order record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order selectByPrimaryKey(String orderId) {
		// TODO Auto-generated method stub
		Order order = mapper.selectByPrimaryKey(orderId);
		return order;
	}

	@Override
	public int updateByPrimaryKeySelective(Order record) {
		// TODO Auto-generated method stub
		int s = mapper.updateByPrimaryKeySelective(record);
		return s;
	}

	@Override
	public int updateByPrimaryKey(Order record) {
		// TODO Auto-generated method stub
		int s = mapper.updateByPrimaryKey(record);
		return s;
	}

}

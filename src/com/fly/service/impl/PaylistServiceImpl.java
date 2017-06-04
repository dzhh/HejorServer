package com.fly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.mapping.PaylistMapper;
import com.fly.model.Paylist;
import com.fly.service.PaylistService;

@Service("PaylistService")
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class PaylistServiceImpl implements PaylistService {

	@Resource
	private PaylistMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String transactionId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Paylist record) {
 
		int s = mapper.insert(record);
		return s;
	}

	@Override
	public int insertSelective(Paylist record) {
		int s = mapper.insertSelective(record);
		return s;
	}

	@Override
	public Paylist selectByPrimaryKey(String transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Paylist record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Paylist record) {
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.fly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.mapping.PowerMapper;
import com.fly.model.Power;
import com.fly.service.PowerService;

@Service("PowerService")
@Transactional
public class PowerServiceImpl implements PowerService {
	@Resource
	private PowerMapper mapper;
	@Override
	public int deleteByPrimaryKey(String powerId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Power record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Power record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Power selectByPrimaryKey(String powerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Power record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Power record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPowerList(List<Power> record) {
		// TODO Auto-generated method stub
		int s = mapper.updateByPowerList(record);
		return s;
	}

}

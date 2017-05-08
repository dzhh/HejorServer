package com.fly.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.mapping.MachineMapper;
import com.fly.model.Machine;
import com.fly.service.MachineService;

@Service("machineServiceImpl")
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class MachineServiceImpl implements MachineService{

	@Resource
	private MachineMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String mId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Machine record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Machine record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Machine selectByPrimaryKey(String mId) {
		// TODO Auto-generated method stub
		Machine machine = mapper.selectByPrimaryKey(mId);
		return machine;
	}

	@Override
	public int updateByPrimaryKeySelective(Machine record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Machine record) {
		// TODO Auto-generated method stub
		return 0;
	}

}

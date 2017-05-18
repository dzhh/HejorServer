package com.fly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.mapping.M2PowerMapper;
import com.fly.model.M2Power;
import com.fly.model.Power;
import com.fly.service.M2PowerService;

@Service("M2PowerService")
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class M2PowerServiceImpl implements M2PowerService {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(M2Power record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(M2Power record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public M2Power selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(M2Power record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(M2Power record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByM_Id(String m_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByM_IdAndC_id(M2Power record) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public List<M2Power> selectByM_Id(String m_id) {
		// TODO Auto-generated method stub
		List<M2Power> mPower = mapper.selectByM_Id(m_id);
		return mPower;
	}


	@Resource
	private M2PowerMapper mapper;


	@Override
	public M2Power selectByM_IdAndC_Id(Integer c_id, String m_id) {
		// TODO Auto-generated method stub
		M2Power mPower = mapper.selectByM_IdAndC_Id(c_id, m_id);
		return mPower;
	}
}

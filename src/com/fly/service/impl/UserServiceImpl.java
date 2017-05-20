package com.fly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.mapping.UserMapper;
import com.fly.model.User;
import com.fly.service.UserService;

/**
 * @author 作者 fly
 * @version 创建时间：2015年11月17日 下午5:32:11
 * 类说明
 */

@Service("UserService")
@Transactional  //此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String userId) {
		return 0;
	}

	@Override
	public int insertSelective(User record) {
		return 0;
	}

	@Override
	public User selectByPrimaryKey(String userId) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return 0;
	}

	@Override
	public User find(String userId) {
		User user = mapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public List<User> findAll() {
		return mapper.findAll();
	}

	@Override
	public int insert(User record) {
		return 0;
	}

	@Override
	public List<User> selectUserList(User user) {
		return null;
	}

	
	
	
//	public User find(String userId) {
//		User user = mapper.selectByPrimaryKey(1);
//		return user;
//	}
//	
//	public User selectByUserName(String username) {
//		User user = mapper.selectByUserName(username);
//		return user;
//	}
//	
//	@Override
//	public List<User> findAll() {
//		List<User> findAllList = mapper.findAll();
//		return findAllList;
//	}
//	
//	@Override
//	public int insert(User record) {
//		return mapper.insert(record);
//	}
//
//	@Override
//	public int deleteByPrimaryKey(Integer userId) {
//		return mapper.deleteByPrimaryKey(userId);
//	}
//
//	@Override
//	public int insertSelective(User record) {
//		return mapper.insertSelective(record);
//	}
//
//	@Override
//	public User selectByPrimaryKey(Integer userId) {
//		return mapper.selectByPrimaryKey(userId);
//	}
//
//	@Override
//	public int updateByPrimaryKeySelective(User record) {
//		return mapper.updateByPrimaryKeySelective(record);
//	}
//
//	@Override
//	public int updateByPrimaryKey(User record) {
//		return mapper.updateByPrimaryKey(record);
//	}
//
//	@Override
//	public List<User> selectUserList(User user) {
//		return mapper.selectUserList(user);
//	}

}

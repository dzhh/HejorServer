package com.fly.service;

import java.util.List;

import com.fly.model.User;


/**
 * @author 作者 fly
 * @version 创建时间：2015年11月17日 下午5:30:55
 * 类说明
 */
public interface UserService {
	
	int deleteByPrimaryKey(Integer userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
	
	User find(Integer userId);
	
	User selectByUserName(String username);
	
	List<User> findAll();
	
	int insert(User record);

	List<User> selectUserList(User user);

}

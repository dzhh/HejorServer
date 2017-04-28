package com.fly.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fly.model.User;

public interface UserMapper {
	
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
//    User selectByUserName(String username);
   
    List<User> findAll();
//	List<User> selectUserList(@Param(value = "user")User user);
}
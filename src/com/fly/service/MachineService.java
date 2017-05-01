package com.fly.service;
import com.fly.model.Machine;

public interface MachineService {

    int deleteByPrimaryKey(String mId);

    int insert(Machine record);

    int insertSelective(Machine record);

    Machine selectByPrimaryKey(String mId);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);
}

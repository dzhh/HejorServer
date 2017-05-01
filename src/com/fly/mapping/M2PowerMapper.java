package com.fly.mapping;

import java.util.List;

import com.fly.model.M2Power;

public interface M2PowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(M2Power record);

    int insertSelective(M2Power record);

    M2Power selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(M2Power record);

    int updateByPrimaryKey(M2Power record);
    
    List<M2Power> selectByM_Id(String m_id);
}
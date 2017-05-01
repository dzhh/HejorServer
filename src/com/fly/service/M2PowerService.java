package com.fly.service;

import java.util.List;

import com.fly.model.M2Power;
import com.fly.model.Power;

public interface M2PowerService {

    int deleteByPrimaryKey(Integer id);

    int insert(M2Power record);

    int insertSelective(M2Power record);

    M2Power selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(M2Power record);

    int updateByPrimaryKey(M2Power record);
    
    int deleteByM_Id(String m_id);
    int updateByM_IdAndC_id(String m_id, Integer c_id);
    List<M2Power> selectByM_Id(String m_id);

}

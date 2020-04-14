package com.xmr.bbs.dao;

import com.xmr.bbs.modal.Integral;
import com.xmr.bbs.modal.IntegralExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralMapper {
    int countByExample(IntegralExample example);

    int deleteByExample(IntegralExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Integral record);

    int insertSelective(Integral record);

    List<Integral> selectByExample(IntegralExample example);

    Integral selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Integral record, @Param("example") IntegralExample example);

    int updateByExample(@Param("record") Integral record, @Param("example") IntegralExample example);

    int updateByPrimaryKeySelective(Integral record);

    int updateByPrimaryKey(Integral record);
}
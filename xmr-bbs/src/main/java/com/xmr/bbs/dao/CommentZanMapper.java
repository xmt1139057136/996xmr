package com.xmr.bbs.dao;

import com.xmr.bbs.modal.CommentZan;
import com.xmr.bbs.modal.CommentZanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentZanMapper {
    int countByExample(CommentZanExample example);

    int deleteByExample(CommentZanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentZan record);

    int insertSelective(CommentZan record);

    List<CommentZan> selectByExample(CommentZanExample example);

    CommentZan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommentZan record, @Param("example") CommentZanExample example);

    int updateByExample(@Param("record") CommentZan record, @Param("example") CommentZanExample example);

    int updateByPrimaryKeySelective(CommentZan record);

    int updateByPrimaryKey(CommentZan record);
}
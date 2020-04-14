package com.xmr.bbs.service;

import com.xmr.bbs.dto.CommentDTO;
import com.xmr.bbs.modal.Comment;

import java.util.List;

public interface CommentService {

    void doComment(Comment comment);

    List<CommentDTO> findSecondComments(Integer id);
}

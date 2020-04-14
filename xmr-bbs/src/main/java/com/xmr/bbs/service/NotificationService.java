package com.xmr.bbs.service;

import com.xmr.bbs.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> list(Integer pageNo, Integer pageSize, Integer id);
}

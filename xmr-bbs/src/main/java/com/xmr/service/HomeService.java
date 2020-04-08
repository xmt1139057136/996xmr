package com.xmr.service;

import com.xmr.mapper.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeService {
    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);
    @Autowired
    private MenuMapper menuMapper;

    public List<Map> getMenus(){
        return menuMapper.getMenus();
    }
}

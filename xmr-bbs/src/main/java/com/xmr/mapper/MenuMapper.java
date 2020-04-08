package com.xmr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {
    @Select("SELECT * FROM menu ")
    List<Map> getMenus();
}

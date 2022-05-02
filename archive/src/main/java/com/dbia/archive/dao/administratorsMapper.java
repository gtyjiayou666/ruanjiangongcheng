//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.dao;

import com.dbia.archive.model.administrators;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface administratorsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(administrators record);

    int insertSelective(administrators record);


    administrators selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(administrators record);

    int updateByPrimaryKey(administrators record);

    int selectid(String fullname);

    String selectpassword(int id);

    void updatepassword(int id, String pass_word);
}

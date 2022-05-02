package com.dbia.archive.dao;

import com.dbia.archive.model.file_inf;
import com.dbia.archive.model.pro_ma;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface pro_maMapper {
    List<pro_ma> select(int userid);

    void delete(int userid);

    void insert(pro_ma record);

    List<pro_ma> selecttrue(int userid, int dirid, int cho);
}

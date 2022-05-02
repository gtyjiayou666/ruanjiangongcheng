//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.dao;

import com.dbia.archive.model.file_inf;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface file_infMapper {
    int insert(file_inf record);

    void diriddelete(int dir_id);

    List<file_inf> dir_idselect(int dir_id);

    file_inf select(int dir_id, String filename);

    void delete(int id);

    List<file_inf> useridselect(int dir_id, int userid);
}

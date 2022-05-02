package com.dbia.archive.service;

import com.dbia.archive.model.file_inf;

import java.util.List;

public interface file_infService {
    /**
     * 插入file
     * @param record
     * @return
     */
    int insert(file_inf record);

    /**
     * 删除
     * @param dir_id
     */
    void diriddelete(int dir_id);

    /**
     * 查文件
     * @param dir_id
     * @return
     */
    List<file_inf> dir_idselect(int dir_id);

    /**
     * 找id
     * @param dir_id
     * @param filename
     * @return
     */
    file_inf select(int dir_id, String filename);

    /**
     * 删除此文件
     * @param id
     */
    void delete(int id);

    List<file_inf> useridselect(int dir_id, int userid);
}

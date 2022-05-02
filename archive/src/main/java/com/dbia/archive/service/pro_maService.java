package com.dbia.archive.service;

import com.dbia.archive.model.pro_ma;

import java.util.List;

public interface pro_maService {
    /**
     * 查找
     * @param userid
     * @return
     */
    List<pro_ma> select(int userid);

    /**
     * 删除
     * @param userid
     */
    void delete(int userid);

    /**
     * 插入
     * @param record
     */
    void insert(pro_ma record);

    /**
     * 查有没有
     * @param userid
     * @param dirid
     * @return
     */
    List<pro_ma> selecttrue(int userid, int dirid, int cho);
}

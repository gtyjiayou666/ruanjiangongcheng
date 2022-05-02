package com.dbia.archive.service;

import com.dbia.archive.model.study_group;

import java.util.List;

public interface study_groupService {
    /**
     * 查询小组
     * @return
     */
    List<study_group> select();

    /**
     * 加小组
     */
    void addgroup(int id,int num);

    int maxid();
}

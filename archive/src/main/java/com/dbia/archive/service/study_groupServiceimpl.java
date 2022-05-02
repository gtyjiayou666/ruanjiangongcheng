package com.dbia.archive.service;

import com.dbia.archive.dao.study_groupMapper;
import com.dbia.archive.model.study_group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class study_groupServiceimpl implements study_groupService{
    @Autowired
    private study_groupMapper study_groupMapper;
    @Override
    public List<study_group> select() {
        return study_groupMapper.select();
    }

    @Override
    public void addgroup(int id,int num) {
        study_groupMapper.addgroup(id,num);
    }

    @Override
    public int maxid() {
        return study_groupMapper.maxid();
    }
}

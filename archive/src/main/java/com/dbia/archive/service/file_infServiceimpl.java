package com.dbia.archive.service;

import com.dbia.archive.dao.file_infMapper;
import com.dbia.archive.model.file_inf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class file_infServiceimpl implements file_infService{
    @Autowired
    private com.dbia.archive.dao.file_infMapper file_infMapper;
    @Override
    public int insert(file_inf record) {
        return file_infMapper.insert(record);
    }

    @Override
    public void diriddelete(int dir_id) {
        file_infMapper.diriddelete(dir_id);
    }

    @Override
    public List<file_inf> dir_idselect(int dir_id) {
        return file_infMapper.dir_idselect(dir_id);
    }

    @Override
    public file_inf select(int dir_id, String filename) {
        return file_infMapper.select(dir_id,filename);
    }

    @Override
    public void delete(int id) {
        file_infMapper.delete(id);
    }

    @Override
    public List<file_inf> useridselect(int dir_id, int userid) {
        return file_infMapper.useridselect(dir_id,userid);
    }
}

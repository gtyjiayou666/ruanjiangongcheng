package com.dbia.archive.service;

import com.dbia.archive.dao.administratorsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminServiceimpl implements adminService{
    @Autowired
    private administratorsMapper administratorsMapper;
    @Override
    public int selectid(String fullname) {
        return administratorsMapper.selectid(fullname);
    }

    @Override
    public String selectpassword(int id) {
        return administratorsMapper.selectpassword(id);
    }

    @Override
    public void updatepassword(int id, String pass_word) {
        administratorsMapper.updatepassword(id,pass_word);
    }
}

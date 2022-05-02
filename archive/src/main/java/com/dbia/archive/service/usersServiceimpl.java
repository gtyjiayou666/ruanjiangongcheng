package com.dbia.archive.service;

import com.dbia.archive.dao.usersMapper;
import com.dbia.archive.model.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class usersServiceimpl implements usersService{

    @Autowired
    private usersMapper usersMapper;
    @Override
    public int selectid(String fullname) {
        return usersMapper.selectid(fullname);
    }

    @Override
    public String selectpassword(int id) {
        return usersMapper.selectpassword(id);
    }

    @Override
    public int selectgroup_id(int id) {
        return usersMapper.selectgroup_id(id);
    }

    @Override
    public String selectname(int id) {
        return usersMapper.selectname(id);
    }

    @Override
    public void insert(users record) {
        usersMapper.insert(record);
    }

    @Override
    public List<users> selectall() {
        return usersMapper.selectall();
    }

    @Override
    public users nameselect(String name) {
        return usersMapper.nameselect(name);
    }

    @Override
    public void update(int id, String pass_word, int group_id, boolean group_leader) {
        usersMapper.update(id,pass_word,group_id,group_leader);
    }

    @Override
    public void delete(int id) {
        usersMapper.delete(id);
    }

    @Override
    public void updatepassword(int id, String pass_word) {
        usersMapper.updatepassword(id,pass_word);
    }
    @Override
    public int[] groupselectid(int groupid)
    {
        return usersMapper.groupselectid(groupid);
    }

    @Override
    public boolean selectgroupleader(int id) {
        return usersMapper.selectgroupleader(id);
    }

    @Override
    public users idselect(int id) {
        return usersMapper.idselect(id);
    }
}

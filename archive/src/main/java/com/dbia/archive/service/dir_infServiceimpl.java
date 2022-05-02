package com.dbia.archive.service;

import com.dbia.archive.dao.dir_infMapper;
import com.dbia.archive.model.dir_inf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dir_infServiceimpl implements dir_infService{
    @Autowired
    private dir_infMapper dir_infMapper;

    @Override
    public void diriddelete(int dir_id) {
        dir_infMapper.diriddelete(dir_id);
    }

    @Override
    public int selectid(String dirname, int group_id) {
        return dir_infMapper.selectid(dirname,group_id);
    }

    @Override
    public int statusselectid(int status, int group_id) {
        return dir_infMapper.statusselectid(status,group_id);
    }

    @Override
    public int[] selectparentid(int id) {
        return dir_infMapper.selectparentid(id);
    }

    @Override
    public String selectname(int id) {
        return dir_infMapper.selectname(id);
    }

    @Override
    public List<dir_inf> groupselect(int group_id) {
        return dir_infMapper.groupselect(group_id);
    }

    @Override
    public String selecttruepath(int id) {
        return dir_infMapper.selecttruepath(id);
    }

    @Override
    public String selectpath(int id) {
        return dir_infMapper.selectpath(id);
    }

    @Override
    public int insert(dir_inf dir) {
        return dir_infMapper.insert(dir);
    }

    @Override
    public dir_inf idselect(int id){
        return dir_infMapper.idselect(id);
    }

    @Override
    public void changename(int id, String name) {
        dir_infMapper.changename(id,name);
    }

    @Override
    public void changepath(int id, String path, String truepath) {
        dir_infMapper.changepath(id,path,truepath);
    }

    @Override
    public dir_inf selectson(int parentid) {
        return dir_infMapper.selectson(parentid);
    }

    @Override
    public int zhoubaoid(int parentid, String username) {
        return dir_infMapper.zhoubaoid(parentid,username);
    }
}

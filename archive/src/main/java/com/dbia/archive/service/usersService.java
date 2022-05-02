package com.dbia.archive.service;


import com.dbia.archive.model.users;

import java.util.List;

public interface usersService {
    /**
     * 查询id
     * @param fullname
     * @return
     */
    int selectid(String fullname);

    /**
     * 查密码
     * @param id
     * @return
     */
    String selectpassword(int id);

    /**
     * 查小组id
     * @param id
     * @return
     */
    int selectgroup_id(int id);

    /**
     * 查询名字
     * @param id
     * @return
     */
    String selectname(int id);

    /**
     * 插入用户
     * @param record
     * @return
     */
    void insert(users record);

    /**
     * 查找用户
     * @return
     */
    List<users> selectall();

    /**
     * 根据名字找用户
     * @param name
     * @return
     */
    users nameselect(String name);

    /**
     * 更改信息
     * @param id
     * @param pass_word
     * @param group_id
     * @param group_leader
     */
    void update(int id,String pass_word,int group_id,boolean group_leader);

    /**
     * 删除用户
     * @param id
     */
    void delete(int id);

    /**
     * 改密码
     * @param id
     * @param pass_word
     */
    void updatepassword(int id, String pass_word);

    /**
     * 小组id插id
     * @param groupid
     * @return
     */
    int[] groupselectid(int groupid);

    /**
     * 组长
     * @param id
     * @return
     */
    boolean selectgroupleader(int id);

    /**
     * idselect
     * @param id
     * @return
     */
    users idselect(int id);
}

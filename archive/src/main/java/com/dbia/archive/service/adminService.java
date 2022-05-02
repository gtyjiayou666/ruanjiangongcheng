package com.dbia.archive.service;

public interface adminService {
    /**
     * 查找ID
     * @param fullname
     * @return
     */
    int selectid(String fullname);

    /**
     * 查找密码
     * @param id
     * @return
     */
    String selectpassword(int id);

    /**
     * 改密码
     * @param id
     * @param pass_word
     */
    void updatepassword(int id, String pass_word);
}

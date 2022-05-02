//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.dao;

import com.dbia.archive.model.users;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface usersMapper {
    void insert(users users);

    int selectid(String fullname);

    boolean selectgroupleader(int id);

    String selectpassword(int id);

    int selectgroup_id(int id);

    String selectname(int id);

    List<users> selectall();

    users nameselect(String name);

    users idselect(int id);

    void update(int id, String pass_word, int group_id, boolean group_leader);

    void delete(int id);

    void updatepassword(int id, String pass_word);

    int[] groupselectid(int groupid);
}

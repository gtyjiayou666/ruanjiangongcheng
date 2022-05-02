//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.dao;

import com.dbia.archive.model.dir_inf;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface dir_infMapper {
    int selectid(String dirname, int group_id);

    int statusselectid(int status, int group_id);

    void diriddelete(int dir_id);

    int[] selectparentid(int pid);

    String selectname(int id);

    List<dir_inf> groupselect(int group_id);

    String selecttruepath(int id);

    String selectpath(int id);

    int insert(dir_inf dir);

    dir_inf idselect(int id);

    void changename(int id, String name);

    void changepath(int id, String path, String truepath);

    dir_inf selectson(int parentid);

    int zhoubaoid(int parentid, String username);
}

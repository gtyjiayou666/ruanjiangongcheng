package com.dbia.archive.service;

import com.dbia.archive.model.dir_inf;

import java.util.List;

public interface dir_infService {
    /**
     * 删除
     * @param dir_id
     */
    void diriddelete(int dir_id);
    /**
     * 查找id
     * @param dirname
     * @return
     */
    int selectid(String dirname, int group_id);

    /**
     * status查找id
     * @param status
     * @param group_id
     * @return
     */
    int statusselectid(int status, int group_id);
    /**
     * 根据父亲id找id
     * @param id
     * @return
     */
    int[] selectparentid(int id);

    /**
     * 根据id找名字
     * @param id
     * @return
     */
    String selectname(int id);

    /**
     * 根据小组找文件夹
     * @param group_id
     * @return
     */
    List<dir_inf> groupselect(int group_id);

    /**
     * 找路径
     * @param id
     * @return
     */
    String selecttruepath(int id);

    /**
     * 找路径
     * @param id
     * @return
     */

    String selectpath(int id);

    /**
     * 插入文件夹
     * @param dir
     * @return
     */
    int insert(dir_inf dir);

    /**
     * 查找文件夹
     * @param id
     * @return
     */
    dir_inf idselect(int id);

    /**
     * 改名字
     * @param id
     * @param name
     */
    void changename(int id,String name);

    /**
     * 该路径
     * @param id
     * @param path
     * @param truepath
     */
    void changepath(int id, String path, String truepath);

    /**
     * 找爸爸
     * @param parentid
     * @return
     */
    dir_inf selectson(int parentid);

    /**
     * 找周报id
     * @param parentid
     * @param username
     * @return
     */
    int zhoubaoid(int parentid,String username);
}

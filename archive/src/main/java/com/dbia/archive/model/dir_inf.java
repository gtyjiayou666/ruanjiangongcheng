//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.model;

public class dir_inf {
    private Integer id;
    private String dir_name;
    private Integer dir_type;
    private Integer parent_dir;
    private String dir_path;
    private String truedir_path;
    private Boolean dir_status;
    private Integer group_id;

    public String toString() {
        return "dir_inf{id=" + this.id + ", dir_name='" + this.dir_name + '\'' + ", dir_type=" + this.dir_type + ", parent_dir=" + this.parent_dir + ", dir_path='" + this.dir_path + '\'' + ", truedir_path='" + this.truedir_path + '\'' + ", dir_status=" + this.dir_status + ", group_id=" + this.group_id + '}';
    }

    public Integer getId() {
        return this.id;
    }

    public dir_inf() {
    }

    public dir_inf(Integer id, String dir_name, Integer dir_type, Integer parent_dir, String dir_path, String truedir_path, Boolean dir_status, Integer group_id) {
        this.id = id;
        this.dir_name = dir_name;
        this.dir_type = dir_type;
        this.parent_dir = parent_dir;
        this.dir_path = dir_path;
        this.truedir_path = truedir_path;
        this.dir_status = dir_status;
        this.group_id = group_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDir_name() {
        return this.dir_name;
    }

    public void setDir_name(String dir_name) {
        this.dir_name = dir_name;
    }

    public Integer getDir_type() {
        return this.dir_type;
    }

    public void setDir_type(Integer dir_type) {
        this.dir_type = dir_type;
    }

    public Integer getParent_dir() {
        return this.parent_dir;
    }

    public void setParent_dir(Integer parent_dir) {
        this.parent_dir = parent_dir;
    }

    public String getDir_path() {
        return this.dir_path;
    }

    public void setDir_path(String dir_path) {
        this.dir_path = dir_path;
    }

    public String getTruedir_path() {
        return this.truedir_path;
    }

    public void setTruedir_path(String truedir_path) {
        this.truedir_path = truedir_path;
    }

    public Boolean getDir_status() {
        return this.dir_status;
    }

    public void setDir_status(Boolean dir_status) {
        this.dir_status = dir_status;
    }

    public Integer getGroup_id() {
        return this.group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.model;

import java.util.Date;

public class users {
    private Integer id;
    private String full_Name;
    private Date created_Time;
    private String pass_word;
    private Integer group_id;
    private Boolean group_leader;

    public Integer getId() {
        return this.id;
    }

    public String toString() {
        return "users{id=" + this.id + ", full_Name='" + this.full_Name + '\'' + ", created_Time=" + this.created_Time + ", pass_word='" + this.pass_word + '\'' + ", group_id=" + this.group_id + ", group_leader=" + this.group_leader + '}';
    }

    public users(Integer id, String full_Name, Date created_Time, String pass_word, Integer group_id, Boolean group_leader) {
        this.id = id;
        this.full_Name = full_Name;
        this.created_Time = created_Time;
        this.pass_word = pass_word;
        this.group_id = group_id;
        this.group_leader = group_leader;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFull_Name() {
        return this.full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }

    public Date getCreated_Time() {
        return this.created_Time;
    }

    public void setCreated_Time(Date created_Time) {
        this.created_Time = created_Time;
    }

    public String getPass_word() {
        return this.pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public Integer getGroup_id() {
        return this.group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Boolean getGroup_leader() {
        return this.group_leader;
    }

    public void setGroup_leader(Boolean group_leader) {
        this.group_leader = group_leader;
    }

    public users() {
    }
}

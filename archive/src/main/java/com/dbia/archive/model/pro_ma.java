package com.dbia.archive.model;

import java.util.Date;

public class pro_ma {
    private Integer id;
    private Integer bid;
    private Integer userid;
    private Integer cho;

    public pro_ma() {
    }

    @Override
    public String toString() {
        return "pro_ma{" +
                "id=" + id +
                ", bid=" + bid +
                ", userid=" + userid +
                ", cho=" + cho +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCho() {
        return cho;
    }

    public void setCho(Integer cho) {
        this.cho = cho;
    }
}

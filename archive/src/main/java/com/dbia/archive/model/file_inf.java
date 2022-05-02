//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.model;

import java.util.Date;

public class file_inf {
    private Integer id;
    private String fileName;
    private String cateId;
    private Integer dirId;
    private Integer userId;
    private Integer fileSize;
    private Date fileUploadTime;

    public file_inf(Integer id, String fileName, String cateId, Integer dirId, Integer userId, Integer fileSize, Date fileUploadTime) {
        this.id = id;
        this.fileName = fileName;
        this.cateId = cateId;
        this.dirId = dirId;
        this.userId = userId;
        this.fileSize = fileSize;
        this.fileUploadTime = fileUploadTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getCateId() {
        return this.cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId == null ? null : cateId.trim();
    }

    public Integer getDirId() {
        return this.dirId;
    }

    public void setDirId(Integer dirId) {
        this.dirId = dirId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Date getFileUploadTime() {
        return this.fileUploadTime;
    }

    public void setFileUploadTime(Date fileUploadTime) {
        this.fileUploadTime = fileUploadTime;
    }
}

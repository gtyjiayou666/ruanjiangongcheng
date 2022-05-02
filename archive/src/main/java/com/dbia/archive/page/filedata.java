package com.dbia.archive.page;

import java.util.Date;

public class filedata {
    private String fileName;

    public Integer getDirId() {
        return dirId;
    }

    public void setDirId(Integer dirId) {
        this.dirId = dirId;
    }

    @Override
    public String toString() {
        return "filedata{" +
                "fileName='" + fileName + '\'' +
                ", cateId='" + cateId + '\'' +
                ", dirname='" + dirname + '\'' +
                ", username='" + username + '\'' +
                ", fileSize=" + fileSize +
                ", fileUploadTime=" + fileUploadTime +
                ", dirId=" + dirId +
                '}';
    }

    public filedata(String fileName, String cateId, String dirname, String username, Integer fileSize, Date fileUploadTime, Integer dirId) {
        this.fileName = fileName;
        this.cateId = cateId;
        this.dirname = dirname;
        this.username = username;
        this.fileSize = fileSize;
        this.fileUploadTime = fileUploadTime;
        this.dirId = dirId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Date getFileUploadTime() {
        return fileUploadTime;
    }

    public void setFileUploadTime(Date fileUploadTime) {
        this.fileUploadTime = fileUploadTime;
    }

    private String cateId;

    private String dirname;

    private String username;

    private Integer fileSize;

    private Date fileUploadTime;

    private Integer dirId;
}

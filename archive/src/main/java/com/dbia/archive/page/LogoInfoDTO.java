package com.dbia.archive.page;

public class LogoInfoDTO {
    /**
     * title : DBIA-文档管理
     * image : images/logo.png
     * href :
     */

    @com.google.gson.annotations.SerializedName("title")
    private String title;
    @com.google.gson.annotations.SerializedName("image")
    private String image;
    @com.google.gson.annotations.SerializedName("href")
    private String href;

    public LogoInfoDTO()
    {
        title="DBIA-文档管理";
        image="images/logo.png";
        href="";
    }
    public static LogoInfoDTO objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, LogoInfoDTO.class);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

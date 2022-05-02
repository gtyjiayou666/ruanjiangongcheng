package com.dbia.archive.page;

public class HomeInfoDTO {
    /**
     * title : 首页
     * href : page/welcome-1.html?t=1
     */

    @com.google.gson.annotations.SerializedName("title")
    private String title;
    @com.google.gson.annotations.SerializedName("href")
    private String href;
    public HomeInfoDTO()
    {
        title="首页";
        href="welcome-1.html";
    }
    public static HomeInfoDTO objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, HomeInfoDTO.class);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

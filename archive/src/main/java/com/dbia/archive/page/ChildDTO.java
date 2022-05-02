package com.dbia.archive.page;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ChildDTO {
    /**
     * title : 插入用户
     * href : page/form.jsp
     * icon : fa fa-list-alt
     * target : _self
     */

    @SerializedName("title")
    private String title;
    @SerializedName("href")
    private String href;
    @SerializedName("icon")
    private String icon;
    @SerializedName("target")
    private String target;
    public ChildDTO(){
        icon="fa fa-file-text";
        target= "_self";
    }

    public static ChildDTO objectFromData(String str) {

        return new Gson().fromJson(str, ChildDTO.class);
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}

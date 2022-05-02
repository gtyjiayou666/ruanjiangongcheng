package com.dbia.archive.page;

import java.util.ArrayList;
import java.util.List;

public class ChildDTOX {
    /**
     *"title": "文件",
     *"href": "page/table.jsp",
     *"icon": "fa fa-file-text",
     *"target": "_self",
     *"child": [
     *]
     */
    private String title;
    private String href;
    private String icon;
    private String target;
    private String dir_id;
    List<ChildDTOX> child;
    public ChildDTOX(){
        title="文件";
        icon="fa fa-file-text";
        href= "";
        target= "_self";
        dir_id="";
        this.child=null;
    }

    @Override
    public String toString() {
        return "ChildDTOX{" +
                "title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", icon='" + icon + '\'' +
                ", target='" + target + '\'' +
                ", dir_id='" + dir_id + '\'' +
                ", child=" + child +
                '}';
    }

    public String getId() {
        return dir_id;
    }

    public void setId(String id) {
        this.dir_id = id;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public String getIcon() {
        return icon;
    }

    public String getTarget() {
        return target;
    }

    public List<ChildDTOX> getChild() {
        return child;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setChild(List<ChildDTOX> child) {
        this.child = child;
    }

    public void setTitle(String title) {
        this.title=title;
    }
}

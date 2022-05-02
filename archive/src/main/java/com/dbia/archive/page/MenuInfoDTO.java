package com.dbia.archive.page;

import java.util.ArrayList;
import java.util.List;

public class MenuInfoDTO {
    private String title;
    private String href;
    private String icon;
    private String target;
    List<ChildDTOX> child;
    public MenuInfoDTO(){
        title="常规管理";
        icon="fa fa-address-book";
        href= "";
        target= "_self";
    }

    public void setChild(List<ChildDTOX> child) {
        this.child = child;
    }
}

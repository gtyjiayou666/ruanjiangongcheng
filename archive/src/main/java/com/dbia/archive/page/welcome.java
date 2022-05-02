package com.dbia.archive.page;


import java.util.List;

public class welcome {

    /**
     * homeInfo : {"title":"首页","href":"page/welcome-1.jsp?t=1"}
     * logoInfo : {"title":"DBIA-文档管理","image":"images/logo.png","href":""}
     * menuInfo : [{"title":"常规管理","icon":"fa fa-address-book","href":"","target":"_self","child":[{"title":"文件","href":"page/table.jsp","icon":"fa fa-file-text","target":"_self","child":[{"title":"100","href":"page/grouptable.jsp?group=100","icon":"fa fa-file-text","target":"_self"},{"title":"101","href":"page/grouptable.jsp?group=101","icon":"fa fa-file-text","target":"_self"},{"title":"102","href":"page/grouptable.jsp?group=102","icon":"fa fa-file-text","target":"_self"},{"title":"103","href":"page/grouptable.jsp?group=103","icon":"fa fa-file-text","target":"_self"},{"title":"104","href":"page/grouptable.jsp?group=104","icon":"fa fa-file-text","target":"_self"},{"title":"105","href":"page/grouptable.jsp?group=105","icon":"fa fa-file-text","target":"_self"},{"title":"106","href":"page/grouptable.jsp?group=106","icon":"fa fa-file-text","target":"_self"},{"title":"107","href":"page/grouptable.jsp?group=107","icon":"fa fa-file-text","target":"_self"},{"title":"108","href":"page/grouptable.jsp?group=108","icon":"fa fa-file-text","target":"_self"},{"title":"109","href":"page/grouptable.jsp?group=109","icon":"fa fa-file-text","target":"_self"},{"title":"110","href":"page/grouptable.jsp?group=110","icon":"fa fa-file-text","target":"_self"},{"title":"111","href":"page/grouptable.jsp?group=111","icon":"fa fa-file-text","target":"_self"},{"title":"112","href":"page/grouptable.jsp?group=112","icon":"fa fa-file-text","target":"_self"},{"title":"113","href":"page/grouptable.jsp?group=113","icon":"fa fa-file-text","target":"_self"},{"title":"114","href":"page/grouptable.jsp?group=114","icon":"fa fa-file-text","target":"_self"},{"title":"115","href":"page/grouptable.jsp?group=115","icon":"fa fa-file-text","target":"_self"},{"title":"116","href":"page/grouptable.jsp?group=116","icon":"fa fa-file-text","target":"_self"},{"title":"117","href":"page/grouptable.jsp?group=117","icon":"fa fa-file-text","target":"_self"},{"title":"118","href":"page/grouptable.jsp?group=118","icon":"fa fa-file-text","target":"_self"},{"title":"119","href":"page/grouptable.jsp?group=119","icon":"fa fa-file-text","target":"_self"},{"title":"120","href":"page/grouptable.jsp?group=120","icon":"fa fa-file-text","target":"_self"},{"title":"121","href":"page/grouptable.jsp?group=121","icon":"fa fa-file-text","target":"_self"},{"title":"122","href":"page/grouptable.jsp?group=122","icon":"fa fa-file-text","target":"_self"},{"title":"123","href":"page/grouptable.jsp?group=123","icon":"fa fa-file-text","target":"_self"},{"title":"124","href":"page/grouptable.jsp?group=124","icon":"fa fa-file-text","target":"_self"},{"title":"125","href":"page/grouptable.jsp?group=125","icon":"fa fa-file-text","target":"_self"},{"title":"126","href":"page/grouptable.jsp?group=126","icon":"fa fa-file-text","target":"_self"},{"title":"127","href":"page/grouptable.jsp?group=127","icon":"fa fa-file-text","target":"_self"},{"title":"128","href":"page/grouptable.jsp?group=128","icon":"fa fa-file-text","target":"_self"},{"title":"129","href":"page/grouptable.jsp?group=129","icon":"fa fa-file-text","target":"_self"},{"title":"130","href":"page/grouptable.jsp?group=130","icon":"fa fa-file-text","target":"_self"},{"title":"131","href":"page/grouptable.jsp?group=131","icon":"fa fa-file-text","target":"_self"},{"title":"132","href":"page/grouptable.jsp?group=132","icon":"fa fa-file-text","target":"_self"}]},{"title":"用户信息","href":"","icon":"fa fa-calendar","target":"_self","child":[{"title":"插入用户","href":"page/form.jsp","icon":"fa fa-list-alt","target":"_self"},{"title":"查看用户","href":"page/user.jsp","icon":"fa fa-navicon","target":"_self"}]}]}]
     */

    public welcome(){};
    public welcome(HomeInfoDTO homeInfo, LogoInfoDTO logoInfo, List<MenuInfoDTO> menuInfo)
    {
        this.homeInfo=homeInfo;
        this.logoInfo=logoInfo;
        this.menuInfo=menuInfo;
    }
    @com.google.gson.annotations.SerializedName("homeInfo")
    private com.dbia.archive.page.HomeInfoDTO homeInfo;
    @com.google.gson.annotations.SerializedName("logoInfo")
    private com.dbia.archive.page.LogoInfoDTO logoInfo;
    @com.google.gson.annotations.SerializedName("menuInfo")
    private List<MenuInfoDTO> menuInfo;
}

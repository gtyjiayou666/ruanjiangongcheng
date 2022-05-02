/**
 * date:2020/02/27
 * author:Mr.Chung
 * version:2.0
 * description:layuimini 菜单框架扩展
 */
layui.define(["element","laytpl" ,"jquery"], function (exports) {
    var element = layui.element,
        $ = layui.$,
        laytpl = layui.laytpl,
        layer = layui.layer;

    var miniMenu = {
        /**
         * 开启右键菜单
         * @param tabId
         * @param left
         */
        openTabRignMenu: function (tabId, left, top) {
            miniMenu.closeTabRignMenu();
            var menuHtml = '<div class="layui-unselect layui-form-select layui-form-selected layuimini-tab-mousedown layui-show" data-tab-id="' + tabId + '" style="left: ' + left + 'px!important" style="top: '+top+'px!important;">\n' +
                '<dl>\n' +
                '<dd><a href="javascript:;" layuimini-tab-menu-close="create">创建子文件夹</a></dd>\n' +
                '<dd><a href="javascript:;" layuimini-tab-menu-close="name">修改名称</a></dd>\n' +
                '<dd><a href="javascript:;" layuimini-tab-menu-close="delete">删除文件夹</a></dd>\n' +
                '</dl>\n' +
                '</div>';
            var makeHtml = '<div class="layuimini-tab-make"></div>';
            $('.layui-side ul').append(menuHtml);
            $('.layui-side ul').append(makeHtml);
        },

        closeTabRignMenu: function () {
            $('.layuimini-tab-mousedown').remove();
            $('.layuimini-tab-make').remove();
        },
        /**
         * 新建文件夹
         * @param id
         */
        create: function (id) {
            layer.prompt(function(value, index, elem){
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET","createdir?dir_id="+id+"&name="+value,true);
                xmlhttp.send();
                layer.close(index);
                window.location = 'index.html';
            });
        },/**
         * 改名
         * @param id
         */
        name: function (id) {
            layer.prompt(function(value, index, elem){
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET","namedir?dir_id="+id+"&name="+value,true);
                xmlhttp.send();
                layer.close(index);
                window.location = 'index.html';
            });
        },
        /**
         * 删除文件夹
         * @param id
         */
        delete: function (id) {
            layer.confirm('真的要删除吗?', {icon: 3, title:'提示'}, function(index){
                //do something
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET","deletedir?dir_id="+id,true);
                xmlhttp.send();
                layer.close(index);
                window.location = 'index.html';
            });
        },
        /**
         * 菜单初始化
         * @param options.menuList   菜单数据信息
         * @param options.multiModule 是否开启多模块
         * @param options.menuChildOpen 是否展开子菜单
         */
        render: function (options) {
            options.menuList = options.menuList || [];
            options.multiModule = options.multiModule || false;
            options.menuChildOpen = options.menuChildOpen || false;
            if (options.multiModule) {
                miniMenu.renderMultiModule(options.menuList, options.menuChildOpen);
            } else {
                miniMenu.renderSingleModule(options.menuList, options.menuChildOpen);
            }
            miniMenu.listen();
        },


        /**
         * 单模块
         * @param menuList 菜单数据
         * @param menuChildOpen 是否默认展开
         */
        renderSingleModule: function (menuList, menuChildOpen) {
            menuList = menuList || [];
            var leftMenuHtml = '',
                childOpenClass = '',
                leftMenuCheckDefault = 'layui-this';
            var me = this ;
            if (menuChildOpen) childOpenClass = ' layui-nav-itemed';
            leftMenuHtml = this.renderLeftMenu(menuList,{ childOpenClass:childOpenClass }) ;
            $('.layui-layout-body').addClass('layuimini-single-module'); //单模块标识
            $('.layuimini-header-menu').remove();
            $('.layuimini-menu-left').html(leftMenuHtml);

            element.init();
        },

        /**
         * 渲染一级菜单
         */
        compileMenu: function(menu,isSub){
            var menuHtml = '<li {{#if( d.menu){ }}  data-menu="{{d.menu}}" {{#}}} class="layui-nav-item menu-li {{d.childOpenClass}} {{d.className}}"  {{#if( d.id){ }}  id="{{d.id}}" {{#}}}> <a {{#if( d.href){ }} layuimini-href="{{d.href}}" {{#}}} {{#if( d.target){ }}  target="{{d.target}}" {{#}}} href="javascript:;"> {{#if( d.icon){ }} <i id="{{d.dir_id}}" class="{{d.icon}}"></i>{{#}}} <span class="layui-left-nav">{{d.title}}</span></a>  {{# if(d.children){}} {{d.children}} {{#}}} </li>' ;
            if(isSub){
                menuHtml = '<dd class="menu-dd {{d.childOpenClass}} {{ d.className }}"> <a href="javascript:;"  {{#if( d.menu){ }}  data-menu="{{d.menu}}" {{#}}} {{#if( d.id){ }}  id="{{d.id}}" {{#}}} {{#if(( !d.child || !d.child.length ) && d.href){ }} layuimini-href="{{d.href}}" {{#}}} {{#if( d.target){ }}  target="{{d.target}}" {{#}}}> {{#if( d.icon){ }}  <i id="{{d.dir_id}}" class="{{d.icon}}"></i> {{#}}} <span class="layui-left-nav"> {{d.title}}</span></a> {{# if(d.children){}} {{d.children}} {{#}}}</dd>'
            }
            return laytpl(menuHtml).render(menu);
        },
        compileMenuContainer :function(menu,isSub){
            var wrapperHtml = '<ul class="layui-nav layui-nav-tree layui-left-nav-tree {{d.className}}" id="{{d.id}}">{{d.children}}</ul>' ;
            if(isSub){
                wrapperHtml = '<dl class="layui-nav-child ">{{d.children}}</dl>' ;
            }
            if(!menu.children){
                return "";
            }
            return laytpl(wrapperHtml).render(menu);
        },

        each:function(list,callback){
            var _list = [];
            for(var i = 0 ,length = list.length ; i<length ;i++ ){
                _list[i] = callback(i,list[i]) ;
            }
            return _list ;
        },
        renderChildrenMenu:function(menuList,options){
            var me = this ;
            menuList = menuList || [] ;
            var html = this.each(menuList,function (idx,menu) {
                if(menu.child && menu.child.length){
                    menu.children = me.renderChildrenMenu(menu.child,{ childOpenClass: options.childOpenClass || '' });
                }
                menu.className = "" ;
                menu.childOpenClass = options.childOpenClass || ''
                return me.compileMenu(menu,true)
            }).join("");
            return me.compileMenuContainer({ children:html },true)
        },
        renderLeftMenu :function(leftMenus,options){
            options = options || {};
            var me = this ;
            var leftMenusHtml =  me.each(leftMenus || [],function (idx,leftMenu) { // 左侧菜单遍历
                var children = me.renderChildrenMenu(leftMenu.child, { childOpenClass:options.childOpenClass });
                var leftMenuHtml = me.compileMenu({
                    href: leftMenu.href,
                    target: leftMenu.target,
                    childOpenClass: options.childOpenClass,
                    icon: leftMenu.icon,
                    title: leftMenu.title,
                    dir_id:leftMenu.dir_id,
                    children: children,
                    className: '',
                });
                return leftMenuHtml ;
            }).join("");

            leftMenusHtml = me.compileMenuContainer({ id:options.parentMenuId,className:options.leftMenuCheckDefault,children:leftMenusHtml }) ;
            return leftMenusHtml ;
        },
        /**
         * 多模块
         * @param menuList 菜单数据
         * @param menuChildOpen 是否默认展开
         */
        renderMultiModule: function (menuList, menuChildOpen) {
            menuList = menuList || [];
            var me = this ;
            var headerMenuHtml = '',
                headerMobileMenuHtml = '',
                leftMenuHtml = '',
                leftMenuCheckDefault = 'layui-this',
                childOpenClass = '',
                headerMenuCheckDefault = 'layui-this';

            if (menuChildOpen) childOpenClass = ' layui-nav-itemed';
            var headerMenuHtml = this.each(menuList, function (index, val) { //顶部菜单渲染
                var menu = 'multi_module_' + index ;
                var id = menu+"HeaderId";
                var topMenuItemHtml = "" ;
                topMenuItemHtml = me.compileMenu({
                    className:headerMenuCheckDefault,
                    menu:menu,
                    id:id,
                    title:val.title,
                    href:"",
                    target:"",
                    children:""
                });
                leftMenuHtml+=me.renderLeftMenu(val.child,{
                    parentMenuId:menu,
                    childOpenClass:childOpenClass,
                    leftMenuCheckDefault:leftMenuCheckDefault
                });
                headerMobileMenuHtml +=me.compileMenu({ id:id,menu:menu,id:id,icon:val.icon, title:val.title, },true);
                headerMenuCheckDefault = "";
                leftMenuCheckDefault = "layui-hide" ;
                return topMenuItemHtml ;
            }).join("");
            $('.layui-layout-body').addClass('layuimini-multi-module'); //多模块标识
            $('.layuimini-menu-header-pc').html(headerMenuHtml); //电脑
            $('.layuimini-menu-left').html(leftMenuHtml);
            $('.layuimini-menu-header-mobile').html(headerMobileMenuHtml); //手机
            element.init();
        },

        /**
         * 监听
         */
        listen: function () {
            /**
             * 禁用网页右键
             */
            $(".layui-side").unbind("mousedown").bind("contextmenu", function (e) {
                e.preventDefault();
                return false;
            });
            //
            /**
             * 注册鼠标右键
             */
            $('body').on('mousedown', '.fa-folder', function (e) {
                var left = $(this).offset().left - $('.fa-folder').offset().left,
                    tabId = $(this).attr('id'),
                    top = $(this).offset().top - $('.fa-folder').offset().top;
                if (e.which === 3) {
                    miniMenu.openTabRignMenu(tabId, left, top);
                }
            });
            /**
             * 关闭tab右键菜单
             */
            $('body').on('click', '.layui-body,.layui-header,.layuimini-menu-left,.layuimini-tab-make,.fa-folder', function () {
                miniMenu.closeTabRignMenu();
            });

            /**
             * tab右键选项卡操作
             */
            $('body').on('click', '[layuimini-tab-menu-close]', function () {
                var loading = layer.load(0, {shade: false, time: 2 * 1000});
                var closeType = $(this).attr('layuimini-tab-menu-close');
                var id = $('.layui-unselect').attr('data-tab-id');
                if (closeType === 'create') {
                    miniMenu.create(id);
                } else if (closeType === 'name') {
                    miniMenu.name(id);
                } else if (closeType === 'delete') {
                    miniMenu.delete(id);
                }
                miniMenu.closeTabRignMenu();
                layer.close(loading);
            });

            /**
             * 菜单模块切换
             */
            $('body').on('click', '[data-menu]', function () {
                var loading = layer.load(0, {shade: false, time: 2 * 1000});
                var menuId = $(this).attr('data-menu');
                // header
                $(".layuimini-header-menu .layui-nav-item.layui-this").removeClass('layui-this');
                $(this).addClass('layui-this');
                // left
                $(".layuimini-menu-left .layui-nav.layui-nav-tree.layui-this").addClass('layui-hide');
                $(".layuimini-menu-left .layui-nav.layui-nav-tree.layui-this.layui-hide").removeClass('layui-this');
                $("#" + menuId).removeClass('layui-hide');
                $("#" + menuId).addClass('layui-this');
                layer.close(loading);
            });

            /**
             * 菜单缩放
             */
            $('body').on('click', '.layuimini-site-mobile', function () {
                var loading = layer.load(0, {shade: false, time: 2 * 1000});
                var isShow = $('.layuimini-tool [data-side-fold]').attr('data-side-fold');
                if (isShow == 1) { // 缩放
                    $('.layuimini-tool [data-side-fold]').attr('data-side-fold', 0);
                    $('.layuimini-tool [data-side-fold]').attr('class', 'fa fa-indent');
                    $('.layui-layout-body').removeClass('layuimini-all');
                    $('.layui-layout-body').addClass('layuimini-mini');
                } else { // 正常
                    $('.layuimini-tool [data-side-fold]').attr('data-side-fold', 1);
                    $('.layuimini-tool [data-side-fold]').attr('class', 'fa fa-outdent');
                    $('.layui-layout-body').removeClass('layuimini-mini');
                    $('.layui-layout-body').addClass('layuimini-all');
                    layer.close(window.openTips);
                }
                element.init();
                layer.close(loading);
            });
            /**
             * 菜单缩放
             */
            $('body').on('click', '[data-side-fold]', function () {
                var loading = layer.load(0, {shade: false, time: 2 * 1000});
                var isShow = $('.layuimini-tool [data-side-fold]').attr('data-side-fold');
                if (isShow == 1) { // 缩放
                    $('.layuimini-tool [data-side-fold]').attr('data-side-fold', 0);
                    $('.layuimini-tool [data-side-fold]').attr('class', 'fa fa-indent');
                    $('.layui-layout-body').removeClass('layuimini-all');
                    $('.layui-layout-body').addClass('layuimini-mini');
                    // $(".menu-li").each(function (idx,el) {
                    //     $(el).addClass("hidden-sub-menu");
                    // });

                } else { // 正常
                    $('.layuimini-tool [data-side-fold]').attr('data-side-fold', 1);
                    $('.layuimini-tool [data-side-fold]').attr('class', 'fa fa-outdent');
                    $('.layui-layout-body').removeClass('layuimini-mini');
                    $('.layui-layout-body').addClass('layuimini-all');
                    // $(".menu-li").each(function (idx,el) {
                    //     $(el).removeClass("hidden-sub-menu");
                    // });
                    layer.close(window.openTips);
                }
                element.init();
                layer.close(loading);
            });

            /**
             * 手机端点开模块
             */
            $('body').on('click', '.layuimini-header-menu.layuimini-mobile-show dd', function () {
                var loading = layer.load(0, {shade: false, time: 2 * 1000});
                var check = $('.layuimini-tool [data-side-fold]').attr('data-side-fold');
                if(check === "1"){
                    $('.layuimini-site-mobile').trigger("click");
                    element.init();
                }
                layer.close(loading);
            });
        },

    };


    exports("miniMenu", miniMenu);
});

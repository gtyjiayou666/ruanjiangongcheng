package com.dbia.archive.controller;
import com.dbia.archive.model.dir_inf;
import com.dbia.archive.model.study_group;
import com.dbia.archive.page.*;
import com.dbia.archive.service.dir_infService;
import com.dbia.archive.service.study_groupService;
import com.dbia.archive.service.usersService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@Controller
public class indexcontroller {
    @Autowired
    private usersService usersService;
    @Autowired
    private com.dbia.archive.service.adminService adminService;

    @Autowired
    private dir_infService dir_infService;
    @Autowired
    private study_groupService study_groupService;
    @Autowired
    private CutTest cutTest;
    @GetMapping("/login")
    public String index(@RequestParam("fullname") String fullname,
                        @RequestParam("password") String password,
                        @RequestParam("user") String user,
                        HttpSession session)
    {
        if(user.equals("用户"))
        {
            int userid = usersService.selectid(fullname);
            if(userid!=0)
            {
                String userpassword=usersService.selectpassword(userid);
                if(userpassword.equals(password))
                {
                    session.setAttribute("fullname",fullname);
                    session.setAttribute("id",userid);
                    session.setAttribute("shenfen","user");
                    return "redirect:/index.html";
                }
                else
                    return "login-1";
            }
            else
            {
                return "login-1";
            }
        }
        else
        {
            int adminid = adminService.selectid(fullname);
            if(adminid!=0)
            {
                String userpassword= adminService.selectpassword(adminid);
                if(userpassword.equals(password))
                {
                    session.setAttribute("fullname",fullname);
                    session.setAttribute("id",adminid);
                    session.setAttribute("shenfen","admin");
                    return "redirect:/index.html";
                }
                else
                    return "login-1";

            }
            else
            {
                return "login-1";
            }
        }
    }

    @GetMapping("/index.html")
    public String indexpage(HttpSession session)
    {
        return "index";
    }

    @GetMapping("/init")
    public void initpage(HttpSession session , HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        HomeInfoDTO homeInfoDTO = new HomeInfoDTO();
        LogoInfoDTO logoInfoDTO = new LogoInfoDTO();
        MenuInfoDTO menuInfoDTO = new MenuInfoDTO();
        if(session.getAttribute("shenfen")!=null&&session.getAttribute("shenfen").equals("user"))
        {
            ChildDTOX childDTOX = new ChildDTOX();
            childDTOX.setHref("upload.html");
            childDTOX.setTitle("文件上传");
            ChildDTOX childDTOX5 =new ChildDTOX();
            List<ChildDTOX> childDTOXList=new ArrayList<>();
            childDTOXList.add(childDTOX);
            if(usersService.selectgroupleader((Integer) session.getAttribute("id")))
            {
                childDTOX5.setTitle("权限管理");
                childDTOX5.setIcon("fa fa-navicon");
                childDTOX5.setHref("pro.html");
                childDTOXList.add(childDTOX5);
            }
            List<study_group> study_groups=study_groupService.select();
            for (study_group i:study_groups) {
                int dir_id=dir_infService.statusselectid(1,i.getGroup_id());
                ChildDTOX childDTOX1=cutTest.searchFolder1(dir_id, (Integer) session.getAttribute("id"));
                if(childDTOX1==null)
                    continue;
                childDTOXList.add(childDTOX1);
            }
            ChildDTOX childDTOX4 = new ChildDTOX();
            childDTOX4.setHref("date.html");
            childDTOX4.setTitle("日历");
            childDTOX4.setIcon("fa fa-calendar");
            childDTOXList.add(childDTOX4);
            menuInfoDTO.setChild(childDTOXList);
            List<MenuInfoDTO> MenuInfoDTOList=new ArrayList<>();
            MenuInfoDTOList.add(menuInfoDTO);
            welcome welcome = new welcome(homeInfoDTO,logoInfoDTO,MenuInfoDTOList);
            Gson gson = new Gson();
            String wel = gson.toJson(welcome);
            PrintWriter out1 = null;
            try {
                out1 = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out1.write(String.valueOf(wel));
            out1.close();
        }
        else if(session.getAttribute("shenfen")!=null&&session.getAttribute("shenfen").equals("admin"))
        {
            ChildDTOX childDTOX = new ChildDTOX();
            childDTOX.setHref("upload.html");
            childDTOX.setTitle("文件上传");
            List<ChildDTOX> childDTOXList=new ArrayList<>();
            childDTOXList.add(childDTOX);
            List<study_group> study_groups=study_groupService.select();
            List<MenuInfoDTO> MenuInfoDTOList=new ArrayList<>();
            for (study_group i:study_groups) {
                int dir_id=dir_infService.statusselectid(1,i.getGroup_id());
                System.out.println(dir_id);
                ChildDTOX childDTOX1=cutTest.searchFolder(dir_id);
                childDTOXList.add(childDTOX1);
            }
            ChildDTOX childDTOX1 = new ChildDTOX();
            childDTOX1.setHref("");
            childDTOX1.setTitle("用户信息");
            childDTOX1.setIcon("fa fa-calendar");
            ChildDTOX childDTOX2 =new ChildDTOX();
            childDTOX2.setTitle("插入用户");
            childDTOX2.setIcon("fa fa-list-alt");
            childDTOX2.setHref("form.html");
            ChildDTOX childDTOX3 =new ChildDTOX();
            childDTOX3.setTitle("查看用户");
            childDTOX3.setIcon("fa fa-navicon");
            childDTOX3.setHref("user.html");
            ChildDTOX childDTOX5 =new ChildDTOX();
            childDTOX5.setTitle("权限管理");
            childDTOX5.setIcon("fa fa-navicon");
            childDTOX5.setHref("pro.html");
            List<ChildDTOX> childDTOXList1=new ArrayList<>();
            childDTOXList1.add(childDTOX2);
            childDTOXList1.add(childDTOX3);
            childDTOXList1.add(childDTOX5);
            childDTOX1.setChild(childDTOXList1);
            childDTOXList.add(childDTOX1);
            ChildDTOX childDTOX4 = new ChildDTOX();
            childDTOX4.setHref("date.html");
            childDTOX4.setTitle("日历");
            childDTOX4.setIcon("fa fa-calendar");
            childDTOXList.add(childDTOX4);
            menuInfoDTO.setChild(childDTOXList);
            MenuInfoDTOList.add(menuInfoDTO);
            welcome welcome = new welcome(homeInfoDTO,logoInfoDTO,MenuInfoDTOList);
            Gson gson = new Gson();
            String wel = gson.toJson(welcome);
            PrintWriter out1 = null;
            try {
                out1 = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out1.write(String.valueOf(wel));
            out1.close();
        }
    }
    @GetMapping("/addgroup")
    public void addgroup(){
        study_groupService.addgroup(0,0);
        int id= study_groupService.maxid();
        String truedir_path=dir_infService.selecttruepath(2)+"/"+id;
        String dir_path=dir_infService.selectpath(2)+"/"+id;
        (new File(truedir_path)).mkdirs();
        dir_infService.insert(new dir_inf(0,  String.valueOf(id), 0, 2, dir_path, truedir_path, true, id));
        return;
    }
    @GetMapping("date.html")
    public String todate(){
        return "date";
    }
    @GetMapping("pro.html")
    public String topro(){
        return "pro";
    }
}

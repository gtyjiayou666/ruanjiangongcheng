package com.dbia.archive.controller;

import com.dbia.archive.model.*;
import com.dbia.archive.page.*;
import com.dbia.archive.service.*;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;


@Controller
public class admincontroller {
    @Autowired
    private study_groupService study_groupService;
    @Autowired
    private usersService usersService;
    @Autowired
    private CutTest cutTest;
    @Autowired
    private dir_infService dir_infService;
    @Autowired
    private file_infService file_infService;
    @Autowired
    private pro_maService pro_maService;
    @GetMapping("/form.html")
    public String form(Model model){
        List<study_group> select = study_groupService.select();
        model.addAttribute("group",select);
        return "form";
    }
    @GetMapping("/users_add")
    @ResponseBody
    public String user_add(@RequestParam("full_Name") String full_Name,
                           @RequestParam("pass_word") String pass_word,
                           @RequestParam("group_id") int group_id,
                           @RequestParam("group_leader") boolean group_leader) throws Exception {
        users users=new users(0,full_Name,new Timestamp(System.currentTimeMillis()),pass_word,group_id,group_leader);
        usersService.insert(users);
        String truepath = dir_infService.selecttruepath(6);
        truepath=truepath+"/"+full_Name;
        String path = dir_infService.selectpath(6);
        path=path+'/'+full_Name;
        dir_infService.insert(new dir_inf(0,full_Name, 0, 6, path, truepath, false, 0));
        (new File(truepath)).mkdirs();

        HdfsService.mkdir(truepath);
        Gson gson=new Gson();
        return gson.toJson(users);
    }
    @GetMapping("/user.html")
    public String user(){
        return "user";
    }
    @GetMapping("/userdata")
    @ResponseBody
    public String userdata(){
        List<users> select = usersService.selectall();
        Gson gson = new Gson();
        String userInfo="{\"code\":\"0\",\"msg\":\"\",\"count\":"+select.size()+",\"data\":"+gson.toJson(select)+"}";
        return userInfo;
    }
    @GetMapping("/selectuser")
    @ResponseBody
    public String selectuser(@RequestParam("username") String full_Name){
        users nameselect = usersService.nameselect(full_Name);
        Gson gson = new Gson();
        String userInfo="{\"code\":\"0\",\"msg\":\"\",\"count\":"+1+",\"data\":["+gson.toJson(nameselect)+"]}";
        return userInfo;
    }
    @GetMapping("/changeuser.html")
    public String changeuser(Model model,@RequestParam("id") String id){
        List<study_group> select = study_groupService.select();
        model.addAttribute("group",select);
        model.addAttribute("id",id);
        return "changeuser";
    }
    @GetMapping("/user_change")
    public String user_change(@RequestParam("id") int id,
                              @RequestParam("pass_word") String pass_word,
                              @RequestParam("group_id") int group_id,
                              @RequestParam("group_leader") boolean group_leader){
        usersService.update(id,pass_word,group_id,group_leader);
        Gson gson=new Gson();
        return gson.toJson("修改成功");
    }
    @GetMapping("/deleteuser")
    public void deleteuser(@RequestParam("id") int id){
        usersService.delete(id);
    }
    @GetMapping("/editor.html")
    public String editor(Model model, @RequestParam("id") String id) throws Exception {
        int dirid=dir_infService.zhoubaoid(6, usersService.selectname(Integer.parseInt(id)));
        List<file_inf> file_infs=file_infService.dir_idselect(dirid);
        String dirpath= dir_infService.selecttruepath(dirid);
        int t=1;
        List<String> xx=new ArrayList<String>();
        for (file_inf i:file_infs)
        {
            String s="";
            String path=dirpath+"/"+i.getFileName();
            s+="周报";
            s+=t++;
            s+=":";
            InputStream inputStream = new FileInputStream(path);
            s+=IOUtils.toString(inputStream, String.valueOf(StandardCharsets.UTF_8));
            xx.add(s);
            inputStream.close();
        }
        model.addAttribute("string", xx);
        return "editor";
    }
    @GetMapping("/datething.html")
    public String datething(Model model, @RequestParam("date") String date, HttpSession session) throws Exception {
        int dirid=dir_infService.zhoubaoid(10, (String) session.getAttribute("fullname"));
        String xx="";
        String dirpath= dir_infService.selecttruepath(dirid);
        int t=1;
        String path=dirpath+"/"+date+".txt";
        File uploadDir = new File(path);
        if (uploadDir.exists()) {
            InputStream inputStream = new FileInputStream(path);
            xx+=IOUtils.toString(inputStream, String.valueOf(StandardCharsets.UTF_8));
            inputStream.close();
        }
        model.addAttribute("string", xx);
        model.addAttribute("date0", date);
        return "datething";
    }
    @ResponseBody
    @PostMapping("/dateupload")
    public String dateupload(HttpServletRequest request, HttpSession session) throws IOException {
        String examplecontent = request.getParameter("neirong");//接收文本内容
        String date=request.getParameter("date0");
        String name = date+".txt";
        int dirid=dir_infService.zhoubaoid(10, (String) session.getAttribute("fullname"));
        String uploadPath = dir_infService.selecttruepath(dirid) +"/"+name;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            //File.createNewFile()创建文件
            uploadDir.createNewFile();

        }
        FileWriter fileWriter =new FileWriter(uploadPath);
        fileWriter.write(examplecontent);
        fileWriter.flush();
        fileWriter.close();
        if(file_infService.select(dirid, name)==null)
        {
            if(session.getAttribute("shenfen").equals("admin"))
                file_infService.insert(new file_inf(0, name, ".txt", dirid, 0, (int) new File(uploadPath).length(), new Timestamp(System.currentTimeMillis())));
            else
                file_infService.insert(new file_inf(0, name, ".txt", dirid, (Integer) session.getAttribute("id"), (int) new File(uploadPath).length(), new Timestamp(System.currentTimeMillis())));
        }
        Gson gson=new Gson();
        String wel = gson.toJson(uploadPath);
        return wel;
    }

    @ResponseBody
    @RequestMapping(value = "/dateu")
    public Map uploadFile(HttpServletRequest request, @Param("file") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        if (file != null) {
            String webapp = request.getServletContext().getRealPath("/");
            System.out.println(webapp);
            try {
                String substring = file.getOriginalFilename();
                String fileName = "/static/images/" + substring;
                File destFile = new File(webapp, fileName);
                File parentFile = destFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                // 把上传的临时图片，复制到当前项目的webapp路径
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destFile));
                map = new HashMap<>();
                map2 = new HashMap<>();
                map.put("code", 0);
                map.put("msg", "上传成功");
                map.put("data", map2);
                map2.put("src", fileName);
                map2.put("title", substring);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    @PostMapping("/prodata")
    @ResponseBody
    public String prodata(){
        Gson gson = new Gson();
        List<DTree> dtrees1 = new ArrayList<DTree>();
        DTree d = null;
        DTree d1 = null;
        List<study_group> study_groups=study_groupService.select();
        DTreeResponse resp = new DTreeResponse();
        Status status = new Status();
        status.setCode(200);
        status.setMessage("操作成功");
        CheckArr checkArr = new CheckArr();
        checkArr.setChecked(String.valueOf(0));
        checkArr.setType(String.valueOf(0));
        List<CheckArr> CheckArr =new ArrayList<>();
        CheckArr.add(checkArr);
        for (study_group i:study_groups) {
            int[] user_id=usersService.groupselectid(i.getGroup_id());
            List<DTree> dtrees = new ArrayList<DTree>();
            for(int l:user_id)
            {
                d = new DTree();
                d.setLast(true);
                d.setParentId(String.valueOf(i.getGroup_id()));
                d.setTitle(usersService.selectname(l));
                d.setId(String.valueOf(l));
                d.setCheckArr(CheckArr);
                d.setLevel(2);
                dtrees.add(d);
            }
            d1 = new DTree();
            d1.setId(String.valueOf(i.getGroup_id()));
            d1.setTitle(String.valueOf(i.getGroup_id()));
            d1.setParentId(String.valueOf(i.getGroup_id()));
            d1.setLast(false);
            if(dtrees.size()==0)
                d1.setLast(true);
            else
                d1.setChildren(dtrees);
            d1.setCheckArr(CheckArr);
            d1.setLevel(1);
            dtrees1.add(d1);
        }
        resp.setData(dtrees1);
        resp.setStatus(status);
        String s = gson.toJson(resp);
        return s;
    }
    @PostMapping("/prodata2")
    @ResponseBody
    public String prodata2(){
        Gson gson = new Gson();
        List<DTree> dtrees1 = new ArrayList<DTree>();
        DTree d1 = null;
        List<study_group> study_groups=study_groupService.select();
        DTreeResponse resp = new DTreeResponse();
        Status status = new Status();
        status.setCode(200);
        status.setMessage("操作成功");
        List<DTree> dtrees = new ArrayList<DTree>();
        for (study_group i:study_groups) {
            int dir_id=dir_infService.statusselectid(1,i.getGroup_id());
            DTree dtrees0=cutTest.DTreesearchFolder(dir_id);
            dtrees.add(dtrees0);
        }
        resp.setData(dtrees);
        resp.setStatus(status);
        String s = gson.toJson(resp);
        return s;
    }
    @PostMapping("/prodata3")
    @ResponseBody
    public String prodata3(@RequestParam(value = "nodeid[]") Integer[] userid){
        Gson gson = new Gson();
        List<DTree> dtrees1 = new ArrayList<DTree>();
        DTree d1 = null;
        List<study_group> study_groups=study_groupService.select();
        DTreeResponse resp = new DTreeResponse();
        Status status = new Status();
        status.setCode(200);
        status.setMessage("操作成功");
        List<DTree> dtrees = new ArrayList<DTree>();
        for (study_group i:study_groups) {
            int dir_id=dir_infService.statusselectid(1,i.getGroup_id());
            DTree dtrees0=cutTest.DTreesearchFolder1(dir_id,userid[0]);
            dtrees.add(dtrees0);
        }
        resp.setData(dtrees);
        resp.setStatus(status);
        String s = gson.toJson(resp);
        return s;
    }
    @PostMapping("/prochange")
    @ResponseBody
    public String prochange(@RequestParam(value = "dirid[]") Integer[] dirid,
                            @RequestParam(value = "datatype[]") Integer[] datatype,
                            @RequestParam(value = "nodeid[]") Integer[] userid)
    {
        Gson gson = new Gson();
        for(int i = 0; i < userid.length; i++)
        {
            pro_maService.delete(userid[i]);
        }
        for(int l:userid)
        {
            for(int i = 0; i < dirid.length; i++)
            {
                pro_ma pro_ma = new pro_ma();
                pro_ma.setId(0);
                pro_ma.setBid(dirid[i]);
                pro_ma.setCho(datatype[i]);
                pro_ma.setUserid(l);
                pro_maService.insert(pro_ma);
            }
        }
        return "1";
    }
    @PostMapping("/prochange1")
    @ResponseBody
    public String prochange1(@RequestParam(value = "dirid[]") Integer[] dirid,
                            @RequestParam(value = "datatype[]") Integer[] datatype,
                            @RequestParam(value = "nodeid[]") Integer[] userid)
    {
        Gson gson = new Gson();
        for(int i = 0; i < userid.length; i++)
        {
            pro_maService.delete(userid[i]);
        }
        for(int l:userid)
        {
            for(int i = 0; i < dirid.length; i++)
            {
                pro_ma pro_ma = new pro_ma();
                pro_ma.setId(0);
                pro_ma.setBid(dirid[i]);
                pro_ma.setCho(datatype[i]);
                pro_ma.setUserid(l);
                pro_maService.insert(pro_ma);
            }
        }
        return "1";
    }
    @PostMapping("/inmygroup")
    @ResponseBody
    public String inmygroup(@RequestParam(value = "nodeid[]") Integer[] userid, HttpSession session)
    {
        for(int l:userid)
        {
            users users=usersService.idselect(l);
            usersService.update(l,users.getPass_word(),usersService.selectgroup_id((Integer) session.getAttribute("id")),false);
        }
        return "1";
    }
    @PostMapping("/removegroup")
    @ResponseBody
    public String removegroup(HttpSession session, @RequestParam(value = "nodeId") Integer userid)
    {
        if(usersService.selectgroup_id(userid)==usersService.selectgroup_id((Integer) session.getAttribute("id")))
        {
            users users=usersService.idselect(userid);
            usersService.update(userid,users.getPass_word(),100,false);
            return "1";
        }
        return "0";
    }
}

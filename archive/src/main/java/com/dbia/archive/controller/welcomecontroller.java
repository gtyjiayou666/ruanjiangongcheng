//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.controller;

import com.dbia.archive.model.dir_inf;
import com.dbia.archive.model.file_inf;
import com.dbia.archive.page.CutTest;
import com.dbia.archive.page.HdfsConfig;
import com.dbia.archive.page.filedata;
import com.dbia.archive.service.*;
import com.google.gson.Gson;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class welcomecontroller {
    @Autowired
    private file_infService file_infService;
    @Autowired
    private dir_infService dir_infService;
    @Autowired
    private usersService usersService;
    @Autowired
    private CutTest cutTest;
    @Autowired
    private adminService adminService;
    @Autowired
    private pro_maService pro_maService;

    public welcomecontroller() {
    }

    @GetMapping({"/welcome-1.html"})
    public String welcome(Model model, HttpSession session) throws IOException {
        String date = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()).toString();
        int dirid = this.dir_infService.zhoubaoid(10, (String)session.getAttribute("fullname"));
        String xx = "";
        if (dirid == 0) {
            this.dir_infService.insert(new dir_inf(0, (String)session.getAttribute("fullname"), 0, 10, this.dir_infService.selectpath(10) + "/" + (String)session.getAttribute("fullname"), this.dir_infService.selecttruepath(10) + "/" + (String)session.getAttribute("fullname"), false, 0));
            dirid = this.dir_infService.zhoubaoid(10, (String)session.getAttribute("fullname"));
            (new File(this.dir_infService.selecttruepath(dirid))).mkdirs();
        }

        String dirpath = this.dir_infService.selecttruepath(dirid);
        String path = dirpath + "/" + date + ".txt";
        File uploadDir = new File(path);
        if (uploadDir.exists()) {
            InputStream inputStream = new FileInputStream(path);
            xx = xx + IOUtils.toString(inputStream, String.valueOf(StandardCharsets.UTF_8));
            inputStream.close();
        }

        model.addAttribute("string", xx);
        return "welcome-1";
    }

    @GetMapping({"/table.html"})
    public String table(@RequestParam("dir_id") int dir_id, Model model) {
        model.addAttribute("dir_id", dir_id);
        return "table";
    }

    @GetMapping({"/filedata"})
    @ResponseBody
    public String filedata(@RequestParam("dir_id") int dir_id) {
        List<file_inf> file_infs = this.file_infService.dir_idselect(dir_id);
        List<filedata> filedata = new ArrayList();
        Iterator var4 = file_infs.iterator();

        while(var4.hasNext()) {
            file_inf i = (file_inf)var4.next();
            String username = this.usersService.selectname(i.getUserId());
            String dir_name = this.dir_infService.selectname(i.getDirId());
            filedata.add(new filedata(i.getFileName(), i.getCateId(), dir_name, username, i.getFileSize(), i.getFileUploadTime(), i.getDirId()));
        }

        Gson gson = new Gson();
        String fileInfo = "{\"code\":\"0\",\"msg\":\"\",\"count\":" + file_infs.size() + ",\"data\":" + gson.toJson(filedata) + "}";
        return fileInfo;
    }

    @GetMapping({"/userfiledata"})
    @ResponseBody
    public String userfiledata(@RequestParam("dir_id") int dir_id, @RequestParam("username") String username) {
        int selectid = this.usersService.selectid(username);
        List<file_inf> file_infs = this.file_infService.useridselect(dir_id, selectid);
        List<filedata> filedata = new ArrayList();
        Iterator var6 = file_infs.iterator();

        while(var6.hasNext()) {
            file_inf i = (file_inf)var6.next();
            String username1 = this.usersService.selectname(i.getUserId());
            String dir_name = this.dir_infService.selectname(i.getDirId());
            filedata.add(new filedata(i.getFileName(), i.getCateId(), dir_name, username1, i.getFileSize(), i.getFileUploadTime(), i.getDirId()));
        }

        Gson gson = new Gson();
        String fileInfo = "{\"code\":\"0\",\"msg\":\"\",\"count\":" + file_infs.size() + ",\"data\":" + gson.toJson(filedata) + "}";
        return fileInfo;
    }

    @GetMapping({"/download"})
    public void download(HttpSession session ,@RequestParam("id") String dir_id, @RequestParam("filename") String fileName, HttpServletResponse response) throws IOException {
        if(pro_maService.selecttrue((Integer) session.getAttribute("id"), Integer.parseInt(dir_id),3) == null)
            return;
        String path = "/media/archive/" + this.dir_infService.selecttruepath(Integer.parseInt(dir_id)) + "/" + fileName;
        System.out.println(path);
        InputStream inputStream = new FileInputStream(path);
        response.reset();
        response.setContentType("application/octet-stream");
        String filename = (new File(path)).getName();
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        while((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        HdfsConfig hdfsConfig = new HdfsConfig();
        FileSystem fs = hdfsConfig.getFileSystem();
        Path newPath = new Path(this.dir_infService.selecttruepath(Integer.parseInt(dir_id)) + "/" + fileName);
        FSDataInputStream in = fs.open(newPath);
        response.reset();
        response.setContentType("application/octet-stream");
        filename = (new File(path)).getName();
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        outputStream = response.getOutputStream();
        while((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
    }

    @GetMapping({"/delete"})
    @ResponseBody
    public String delete(HttpSession session ,@RequestParam("id") String dir_id, @RequestParam("filename") String fileName) throws IOException {
        if(pro_maService.selecttrue((Integer) session.getAttribute("id"), Integer.parseInt(dir_id),7) == null)
            return "没有权限";
        file_inf file_inf = this.file_infService.select(Integer.parseInt(dir_id), fileName);
        HdfsConfig hdfsConfig = new HdfsConfig();
        FileSystem fs = hdfsConfig.getFileSystem();
        Path newPath = new Path(this.dir_infService.selecttruepath(Integer.parseInt(dir_id)) + "/" + fileName);
        fs.deleteOnExit(newPath);
        fs.close();
        this.cutTest.deleteDir(new File(this.dir_infService.selecttruepath(Integer.parseInt(dir_id)) + "/" + fileName));
        this.file_infService.delete(file_inf.getId());
        return "删除成功";
    }

    @GetMapping({"/user_password.html"})
    public String user_password() {
        return "user_password";
    }

    @GetMapping({"/updatepassword"})
    @ResponseBody
    public String updatepassword(@RequestParam("password") String password, @RequestParam("oldpassword") String oldpassword, HttpSession session) {
        Gson gson = new Gson();
        if (session.getAttribute("shenfen").equals("admin")) {
            if (this.adminService.selectpassword((Integer)session.getAttribute("id")).equals(oldpassword)) {
                this.adminService.updatepassword((Integer)session.getAttribute("id"), password);
                return gson.toJson("修改成功");
            } else {
                return gson.toJson("密码错误");
            }
        } else if (session.getAttribute("shenfen").equals("user")) {
            if (this.usersService.selectpassword((Integer)session.getAttribute("id")).equals(oldpassword)) {
                this.usersService.updatepassword((Integer)session.getAttribute("id"), password);
                return gson.toJson("修改成功");
            } else {
                return gson.toJson("密码错误");
            }
        } else {
            return gson.toJson("error");
        }
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.controller;

import com.dbia.archive.model.dir_inf;
import com.dbia.archive.model.file_inf;
import com.dbia.archive.model.pro_ma;
import com.dbia.archive.page.HdfsConfig;
import com.dbia.archive.service.*;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class uploadcontroller {
    @Autowired
    private dir_infService dir_infService;
    @Autowired
    private file_infService file_infService;
    @Autowired
    private pro_maService pro_maService;

    public uploadcontroller() {
    }

    @GetMapping({"/upload.html"})
    public String upload(HttpSession session, Model model) {
        if (session.getAttribute("id") == null) {
            return "index";
        } else {
            if (session.getAttribute("shenfen").equals("user")) {
                int id = (Integer)session.getAttribute("id");
//                int group_id = this.usersService.selectgroup_id(id);
//                List<dir_inf> dir_infList = this.dir_infService.groupselect(group_id);
                List<dir_inf> dir_infList = new ArrayList<>();
                List<pro_ma> select = pro_maService.select(id);
                for(pro_ma i:select)
                {
                    if(i.getCho() == 1)
                        dir_infList.add(dir_infService.idselect(i.getBid()));
                }
                dir_infList.add(this.dir_infService.idselect(this.dir_infService.zhoubaoid(6, (String)session.getAttribute("fullname"))));
                model.addAttribute("dir", dir_infList);
            } else {
                int[] groupid = this.dir_infService.selectparentid(2);
                List<dir_inf> dir_infList = new ArrayList();

                for(int i = 1; i < groupid.length; ++i) {
                    dir_infList.add(this.dir_infService.idselect(groupid[i]));
                }

                model.addAttribute("dir", dir_infList);
            }

            return "upload";
        }
    }

    @PostMapping({"/userupload"})
    @ResponseBody
    public String userupload(HttpSession session, HttpServletRequest request, @RequestPart("file") MultipartFile[] file) throws IOException {
        int dir_id = Integer.parseInt(request.getParameter("id"));
        Map<String, Object> result = new HashMap();
        Gson gson = new Gson();
        if (file.length == 0) {
            result.put("code", 1);
            String wel = gson.toJson(result);
            return wel;
        } else {
            result.put("code", 0);
            result.put("msg", "上传成功");
            for(int i = 0; i < file.length; ++i) {
                if (!file[i].isEmpty()) {
                    String filename = file[i].getOriginalFilename();
                    String filepath = "/media/archive/" + this.dir_infService.selecttruepath(dir_id) + "/" + filename;

                    HdfsConfig hdfsConfig = new HdfsConfig();
                    FileSystem fs = hdfsConfig.getFileSystem();
                    // 上传时默认当前目录，后面自动拼接文件的目录
                    Path newPath = new Path(this.dir_infService.selecttruepath(dir_id) + "/" + filename);
                    // 打开一个输出流
                    FSDataOutputStream outputStream = fs.create(newPath);
                    outputStream.write(file[i].getBytes());
                    outputStream.close();
                    fs.close();
                    try {
                        file[i].transferTo(new File(filepath));
                    } catch (IOException var14) {
                        return "上传失败";
                    }

                    String suffixName = filename.substring(filename.lastIndexOf("."));
                    Timestamp d = new Timestamp(System.currentTimeMillis());
                    file_inf file_inf1 = new file_inf(0, filename, suffixName, dir_id, (Integer)session.getAttribute("id"), (int)(new File(filepath)).length(), d);
                    file_inf select = this.file_infService.select(dir_id, filename);
                    if (select != null) {
                        this.file_infService.delete(select.getId());
                    }

                    this.file_infService.insert(file_inf1);
                    result.put("filePath", filepath + d + suffixName);
                }
            }

            String wel = gson.toJson(result);
            return wel;
        }
    }
}

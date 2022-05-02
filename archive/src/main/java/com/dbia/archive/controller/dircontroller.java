package com.dbia.archive.controller;

import com.dbia.archive.model.dir_inf;
import com.dbia.archive.page.CutTest;
import com.dbia.archive.service.HdfsService;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hdfs.server.datanode.DiskBalancerWorkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.BlockLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class dircontroller {
    @Autowired
    private com.dbia.archive.service.dir_infService dir_infService;

    @GetMapping("/createdir")
    public String createdir(@RequestParam("dir_id") int dir_id,
                            @RequestParam("name") String name,HttpSession session) throws Exception {
        dir_inf dir=dir_infService.idselect(dir_id);
        dir_inf dir_inf = new dir_inf(0,name,0,dir.getId(),dir.getDir_path()+"/"+name,dir.getTruedir_path()+"/"+name,false,dir.getGroup_id());
        dir_infService.insert(dir_inf);
        Path path = Paths.get(dir.getTruedir_path()+"/"+name);
        try {
            Path pathCreate = Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(dir.getTruedir_path()+"/"+name)) {
            return "index";
        }
        // 创建空文件夹
        HdfsService.mkdir(dir.getTruedir_path()+"/"+name);
        return "index";
    }
    @Autowired
    private CutTest cutTest;
    @GetMapping("/namedir")
    public String namedir(@RequestParam("dir_id") int dir_id,
                          @RequestParam("name") String name,
                          HttpSession session) throws Exception {
        dir_inf dir_inf=dir_infService.idselect(dir_id);
        dir_inf dir_inf1=dir_infService.selectson(dir_inf.getParent_dir());
        String path=dir_inf1.getDir_path();
        String truepath=dir_inf1.getTruedir_path();
        File file1 = new File(dir_inf.getTruedir_path());
        file1.renameTo(new File(truepath+"/"+name));
        HdfsService.renameFile(truepath, truepath+"/"+name);
        dir_infService.changename(dir_id,name);
        dir_infService.changepath(dir_id,path+"/"+name,truepath+"/"+name);
        int[] x=dir_infService.selectparentid(dir_id);
        for (int i:x) {
            cutTest.changeallpath(i,path+"/"+name,truepath+"/"+name);
        }
        return "index";
    }
    @GetMapping("/deletedir")
    public String deletedir(@RequestParam("dir_id") int dir_id,
                          HttpSession session) throws Exception {
        cutTest.deleteDir(new File(dir_infService.selecttruepath(dir_id)));
        cutTest.deletedir(dir_id);
        return "index";
    }
}

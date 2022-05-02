package com.dbia.archive.controller;

import com.dbia.archive.model.study_group;
import com.dbia.archive.page.CutTest;
import com.dbia.archive.service.dir_infService;
import com.dbia.archive.service.file_infService;
import com.dbia.archive.service.study_groupService;
import com.dbia.archive.service.usersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class logincontroller {
    @Autowired
    private com.dbia.archive.service.study_groupService study_groupService;
    @GetMapping(value = {"/","/login-1"})
    public String loginpage(){
        return "login-1";
    }

    @GetMapping(value = {"/zhuce"})
    public String zhucepage(Model model){
        List<study_group> select = study_groupService.select();
        model.addAttribute("group",select);
        return "zhuce";
    }

}

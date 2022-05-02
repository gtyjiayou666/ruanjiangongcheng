//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dbia.archive.dao;

import com.dbia.archive.model.study_group;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Mapper
public interface study_groupMapper {
    List<study_group> select();

    void addgroup(int id, int num);

    int maxid();
}

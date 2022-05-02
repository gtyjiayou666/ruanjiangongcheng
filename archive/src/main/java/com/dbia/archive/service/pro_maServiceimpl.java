package com.dbia.archive.service;

import com.dbia.archive.model.pro_ma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class pro_maServiceimpl  implements pro_maService{
    @Autowired
    private com.dbia.archive.dao.pro_maMapper pro_maMapper;

    @Override
    public List<pro_ma> select(int userid) {
        return pro_maMapper.select(userid);
    }

    @Override
    public void delete(int userid) {
        pro_maMapper.delete(userid);
    }

    @Override
    public void insert(pro_ma record) {
        pro_maMapper.insert(record);
    }

    @Override
    public List<pro_ma> selecttrue(int userid, int dirid, int cho) {
        if(pro_maMapper.selecttrue(userid,dirid, cho).size() == 0)
            return null;
        else
            return pro_maMapper.selecttrue(userid,dirid, cho);
    }
}

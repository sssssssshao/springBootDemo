package com.sfy.myproj.service;

import com.sfy.myproj.entity.Coords;
import com.sfy.myproj.respository.CoordsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordsServiceImpl {
    @Autowired
    private CoordsDao dao;

    public List<Coords> findBySsxq(String ssxq){
        return dao.findBySsxqLike(ssxq);
    }

    public List<Coords> findAll(){
        return dao.findAll();
    }
}

package com.sfy.myproj.controller;

import com.google.gson.Gson;
import com.sfy.myproj.entity.Coords;
import com.sfy.myproj.service.CoordsServiceImpl;
import com.sfy.myproj.utils.Feature;
import com.sfy.myproj.utils.FeatureCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CoordsController {
    @Autowired
    private CoordsServiceImpl coordsService;

    @ResponseBody
    @RequestMapping("list")
    public String list(){
        List<Coords> pztZrqJbs = coordsService.findBySsxq("330206081");
        Gson gson = new Gson();
        return "success:" + gson.toJson(pztZrqJbs);
    }

    @RequestMapping("show{path}")
    public String showChina(@PathVariable String path, Model model){
        return "show"+path;
    }

    @ResponseBody
    @RequestMapping("geo.json")
    public String geo(){
        List<Coords> coordsList = coordsService.findAll();
        FeatureCollection featureCollection = new FeatureCollection(coordsList);
        String result = new Gson().toJson(featureCollection);
        return result;
    }
}

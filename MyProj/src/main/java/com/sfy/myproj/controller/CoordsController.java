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

import java.util.*;

@Controller
public class CoordsController {
    @Autowired
    private CoordsServiceImpl coordsService;

    @RequestMapping("/")
    public String index(){
        return "redirect:/show";
    }

    @RequestMapping("show{path}")
    public String showChina(@PathVariable String path, Model model){
        return "show" + path;
    }

    @ResponseBody
    @RequestMapping("geo.json")
    public String geo() {
        List<Coords> coordsList = coordsService.findBySsxq("330304%");
        FeatureCollection featureCollection = new FeatureCollection(coordsList);
        String result = new Gson().toJson(featureCollection);
        return result;
    }

    @ResponseBody
    @RequestMapping("nameMap.json")
    public String nameMap(){
        List<Coords> coordsList = coordsService.findBySsxq("330304%");
        Map<String, String> nameMap = new HashMap<>();
        for (Coords coords: coordsList) {
            nameMap.put(coords.getName(), coords.getChinaName());
        }
        return new Gson().toJson(nameMap);
    }

    @ResponseBody
    @RequestMapping("data.json")
    public String data(){
        List<Coords> coordsList = coordsService.findBySsxq("330304%");
        Map<String, List> map = new HashMap<>();
        List<Map> list = new ArrayList<>();
        List<Map> yylist = new ArrayList<>();
        Random r = new Random();
        for (Coords coords: coordsList) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", coords.getName());
            data.put("value", r.nextInt(10000));
            list.add(data);
            Map<String, Object> data1 = new HashMap<>();
            data1.put("name", coords.getName());
            data1.put("value", r.nextInt(10000));
            yylist.add(data1);
        }
        map.put("重点", list);
        map.put("医院", yylist);
        return new Gson().toJson(map);
    }
}

package com.sfy.mine.controller;

import com.sfy.mine.annotation.MineRequestMapping;
import com.sfy.mine.annotation.MineRestController;

@MineRestController
public class MineIndexRestController {
    @MineRequestMapping("/indexRest")
    public String indexRest(){
        return "indexRest";
    }
}

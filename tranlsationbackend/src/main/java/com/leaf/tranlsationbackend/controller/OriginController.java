package com.leaf.tranlsationbackend.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-19 00:52:11
 * @description todo
 */
@CrossOrigin
@RestController
@RequestMapping("/origin")
public class OriginController {

    @PostMapping("/getOrigin")
    public String getOrigin(@RequestBody JSONObject originString) {
        System.out.println(originString);

        String[] str=originString.getString("origin").split(" ");

        System.out.println(Arrays.toString(str));
        for(String a:str){
            System.out.println(a);
        }

        return "test";
    }

}

package com.leaf.tranlsationbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    public String test(){
        System.out.println("fuck");
        return "test";
    }

}

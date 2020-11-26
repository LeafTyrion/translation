package com.leaf.tranlsationbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.leaf.tranlsationbackend.service.OriginService;
import com.leaf.tranlsationbackend.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    OriginService originService;
    @Autowired
    TranslationService translationService;

    @PostMapping("/getTranslationByOrigin")
    public String getOrigin(@RequestBody JSONObject originString) {
        return originService.getTranslationByOrigin(originString.getString("origin"));
    }

}

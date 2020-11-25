package com.leaf.tranlsationbackend.controller;

import com.leaf.tranlsationbackend.service.OriginService;
import com.leaf.tranlsationbackend.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-24 00:23:51
 * @description todo
 */
@CrossOrigin
@RestController
@RequestMapping("/translation")
public class TranslationController {

    @Autowired
    OriginService originService;
    @Autowired
    TranslationService translationService;



}

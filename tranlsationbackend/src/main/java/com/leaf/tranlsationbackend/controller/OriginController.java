package com.leaf.tranlsationbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.leaf.tranlsationbackend.entity.Origin;
import com.leaf.tranlsationbackend.service.OriginService;
import com.leaf.tranlsationbackend.service.TranslationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-19 00:52:11
 * @description todo
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/origin")
public class OriginController {

    @Autowired
    OriginService originService;
    @Autowired
    TranslationService translationService;

    @GetMapping("/allOrigin")
    public Object allOrigin(@RequestParam(required = false) Integer size,
                            @RequestParam(required = false) Integer page,
                            @RequestParam(required = false) String query) {
        if (page != null && size != null) {
            if (query == null) {
                return originService.getAllOrigin(page - 1, size);
            }
            return originService.getAllOrigin("%" + query + "%", page - 1, size);
        }
        return originService.getAllOrigin();
    }

    @PostMapping("/addOrigin")
    public Origin addOrigin(@RequestBody JSONObject jsonObject) throws Exception {
        return originService.addOrigin(jsonObject);
    }

    @PostMapping("/updateOrigin")
    public boolean updateOrigin(@RequestBody JSONObject jsonObject) {
        try {
            originService.updateOrigin(jsonObject);
            return true;
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return false;
    }

    @GetMapping("/deleteOrigin")
    public boolean deleteOrigin(@RequestParam("id") int id) {
        try {
            originService.deleteOriginById(id);
            return true;
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return false;
    }

    @GetMapping("/getOriginById")
    public Origin getOriginById(@RequestParam("id") int id) {
        return originService.getOriginById(id);
    }


    @PostMapping("/getTranslationByOrigin")
    public String getOrigin(@RequestBody JSONObject originString) {
        return originService.getTranslationByOrigin(originString.getString("origin"));
    }

}

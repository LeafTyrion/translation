package com.leaf.tranlsationbackend.service;

import com.leaf.tranlsationbackend.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-26 00:10:27
 * @description todo
 */
@Service
public class TranslationService {

    @Autowired
    TranslationRepository translationRepository;
}

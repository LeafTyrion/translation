package com.leaf.tranlsationbackend.service;

import com.leaf.tranlsationbackend.entity.Translation;
import com.leaf.tranlsationbackend.repository.OriginRepository;
import com.leaf.tranlsationbackend.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-24 00:17:47
 * @description todo
 */
@Service
public class OriginService {

    @Autowired
    OriginRepository originRepository;
    @Autowired
    TranslationRepository translationRepository;

    public String getTranslationByOrigin(String originString) {

        String translationString = "";

        String[] originLine = originString.split("\n");
        for (String strLine : originLine) {
            String[] originArray = strLine.split(" ");
            for (String origin : originArray) {
                List<Translation> translations = translationRepository.findTranslationsByOrigin_OriginChar(origin);
                if (translations.isEmpty()) {
                    translationString = translationString.concat("    ");
                } else {
                    translationString = translationString.concat(translations.get(0).getTranslatedChar());
                }
                translationString = translationString.concat(" ");
            }
            translationString = translationString.concat("\n");
        }
        return translationString;
    }

}

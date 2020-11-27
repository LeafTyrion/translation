package com.leaf.tranlsationbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.leaf.tranlsationbackend.entity.Origin;
import com.leaf.tranlsationbackend.entity.Translation;
import com.leaf.tranlsationbackend.repository.OriginRepository;
import com.leaf.tranlsationbackend.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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


    public Object getAllOrigin(String query, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return originRepository.findOriginByQuery("%" + query + "%", pageRequest);
    }

    public Object getAllOrigin(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return originRepository.findAllOrigin(pageRequest);
    }

    public Object getAllOrigin() {
        return originRepository.findAll();
    }

    public void updateOrigin(JSONObject jsonObject) {


        originRepository.save(jsonObject.toJavaObject(Origin.class));

    }

    public void deleteOriginById(int id) {
        originRepository.deleteById(id);
    }

    public Origin getOriginById(int id) {
        return originRepository.findById(id);
    }

    public Origin addOrigin(JSONObject jsonObject) throws Exception {

        Origin origin = new Origin();
        Translation translation = new Translation();

        String originChar = jsonObject.getString("origin");


        if (originRepository.existsByOriginChar(originChar)) {
            throw new Exception();
        }
        origin.setOriginChar(originChar);
        translation.setTranslatedChar(jsonObject.getString("translation"));

        translation.setOrigin(origin);
        origin = originRepository.save(origin);

        translationRepository.save(translation);
        return origin;
    }


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

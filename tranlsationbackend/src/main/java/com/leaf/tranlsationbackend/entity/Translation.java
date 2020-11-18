package com.leaf.tranlsationbackend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-14 02:38:44
 * @description todo
 */
@Entity
@Data
public class Translation {

    @Id
    private int id;

    private String translatedChar;

    @ManyToOne
    private Origin origin;
}

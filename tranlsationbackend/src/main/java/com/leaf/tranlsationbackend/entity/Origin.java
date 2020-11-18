package com.leaf.tranlsationbackend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-14 02:33:44
 * @description todo
 */
@Entity
@Data
public class Origin {

    @Id
    private int id;

    private String originChar;

    @OneToMany(mappedBy = "origin")
    private List<Translation> translations;
}

package com.leaf.tranlsationbackend.entity;

import lombok.Data;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String originChar;

    @OneToMany(mappedBy = "origin", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Translation> translations;
}

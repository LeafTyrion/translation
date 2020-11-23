package com.leaf.tranlsationbackend.repository;

import com.leaf.tranlsationbackend.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-24 00:22:39
 * @description todo
 */
@Repository
public interface TranslationRepository extends JpaRepository<Translation,Integer>, JpaSpecificationExecutor<Translation> {
}

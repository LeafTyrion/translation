package com.leaf.tranlsationbackend.repository;

import com.leaf.tranlsationbackend.entity.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-24 00:05:20
 * @description todo
 */
@Repository
public interface OriginRepository extends JpaRepository<Origin, Integer>, JpaSpecificationExecutor<Origin> {
}

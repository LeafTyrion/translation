package com.leaf.tranlsationbackend.repository;

import com.leaf.tranlsationbackend.entity.Origin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author YeYaQiao
 * @email yyq920201895@163.com
 * @date 2020-11-24 00:05:20
 * @description todo
 */
@Repository
public interface OriginRepository extends JpaRepository<Origin, Integer>, JpaSpecificationExecutor<Origin> {

    @Query("select origin from Origin origin")
    Page<Origin> findAllOrigin(Pageable pageable);

    @Query("select origin from Origin origin where origin.originChar like :query")
    Page<Origin> findOriginByQuery(@Param("query") String query, Pageable pageable);

    Origin findById(int id);

    Origin findByOriginChar(String originChar);

    boolean existsByOriginChar(String originChar);
}

package com.scriptlte.gopandas.modules.log.dao;

import com.scriptlte.gopandas.modules.log.pojo.LogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogEntityRepository  extends JpaRepository<LogEntity,String> {

    Page<LogEntity> findAllByOperaModule(String module,Pageable pageable);
    List<LogEntity> findAllByOperaModule(String module);
}

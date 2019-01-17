package com.scriptlte.gopandas.modules.log.service;

import com.scriptlte.gopandas.modules.log.dao.LogEntityRepository;
import com.scriptlte.gopandas.modules.log.pojo.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sun.util.locale.provider.LocaleServiceProviderPool;

import java.util.List;

@Service("logEntityService")
public class LogEntityService {
    @Autowired
    private LogEntityRepository logRepository;

    public void addLogEntity(LogEntity logEntity){
        logRepository.save(logEntity);
    }

    public List<LogEntity> getAllLogs(){
        return logRepository.findAll();
    }
    @PreAuthorize("hasRole('TESTROLE')")
    public List<LogEntity> getLogsByModule(String moduleName){
        return logRepository.findAllByOperaModule(moduleName);
    }
}

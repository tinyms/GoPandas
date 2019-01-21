package com.scriptlte.gopandas.modules.security.custom_url_access;


import com.scriptlte.gopandas.modules.security.custom_url_access.pojo.UrlAccessConfigEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UrlAccessService {
    private static UrlAccessRepository urlAccessRepository;

    @Autowired
    public void setUrlAccessRepository(UrlAccessRepository urlAccessRepo) {
        UrlAccessService.urlAccessRepository = urlAccessRepo;
    }

    public static UrlAccessConfigEntity save(UrlAccessConfigEntity urlAccessConfigEntity){
       return (UrlAccessConfigEntity) urlAccessRepository.save(urlAccessConfigEntity);
    }

    public static List<UrlAccessConfigEntity> getAll(){
        return urlAccessRepository.findAll();
    }

}

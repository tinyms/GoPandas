package com.scriptlte.gopandas.security.service;

import com.scriptlte.gopandas.security.dao.grant.OrgGrantRepository;
import com.scriptlte.gopandas.security.dao.role.OrgRoleRepository;
import com.scriptlte.gopandas.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "grantServiceCache")
public class OrgGrantService {
    @Autowired
    private OrgGrantRepository orgGrantRepository;

    @Cacheable
    public List<OrgGrant> getGrantsByIds(List<String> grantIds) {
        return orgGrantRepository.findAllById(grantIds);
    }

    public OrgGrant save(OrgGrant grant){
        return orgGrantRepository.saveAndFlush(grant);
    }
}

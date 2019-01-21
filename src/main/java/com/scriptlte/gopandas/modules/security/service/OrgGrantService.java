package com.scriptlte.gopandas.modules.security.service;

import com.scriptlte.gopandas.modules.security.dao.grant.OrgGrantRepository;
import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service("orgGrantService")
@CacheConfig(cacheNames = "grantServiceCache")
public class OrgGrantService {

    private static OrgGrantRepository orgGrantRepository;

    @Autowired
    public void setOrgGrantRepository(OrgGrantRepository orgGrantRepo) {
        OrgGrantService.orgGrantRepository = orgGrantRepo;
    }

    public List<OrgGrant> getGrantsByCodes(Set<String> grantCodes) {
        return orgGrantRepository.findOrgGrantsByGrantCodeIn(grantCodes);
    }

    public static OrgGrant save(OrgGrant grant) {
        return orgGrantRepository.saveAndFlush(grant);
    }
}

package com.scriptlte.gopandas.modules.security.service;

import com.scriptlte.gopandas.modules.security.dao.grant.OrgGrantRepository;
import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    public List<OrgGrant> getAllGrants(){
        return orgGrantRepository.findAll();
    }

    public OrgGrant getGrantByGrantCode(String grantCode) {
        return orgGrantRepository.findOrgGrantByGrantCode(grantCode);
    }

    public List<OrgGrant> getGrantsByCodes(Set<String> grantCodes) {
        return orgGrantRepository.findOrgGrantsByGrantCodeIn(grantCodes);
    }

    public List<OrgGrant> getGrantByGrantName(String grantName) {
        return orgGrantRepository.findOrgGrantByGrantName(grantName);
    }

    public static OrgGrant save(OrgGrant grant){
        return orgGrantRepository.saveAndFlush(grant);
    }
}

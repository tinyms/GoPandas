package com.scriptlte.gopandas.modules.security.service;

import com.scriptlte.gopandas.modules.security.dao.role.OrgRoleRepository;
import com.scriptlte.gopandas.modules.security.pojo.role.OrgRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orgRoleService")
@CacheConfig(cacheNames = "roleServiceCache")
public class OrgRoleService {
    @Autowired
    private OrgRoleRepository orgRoleRepository;

    public OrgRole getRoleByName(String rolename) {
        OrgRole role = orgRoleRepository.findOrgRoleByRoleName(rolename);
        return role;
    }

    public OrgRole save(OrgRole role){
        return orgRoleRepository.save(role);
    }

    public List<OrgRole> getRolesByIds(List<String> roleIds) {
        return orgRoleRepository.findAllById(roleIds);
    }
}

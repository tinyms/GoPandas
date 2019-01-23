package com.scriptlte.gopandas.modules.security.service;

import com.scriptlte.gopandas.modules.security.dao.role.OrgRoleRepository;
import com.scriptlte.gopandas.modules.security.pojo.role.OrgRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("orgRoleService")
@CacheConfig(cacheNames = "roleServiceCache")
public class OrgRoleService {
    @Autowired
    private OrgRoleRepository orgRoleRepository;

    public List<OrgRole> getAllRoles() {
        return orgRoleRepository.findAll();
    }

    public OrgRole getRoleById(String id) {
        return orgRoleRepository.findOrgRoleById(id);
    }

    public OrgRole getRoleByName(String rolename) {
        OrgRole role = orgRoleRepository.findOrgRoleByRoleName(rolename);
        return role;
    }

    public OrgRole save(OrgRole role){
        return orgRoleRepository.save(role);
    }

    public List<OrgRole> getRolesByIds(Set<String> roleIds) {
        return orgRoleRepository.findAllById(roleIds);
    }
}

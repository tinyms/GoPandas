package com.scriptlte.gopandas.security.service;

import com.scriptlte.gopandas.security.dao.role.OrgRoleRepository;
import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

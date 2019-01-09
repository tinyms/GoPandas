package com.scriptlte.gopandas.security.service;

import com.scriptlte.gopandas.security.dao.OrgRoleRepository;
import com.scriptlte.gopandas.security.dao.OrgUserRepository;
import com.scriptlte.gopandas.security.pojo.OrgRole;
import com.scriptlte.gopandas.security.pojo.OrgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrgRoleService {
    @Autowired
    private OrgRoleRepository orgRoleRepository;

    public OrgRole getRoleByName(String rolename) throws UsernameNotFoundException {
        OrgRole Role = orgRoleRepository.findOrgRoleByRoleName(rolename);
        return Role;
    }

    public OrgRole save(OrgRole role){
        return orgRoleRepository.save(role);
    }
}

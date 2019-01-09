package com.scriptlte.gopandas.security.dao;

import com.scriptlte.gopandas.security.pojo.OrgRole;
import org.springframework.data.repository.CrudRepository;

public interface OrgRoleRepository extends CrudRepository<OrgRole,Long> {
    OrgRole findOrgRoleByRoleName(String rolename);
}

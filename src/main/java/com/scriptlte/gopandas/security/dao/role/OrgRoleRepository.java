package com.scriptlte.gopandas.security.dao.role;

import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRoleRepository extends JpaRepository<OrgRole,String> {
    OrgRole findOrgRoleByRoleName(String rolename);
}

package com.scriptlte.gopandas.modules.security.dao.role;

import com.scriptlte.gopandas.modules.security.pojo.role.OrgRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRoleRepository extends JpaRepository<OrgRole,String> {
    OrgRole findOrgRoleByRoleName(String rolename);
    OrgRole findOrgRoleById(String id);
}

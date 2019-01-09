package com.scriptlte.gopandas.security.dao;

import com.scriptlte.gopandas.security.pojo.OrgUser;
import org.springframework.data.repository.CrudRepository;

public interface OrgUserRepository extends CrudRepository<OrgUser,Long> {
    OrgUser findOrgUserByUsername(String username);
}

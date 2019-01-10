package com.scriptlte.gopandas.security.dao.user;

import com.scriptlte.gopandas.security.pojo.user.OrgUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgUserRepository extends JpaRepository<OrgUser,String> {
    OrgUser findOrgUserByUsername(String username);
}

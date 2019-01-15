package com.scriptlte.gopandas.modules.security.dao.grant;

import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgGrantRepository extends JpaRepository<OrgGrant,String> {
    OrgGrant findOrgGrantByGrantName(String grantName);
    List<OrgGrant> findOrgGrantsByIdIn(List<String> grantNames);
}

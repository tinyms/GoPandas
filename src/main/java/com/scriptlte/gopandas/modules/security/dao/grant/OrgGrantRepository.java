package com.scriptlte.gopandas.modules.security.dao.grant;

import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrgGrantRepository extends JpaRepository<OrgGrant,String> {
    OrgGrant findOrgGrantByGrantName(String grantName);
    List<OrgGrant> findOrgGrantsByGrantCodeIn(Set<String> grantCodes);
    OrgGrant findOrgGrantByGrantCode(String grantCode);
}

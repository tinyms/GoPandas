package com.scriptlte.gopandas.security.dao.grant;

import com.scriptlte.gopandas.security.pojo.grant.OrgGrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrgGrantRepository extends JpaRepository<OrgGrant,String> {
    OrgGrant findOrgGrantByGrantName(String grantName);
    List<OrgGrant> findOrgGrantsByIdIn(List<String> grantNames);
}

package com.scriptlte.gopandas.modules.security.dao.relation;

import com.scriptlte.gopandas.modules.security.pojo.relation.OrgRel_X_Grant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface OrgRel_X_GrantRepository extends JpaRepository<OrgRel_X_Grant, String> {

    @Query(value="select xg.grantCode from OrgRel_X_Grant xg where xg.relObjectId In (:relObjIds) ")
    Set<String> queryGrantCodesByRelObjectIds(@Param("relObjIds")List<String> relObjIds);
}

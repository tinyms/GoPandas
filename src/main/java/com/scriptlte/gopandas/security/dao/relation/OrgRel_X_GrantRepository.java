package com.scriptlte.gopandas.security.dao.relation;

import com.scriptlte.gopandas.security.pojo.relation.OrgRel_X_Grant;
import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrgRel_X_GrantRepository extends JpaRepository<OrgRel_X_Grant, String> {

    @Query(value="select grantId from OrgRel_X_Grant xg where xg.relObjectId In (:relObjIds) ")
    List<String> queryGrantIdsByRelObjectIds(@Param("relObjIds")List<String> relObjIds);
}

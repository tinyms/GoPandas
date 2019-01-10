package com.scriptlte.gopandas.security.pojo.relation;

import com.scriptlte.gopandas.security.pojo.relation.relationid.X_Role_RelationId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class OrgRel_X_Role implements Serializable {

    private static final long serialVersionUID = -8314562893920558260L;

    @EmbeddedId
    private X_Role_RelationId x_role_relationId;
    @Column(nullable = false)
    private String relObjectType;
}

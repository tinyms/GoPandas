package com.scriptlte.gopandas.security.pojo.relation;

import com.scriptlte.gopandas.security.config.SecurityConstant;
import com.scriptlte.gopandas.security.pojo.relation.relationid.X_Grant_RelationId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class OrgRel_X_Grant implements Serializable {

    private static final long serialVersionUID = 6890984640925545542L;

    @EmbeddedId
    private X_Grant_RelationId x_grant_relationId;
    @Column(nullable = false)
    private String relObjectType;
}

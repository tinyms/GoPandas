package com.scriptlte.gopandas.security.pojo.relation.relationid;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class X_Role_RelationId implements Serializable {
    private String relObjectId;
    private String roleId;
}

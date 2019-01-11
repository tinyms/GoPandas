package com.scriptlte.gopandas.security.pojo.relation;

import com.scriptlte.gopandas.security.config.SecurityConstant;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"relObjectId","grantId"})})
public class OrgRel_X_Grant implements Serializable {

    private static final long serialVersionUID = 6890984640925545542L;

    @Id
    @GeneratedValue(generator = "jpa-uuid-rel-x-grant")
    @GenericGenerator(name = "jpa-uuid-rel-x-grant",strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String relObjectId;
    @Column(nullable = false)
    private String grantId;
    @Column(nullable = false)
    private String relObjectType;

    public OrgRel_X_Grant(String id, String relObjectId, String grantId, String relObjectType) {
        this.id = id;
        this.relObjectId = relObjectId;
        this.grantId = grantId;
        this.relObjectType = relObjectType;
    }

    public OrgRel_X_Grant(String relObjectId, String grantId, String relObjectType) {
        this.relObjectId = relObjectId;
        this.grantId = grantId;
        this.relObjectType = relObjectType;
    }

    public OrgRel_X_Grant() {
    }
}

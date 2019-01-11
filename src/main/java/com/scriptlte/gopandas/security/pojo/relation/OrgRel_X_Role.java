package com.scriptlte.gopandas.security.pojo.relation;

import lombok.Data;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"relObjectId","roleId"})})
public class OrgRel_X_Role implements Serializable {

    private static final long serialVersionUID = -8314562893920558260L;

    @Id
    @GeneratedValue(generator = "jpa-uuid-rel-x-role")
    @GenericGenerator(name = "jpa-uuid-rel-x-role",strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String relObjectId;
    @Column(nullable = false)
    private String roleId;
    @Column(nullable = false)
    private String relObjectType;

    public OrgRel_X_Role(String id, String relObjectId, String roleId, String relObjectType) {
        this.id = id;
        this.relObjectId = relObjectId;
        this.roleId = roleId;
        this.relObjectType = relObjectType;
    }

    public OrgRel_X_Role(String relObjectId, String roleId, String relObjectType) {
        this.relObjectId = relObjectId;
        this.roleId = roleId;
        this.relObjectType = relObjectType;
    }

    public OrgRel_X_Role() {}
}

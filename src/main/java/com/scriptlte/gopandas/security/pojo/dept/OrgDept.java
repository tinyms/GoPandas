package com.scriptlte.gopandas.security.pojo.dept;

import com.scriptlte.gopandas.security.pojo.employee.OrgEmployee;
import com.scriptlte.gopandas.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class OrgDept {
    @Id
    @GeneratedValue(generator = "jpa-uuid-dept")
    @GenericGenerator(name = "jpa-uuid-dept",strategy = "uuid")
    @Column(length = 32)
    private String id;
    @Column(unique = true,nullable = false)
    private String deptCode;
    @Column(nullable = false)
    private String deptName;
    private String parentCode;
    private String deptType;
    @Transient
    private List<OrgGrant> grants;
    @Transient
    private List<OrgRole> roles;
    @Transient
    private List<OrgEmployee> eployees;
}

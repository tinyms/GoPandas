package com.scriptlte.gopandas.modules.security.pojo.employee;

import com.scriptlte.gopandas.modules.security.pojo.dept.OrgDept;
import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.modules.security.pojo.role.OrgRole;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class OrgEmployee {
    @Id
    @GeneratedValue(generator = "jpa-uuid-employee")
    @GenericGenerator(name = "jpa-uuid-employee",strategy = "uuid")
    @Column(length = 32)
    private String id;
    @Column(unique = true)
    private String employeeCode;
    private String name;
    private short age;
    private String phoneNumber;
    private String email;
    private String deptId;
    @Transient
    private List<OrgGrant> grants;
    @Transient
    private List<OrgRole> roles;
    @Transient
    private OrgDept orgDept;
}

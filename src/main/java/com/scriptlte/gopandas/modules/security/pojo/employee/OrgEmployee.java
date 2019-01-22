package com.scriptlte.gopandas.modules.security.pojo.employee;

import com.scriptlte.gopandas.modules.security.pojo.dept.OrgDept;
import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.modules.security.pojo.role.OrgRole;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class OrgEmployee implements Serializable{
    private static final long serialVersionUID = 7839126919142789501L;
    @Id
    @GeneratedValue(generator = "jpa-uuid-employee")
    @GenericGenerator(name = "jpa-uuid-employee",strategy = "uuid")
    @Column(length = 32)
    private String id;
    @Column(unique = true,nullable = false)
    private String employeeCode;
    private String deptCode;
    private String name;
    private short age;
    private String phoneNumber;
    private String email;
    @Transient
    private List<OrgGrant> grants;
    @Transient
    private List<OrgRole> roles;
    @Transient
    private OrgDept orgDept;

    public OrgEmployee() {
    }

    public OrgEmployee(String id,String employeeCode, String deptCode, String name, short age, String phoneNumber, String email) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.deptCode = deptCode;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}

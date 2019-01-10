package com.scriptlte.gopandas.security.pojo;

import org.apache.catalina.LifecycleState;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class OrgDept {
    @Id
    @GeneratedValue
    private Long id;
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

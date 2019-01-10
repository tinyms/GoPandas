package com.scriptlte.gopandas.security.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

public class OrgEmployee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private short age;
    private String phoneNumber;
    private String email;
    @Transient
    private List<OrgGrant> grants;
    @Transient
    private List<OrgRole> roles;
}

package com.scriptlte.gopandas.security.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class OrgRole implements Serializable {

    private static final long serialVersionUID = 4259151026867212382L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String roleName;
    @Transient
    private List<OrgGrant> grants;

}

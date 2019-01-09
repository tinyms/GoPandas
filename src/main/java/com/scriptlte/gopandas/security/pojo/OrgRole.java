package com.scriptlte.gopandas.security.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class OrgRole implements Serializable {

    private static final long serialVersionUID = 4259151026867212382L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String roleName;

}

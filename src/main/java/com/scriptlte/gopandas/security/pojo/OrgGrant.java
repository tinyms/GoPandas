package com.scriptlte.gopandas.security.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Data
public class OrgGrant {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String grantname;
}

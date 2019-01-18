package com.scriptlte.gopandas.modules.security.custom_url_access.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UrlAccessConfigEntity {

    @Id
    @GeneratedValue(generator = "jpa-uuid-customUrlAccessConfig")
    @GenericGenerator(name = "jpa-uuid-customUrlAccessConfig",strategy = "uuid")
    private String id;
    private String antUrlPattern;
    @Column(nullable = false)
    private String roleExpress;
    @Column(nullable = false)
    private String authorityExpress;
}

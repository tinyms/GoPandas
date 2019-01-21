package com.scriptlte.gopandas.modules.security.custom_url_access.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * url访问配置实体类，一个配置实体对应一个权限编码
 * 在配置权限编码之前需要确认该权限编码已有对应的权限对象在数据库中！
 */
@Entity
@Data
public class UrlAccessConfigEntity {

    @Id
    @GeneratedValue(generator = "jpa-uuid-customUrlAccessConfig")
    @GenericGenerator(name = "jpa-uuid-customUrlAccessConfig",strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String antUrlPattern;
    @Column(nullable = false)
    private String grantCode;
}

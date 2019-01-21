package com.scriptlte.gopandas.modules.security.custom_url_access.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * url访问配置实体类，一个配置实体对应一个权限编码
 * 在配置权限编码之前需要确认该权限编码已有对应的权限对象在数据库中！
 */
@Entity
@Data
public class UrlAccessConfigEntity implements Serializable {
    private static final long serialVersionUID = 7314973018307525185L;
    @Id
    private String antUrlPattern;
    @Column(nullable = false)
    private String grantCode;

    public UrlAccessConfigEntity(String antUrlPattern, String grantCode) {
        this.antUrlPattern = antUrlPattern;
        this.grantCode = grantCode;
    }

    public UrlAccessConfigEntity() {
    }
}

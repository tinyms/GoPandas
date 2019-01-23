package com.scriptlte.gopandas.modules.security.pojo.role;

import com.scriptlte.gopandas.modules.security.config.SecurityConstant;
import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 注:由于 SpringSecurity框架会对角色和权限作统一处理，且会根据 ROLE_前缀做来区分角色和权限
*  所以在getAuthority方法中返回的角色编码要加上前缀
 * 在用户和数据库中可以不用保存这个前缀
 */
@Entity
@Data
public class OrgRole implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 4259151026867212382L;

    @Id
    @GeneratedValue(generator = "jpa-uuid-role")
    @GenericGenerator(name = "jpa-uuid-role",strategy = "uuid")
    @Column(length = 32)
    private String id;
    @Column(unique = true)
    private String roleName;
    @Column(nullable = false)
    private String CreaterUsername;
    @Transient
    private List<OrgGrant> grants;

    @Override
    public String getAuthority() {
        if (!roleName.startsWith(SecurityConstant.ROLE_PREFIX)){
            roleName = SecurityConstant.ROLE_PREFIX + roleName;
            return roleName;
        }
        return roleName;
    }

    public OrgRole() {
    }

    public OrgRole(String id,String roleName, String createrUsername) {
        this.id = id;
        this.roleName = roleName;
        this.CreaterUsername = createrUsername;
    }
}

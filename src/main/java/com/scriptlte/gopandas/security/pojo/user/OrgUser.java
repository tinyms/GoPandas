package com.scriptlte.gopandas.security.pojo.user;

import com.scriptlte.gopandas.security.config.SecurityConstant;
import com.scriptlte.gopandas.security.pojo.employee.OrgEmployee;
import com.scriptlte.gopandas.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
public class OrgUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 6427512514997356L;

    @Id
    @GeneratedValue(generator = "jpa-uuid-user")
    @GenericGenerator(name = "jpa-uuid-user",strategy = "uuid")
    @Column(length = 32)
    private String id;
    private String employeeId;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String status;
    private String nickname;
    @Transient
    private Set<OrgRole> roles;
    @Transient
    private Set<OrgGrant> grants;
    @Transient
    private OrgEmployee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> auths = new HashSet<>();
        for (OrgRole role : roles){
            auths.add(role);
        }
        for (OrgGrant grant : grants){
            auths.add(grant);
        }
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return SecurityConstant.USER_STATUS_ENABLE.equals(status)?true:false;
    }
}

package com.scriptlte.gopandas.security.pojo;

import com.scriptlte.gopandas.security.config.SecurityConstant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class OrgUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 6427512514997356L;

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String status;
    @ManyToMany(targetEntity = OrgRole.class)
    private List<OrgRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

package com.scriptlte.gopandas.modules.security.pojo.grant;

import com.scriptlte.gopandas.modules.security.config.SecurityConstant;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
public class OrgGrant implements GrantedAuthority {
    @Id
    private String grantCode;
    @Column(nullable = false)
    private String grantName;
    @Column(nullable = false)
    private String grantType = SecurityConstant.GRANT_TYPE_DEFAULT;

    @Override
    public String getAuthority() {
        return grantCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrgGrant)) return false;
        OrgGrant orgGrant = (OrgGrant) o;
        return  Objects.equals(grantCode, orgGrant.grantCode) &&
                Objects.equals(grantName, orgGrant.grantName) &&
                Objects.equals(grantType, orgGrant.grantType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grantCode, grantName, grantType);
    }
}

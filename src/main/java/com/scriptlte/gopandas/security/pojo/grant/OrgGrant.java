package com.scriptlte.gopandas.security.pojo.grant;

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
    @GeneratedValue(generator = "jpa-uuid-grant")
    @GenericGenerator(name = "jpa-uuid-grant",strategy = "uuid")
    @Column(length = 32)
    private String id;
    @Column(unique = true)
    private String grantName;

    @Override
    public String getAuthority() {
        return grantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrgGrant)) return false;
        OrgGrant orgGrant = (OrgGrant) o;
        return Objects.equals(id, orgGrant.id) &&
                Objects.equals(grantName, orgGrant.grantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grantName);
    }
}

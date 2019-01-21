package com.scriptlte.gopandas.modules.security.custom_url_access;

import com.scriptlte.gopandas.modules.security.custom_url_access.pojo.UrlAccessConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlAccessRepository extends JpaRepository<UrlAccessConfigEntity,String> {
}

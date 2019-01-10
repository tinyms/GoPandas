package com.scriptlte.gopandas.security.dao.dept;

import com.scriptlte.gopandas.security.pojo.dept.OrgDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrgDeptRepository extends JpaRepository<OrgDept,String> {
    OrgDept findOrgDeptByDeptCode(String deptCode);
}

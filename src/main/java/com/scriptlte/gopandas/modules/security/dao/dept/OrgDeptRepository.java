package com.scriptlte.gopandas.modules.security.dao.dept;

import com.scriptlte.gopandas.modules.security.pojo.dept.OrgDept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgDeptRepository extends JpaRepository<OrgDept,String> {
    OrgDept findOrgDeptByDeptCode(String deptCode);
    List<OrgDept> findOrgDeptByDeptName(String deptName);
}

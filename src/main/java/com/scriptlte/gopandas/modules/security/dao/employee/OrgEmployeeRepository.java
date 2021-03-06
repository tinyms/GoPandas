package com.scriptlte.gopandas.modules.security.dao.employee;

import com.scriptlte.gopandas.modules.security.pojo.employee.OrgEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgEmployeeRepository extends JpaRepository<OrgEmployee,String> {
    OrgEmployee findOrgEmployeeByEmployeeCode(String employeeCode);
}

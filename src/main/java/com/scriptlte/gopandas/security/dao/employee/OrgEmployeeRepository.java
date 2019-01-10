package com.scriptlte.gopandas.security.dao.employee;

import com.scriptlte.gopandas.security.pojo.employee.OrgEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrgEmployeeRepository extends JpaRepository<OrgEmployee,String> {
}

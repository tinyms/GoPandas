package com.scriptlte.gopandas.security.service;

import com.scriptlte.gopandas.security.dao.employee.OrgEmployeeRepository;
import com.scriptlte.gopandas.security.dao.role.OrgRoleRepository;
import com.scriptlte.gopandas.security.pojo.employee.OrgEmployee;
import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrgEmployeeService {
    @Autowired
    private OrgEmployeeRepository orgEmployeeRepository;

    public OrgEmployee getEmpolyee(String employeeId) {
        OrgEmployee employee = orgEmployeeRepository.getOne(employeeId);
        return employee;
    }

    public OrgEmployee save(OrgEmployee employee){
        return orgEmployeeRepository.saveAndFlush(employee);
    }
}

package com.scriptlte.gopandas.modules.security.service;

import com.scriptlte.gopandas.modules.security.dao.employee.OrgEmployeeRepository;
import com.scriptlte.gopandas.modules.security.pojo.employee.OrgEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orgEmployeeService")
public class OrgEmployeeService {
    @Autowired
    private OrgEmployeeRepository orgEmployeeRepository;

    public OrgEmployee getEmpolyeeById(String employeeId) {
        OrgEmployee employee = orgEmployeeRepository.getOne(employeeId);
        return employee;
    }

    public OrgEmployee getEmpolyeeByCode(String employeeCode) {
        OrgEmployee employee = orgEmployeeRepository.findOrgEmployeeByEmployeeCode(employeeCode);
        return employee;
    }

    public OrgEmployee save(OrgEmployee employee){
        return orgEmployeeRepository.saveAndFlush(employee);
    }
}

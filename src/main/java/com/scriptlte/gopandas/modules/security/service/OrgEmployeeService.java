package com.scriptlte.gopandas.modules.security.service;

import com.scriptlte.gopandas.modules.security.dao.employee.OrgEmployeeRepository;
import com.scriptlte.gopandas.modules.security.pojo.employee.OrgEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orgEmployeeService")
public class OrgEmployeeService {
    @Autowired
    private OrgEmployeeRepository orgEmployeeRepository;

    public List<OrgEmployee> getAllEmployees(){
        return orgEmployeeRepository.findAll();
    }

    public OrgEmployee getEmployeeById(String employeeId) {
        OrgEmployee employee = orgEmployeeRepository.getOne(employeeId);
        return employee;
    }

    public OrgEmployee getEmployeByCode(String employeeCode) {
        OrgEmployee employee = orgEmployeeRepository.findOrgEmployeeByEmployeeCode(employeeCode);
        return employee;
    }

    public List<OrgEmployee> getEmployeebyEmployeeName(String employeeName){
        return orgEmployeeRepository.findOrgEmployeeByName(employeeName);
    }

    public OrgEmployee save(OrgEmployee employee){
        return orgEmployeeRepository.saveAndFlush(employee);
    }
}

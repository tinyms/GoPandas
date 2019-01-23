package com.scriptlte.gopandas.modules.security.service;

import com.scriptlte.gopandas.modules.security.dao.dept.OrgDeptRepository;
import com.scriptlte.gopandas.modules.security.pojo.dept.OrgDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orgDeptService")
public class OrgDeptService {
    @Autowired
    private OrgDeptRepository orgDeptRepository;

    public List<OrgDept> getAllDepts(){
        return orgDeptRepository.findAll();
    }

    public OrgDept getDeptById(String deptId) {
        return orgDeptRepository.getOne(deptId);
    }

    public OrgDept getDeptByDeptCode(String deptCode) {
        return orgDeptRepository.findOrgDeptByDeptCode(deptCode);
    }

    public List<OrgDept> getDeptByDeptName(String deptName){
        return orgDeptRepository.findOrgDeptByDeptName(deptName);
    }

    public OrgDept save(OrgDept dept){
        return orgDeptRepository.saveAndFlush(dept);
    }
}

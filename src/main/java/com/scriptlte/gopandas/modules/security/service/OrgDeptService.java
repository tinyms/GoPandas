package com.scriptlte.gopandas.modules.security.service;

import com.scriptlte.gopandas.modules.security.dao.dept.OrgDeptRepository;
import com.scriptlte.gopandas.modules.security.pojo.dept.OrgDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orgDeptService")
public class OrgDeptService {
    @Autowired
    private OrgDeptRepository orgDeptRepository;

    public OrgDept getDeptById(String deptId) {
        OrgDept dept = orgDeptRepository.getOne(deptId);
        return dept;
    }

    public OrgDept getDeptByDeptCode(String deptCode) {
        OrgDept dept = orgDeptRepository.findOrgDeptByDeptCode(deptCode);
        return dept;
    }

    public OrgDept save(OrgDept dept){
        return orgDeptRepository.saveAndFlush(dept);
    }
}

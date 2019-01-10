package com.scriptlte.gopandas.security.service;

import com.scriptlte.gopandas.security.dao.dept.OrgDeptRepository;
import com.scriptlte.gopandas.security.dao.role.OrgRoleRepository;
import com.scriptlte.gopandas.security.pojo.dept.OrgDept;
import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
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

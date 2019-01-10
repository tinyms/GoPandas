package com.scriptlte.gopandas.security.service;

import com.scriptlte.gopandas.security.dao.relation.OrgRel_X_GrantRepository;
import com.scriptlte.gopandas.security.dao.relation.OrgRel_X_RoleRepository;
import com.scriptlte.gopandas.security.dao.user.OrgUserRepository;
import com.scriptlte.gopandas.security.pojo.dept.OrgDept;
import com.scriptlte.gopandas.security.pojo.employee.OrgEmployee;
import com.scriptlte.gopandas.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.security.pojo.role.OrgRole;
import com.scriptlte.gopandas.security.pojo.user.OrgUser;
import com.scriptlte.gopandas.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service("orgUserService")
public class OrgUserService implements UserDetailsService {

    /*Repositorys*/
    @Autowired
    private OrgUserRepository orgUserRepository;
    @Autowired
    private OrgRel_X_RoleRepository rel_RoleRepository;
    @Autowired
    private OrgRel_X_GrantRepository rel_GrantRepository;

    /*Services*/
    @Autowired
    private OrgRoleService orgRoleService;
    @Autowired
    private OrgDeptService orgDeptService;
    @Autowired
    private OrgEmployeeService orgEmployeeService;
    @Autowired
    private OrgGrantService orgGrantService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OrgUser user = orgUserRepository.findOrgUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("用户不存在！(用户名:[%s])", username));
        }
        //分析用户关联的员工
        OrgEmployee employee = analyzeEmployee(user);
        //分析员工所对应的部门
        OrgDept dept = analyzeDept(employee);
        //分析用户所有拥有的所有角色
        Set<OrgRole> roles = analyzeRoles(user, employee, dept);
        //分析用户所拥有的所有权限
        analyzeGrants(user, employee, dept, roles);
        return user;
    }

    public OrgUser saveOrUpdate(OrgUser user) {
        return orgUserRepository.save(user);
    }

    public OrgUser getUserByUserName(String username) {
        OrgUser user = orgUserRepository.findOrgUserByUsername(username);
        return user;
    }


    public OrgEmployee analyzeEmployee(OrgUser user) {
        if (user == null) {
            return null;
        }
        OrgEmployee employee = null;
        String empolyeeId = user.getEmployeeId();
        //获取用户所关联的员工
        if (StringUtils.isNotBlank(empolyeeId)){
            employee = orgEmployeeService.getEmpolyee(empolyeeId);
            user.setEmployee(employee);
        }
        return employee;
    }

    public OrgDept analyzeDept(OrgEmployee employee) {
        if (employee == null) {
            return null;
        }
        OrgDept dept = null;
        String deptCode = employee.getDeptCode();
        //获取员工所关联的部门
        if (StringUtils.isNotBlank(deptCode)){
            dept = orgDeptService.getDeptByDeptCode(deptCode);
            employee.setOrgDept(dept);
        }
        return dept;
    }

    /**
     * 根据 userID employeeID deptID 获取用户应该具备的所有角色
     *
     * @param user     用户对象 不可能为Null
     * @param employee 员工对象 可能为Null
     * @param dept     部门对象 可能为Null
     * @return
     */
    public Set<OrgRole> analyzeRoles(OrgUser user, OrgEmployee employee, OrgDept dept) {
        Set<OrgRole> orgRoles = new HashSet<>();
        List<String> ids = packageIds(user, employee, dept, null, "角色分析");
        //根据ids去 角色关联表中查询所有的角色对象
        if (!ids.isEmpty()) {
            //获得所有的role的Id
            List<String> roleIds = rel_RoleRepository.queryRoleIdsByRelObjectIds(ids);
            orgRoles.addAll(orgRoleService.getRolesByIds(roleIds));
        }
        user.setRoles(orgRoles);
        return orgRoles;
    }

    /**
     * @param user 用户对象 不可能为Null
     * @param employee 员工对象 可能为Null
     * @param dept 部门对象 可能为Null
     * @param roles 角色对象列表 可能为Null
     */
    public Set<OrgGrant> analyzeGrants(OrgUser user, OrgEmployee employee, OrgDept dept, Set<OrgRole> roles) {
        //如果用户未绑定员工则为null
        Set<OrgGrant> orgGrants = new HashSet<>();
        List<String> ids = packageIds(user,employee ,dept , roles, "权限分析");
        //如果ids不为空,查询出所有对应的权限对象
        if (!ids.isEmpty()){
            List<String> grantIds = rel_GrantRepository.queryGrantIdsByRelObjectIds(ids);
            orgGrants.addAll(orgGrantService.getGrantsByIds(grantIds));
            user.setGrants(orgGrants);
        }
        return orgGrants;
    }

    public List<String> packageIds(OrgUser user, OrgEmployee employee, OrgDept dept, Set<OrgRole> roles, String type){
        List<String> ids = new ArrayList<>();
            ids.add(user.getId());
            if (employee != null) {
                ids.add(employee.getId());
            } else {
                LogUtil.printDebug(String.format("用户%s(用户名 [%s]): employee对象为null...", type, user.getUsername()), log);
            }
            if (dept != null) {
                ids.add(dept.getId());
            } else {
                LogUtil.printDebug(String.format("用户%s(用户名 [%s]): dept对象为null...", type, user.getUsername()), log);
            }
            if (roles != null) {
                for (OrgRole role : roles) {
                    ids.add(role.getId());
                }
            } else {
                LogUtil.printDebug(String.format("用户%s(用户名 [%s]): role集合为null...", type, user.getUsername()), log);
            }
        return ids;
    }
}

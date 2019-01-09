package com.scriptlte.gopandas.security.service;

import com.scriptlte.gopandas.security.dao.OrgUserRepository;
import com.scriptlte.gopandas.security.pojo.OrgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("orgUserService")
public class OrgUserService implements UserDetailsService {

    @Autowired
    private OrgUserRepository orgUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OrgUser user = orgUserRepository.findOrgUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(String.format("用户名[%s]对应用户不存在", username));
        }
        return user;
    }

    public OrgUser saveOrUpdate(OrgUser user){
        return orgUserRepository.save(user);
    }

    public OrgUser getUserByUserName(String username){
        OrgUser user = orgUserRepository.findOrgUserByUsername(username);
        return user;
    }
}

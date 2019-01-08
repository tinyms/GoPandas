/*
 * Copyright 2014-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scriptlte.gopandas;

import com.scriptlte.gopandas.models.Employee;
import com.scriptlte.gopandas.models.EmployeeRepository;
import com.scriptlte.gopandas.models.Item;
import com.scriptlte.gopandas.models.ItemRepository;
import com.scriptlte.gopandas.security.config.SecurityConstant;
import com.scriptlte.gopandas.security.dao.OrgRoleRepository;
import com.scriptlte.gopandas.security.dao.OrgUserRepository;
import com.scriptlte.gopandas.security.pojo.OrgRole;
import com.scriptlte.gopandas.security.pojo.OrgUser;
import com.scriptlte.gopandas.security.service.OrgRoleService;
import com.scriptlte.gopandas.security.service.OrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * This example shows various ways to secure Spring Data REST applications using Spring Security
 *
 * @author Greg Turnquist
 */

@SpringBootApplication
public class Application {

	@Autowired
    ItemRepository itemRepository;
	@Autowired
    EmployeeRepository employeeRepository;

	@Autowired
	OrgUserService orgUserService;
	@Autowired
	OrgRoleService orgRoleService;
	@Autowired
	OrgUserRepository orgUserRepository;
	@Autowired
	OrgRoleRepository orgRoleRepository;
	public static void main(String[] args) {
	    SpringApplication.run(Application.class);
	}

	/**
	 * Pre-load the system with employees and items.
	 */
	public @PostConstruct void init() {
		addUserRole();
//		employeeRepository.save(new Employee("Bilbo", "Baggins", "thief"));
//		employeeRepository.save(new Employee("Frodo", "Baggins", "ring bearer"));
//		employeeRepository.save(new Employee("Gandalf", "the Wizard", "servant of the Secret Fire"));

		/**
		 * Due to method-level protections on {@link example.company.ItemRepository}, the security context must be loaded
		 * with an authentication token containing the necessary privileges.
		 */
//		SecurityUtils.runAs("system", "system", "ROLE_ADMIN");

//		itemRepository.save(new Item("Sting"));
//		itemRepository.save(new Item("the one ring"));

	}

	public void addUserRole(){
		OrgUser userAdmin = orgUserService.getUserByUserName("admin");
		if (userAdmin==null)
			userAdmin = new OrgUser();
		userAdmin.setUsername("admin");
		userAdmin.setPassword("123");
		userAdmin.setStatus(SecurityConstant.USER_STATUS_ENABLE);
		orgUserService.saveOrUpdate(userAdmin);
		OrgUser user = orgUserService.getUserByUserName("zhangsan");
		if (user == null)
			user = new OrgUser();
		user.setUsername("zhangsan");
		user.setPassword("123");
		user.setStatus(SecurityConstant.USER_STATUS_ENABLE);
		orgUserService.saveOrUpdate(user);
		OrgRole role = orgRoleService.getRoleByName("角色名");
		if (role == null){
			role = new OrgRole();
		}
		List<OrgRole> roles = new ArrayList<>();
		role.setRoleName("角色名");
		orgRoleService.save(role);
		roles.add(role);
		user.setRoles(roles);
		orgUserService.saveOrUpdate(user);
	}
}

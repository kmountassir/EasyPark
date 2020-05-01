package iga.tiw.easypark.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import iga.tiw.easypark.entities.RoleApp;
import iga.tiw.easypark.entities.UserApp;
import iga.tiw.easypark.repository.RoleAppRepository;
import iga.tiw.easypark.repository.UserAppRepository;

@Service
@Transactional
public class AccountService {
	@Autowired
	UserAppRepository userAppRepository;
	
	@Autowired
	RoleAppRepository roleAppRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserApp getUserByUsername(String username) {
		return userAppRepository.findByUsername(username);
	}
	
	public RoleApp getRoleByRolename(String rolename) {
		return roleAppRepository.findByRolename(rolename);
	}
	
	public UserApp saveUser(UserApp userApp) {
		userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
		return userAppRepository.save(userApp);
	}
	
	public RoleApp saveRole(RoleApp roleApp) {
		return roleAppRepository.save(roleApp);
	}
	
	public void addRoleToUser(String username,String rolename) {
		UserApp user = userAppRepository.findByUsername(username);
		RoleApp role = roleAppRepository.findByRolename(rolename);
		user.getRoles().add(role);
	}
}

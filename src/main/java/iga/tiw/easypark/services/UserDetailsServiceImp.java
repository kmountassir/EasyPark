package iga.tiw.easypark.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import iga.tiw.easypark.entities.UserApp;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApp user = accountService.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Username : "+username);
		}
		List<GrantedAuthority>authorities = new ArrayList<>();
		user.getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));
		});
		return new User(user.getUsername(),user.getPassword(),authorities);
	}

}

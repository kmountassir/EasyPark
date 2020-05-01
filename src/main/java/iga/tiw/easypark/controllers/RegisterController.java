package iga.tiw.easypark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iga.tiw.easypark.entities.UserApp;
import iga.tiw.easypark.services.AccountService;
import iga.tiw.easypark.services.RegisterForm;

@RestController
public class RegisterController {
	
	@Autowired
	AccountService accountService;
	

	@PostMapping("/register")
	public UserApp register(@RequestBody RegisterForm userForm) {
		UserApp user = accountService.getUserByUsername(userForm.getUsername());
		if(user!=null)
			throw new RuntimeException("Username Already exists");
		if(!userForm.getPassword().equals(userForm.getRepassword()))
			throw new RuntimeException("Paswords should be the same");
		user = new UserApp();
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		accountService.saveUser(user);
		accountService.addRoleToUser(user.getUsername(), "USER");
		return user ;
	}
}

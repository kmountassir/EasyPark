package iga.tiw.easypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import iga.tiw.easypark.entities.RoleApp;
import iga.tiw.easypark.entities.UserApp;
import iga.tiw.easypark.repository.UserAppRepository;
import iga.tiw.easypark.services.AccountService;

@SpringBootApplication
public class EasyParkApplication implements CommandLineRunner {
	
	@Autowired
	AccountService accountService;
	@Autowired
	UserAppRepository userAppRepository;

	public static void main(String[] args) {
		SpringApplication.run(EasyParkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		accountService.saveUser(new UserApp(null, "admin", "1234", null, null,null));
		accountService.saveUser(new UserApp(null, "user", "1234", null, null,null));
		accountService.saveRole(new RoleApp(null,"ADMIN"));
		accountService.saveRole(new RoleApp(null,"USER"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("user", "USER");
		userAppRepository.findAll().forEach(user->{
			System.out.println(user);
		});
	}

}

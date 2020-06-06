package iga.tiw.easypark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import iga.tiw.easypark.entities.Location;
import iga.tiw.easypark.entities.Path;
import iga.tiw.easypark.entities.RoleApp;
import iga.tiw.easypark.entities.UserApp;
import iga.tiw.easypark.entities.Voice;
import iga.tiw.easypark.repository.PathRepository;
import iga.tiw.easypark.repository.UserAppRepository;
import iga.tiw.easypark.repository.VoiceRepository;
import iga.tiw.easypark.services.AccountService;

@SpringBootApplication
@Transactional
public class EasyParkApplication implements CommandLineRunner {
	
	@Autowired
	AccountService accountService;
	@Autowired
	UserAppRepository userAppRepository;
	@Autowired
	VoiceRepository voiceRepository;
	@Autowired
	PathRepository pathRepository;

	public static void main(String[] args) {
		SpringApplication.run(EasyParkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserApp admin = accountService.saveUser(new UserApp(null, "admin", "1234", null, null));
		accountService.saveUser(new UserApp(null, "user", "1234", null, null));
		accountService.saveRole(new RoleApp(null,"ADMIN"));
		accountService.saveRole(new RoleApp(null,"USER"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("user", "USER");
		userAppRepository.findAll().forEach(user->{
			System.out.println(user);
		});
		voiceRepository.save(new Voice(null,"ceci est mon avis",admin));
		voiceRepository.findAll().forEach(voice->{
			System.out.println(voice);
		});
		Location location = new Location(3000.0,5000.0);
		pathRepository.save(new Path(null,location,location,null,admin));
		pathRepository.findAll().forEach(path->{
			System.out.println(path);
		});
	}

}

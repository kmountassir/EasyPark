package iga.tiw.easypark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import iga.tiw.easypark.entities.UserApp;

@RepositoryRestResource(path = "users")
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
	public UserApp findByUsername(String username);

}

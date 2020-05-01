package iga.tiw.easypark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import iga.tiw.easypark.entities.RoleApp;

@RepositoryRestResource(exported = false)
public interface RoleAppRepository extends JpaRepository<RoleApp, Long> {
	public RoleApp findByRolename(String rolename);
}

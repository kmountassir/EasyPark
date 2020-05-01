package iga.tiw.easypark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import iga.tiw.easypark.entities.Location;

@RepositoryRestResource
public interface LocationRepository extends JpaRepository<Location, Long>{

}

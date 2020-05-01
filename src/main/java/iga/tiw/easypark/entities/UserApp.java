package iga.tiw.easypark.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
@JsonIgnoreProperties(allowSetters = true, value = {"password"})
public class UserApp implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true,nullable = false,updatable = false)
	String username;
	@Column(nullable = false)
	private String password;
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<RoleApp> roles = new ArrayList<RoleApp>();
}

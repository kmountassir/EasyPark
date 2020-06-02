package iga.tiw.easypark.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data  @NoArgsConstructor @ToString 
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
	private Collection<RoleApp> roles;
	@OneToMany(mappedBy="user")
	private List<Location>locations;
	
	public UserApp(Long id, String username, String password, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roles = new ArrayList<RoleApp>();
		this.locations = new ArrayList<Location>();
	}
}


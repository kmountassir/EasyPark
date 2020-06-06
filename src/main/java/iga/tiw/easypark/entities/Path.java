package iga.tiw.easypark.entities;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Path {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPath;
	@Column(name = "location_source")
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "longitude", column = @Column(name = "longitude_source")),
		  @AttributeOverride( name = "latitude", column = @Column(name = "latitude__source"))
		})
	private Location locationSource;
	@Column(name = "location_destination")
	@Embedded
	@AttributeOverrides({
		  @AttributeOverride( name = "longitude", column = @Column(name = "longitude_destination")),
		  @AttributeOverride( name = "latitude", column = @Column(name = "latitude__destination"))
		})
	private Location locationDestination;
	@CreationTimestamp
	private Date createdAt;
	@ManyToOne
	@JoinColumn(name="user_id_fk")
	private UserApp user;

}

package iga.tiw.easypark.services;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Service
@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterForm implements Serializable {
	private String username;
	private String password;
	private String repassword;
	

}

package iga.tiw.easypark.sec;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import iga.tiw.easypark.entities.UserApp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class Reponse{
	 String user;
	 Date expiration;
}

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UserApp userApp = null;
		try {
			userApp = new ObjectMapper().readValue(request.getInputStream(), UserApp.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(userApp.getUsername() + "/"+userApp.getPassword());
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userApp.getUsername(), userApp.getPassword()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User springUser = (User) authResult.getPrincipal();
		String jwtToken = Jwts.builder()
						.setSubject(springUser.getUsername())
						.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
						.claim("roles", springUser.getAuthorities())
						.compact();
		response.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+jwtToken);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Reponse rep = new Reponse();
		rep.user = springUser.getUsername();
		rep.expiration = new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME);
		String reponseJsonString = new Gson().toJson(rep);
		PrintWriter out = response.getWriter();
		out.print(reponseJsonString);
		out.flush();
	}

}

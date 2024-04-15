package com.karapada.school.adminstrative.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.school.adminstrative.dto.AuthRequest;
import com.karapada.school.adminstrative.dto.JwtResponse;
import com.karapada.school.adminstrative.security.util.JwtTokenGenerate;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private JwtTokenGenerate jwtTokenGenerate;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/authenticate/generateToken")
	public String authenticateAndGenerateToken(@RequestBody AuthRequest authRequest)
	{
	 Authentication authentcation = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	 if(authentcation.isAuthenticated())
	 {
		 return jwtTokenGenerate.generateToken(authRequest.getUsername()); 
	 }
	 else {
		 throw new UsernameNotFoundException("Invalid user request!");
	 }
		
	}
	
	@PostMapping("/authenticate/generateTokenWithusername")
	public ResponseEntity<JwtResponse> authenticateAndGenerateTokenWithUsername(@RequestBody AuthRequest authRequest)
	{
		try {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        
			// Convert roles to GrantedAuthority objects
	       // List<GrantedAuthority> authorities = authRequest.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

	        // Authenticate the user and generate a token with roles
	        String token = jwtTokenGenerate.generateToken(authRequest.getUsername());
	        // If authentication succeeds, generate JWT token and return response
	        JwtResponse response = new JwtResponse(token, authRequest.getUsername());
	        return ResponseEntity.ok(response);
	        
	    } catch (BadCredentialsException e) {
	        // Authentication failed due to bad credentials (username or password)
	        throw new BadCredentialsException("Invalid username or password or roles");
	        
	    }
	}

}

package com.karapada.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.karapada.security.model.UserInfo2;
import com.karapada.security.repo.IEmpRepo;
@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IEmpRepo iEmpRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	UserInfo2 	userInfo = iEmpRepo.findByEmail(email);
	
	System.out.println("userInfo: "+userInfo.toString());
	
	if(userInfo==null)
	{
		throw new UsernameNotFoundException("User Details Not Found");
	}
	else
	{
		return new CustomUser(userInfo);
	}
	}

}

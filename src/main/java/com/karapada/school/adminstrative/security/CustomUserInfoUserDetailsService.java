package com.karapada.school.adminstrative.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.karapada.school.adminstrative.security.entity.KarapadaSchoolAdminInfo;
import com.karapada.school.adminstrative.security.repo.IKarapadaSchoolAdminInfoRepository;
import com.karapada.school.adminstrative.security.util.JwtTokenGenerate;

@Component
public class CustomUserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private JwtTokenGenerate jwtTokenGenerate;
	
    @Autowired
    private IKarapadaSchoolAdminInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<KarapadaSchoolAdminInfo> userInfo = repository.findAdminInfoByName(username);
        return userInfo.map(CustomUserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
    
    public boolean isTokenValidForUsername(String token, String username) {
        String tokenUsername = jwtTokenGenerate.extractUsername(token);
        return username.equals(tokenUsername);
    }
    
    public boolean validateToken(String token) {
        String username = jwtTokenGenerate.extractUsername(token);
        UserDetails userDetails = loadUserByUsername(username);
        
        return username.equals(userDetails.getUsername()) && !jwtTokenGenerate.isTokenExpired(token);
    }
}
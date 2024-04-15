package com.karapada.school.adminstrative.security;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.karapada.school.adminstrative.security.entity.KarapadaSchoolAdminInfo;

public class CustomUserInfoUserDetails implements UserDetails {


    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserInfoUserDetails(KarapadaSchoolAdminInfo userInfo) {
        name=userInfo.getName();
        password=userInfo.getPassword();
        
        //If multiple roles:using loop(working)
      /*  for (String role : userInfo.getRoles()) 
        {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        */
        //using Java8 stream
        authorities =  userInfo.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
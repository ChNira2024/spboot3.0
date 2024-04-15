package com.karapada.school.adminstrative.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.karapada.school.adminstrative.security.CustomUserInfoUserDetailsService;
import com.karapada.school.adminstrative.security.filter.JwtAuthFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig 
{
	
	 @Bean
	    //authentication
	    public UserDetailsService userDetailsService() {
//	        UserDetails admin = User.withUsername("niranjana")
//	                .password(encoder.encode("niranjana"))
//	                .roles("ROLE_ADMIN")
//	                .build();
//	        UserDetails user = User.withUsername("lokesh")
//	                .password(encoder.encode("lokesh"))
//	                .roles("USER","ADMIN","HR")
//	                .build();
//	        return new InMemoryUserDetailsManager(admin, user);
	        return new CustomUserInfoUserDetailsService();
	    }
	
	 @Autowired
	 private JwtAuthFilter authFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/login/**","/admin/insert","/token/validateToken").permitAll()
				.and().authorizeHttpRequests().anyRequest().authenticated()
				//.and().authorizeHttpRequests().requestMatchers("/teacher/**").permitAll()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authenticationProvider(authenticationProvider())
		.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	
	@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

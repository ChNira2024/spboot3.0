package com.karapada.school.adminstrative.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.karapada.school.adminstrative.security.CustomUserInfoUserDetailsService;
import com.karapada.school.adminstrative.security.util.JwtTokenGenerate;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenGenerate jwtTokenGenerate;

    @Autowired
    private CustomUserInfoUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) 
        {
            token = authHeader.substring(7);
            System.out.println("token:"+token);
            username = jwtTokenGenerate.extractUsername(token);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
        {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenGenerate.validateToken(token, userDetails)) 
            {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                System.out.println("authToken: "+authToken);//u got roles here
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            else {
            	System.out.println("Token is not valid");
            }
        }
        filterChain.doFilter(request, response);
    }
}
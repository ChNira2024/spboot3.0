package com.karapada.school.adminstrative.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.school.adminstrative.security.CustomUserInfoUserDetailsService;
import com.karapada.school.adminstrative.security.util.JwtTokenGenerate;

@RestController
@RequestMapping("/token")
public class TokenValidationController {

	@Autowired
	private JwtTokenGenerate jwtTokenGenerate;

    @Autowired
    @Qualifier("customUserInfoUserDetailsService")
    private CustomUserInfoUserDetailsService userService;

    @GetMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestParam String token, @RequestParam String username) {
        if (userService.validateToken(token)) {
            if (userService.isTokenValidForUsername(token, username)) {
                return ResponseEntity.ok("Token is valid for username: " + username);
            } else {
                return ResponseEntity.badRequest().body("Token is not valid for username: " + username);
            }
        } else {
            return ResponseEntity.badRequest().body("Token is not valid.");
        }
    }
}


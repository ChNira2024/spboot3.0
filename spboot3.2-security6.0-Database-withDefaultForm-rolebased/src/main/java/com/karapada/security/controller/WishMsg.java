package com.karapada.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishMsg {

	@GetMapping("/")
	public String wishMsg()
	{
		return "Good Morning :)";
	}
}

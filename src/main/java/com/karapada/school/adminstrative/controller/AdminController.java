package com.karapada.school.adminstrative.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.school.adminstrative.security.entity.KarapadaSchoolAdminInfo;
import com.karapada.school.adminstrative.service.IKarapadaSchoolAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController 
{
	
	@Autowired
	private IKarapadaSchoolAdminService iKarapadaSchoolAdminService;
	
	@PostMapping("/insert")
	public String addAdminInfoIntoDatabase(@RequestBody KarapadaSchoolAdminInfo adminInfo)
	{
		return iKarapadaSchoolAdminService.addAdminIntoDB(adminInfo);
	}
}

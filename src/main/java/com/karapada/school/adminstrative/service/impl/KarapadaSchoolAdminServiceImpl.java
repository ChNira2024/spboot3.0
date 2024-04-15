package com.karapada.school.adminstrative.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.karapada.school.adminstrative.security.entity.KarapadaSchoolAdminInfo;
import com.karapada.school.adminstrative.security.repo.IKarapadaSchoolAdminInfoRepository;
import com.karapada.school.adminstrative.service.IKarapadaSchoolAdminService;


@Service
public class KarapadaSchoolAdminServiceImpl implements IKarapadaSchoolAdminService {

	 @Autowired
	    private PasswordEncoder passwordEncoder; 
	 
	 @Autowired
	 private IKarapadaSchoolAdminInfoRepository iKarapadaSchoolAdminInfoRepository;
	 
	@Override
	public String addAdminIntoDB(KarapadaSchoolAdminInfo adminInfo) {
		adminInfo.setPassword(passwordEncoder.encode(adminInfo.getPassword()));
		iKarapadaSchoolAdminInfoRepository.save(adminInfo);
		return "Admin "+adminInfo.getName()+ " inserted into DB!";
	}
}

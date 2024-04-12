package com.karapada.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karapada.security.model.UserInfo2;

public interface IEmpRepo extends JpaRepository<UserInfo2, Integer> {
	
	public UserInfo2 findByEmail(String email);

}

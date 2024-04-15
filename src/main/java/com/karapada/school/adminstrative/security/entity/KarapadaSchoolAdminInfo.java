package com.karapada.school.adminstrative.security.entity;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="KARAPADA_SCHOOL_ADMIN")
public class KarapadaSchoolAdminInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "ADMIN_NAME", length = 50)
    private String name;
    
    @Column(name = "ADMIN_EMAIL", length = 50)
    private String email;
    
    @Column(name = "ADMIN_PASSWORD", length = 500)
    private String password;
    
    @Column(name = "ADMIN_ROLES", length = 50)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ADMIN_ROLES", joinColumns = @JoinColumn(name = "admin_info_id"))
    private Set<String> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "KarapadaSchoolAdminInfo [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", roles=" + roles + "]";
	}

	
	

	
    
}
//
//Hibernate: create table karapada_school_admin (id number(19,0) not null, admin_email varchar2(50 char), admin_name varchar2(50 char), admin_passWord varchar2(500 char), primary key (id))
//Hibernate: create sequence karapada_school_admin_seq start with 1 increment by 50

//CREATE SEQUENCE karapada_school_admin_info START WITH 1 INCREMENT BY 1 NOCYCLE;
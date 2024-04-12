package com.karapada.security.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.security.model.Employee;

@RestController
@RequestMapping("/emp")
public class EmployeeController 
{

	
	
	@GetMapping("/show")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Employee>> allEmployee()
	{
	List<Employee> list = Arrays.asList(new Employee(101L, "sisu", "karapada", 828282828L),new Employee(103L, "abhi", "karapada", 828282828L),new Employee(103L, "biki", "karapada", 828282828L));
		
	return new ResponseEntity<List<Employee>>(list,HttpStatus.CREATED);
	}

	@GetMapping("/visit")
	@PreAuthorize("hasRole('ROLE_TEACHER')")
	public ResponseEntity<List<Employee>> getEmployee()
	{
	List<Employee> list = Arrays.asList(new Employee(101L, "aaa", "karapada", 828282828L),new Employee(103L, "aaa", "karapada", 828282828L),new Employee(103L, "aaa", "karapada", 828282828L));
		
		return new ResponseEntity<List<Employee>>(list,HttpStatus.CREATED);
	}

}

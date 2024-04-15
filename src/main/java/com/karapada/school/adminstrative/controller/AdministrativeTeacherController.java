package com.karapada.school.adminstrative.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.school.adminstrative.entity.Teacher;
import com.karapada.school.adminstrative.service.ITeachersService;

@RestController
@RequestMapping("/admin")
public class AdministrativeTeacherController {
	
	private static final Logger logger =  LoggerFactory.getLogger(AdministrativeTeacherController.class);
	
	@Autowired
	private ITeachersService iTeachersService;

	@PostMapping("/teacher/insert")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Teacher insertTeachersDetailsIntoDB(@RequestBody Teacher tdata)
	{
		logger.info("inside AdministrativeController class: insertTeachersDetailsIntoDB()");
		return iTeachersService.registerTeachersDetailsIntoDB(tdata);
	}
	
	@PutMapping("/teacher/update-by-id/{tid}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateTeacherDetailsIntoDBUsingByID(@RequestBody Teacher tdata,@PathVariable Long tid)
	{
		logger.info("Inside AdministrativeTeacherController class: updateTeacherDetailsIntoDBUsingByID()");
        List<Teacher> updatedTeacherDetails = iTeachersService.updateTeacherDataIntoDBUsingByID(tdata,tid);
        if (updatedTeacherDetails != null) 
        {
            return ResponseEntity.status(HttpStatus.OK).body(updatedTeacherDetails);
        } 
        else 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher data not found or failed to update.");
        }
	}
	
	@DeleteMapping("/teacher/delete-by-id/{sid}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteTeacherDetailsIntoDBUsingByID(@PathVariable Long sid)
	{
		logger.info("Inside AdministrativeTeacherController class: deleteTeacherDetailsIntoDBUsingByID()");
		try {
			Integer id = iTeachersService.deleteTeacherDataFromDBUsingByID(sid);
			 if (id != null) 
		        {
		            return ResponseEntity.status(HttpStatus.OK).body("Teacher ID "+id+" details deleted!");
		        }
		        else 
		        {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher ID"+ id +" not found to delete.");
		        }
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher ID"+ sid +" not found to delete.");
		}
	}
	
	@GetMapping("/teacher/fetchAll")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEACHER')")
	public List<Teacher> fetchAllTeachersDetailsFromDB()
	{
		logger.info("inside AdministrativeTeacherController class: fetchAllTeachersDetailsFromDB()");
		return iTeachersService.fetchAllTeacher();
	}
	

}

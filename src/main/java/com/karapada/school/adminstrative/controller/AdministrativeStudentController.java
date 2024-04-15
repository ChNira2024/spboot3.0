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

import com.karapada.school.adminstrative.entity.Student;
import com.karapada.school.adminstrative.service.IStudentsService;

@RestController
@RequestMapping("/admin")
public class AdministrativeStudentController {
	
	private static final Logger logger =  LoggerFactory.getLogger(AdministrativeStudentController.class);
	
	@Autowired
	private IStudentsService iStudentsService;
	
	
	@PostMapping("/student/insert")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Student insertStudentsDetailsIntoDB(@RequestBody Student sdata)
	{
		logger.info("inside AdministrativeController class: insertStudentsDetailsIntoDB()");
		return iStudentsService.registerStudentsDetailsIntoDB(sdata);
	}
	
	@PutMapping("/student/update-by-id/{sid}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateStudentDetailsIntoDBUsingByID(@RequestBody Student sdata,@PathVariable Long sid)
	{
		logger.info("Inside AdministrativeStudentController class: updateStudentDetailsIntoDBUsingByID()");
        List<Student> updatedStudentDetails = iStudentsService.updateStudentDataIntoDBUsingByID(sdata,sid);
        if (updatedStudentDetails != null) 
        {
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudentDetails);
        } 
        else 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student data not found or failed to update.");
        }
	}
	
	@DeleteMapping("/student/delete-by-id/{sid}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteStudentDetailsIntoDBUsingByID(@PathVariable Long sid)
	{
		logger.info("Inside AdministrativeStudentController class: deleteStudentDetailsIntoDBUsingByID()");
		try {
			Integer id = iStudentsService.deleteStudentDataIntoDBUsingByID(sid);
			 if (id != null) 
		        {
		            return ResponseEntity.status(HttpStatus.OK).body("Student ID "+id+" details deleted!");
		        }
		        else 
		        {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ID"+ id +" not found to delete.");
		        }
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ID"+ sid +" not found to delete.");
		}
	}
	
	@GetMapping("/student/fetchAll")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEACHER')")
	public List<Student> fetchAllStudentsDetailsIntoDB()
	{
		logger.info("inside AdministrativeController class: fetchAllStudentsDetailsIntoDB()");
		return iStudentsService.fetchAllStudents();
	}
	
	@GetMapping("/student/{performance}")
	public List<Student> fetchPerformaceStudentsDetailsIntoDB(@PathVariable String performance)
	{
		logger.info("inside AdministrativeController class: fetchPerformaceStudentsDetailsIntoDB()");
		return iStudentsService.fetchByPerformace(performance);
	}

}

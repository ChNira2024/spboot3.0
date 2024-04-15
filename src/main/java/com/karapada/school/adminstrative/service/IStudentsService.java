package com.karapada.school.adminstrative.service;

import java.util.List;

import com.karapada.school.adminstrative.entity.Student;

public interface IStudentsService 
{

	Student registerStudentsDetailsIntoDB(Student sdata);

	List<Student> fetchByPerformace(String performance);

	List<Student> fetchAllStudents();

	Integer deleteStudentDataIntoDBUsingByID(Long sid);

	List<Student> updateStudentDataIntoDBUsingByID(Student sdata,Long sid);
	
	public List<Student> fetchStudentDataFromDBByUsingID(Long sid);

}

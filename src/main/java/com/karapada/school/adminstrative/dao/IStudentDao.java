package com.karapada.school.adminstrative.dao;

import java.util.List;

import com.karapada.school.adminstrative.entity.Student;

public interface IStudentDao {

	Student insertStudentsDetailsIntoDB(Student sdata);

	List<Student> fetchAllStudentsData();
	
	List<Student> fetchByPerformance(String performace);

	Student fetchStudentDataFromDBByID(Long sid);

	Integer deleteStudentDetailsIntoDBByUsingID(Long sid);

	List<Student> updateStudentDetailsIntoDBByUsingID(Student sdata,Long sid);

	List<Student> fetchStudentDataFromDBByUsingID(Long sid);
}

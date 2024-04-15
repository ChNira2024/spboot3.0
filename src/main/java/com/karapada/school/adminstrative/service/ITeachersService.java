package com.karapada.school.adminstrative.service;

import java.util.List;

import com.karapada.school.adminstrative.entity.Student;
import com.karapada.school.adminstrative.entity.Teacher;

public interface ITeachersService {
	
	public Teacher registerTeachersDetailsIntoDB(Teacher tdata);

	public List<Teacher> fetchAllTeacher();

	public Integer deleteTeacherDataFromDBUsingByID(Long tid);

	public List<Teacher> updateTeacherDataIntoDBUsingByID(Teacher sdata, Long tid);
	
	public List<Teacher> fetchTeacherDataFromDBByUsingID(Long tid);

}

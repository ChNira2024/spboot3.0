package com.karapada.school.adminstrative.dao;

import java.util.List;

import com.karapada.school.adminstrative.entity.Student;
import com.karapada.school.adminstrative.entity.Teacher;

public interface ITeacherDao {

	public Teacher insertTeachersDetailsIntoDB(Teacher tdata);

	public List<Teacher> fetchAllTeachersData();

	public Teacher fetchTeacherDataFromDBByID(Long tid);

	public Integer deleteTeacherDetailsIntoDBByUsingID(Long tid);

	public List<Teacher> fetchTeacherDataFromDBByUsingID(Long tid);

	public List<Teacher> updateTeacherDetailsIntoDBByUsingID(Teacher tdata, Long teacherId);
}

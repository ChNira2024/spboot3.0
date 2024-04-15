package com.karapada.school.adminstrative.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karapada.school.adminstrative.dao.IStudentDao;
import com.karapada.school.adminstrative.entity.Student;
import com.karapada.school.adminstrative.service.IStudentsService;

@Service
public class StudentServiceImpl implements IStudentsService {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private IStudentDao iStudentDao;

	@Override
	public Student registerStudentsDetailsIntoDB(Student sdata) 
	{
		return iStudentDao.insertStudentsDetailsIntoDB(sdata);
	}

	@Override
	public List<Student> fetchByPerformace(String performance) 
	{
		List<Student> listOFData =  iStudentDao.fetchByPerformance(performance);
		System.out.println("listOFData: "+listOFData);
		
		List<Student> studentsWithPerformance = new ArrayList<>();
		for(Student student : listOFData)
		{
			if (student.getAcademicPerformance().equalsIgnoreCase(performance)) 
			{
				studentsWithPerformance.add(student);
		    }
		}
		return studentsWithPerformance;
	}

	@Override
	public List<Student> fetchAllStudents() {
		return iStudentDao.fetchAllStudentsData();
	}

	@Override
	public List<Student> fetchStudentDataFromDBByUsingID(Long sid) 
	{
		logger.info("inside StudentServiceImpl class: fetchStudentDataFromDBByUsingID()");
		return iStudentDao.fetchStudentDataFromDBByUsingID(sid);
	}
	@Override
	public Integer deleteStudentDataIntoDBUsingByID(Long sid) 
	{
		logger.info("inside StudentServiceImpl class: deleteStudentDataIntoDBUsingByID()");
		Integer studentId = sid.intValue();
        logger.info("Student ID received from Client Request: " + studentId);
        try {
        	Student student = iStudentDao.fetchStudentDataFromDBByID(sid);
            if (student.getId() != sid) {
                return null; // Provided ID does not match the fetched student ID
            }

            Integer rowsAffected = iStudentDao.deleteStudentDetailsIntoDBByUsingID(sid);
            if (rowsAffected > 0) {
                return studentId; // Return the deleted teacher's ID
            } else {
                return null; // No rows were affected, indicating deletion failure
            }
        } catch (Exception e) {
            // Handle exceptions
            return null;
        }
	}

	@Override
	public List<Student> updateStudentDataIntoDBUsingByID(Student sdata,Long sid) 
	{
		logger.info("inside StudentServiceImpl class: updateStudentDataIntoDBUsingByID()");
		Long studentId = sid;
        logger.info("Student ID received from Client Request: " + studentId);

        if (studentId != null) 
        {
            try {
                List<Student> sdetails = fetchStudentDataFromDBByUsingID(studentId);
                logger.info("List of Student fetched from DB: " + sdetails);

                if (!sdetails.isEmpty() && studentId.equals(sdetails.get(0).getId())) 
                {
                    return iStudentDao.updateStudentDetailsIntoDBByUsingID(sdata,studentId);
                } 
                else 
                {
                    logger.error("Student ID does not match with the client-requested Student ID or not present in DB!");
                    return null;
                }
            } catch (Exception e) {
                logger.error("An error occurred while updating Student details: " + e.getMessage());
                return null;
            }
        } else {
            logger.error("ID is Null. I need Student ID is required for updating Student details.");
            return null;
        }
	}
	
	

}

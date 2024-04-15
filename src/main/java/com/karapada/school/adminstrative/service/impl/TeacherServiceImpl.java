package com.karapada.school.adminstrative.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karapada.school.adminstrative.dao.ITeacherDao;
import com.karapada.school.adminstrative.entity.Student;
import com.karapada.school.adminstrative.entity.Teacher;
import com.karapada.school.adminstrative.service.ITeachersService;

@Service
public class TeacherServiceImpl implements ITeachersService {

	private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
	@Autowired
	private ITeacherDao iTeacherDao;
	
	@Override
	public Teacher registerTeachersDetailsIntoDB(Teacher tdata) 
	{
		return iTeacherDao.insertTeachersDetailsIntoDB(tdata);
	}

	@Override
	public List<Teacher> fetchAllTeacher() 
	{
		return iTeacherDao.fetchAllTeachersData();
	}

	@Override
	public Integer deleteTeacherDataFromDBUsingByID(Long sid) 
	{
		logger.info("inside TeacherServiceImpl class: deleteStudentDataIntoDBUsingByID()");
		Integer studentId = sid.intValue();
        logger.info("Student ID received from Client Request: " + studentId);
        try {
        	Teacher teacher = iTeacherDao.fetchTeacherDataFromDBByID(sid);
            if (teacher.getId() != sid) {
                return null; // Provided ID does not match the fetched student ID
            }

            Integer rowsAffected = iTeacherDao.deleteTeacherDetailsIntoDBByUsingID(sid);
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
	public List<Teacher> updateTeacherDataIntoDBUsingByID(Teacher sdata, Long sid)
	{
		logger.info("inside TeacherServiceImpl class: updateTeacherDataIntoDBUsingByID()");
		Long teacherId = sid;
        logger.info("Teacher ID received from Client Request: " + teacherId);

        if (teacherId != null) 
        {
            try {
                List<Teacher> tdetails = fetchTeacherDataFromDBByUsingID(teacherId);
                logger.info("List of Teacher fetched from DB: " + tdetails);

                if (!tdetails.isEmpty() && teacherId.equals(tdetails.get(0).getId())) 
                {
                    return iTeacherDao.updateTeacherDetailsIntoDBByUsingID(sdata,teacherId);
                } 
                else 
                {
                    logger.error("Teacher ID does not match with the client-requested Teacher ID or not present in DB!");
                    return null;
                }
            } catch (Exception e) {
                logger.error("An error occurred while updating Teacher details: " + e.getMessage());
                return null;
            }
        } else {
            logger.error("ID is Null. I need Teacher ID is required for updating Teacher details.");
            return null;
        }
	}

	@Override
	public List<Teacher> fetchTeacherDataFromDBByUsingID(Long sid) 
	{
		logger.info("inside TeacherServiceImpl class: fetchTeacherDataFromDBByUsingID()");
		return iTeacherDao.fetchTeacherDataFromDBByUsingID(sid);
	}

}

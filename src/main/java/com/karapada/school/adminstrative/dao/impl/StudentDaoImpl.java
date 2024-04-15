package com.karapada.school.adminstrative.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.karapada.school.adminstrative.dao.IStudentDao;
import com.karapada.school.adminstrative.entity.Student;

@Repository
public class StudentDaoImpl implements IStudentDao {

	private static final Logger logger =  LoggerFactory.getLogger(StudentDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${DBAPPEND}")
	private String DBAPPEND;
	


	@Override
	public Student insertStudentsDetailsIntoDB(Student sdata) 
	{
		String seq = "SELECT " + DBAPPEND + "STUDENT_ID.NEXTVAL FROM DUAL";
		System.out.println("SEQ:"+seq);
		Long studentId = jdbcTemplate.queryForObject(seq, Long.class);//we get teacher seq id
		System.out.println("studentId:"+studentId);
		logger.info("inside StudentDaoImpl class: insertStudentsDetailsIntoDB()");
		String sql = "INSERT INTO " + DBAPPEND + "KARAPADA_SCHOOL_STUDENTS (ID,ACADEMIC_PERFORMANCE, ADDRESS, ATTENDANCE_RECORD, CLASS_GRADE, CONTACT_NUMBER, DATE_OF_BIRTH, " +
                "EMAIL, EMERGENCY_CONTACT, EXTRACURRICULAR_ACTIVITIES, FEES_PAYMENTS, FIRST_NAME, GENDER, LAST_NAME, MEDICAL_INFORMATION,PARENT_GUARDIAN_NAME,REMARKS_NOTES) " +
                "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

		int numberOfStudentInserted = jdbcTemplate.update(sql, studentId,sdata.getAcademicPerformance(), sdata.getAddress(),sdata.getAttendanceRecord(), sdata.getClassGrade(), sdata.getContactNumber(), sdata.getDateOfBirth(),
				sdata.getEmail(), sdata.getEmergencyContact(), sdata.getExtracurricularActivities(), sdata.getFeesPayments(), 
                       sdata.getFirstName(), sdata.getGender(), sdata.getLastName(),sdata.getMedicalInformation(),sdata.getParentGuardianName(),sdata.getRemarksNotes());
		logger.info("number Of Student Inserted into database: " + numberOfStudentInserted);
		if (numberOfStudentInserted == 1) 
		{
			logger.info("inside StudentDaoImpl class: insertStudentsDetailsIntoDB(),Student details inserted successfully: " + sdata);
		} else {
			logger.error("Failed to insert student details into DB!");
		}
		return sdata;
	}



	@Override
	public List<Student> fetchAllStudentsData() {
		
		logger.info("inside StudentDaoImpl class: fetchAllStudentsData()");
		String sql = "SELECT * FROM " + DBAPPEND + "KARAPADA_SCHOOL_STUDENTS";

		List<Student> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class));
		logger.info("number Of Student: " + list);
		return list;
	}

	@Override
	public List<Student> fetchStudentDataFromDBByUsingID(Long sid)
	{
		logger.info("inside StudentDaoImpl class: fetchStudentSingleDataFromDB()");
		String FETCH_QUERY = "SELECT * FROM " + DBAPPEND + "KARAPADA_SCHOOL_STUDENTS WHERE ID=?";
		try {
			List<Student> studentDetails = jdbcTemplate.query(FETCH_QUERY,new Object[] {sid},BeanPropertyRowMapper.newInstance(Student.class));
			logger.info("StudentDaoImpl class: fetchStudentSingleDataFromDB(),List of Student Details: "+studentDetails);
			return studentDetails;
		}
		catch(DataAccessException e)
		{
			logger.info("StudentDaoImpl class: fetchStudentSingleDataFromDB(),sid is :"+sid+" Not present in DB So Unable to fetch");
			e.printStackTrace();
			throw e;
		}
	}


	@Override
	public List<Student> fetchByPerformance(String performace) {
		logger.info("inside StudentDaoImpl class: fetchByPerformance()");
		String sql = "SELECT * FROM " + DBAPPEND + "KARAPADA_SCHOOL_STUDENTS WHERE LOWER(ACADEMIC_PERFORMANCE) = LOWER(?)";

		List<Student> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class),performace);
		return list;
	}



	@Override
	public Student fetchStudentDataFromDBByID(Long sid) 
	{
		logger.info("inside StudentDaoImpl class: fetchStudentDataFromDBByID()");
		String FETCH_QUERY = "SELECT * FROM "+ DBAPPEND + "KARAPADA_SCHOOL_STUDENTS WHERE ID=?";
		try {
			List<Student> studentDetails = jdbcTemplate.query(FETCH_QUERY,new Object[] {sid},BeanPropertyRowMapper.newInstance(Student.class));
			logger.info("StudentDaoImpl class: fetchStudentSingleDataFromDB(),List of Student Details: "+studentDetails);
			sid = studentDetails.get(0).getId();
            logger.info("StudentDaoImpl class: fetchStudentDataFromDBByID(),sid is :"+sid);
			return studentDetails.get(0);
		}
			catch(Exception e)
			{
				logger.info("StudentDaoImpl class: fetchStudentSingleDataFromDB(),sid is :"+ sid +" Not present in DB So Unable to fetch");
				throw new RuntimeException("Failed to fetch Student with ID " + sid + ": " + e.getMessage());
			}
	}



	@Override
	public Integer deleteStudentDetailsIntoDBByUsingID(Long sid) 
	{
		logger.info("inside StudentDaoImpl class: deleteStudentDetailsIntoDBByUsingID()");
		String DELETE_QUERY = "DELETE FROM "+ DBAPPEND + "KARAPADA_SCHOOL_STUDENTS WHERE ID = ?";
        return jdbcTemplate.update(DELETE_QUERY, sid);
	}



	@Override
	public List<Student> updateStudentDetailsIntoDBByUsingID(Student sdata,Long sid) 
	{
		logger.info("inside StudentDaoImpl class: updateStudentDetailsIntoDBByUsingID()");
		String UPDATE_QUERY = "UPDATE "+ DBAPPEND + "KARAPADA_SCHOOL_STUDENTS SET " +
	            "ACADEMIC_PERFORMANCE = ?, " +
	            "ADDRESS = ?, " +
	            "ATTENDANCE_RECORD = ?, " +
	            "CLASS_GRADE = ?, " +
	            "CONTACT_NUMBER = ?, " +
	            "DATE_OF_BIRTH = ?, " +
	            "EMAIL = ?, " +
	            "EMERGENCY_CONTACT = ?, " +
	            "EXTRACURRICULAR_ACTIVITIES = ?, " +
	            "FEES_PAYMENTS = ?, " +
	            "FIRST_NAME = ?, " +
	            "GENDER = ?, " +
	            "LAST_NAME = ?, " +
	            "MEDICAL_INFORMATION = ?, " +
	            "PARENT_GUARDIAN_NAME = ?, " +
	            "REMARKS_NOTES = ? " +
	            "WHERE ID = ?";
		int rowUpdatedCount = jdbcTemplate.update(UPDATE_QUERY, sdata.getAcademicPerformance(), sdata.getAddress(), 
	            sdata.getAttendanceRecord(), sdata.getClassGrade(), sdata.getContactNumber(), 
	            sdata.getDateOfBirth(), sdata.getEmail(), sdata.getEmergencyContact(), 
	            sdata.getExtracurricularActivities(), sdata.getFeesPayments(), sdata.getFirstName(), 
	            sdata.getGender(), sdata.getLastName(), sdata.getMedicalInformation(), 
	            sdata.getParentGuardianName(), sdata.getRemarksNotes(), sid);
	
		if(rowUpdatedCount > 0)
		{
			return fetchStudentDataFromDBByUsingID(sid);
		}
		else
		{
			return null;//No row data updated
		}
	}

}

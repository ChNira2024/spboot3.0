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

import com.karapada.school.adminstrative.dao.ITeacherDao;
import com.karapada.school.adminstrative.entity.Student;
import com.karapada.school.adminstrative.entity.Teacher;

@Repository
public class TeacherDaoImpl implements ITeacherDao {

	private static final Logger logger =  LoggerFactory.getLogger(TeacherDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${DBAPPEND}")
	private String DBAPPEND;
	
	@Override
	public Teacher insertTeachersDetailsIntoDB(Teacher tdata) 
	{
		String seq = "SELECT " + DBAPPEND + "TEACHER_ID_SEQ.NEXTVAL FROM DUAL";
		System.out.println("SEQ:"+seq);
		Long teacherId = jdbcTemplate.queryForObject(seq, Long.class);//we get teacher seq id
		System.out.println("teacherId:"+teacherId);
		logger.info("inside TeacherDaoImpl class: insertTeachersDetailsIntoDB()");
		String sql = "INSERT INTO KARAPADA_SCHOOL_TEACHER (ID,FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, CONTACT_NUMBER, SUBJECT, " +
                "CLASS_GRADE, JOINING_DATE, SALARY, QUALIFICATIONS, EXPERIENCE, DESIGNATION, DEPARTMENT, DATE_OF_BIRTH) " +
                "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		int numberOfTeacherInserted = jdbcTemplate.update(sql, teacherId,tdata.getFirstName(), tdata.getLastName(), tdata.getEmail(), tdata.getAddress(), tdata.getContactNumber(), tdata.getSubject(), tdata.getClassGrade(), tdata.getJoiningDate(), tdata.getSalary(), 
                       tdata.getQualifications(), tdata.getExperience(), tdata.getDesignation(),tdata.getDepartment(),tdata.getDateOfBirth());
		logger.info("number Of Teacher Inserted into database: " + numberOfTeacherInserted);
		if (numberOfTeacherInserted == 1) 
		{
			logger.info("inside TeacherDaoImpl class: insertTeachersDetailsIntoDB(),Teacher details inserted successfully: " + tdata);
		} else {
			logger.error("Failed to insert teacher details into DB!");
		}
		return tdata;
	
	}

	@Override
	public List<Teacher> fetchAllTeachersData() 
	{
		logger.info("inside TeacherDaoImpl class: fetchAllTeachersData()");
		String sql = "SELECT * FROM " + DBAPPEND + "KARAPADA_SCHOOL_TEACHER";

		List<Teacher> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Teacher.class));
		logger.info("number Of Teacher: " + list);
		return list;
	}

	@Override
	public Teacher fetchTeacherDataFromDBByID(Long tid)
	{
		logger.info("inside TeacherDaoImpl class: fetchTeacherDataFromDBByID()");
		String FETCH_QUERY = "SELECT * FROM "+ DBAPPEND + "KARAPADA_SCHOOL_TEACHER WHERE ID=?";
		try {
			List<Teacher> teacherDetails = jdbcTemplate.query(FETCH_QUERY,new Object[] {tid},BeanPropertyRowMapper.newInstance(Teacher.class));
			logger.info("TeacherDaoImpl class: fetchTeacherDataFromDBByID(),List of Teacher Details: "+teacherDetails);
			tid = teacherDetails.get(0).getId();
            logger.info("TeacherDaoImpl class: fetchTeacherDataFromDBByID(),sid is :"+tid);
			return teacherDetails.get(0);
		}
			catch(Exception e)
			{
				logger.info("TeacherDaoImpl class: fetchTeacherDataFromDBByID(),tid is :"+ tid +" Not present in DB So Unable to fetch");
				throw new RuntimeException("Failed to fetch Teacher with ID " + tid + ": " + e.getMessage());
			}
	}

	@Override
	public Integer deleteTeacherDetailsIntoDBByUsingID(Long tid)
	{
		logger.info("inside TeacherDaoImpl class: deleteTeacherDetailsIntoDBByUsingID()");
		String DELETE_QUERY = "DELETE FROM "+ DBAPPEND + "KARAPADA_SCHOOL_TEACHER WHERE ID = ?";
        return jdbcTemplate.update(DELETE_QUERY, tid);
	}

	@Override
	public List<Teacher> fetchTeacherDataFromDBByUsingID(Long tid) 
	{
		logger.info("inside TeacherDaoImpl class: fetchTeacherDataFromDBByUsingID()");
		String FETCH_QUERY = "SELECT * FROM " + DBAPPEND + "KARAPADA_SCHOOL_TEACHER WHERE ID=?";
		try {
			List<Teacher> teacherDetails = jdbcTemplate.query(FETCH_QUERY,new Object[] {tid},BeanPropertyRowMapper.newInstance(Teacher.class));
			logger.info("TeacherDaoImpl class: fetchTeacherDataFromDBByUsingID(),List of Teacher Details: "+teacherDetails);
			return teacherDetails;
		}
		catch(DataAccessException e)
		{
			logger.info("TeacherDaoImpl class: fetchTeacherDataFromDBByUsingID(),tid is :"+tid+" Not present in DB So Unable to fetch");
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Teacher> updateTeacherDetailsIntoDBByUsingID(Teacher tdata, Long teacherId)
	{
		logger.info("inside StudentDaoImpl class: updateStudentDetailsIntoDBByUsingID()");
		String UPDATE_QUERY = "UPDATE " + DBAPPEND + "KARAPADA_SCHOOL_TEACHER SET " +
	             "FIRST_NAME = ?, " +
	             "LAST_NAME = ?, " +
	             "EMAIL = ?, " +
	             "ADDRESS = ?, " +
	             "CONTACT_NUMBER = ?, " +
	             "SUBJECT = ?, " +
	             "CLASS = ?, " +
	             "JOINING_DATE = ?, " +
	             "SALARY = ?, " +
	             "QUALIFICATIONS = ?, " +
	             "EXPERIENCE = ?, " +
	             "DESIGNATION = ?, " +
	             "DEPARTMENT = ?, " +
	             "DATE_OF_BIRTH = ? " +
	             "WHERE ID = ?";
		Object[] params = {
		        tdata.getFirstName(),
		        tdata.getLastName(),
		        tdata.getEmail(),
		        tdata.getAddress(),
		        tdata.getContactNumber(),
		        tdata.getSubject(),
		        tdata.getClassGrade(),
		        tdata.getJoiningDate(),
		        tdata.getSalary(),
		        tdata.getQualifications(),
		        tdata.getExperience(),
		        tdata.getDesignation(),
		        tdata.getDepartment(),
		        tdata.getDateOfBirth(),
		        teacherId
		    };
		int rowUpdatedCount = jdbcTemplate.update(UPDATE_QUERY, params);
	
		if(rowUpdatedCount > 0)
		{
			return fetchTeacherDataFromDBByUsingID(teacherId);
		}
		else
		{
			return null;//No row data updated
		}
	}

}

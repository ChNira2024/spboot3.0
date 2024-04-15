package com.karapada.school.adminstrative.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "KARAPADA_SCHOOL_STUDENTS")
@Data
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
    private Long id;

	@Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "ADDRESS", length = 100)
    private String address;

    @Column(name = "CONTACT_NUMBER", length = 20)
    private String contactNumber;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "GENDER", length = 10)
    private String gender;

    @Column(name = "CLASS_GRADE", length = 20)
    private String classGrade;

    @Column(name = "PARENT_GUARDIAN_NAME", length = 100)
    private String parentGuardianName;

    @Column(name = "EMERGENCY_CONTACT", length = 20)
    private String emergencyContact;

    @Column(name = "MEDICAL_INFORMATION", length = 200)
    private String medicalInformation;

    @Column(name = "ATTENDANCE_RECORD", length = 200)
    private String attendanceRecord;

    @Column(name = "ACADEMIC_PERFORMANCE", length = 200)
    private String academicPerformance;

    @Column(name = "EXTRACURRICULAR_ACTIVITIES", length = 200)
    private String extracurricularActivities;

    @Column(name = "FEES_PAYMENTS", length = 200)
    private String feesPayments;

    @Column(name = "REMARKS_NOTES", length = 200)
    private String remarksNotes;

    
}

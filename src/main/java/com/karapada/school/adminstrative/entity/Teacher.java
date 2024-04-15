package com.karapada.school.adminstrative.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="KARAPADA_SCHOOL_TEACHER")
public class Teacher {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "ADDRESS", length = 100)
    private String address;

    @Column(name = "CONTACT_NUMBER", length = 20)
    private String contactNumber;

    @Column(name = "SUBJECT", length = 50)
    private String subject;

    @Column(name = "CLASS_GRADE", length = 20)
    private String classGrade;

    @Column(name = "JOINING_DATE")
    private LocalDate joiningDate;

    @Column(name = "SALARY")
    private BigDecimal salary;

    @Column(name = "QUALIFICATIONS", length = 100)
    private String qualifications;

    @Column(name = "EXPERIENCE")
    private int experience;

    @Column(name = "DESIGNATION", length = 50)
    private String designation;

    @Column(name = "DEPARTMENT", length = 50)
    private String department;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getClassGrade() {
		return classGrade;
	}

	public void setClassGrade(String classGrade) {
		this.classGrade = classGrade;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", contactNumber=" + contactNumber + ", subject=" + subject + ", classGrade="
				+ classGrade + ", joiningDate=" + joiningDate + ", salary=" + salary + ", qualifications="
				+ qualifications + ", experience=" + experience + ", designation=" + designation + ", department="
				+ department + ", dateOfBirth=" + dateOfBirth + ", getId()=" + getId() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()=" + getEmail() + ", getAddress()="
				+ getAddress() + ", getContactNumber()=" + getContactNumber() + ", getSubject()=" + getSubject()
				+ ", getClassGrade()=" + getClassGrade() + ", getJoiningDate()=" + getJoiningDate() + ", getSalary()="
				+ getSalary() + ", getQualifications()=" + getQualifications() + ", getExperience()=" + getExperience()
				+ ", getDesignation()=" + getDesignation() + ", getDepartment()=" + getDepartment()
				+ ", getDateOfBirth()=" + getDateOfBirth() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
    
    
}

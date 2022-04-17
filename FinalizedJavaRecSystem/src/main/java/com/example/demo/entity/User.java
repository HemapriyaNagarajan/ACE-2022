package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserTable")
public class User implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName, lastName;
	private String email, alternateEmail;
	private String address, contactNo, alternatePhone;
	
	private String degree, domain;
	private int cgpa, hsc, sslc;
	private String tech1, tech2, tech3;
	private int tech1_level, tech2_level, tech3_level;
	private String experience, establishment, accomplishment;
	private String internship, project;
	private String hobbies, interests, links;
	private String myusp;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAlternatePhone() {
		return alternatePhone;
	}
	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getDomain() {
		return domain;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getCgpa() {
		return cgpa;
	}
	public void setCgpa(int cgpa) {
		this.cgpa = cgpa;
	}
	public int getHsc() {
		return hsc;
	}
	public void setHsc(int hsc) {
		this.hsc = hsc;
	}
	public int getSslc() {
		return sslc;
	}
	public void setSslc(int sslc) {
		this.sslc = sslc;
	}
	public String getTech1() {
		return tech1;
	}
	public void setTech1(String tech1) {
		this.tech1 = tech1;
	}
	public String getTech2() {
		return tech2;
	}
	public void setTech2(String tech2) {
		this.tech2 = tech2;
	}
	public String getTech3() {
		return tech3;
	}
	public void setTech3(String tech3) {
		this.tech3 = tech3;
	}
	public int getTech1_level() {
		return tech1_level;
	}
	public void setTech1_level(int tech1_level) {
		this.tech1_level = tech1_level;
	}
	public int getTech2_level() {
		return tech2_level;
	}
	public void setTech2_level(int tech2_level) {
		this.tech2_level = tech2_level;
	}
	public int getTech3_level() {
		return tech3_level;
	}
	public void setTech3_level(int tech3_level) {
		this.tech3_level = tech3_level;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getEstablishment() {
		return establishment;
	}
	public void setEstablishment(String establishment) {
		this.establishment = establishment;
	}
	public String getAccomplishment() {
		return accomplishment;
	}
	public void setAccomplishment(String accomplishment) {
		this.accomplishment = accomplishment;
	}
	public String getInternship() {
		return internship;
	}
	public void setInternship(String internship) {
		this.internship = internship;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getLinks() {
		return links;
	}
	public void setLinks(String links) {
		this.links = links;
	}
	public String getMyusp() {
		return myusp;
	}
	public void setMyusp(String myusp) {
		this.myusp = myusp;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", alternateEmail=" + alternateEmail + ", address=" + address + ", contact=" + contactNo
				+ ", alternatePhone=" + alternatePhone + ", degree=" + degree + ", domain=" + domain + ", cgpa=" + cgpa
				+ ", hsc=" + hsc + ", sslc=" + sslc + ", tech1=" + tech1 + ", tech2=" + tech2 + ", tech3=" + tech3
				+ ", tech1_level=" + tech1_level + ", tech2_level=" + tech2_level + ", tech3_level=" + tech3_level
				+ ", experience=" + experience + ", establishment=" + establishment + ", accomplishment="
				+ accomplishment + ", internship=" + internship + ", project=" + project + ", hobbies=" + hobbies
				+ ", interests=" + interests + ", links=" + links + ", myusp=" + myusp + "]";
	}
	
	
}

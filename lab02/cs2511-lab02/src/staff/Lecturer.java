package staff;

import java.time.LocalDate;

public class Lecturer extends StaffMember{
	private String school;
	private char academicStatus;
	
	public Lecturer(String name, double salary, LocalDate hireDate, LocalDate endDate, String school, char academicStatus) {
		super(name, salary, hireDate, endDate);
		setSchool(school);
		setAcademicStatus(academicStatus);
	}
	
	public String getSchool() {
		return school;
	}
	private void setSchool(String school) {
		this.school = school;
	}
	public char getAcademicStatus() {
		return academicStatus;
	}
	private void setAcademicStatus(char academicStatus) {
		this.academicStatus = academicStatus;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!super.equals(obj)) return false;
		Lecturer otherObj = (Lecturer) obj;
		return this.school.equals(otherObj.school) && this.academicStatus == otherObj.academicStatus;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = super.toString() 
			+ "\t SCHOOL:" + getSchool() + "\t ACADEMIC_STATUS:" + getAcademicStatus(); 
		return str;
	}
}

package unsw.enrolment;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;

    public Enrolment(CourseOffering offering, Student student) {
        this.offering = offering;
        this.student = student;
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }
    
    /**
	 * @return the grade
	 */
	public Grade getGrade() {
		return grade;
	}

	public String getCourseCode() {
    	return getCourse().getCourseCode();
    }
	
	public String getCourseName() {
    	return getCourse().getTitle();
    }
	
	public int getCourseMarks() {
		return getGrade().getMark();
	}
    
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\n" + getCourseCode() + " ~ " + getCourseName() + "\n";
	}
	
}

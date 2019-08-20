package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Enrolment extends Observable{

    private CourseOffering offering;
    private CalculateMarks grade;
    private Student student;
    private List<Session> sessions;

    public Enrolment(CourseOffering offering, Student student, Session... sessions) {
        this.offering = offering;
        this.student = student;
        this.grade = null; // Student has not completed course yet.
        student.addEnrolment(this);
        offering.addEnrolment(this);
        this.sessions = new ArrayList<>();
        for (Session session : sessions) {
            this.sessions.add(session);
        }
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getCourseCode() {
        return getCourse().getCourseCode();
    }
    
    public String getTerm() {
        return offering.getTerm();
    }

    public boolean hasPassed() {
        return grade != null && grade.isPassing();
    }

	/**
	 * @param marks the marks to set
	 */
	public void setGrade(Marks grade) {
		this.grade = grade;
		setChanged();
		notifyObservers(grade);
	}

	public CalculateMarks getGrade() {
		return grade;
	}
	
	public int getMarks() {
		return grade.getFinalMarks();
	}
    
	public String getZID() {
		return student.getZID();
	}
    
//    Whole course marks can no longer be assigned this way.
//    public void assignMark(int mark) {
//        grade = new Grade(mark);
//    }

}

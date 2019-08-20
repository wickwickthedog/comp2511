package unsw.enrolment;
import java.util.ArrayList;
import java.util.List;

/**
 * A course in the enrolment system.
 * @author Robert Clifton-Everest
 *
 */
public class Course {

    private String courseCode;
    private String title;
    private int uoc;
    private List<Course> prereqs;
    private List<CourseOffering> courseOfferings;


    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.title = title;
        this.prereqs = new ArrayList<Course>();
    }
    
    /**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	public void addPrereq(Course course) {
        prereqs.add(course);
    }
    
    public String getCourseCode() {
        return courseCode;
    }

    public int getUOC() {
        return uoc;
    }

    public boolean checkPrereqs(ArrayList<Enrolment> enrolmentList) {
    	int prereqsCount = 0;
    	if (enrolmentList.isEmpty()) {
    		System.out.println("No enrolments");
        	return false;
    	}
    	for (Enrolment e1 : enrolmentList) {
    		if (prereqs.isEmpty()) return true;
    		for (Course c : prereqs) {
    			if (e1.getCourseName().equals(c.title)) {
    				if (e1.getGrade() == null) break;
    				if (e1.getCourseMarks() >= 50) prereqsCount++;
    			}
    			if (prereqsCount == prereqs.size()) return true;
    		}
    	}
    	System.out.println("Did not satidfy prereq for " + courseCode + " "+ title);
    	return false;
    }
}

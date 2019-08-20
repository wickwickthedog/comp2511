package unsw.enrolment;
import java.util.ArrayList;

public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        enrolments = new ArrayList<>();
    }

	public String getZID() {
		return zid;
	}

    public void addEnrolment(Enrolment enrolment) {
        enrolments.add(enrolment);
    }

    public boolean hasPassed(Course prereq) {
        boolean passed = false;
        for (Enrolment enrolment : enrolments)
            if (enrolment.getCourse().equals(prereq))
                if (enrolment.hasPassed())
                    passed = true;
        return passed;
    }

}

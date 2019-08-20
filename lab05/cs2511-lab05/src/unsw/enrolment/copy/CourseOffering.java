package unsw.enrolment.copy;
import java.util.ArrayList;
import java.util.List;

public class CourseOffering {

    private Course course;
    private String term;
    private List<Session> sessions;
    private List<Enrolment> enrolments;

    public CourseOffering(Course course, String term) {
        this.course = course;
        this.term = term;
        this.sessions = new ArrayList<>();
        this.enrolments = new ArrayList<>();
        this.course.addOffering(this);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public Course getCourse() {
        return course;
    }

    public String getTerm() {
        return term;
    }

    /**
     * Enrol the given student in this offering. If student has not met prereqs
     * then this method returns null.
     * @param student
     * @param sessions
     * @return
     */
    public Enrolment enrol(Student student, Session... sessions) {
        if (course.satisfiesPrerequisites(student)) {
            return new Enrolment(this, student, sessions);
        }
        return null;
    }

    public void addEnrolment(Enrolment enrolment) {
        enrolments.add(enrolment);
    }

}

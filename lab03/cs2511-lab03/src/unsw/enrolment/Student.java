package unsw.enrolment;
import java.util.ArrayList;

public class Student {

    private String zid;
    private ArrayList<Enrolment> enrolments;

	public Student(String zid) {
        this.zid = zid;
        this.enrolments = new ArrayList<Enrolment>();
    }

	public String getZID() {
		return zid;
	}

	/**
	 * @return the enrolments
	 */
	public ArrayList<Enrolment> getEnrolments() {
		return enrolments;
	}

	/**
	 * @param enrolments the enrolments to add
	 */
	public void addEnrolments(Enrolment enrolments) {
		this.enrolments.add(enrolments);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return zid + "'s Enrolments:\n" + enrolments.toString();
	}
}

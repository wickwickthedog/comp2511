package unsw.enrolment;

public class Grade {
    private int mark;
    private String grade;
    
    public Grade(int mark) {
    	this.mark = mark;
    	this.grade = setGrade(mark);
    }
    
    /**
	 * @return the mark
	 */
	public int getMark() {
		return mark;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(int mark) {
		this.mark = mark;
	}

	public String setGrade(int mark) {
    	String grade = "F";
    	if (mark >= 85) grade = "HD";
    	else if (mark >= 75) grade = "D";
    	else if (mark >= 65) grade = "C";
    	else if (mark >= 50) grade = "P";
    	return grade;
    }
}

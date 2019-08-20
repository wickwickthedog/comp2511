package unsw.enrolment;

/**
 * Composite pattern
 * this is the base component
 * @author Harvey
 */
public class Grade implements CalculateMarks{
    private int mark;
    private String grade;
    private String assessment;

    public Grade(String assessment, int mark) {
    	this.mark = mark;
    	this.grade = updateGrade();
    	this.assessment = assessment;
    }
    
    /**
	 * @param mark the mark to set
	 */
	public void setMark(int mark) {
		this.mark = mark;
	}

	/**
	 * @return the mark
	 */
	public int getMark() {
		return mark;
	}

	/**
	 * @param grade the grade to set
	 * @return 
	 */
	public String updateGrade() {
//		getFinalMarks();
		if (mark < 50)
            return "FL";
        else if (mark < 65)
            return "PS";
        else if (mark < 75)
            return "DN";
        else
            return "HD";
	}

	public boolean isPassing() {
        return getFinalMarks() >= 50;
    }
    
	@Override
	public String getName() {
		// DONE Auto-generated method stub
		return assessment;
	}
	
	@Override
	public int getMarks() {
		// DONE Auto-generated method stub
		return this.mark;
	}
	
	@Override
	public int getPracMarks() {
		// DONE Auto-generated method stub
//		if (assessments != null) {
//			this.mark = assessments.getPracMarks();
//			return assessments.getPracMarks();
//		}
//		else return 0;
		return this.mark;
	}

	@Override
	public int getFinalMarks() {
		// DONE Auto-generated method stub
//		if (assessment == "FinalExam") {
//			this.mark = assessments.getFinalMarks();
//			return assessments.getFinalMarks();
//		}
//		else return 0;
		return this.mark;
	}
    
}

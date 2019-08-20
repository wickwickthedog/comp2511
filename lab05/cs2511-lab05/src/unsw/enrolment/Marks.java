package unsw.enrolment;

import java.util.ArrayList;

/**
 * Composite pattern
 * this is the composite component
 * @author Harvey
 */
public class Marks implements CalculateMarks{
	
	private String assessment;
	private int marks = 0;
	private ArrayList<CalculateMarks> assessments;
	
	public Marks(String assessment) {
		this.assessment = assessment;
		this.assessments = new ArrayList<CalculateMarks>();
	}
	
//	public Marks(int marks) {
//		setMarks(marks);
//		this.assessment = "FinalExam";
//		this.assessments = new ArrayList<CalculateMarks>();
//	}

	/**
	 * @param marks the marks to set
	 */
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	/**
	 * @return the assignments
	 */
	public ArrayList<CalculateMarks> getassessments() {
		return assessments;
	}

	/**
	 * @param marks the markss to add to assessments
	 */
	public void addassessments(CalculateMarks marks) {
		this.assessments.add(marks);
	}

	/**
	 * if it is assignement 1 with no milestones return marks
	 * else get the average of all the milestones
	 * @return average marks
	 */
	public int averageMarks() {
		if (assessments.isEmpty()) return this.marks;
		setMarks(0);
		for (int i = 0; i < assessments.size(); i ++) {
			this.marks += assessments.get(i).getMarks();
		}
		return this.marks/this.assessments.size();
	}
	
	@Override
	public String getName() {
		// DONE Auto-generated method stub
		return assessment;
	}

	@Override
	public int getMarks() {
		// DONE Auto-generated method stub
		return this.marks;
	}
	
	/**
	 * Just practical marks without final exam marks
	 */
	@Override
	public int getPracMarks() {
		// DONE Auto-generated method stub
		if (assessments.isEmpty()) return averageMarks();
		else {
			int flag = 0;
			setMarks(0);
			for (CalculateMarks m: assessments) {
				if(m.getName().equals("FinalExam")) {
					flag ++;
					continue;
				}
				this.marks += m.getPracMarks();
			}
			if (flag != 0) {
				this.marks = this.marks/this.assessments.size() - flag;
				return this.marks;
			} else {
				this.marks = this.marks/this.assessments.size();
				return this.marks;
			}
		}
	}

	/**
	 * Total average of marks
	 */
	@Override
	public int getFinalMarks() {
		// DONE Auto-generated method stub
		if (assessments.isEmpty()) return this.marks;
		else {
			setMarks(0);
			for (CalculateMarks m: assessments) {
				if (m.getName().equals("FinalExam")) 
					this.marks += m.getFinalMarks();
			}
			this.marks += getPracMarks();
			return this.marks;
		}
	}

	@Override
	public boolean isPassing() {
		// DONE Auto-generated method stub
		this.marks = getFinalMarks();
		return this.marks >= 50;
	}
	
}

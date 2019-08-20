package unsw.enrolment;

/**
 * Composite pattern
 * this is the main composite interface
 * @author Harvey
 */
public interface CalculateMarks {
	public String getName();
	public int getMarks();
	public int getPracMarks();
	public int getFinalMarks();
	public boolean isPassing();
}

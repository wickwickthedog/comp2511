package unsw.movies;

public interface Category {
	public double getCharge(int daysRented);
	public Category toRegular();
	public Category toNewRelease();
	public Category toChildren();
	public Category toClassic();
	
}

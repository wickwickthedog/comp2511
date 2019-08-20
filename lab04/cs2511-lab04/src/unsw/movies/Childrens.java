package unsw.movies;

public class Childrens implements Category {

    @Override
    public double getCharge(int daysRented) {
        double charge = 1.5;
        if (daysRented > 3)
            charge += (daysRented - 3) * 1.5;
        return charge;
    }

	@Override
	public Category toRegular() {
		// DONE Auto-generated method stub
		return new Regular();
	}

	@Override
	public Category toNewRelease() {
		// DONE Auto-generated method stub
		return this;
	}

	@Override
	public Category toChildren() {
		// DONE Auto-generated method stub
		return this;
	}

	@Override
	public Category toClassic() {
		// DONE Auto-generated method stub
		return new Classic();
	}

}

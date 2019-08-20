package unsw.movies;

public class Regular implements Category {

    @Override
    public double getCharge(int daysRented) {
        double charge = 2;
        if (daysRented > 2)
            charge += (daysRented - 2) * 1.5;
        return charge;
    }

	@Override
	public Category toRegular() {
		// DONE Auto-generated method stub
		return this;
	}

	@Override
	public Category toNewRelease() {
		// DONE Auto-generated method stub
		return this;
	}

	@Override
	public Category toChildren() {
		// DONE Auto-generated method stub
		return new Childrens();
	}

	@Override
	public Category toClassic() {
		// DONE Auto-generated method stub
		return new Classic();
	}

}

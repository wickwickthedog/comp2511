package unsw.movies;

public class Classic implements Category{

	@Override
	public double getCharge(int daysRented) {
		double charge = 2;
        if (daysRented > 5)
            charge += 1;
        return charge;
	}

	@Override
	public Category toRegular() {
		// DONE Auto-generated method stub
		return new Regular();
	}

	@Override
	public Category toNewRelease() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Category toChildren() {
		// TODO Auto-generated method stub
		return new Childrens();
	}

	@Override
	public Category toClassic() {
		// TODO Auto-generated method stub
		return this;
	}
	
}

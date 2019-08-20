package unsw.movies;

public class NewRelease implements Category {

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
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
		return new Childrens();
	}

	@Override
	public Category toClassic() {
		// DONE Auto-generated method stub
		return new Classic();
	}

}

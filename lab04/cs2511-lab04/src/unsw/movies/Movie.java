package unsw.movies;

public class Movie {
    private String title;
    private Category category;

    public Movie(String title) {
        this.title = title;
        this.category = new NewRelease();
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        return category.getCharge(daysRented);
    }

    public Category getCategory() {
    	return category;
    }
    
    public Category toRegular() {
    	return category = category.toRegular();
    }
    
    public Category toNewRelease() {
    	return category = category.toNewRelease();
    }
    
    public Category toChildren() {
    	return category = category.toChildren();
    }
    
    public Category toClassic() {
    	return category = category.toClassic();
    }
}
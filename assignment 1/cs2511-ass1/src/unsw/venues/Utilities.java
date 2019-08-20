package unsw.venues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public interface Utilities {
	/**
	 * Sorts the bookingList by start date
	 * and returns a sorted List
	 * @param bookingList the bookingList from VenueHireSystem
	 * @return ArrayList<Request>
	 */
	public static ArrayList<Request> sortByStartDate(ArrayList<Request> bookingList) {
    	Collections.sort(bookingList, new Comparator<Request>() {
    	    @Override
    	    public int compare(Request r1, Request r2) {
    	        return r1.getStart().compareTo(r2.getStart());
    	    }
    	});
		return bookingList;
    }
}

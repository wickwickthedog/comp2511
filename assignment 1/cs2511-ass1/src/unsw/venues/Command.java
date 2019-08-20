package unsw.venues;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Command {
	JsonOutput print = JsonOutput.getInstance();
	/**
	 * Checks if venue exists in venueList
	 * if venue exists set the venue to the existing venue
	 * then check if room exist in venue 
	 * if room doens't exist add room to venue's arrayList<Room>
	 * else print JSON errorMessage
	 * @param venueList the venuelist from the VenueHireSystem
	 * @param venue 
	 * @param room
	 */
	public static void addRoom(ArrayList<Venue> venueList, Venue venue, Room room){
		for (Venue v : venueList) {
			if (v.getName().equals(venue.getName())) venue = v;
		}
		for (Room r : venue.getRoomList()) {
			if (r.equals(room)) {
				print.jsonObject(print.errorMessage());
				return;
			}
		}
		venue.addRoom(room);
		if (! venueList.contains(venue)) venueList.add(venue);
	}
	/**
	 * Check available Venue from venueList
	 * if venue available setVenue
	 * else print JSON errorMessage
	 * requests size < 0 addRoom to request according to size
	 * @param venueList the venuelist from the VenueHireSystem
	 * @param request
	 * @return boolean
	 */
	public static boolean checkRequest(ArrayList<Venue> venueList, Request request) {
		Venue venue = null;
		for (Venue v : venueList) {
			if (request.checkAvailability(v)) {
				venue = v;
				request.setVenue(venue);
				venue.equals(request.getVenue());
				break;
			}
		}
		if (request.getVenue() == null) {
			print.jsonObject(print.errorMessage());
			return false;
		}
		if (request.getSmall() >= 0) {
    		ArrayList<Room> freeSmallRooms = request.getVenue().searchSmallRoom(request.getStart(), request.getEnd());
    		request.checkRoom(freeSmallRooms, request.getSmall());
    	}
		if (request.getMedium() >= 0) {
    		ArrayList<Room> freeMediumRooms = request.getVenue().searchMediumRoom(request.getStart(), request.getEnd());
    		request.checkRoom(freeMediumRooms, request.getMedium());
    	}
    	if (request.getLarge() >= 0) {
    		ArrayList<Room> freeLargeRooms = request.getVenue().searchLargeRoom(request.getStart(), request.getEnd());
    		request.checkRoom(freeLargeRooms, request.getLarge());
    	}
    	request.updateRoom(request.getStart(), request.getEnd());
		return true;
	}
	/**
	 * Add request to bookingist
	 * print JSON statement
	 * @param bookingList the bookinglist from the VenueHireSystem
	 * @param request
	 */
	public static void addRequest(ArrayList<Request> bookingList, Request request){
		JSONObject result = new JSONObject();
    	bookingList.add(request);
    	print.successMessage(result);
    	print.setField(result, "venue", request.getVenueName());
    	JSONArray rooms = new JSONArray();
    	for (Room r : request.getRoomList()) {
    		print.setField(rooms, r.getName());
    	}
    	print.addJSONArray(result, "rooms", rooms);
    	print.jsonObject(result);
	}
	/**
	 * Remove oldRequest and add newRequest to bookingList
	 * print JSON statement
	 * @param bookingList the bookinglist from the VenueHireSystem
	 * @param oldRequest to remove
	 * @param newRequest to add
	 */
	public static void changeRequest(ArrayList<Request> bookingList, Request oldRequest, Request newRequest) {
		JSONObject result = new JSONObject();
		bookingList.remove(oldRequest);
		bookingList.add(newRequest);
		print.successMessage(result);
		print.setField(result, "venue", newRequest.getVenueName());
		JSONArray rooms = new JSONArray();
    	for (Room r : newRequest.getRoomList()) {
    		print.setField(rooms, r.getName());
    	}
    	print.addJSONArray(result, "rooms", rooms);
    	print.jsonObject(result);
	}
	/**
	 * Check if request exist in bookingList
	 * else print JSON errorMessage 
	 * @param bookingList the bookinglist from the VenueHireSystem
	 * @param id of a request
	 * @return request based on id
	 */
	public static Request checkRequest(ArrayList<Request> bookingList, String id) {
		Request request = null;
		for (Request r : bookingList) {
			if (r.getId().equals(id)) return request = r;
		}
		print.jsonObject(print.errorMessage());
		return request;
	}
	/**
	 * Check if request exist in bookingList
	 * else print JSON errorMessage
	 * @param bookingList the bookinglist from the VenueHireSystem
	 * @param id of a request
	 * @return  ArrayList<Request> based on id
	 */
	public static ArrayList<Request> checkRequests(ArrayList<Request> bookingList, String id) {
		ArrayList<Request> requestList = new ArrayList<Request>();
		for (Request r : bookingList) {
			if (r.getId().equals(id)) requestList.add(r);
		}
		if (requestList.isEmpty()) print.jsonObject(print.errorMessage());
		return requestList;
	}
	/**
	 * Remove requestList from bookingList
	 * print JSON statement
	 * @param bookingList the bookinglist from the VenueHireSystem
	 * @param requestList
	 */
	public static void cancelRequest(ArrayList<Request> bookingList, ArrayList<Request> requestList) {
		Iterator<Request> iterator = bookingList.iterator();
		while (iterator.hasNext()) {
			for (Request request : requestList) {
				if (request.equals(iterator.next())) {
					Room toDel = null;
					for (Room r : request.getRoomList()) {
						toDel = r;
						r.delDate(request.getStart(), request.getEnd());
					}
					request.getRoomList().remove(toDel);
					if (request.getRoomList().isEmpty()) iterator.remove();
				}
			}
		}
		print.jsonObject(print.successMessage());
	}
	/**
	 * Check if venue exist in venueList
	 * else print JSON errorMessage
	 * @param venueList the venuelist from the VenueHireSystem
	 * @param venueName
	 * @return venue based on venueName
	 */
	public static Venue checkVenue(ArrayList<Venue> venueList, String venueName) {
		Venue venue = null;
		for (Venue v : venueList) {
			if (v.getName().equals(venueName)) return venue = v;
		}
		print.jsonObject(print.errorMessage());
		return venue;
	}
	/**
	 * use Utilities.sortByStartDate on bookingList
	 * print JSON statement
	 * @param bookingList the bookinglist from the VenueHireSystem
	 * @param venue
	 */
	public static void listVenue(ArrayList<Request> bookingList, Venue venue) {
		JSONArray array = new JSONArray();
		ArrayList<Request> sortedBookingList = Utilities.sortByStartDate(bookingList);
		for (Room room : venue.getRoomList()) {
			JSONObject result = new JSONObject();
			print.setField(result, "room", room.getName());
			JSONArray bookings = new JSONArray();
			for (int i = 0; i < sortedBookingList.size(); i++) {
				if (sortedBookingList.get(i).getRoomList().isEmpty()) continue;
				for (Room room2 : sortedBookingList.get(i).getRoomList()) {
					if (room2.equals(room)) {
						JSONObject booking = new JSONObject();
						print.setField(booking, "id", sortedBookingList.get(i).getId());
						print.setField(booking, "start", sortedBookingList.get(i).getStart());
						print.setField(booking, "end", sortedBookingList.get(i).getEnd());
						print.addJSONObject(bookings, booking);
					}
				}
			}
			print.addJSONArray(result, "reservations", bookings);
			print.addJSONObject(array, result);
		}
		print.jsonArray(array);	
	}
}

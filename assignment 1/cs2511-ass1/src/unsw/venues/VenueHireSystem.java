/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

import unsw.venues.Room;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Harvey z5147986
 *
 */
public class VenueHireSystem {

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
	private ArrayList<Venue> venueList;
	private ArrayList<Request> bookingList;
	
    private VenueHireSystem() {
        // DONE Auto-generated constructor stub
    	venueList = new ArrayList<Venue>();
    	bookingList = new ArrayList<Request>();
    }

    private void processCommand(JSONObject json) {
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            
            Venue v = new Venue(venue);
            Room r = new Room(room, size);
            Command.addRoom(venueList, v, r);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            Request request = new Request(id, start, end, small, medium, large);            
            if (!Command.checkRequest(venueList, request)) break;
            else Command.addRequest(bookingList, request);
            break;

        // COMPLETED Implement other commands
        case "change":
        	String newId = json.getString("id");
            LocalDate newStart = LocalDate.parse(json.getString("start"));
            LocalDate newEnd = LocalDate.parse(json.getString("end"));
            int newSmall = json.getInt("small");
            int newMedium = json.getInt("medium");
            int newLarge = json.getInt("large");
            
            Request newRequest = new Request(newId, newStart, newEnd, newSmall, newMedium, newLarge);
            if (Command.checkRequest(bookingList, newId) == null) break;
            else {
            	Request oldRequest = Command.checkRequest(bookingList, newId);
            	oldRequest.cancelRoom(oldRequest.getStart(), oldRequest.getEnd());
                if (!Command.checkRequest(venueList, newRequest)) break;
                else Command.changeRequest(bookingList, oldRequest, newRequest);
            }
        	break;
        	
        case "cancel":
        	String delId = json.getString("id");

        	if (Command.checkRequests(bookingList, delId).isEmpty()) break;
        	else Command.cancelRequest(bookingList, Command.checkRequests(bookingList, delId));        	
        	break;
        	
        case "list":
        	String listVenue = json.getString("venue");

        	if (Command.checkVenue(venueList, listVenue) == null) break;
        	else Command.listVenue(bookingList, Command.checkVenue(venueList, listVenue));
        	break;
        }
    }
    
	public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }
}

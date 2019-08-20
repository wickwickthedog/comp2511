package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

public class Request {
	private String id;
	private LocalDate start;
	private LocalDate end;
	private int small;
	private int medium;
	private int large;
	private Venue venue;
	private ArrayList<Room> roomList;
	
	public Request(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
		setId(id);
		setStart(start);
		setEnd(end);
		setSmall(small);
		setMedium(medium);
		setLarge(large);
		roomList = new ArrayList<Room>();
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	private void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the start
	 */
	public LocalDate getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	private void setStart(LocalDate start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public LocalDate getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	private void setEnd(LocalDate end) {
		this.end = end;
	}
	/**
	 * @return the small
	 */
	public int getSmall() {
		return small;
	}
	/**
	 * @param small the small to set
	 */
	private void setSmall(int small) {
		if (small < 0) small = 0;
		this.small = small;
	}
	/**
	 * @return the medium
	 */
	public int getMedium() {
		return medium;
	}
	/**
	 * @param medium the medium to set
	 */
	private void setMedium(int medium) {
		if (medium < 0) medium = 0;
		this.medium = medium;
	}
	/**
	 * @return the large
	 */
	public int getLarge() {
		return large;
	}
	/**
	 * @param large the large to set
	 */
	private void setLarge(int large) {
		if (large < 0) large = 0;
		this.large = large;
	}
	/**
	 * @return the venue
	 */
	public Venue getVenue() {
		return venue;
	}
	/**
	 * @param venue the venue to set
	 */
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	/**
	 * @return the roomList
	 */
	public ArrayList<Room> getRoomList() {
		return roomList;
	}
	/**
	 * @return venue name
	 */
	public String getVenueName() {
		return this.venue.getName();
	}
	/**
	 * Add room to roomList
	 * @param room
	 */
	private void addRoom(Room room) {
		roomList.add(room);
	}
	/**
	 * Check availability of venue
	 * calls search<size>Room which 
	 * return list of free rooms according to size
	 * @param venue
	 * @return boolean
	 */
	public boolean checkAvailability(Venue venue) {
		int freeSmallRooms = venue.searchSmallRoom(start, end).size();
		int freeMediumRooms = venue.searchMediumRoom(start, end).size();
		int freeLargeRooms = venue.searchLargeRoom(start, end).size();
		
		if (small <= freeSmallRooms && medium <= freeMediumRooms && large <= freeLargeRooms) return true;
		else return false;
	}
	/**
	 * Add count of room
	 * into roomList 
	 * @param freeRoomList
	 * @param count
	 */
	public void checkRoom(ArrayList<Room> freeRoomList, int count) {
		int i = 0;
		if (i < count) {
			for (Room r: freeRoomList) {
				if (count == i) return;
				addRoom(r);
				i++;
			}
		}	
	}
	/**
	 * Add start and end date
	 * to room's dateList
	 * @param start
	 * @param end
	 */
	public void updateRoom(LocalDate start, LocalDate end) {
		for (Room r : roomList) {
			r.addDate(start, end);
		}
	}
	/**
	 * Remove start and end date
	 * to room's dateList
	 * @param start
	 * @param end
	 */
	public void cancelRoom(LocalDate start, LocalDate end) {
		for (Room r : roomList) {
			r.delDate(start, end);
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// DONE Auto-generated method stub
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Request otherObj = (Request) obj;
		return this.id.equals(otherObj.id)
				&& this.start.isEqual(otherObj.start) 
				&& this.end.isEqual(otherObj.end) 
				&& this.small == otherObj.small
				&& this.medium == otherObj.medium
				&& this.large == otherObj.large
				&& this.roomList.equals(otherObj.roomList);
	}
	
}

package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venue {
	private String name;
	private ArrayList<Room> roomList;
	
	public Venue(String name) {
		setName(name);
		roomList = new ArrayList<Room>();
	};
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the roomList
	 */
	public ArrayList<Room> getRoomList() {
		return roomList;
	}
	/**
	 * Add room to roomList
	 * @param room
	 */
	public void addRoom(Room room) {
		roomList.add(room);
	}
	/**
	 * Returns a list of free small rooms
	 * @param start
	 * @param end
	 * @return ArrayList<Room>
	 */
	public ArrayList<Room> searchSmallRoom(LocalDate start, LocalDate end) {
		ArrayList<Room> freeSmallRoomList = new ArrayList<Room>();
		for (int i = 0; i < roomList.size(); i++) {
			Room r = roomList.get(i);
			if (r.getSize().equals("small") && r.checkDate(start, end)) freeSmallRoomList.add(r);
		}
		return freeSmallRoomList;
	}
	/**
	 * Returns a list of free medium rooms
	 * @param start
	 * @param end
	 * @return ArrayList<Room>
	 */
	public ArrayList<Room> searchMediumRoom(LocalDate start, LocalDate end) {
		ArrayList<Room> freeMediumRoomList = new ArrayList<Room>();
		for (int i = 0; i < roomList.size(); i++) {
			Room r = roomList.get(i);
			if (r.getSize().equals("medium") && r.checkDate(start, end)) freeMediumRoomList.add(r);
		}
		return freeMediumRoomList;
	}
	/**
	 * Returns a list of free large rooms
	 * @param start
	 * @param end
	 * @return ArrayList<Room>
	 */
	public ArrayList<Room> searchLargeRoom(LocalDate start, LocalDate end) {
		ArrayList<Room> freeLargeRoomList = new ArrayList<Room>();
		for (int i = 0; i < roomList.size(); i++) {
			Room r = roomList.get(i);
			if (r.getSize().equals("large") && r.checkDate(start, end)) freeLargeRoomList.add(r);
		}
		return freeLargeRoomList;
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
		Venue otherObj = (Venue) obj;
		return this.name.equals(otherObj.name) 
				&& this.roomList.equals(otherObj.roomList);
	}	
}

package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Room {
	private String name;
	private String size;
	private ArrayList<LocalDate> dateList;
	
	public Room(String name, String size) {
		setName(name);
		setSize(size);
		dateList = new ArrayList<LocalDate>();
	}
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
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	private void setSize(String size) {
		this.size = size;
	}
	/**
	 * Compare start and end dates
	 * in dateList
	 * @param start
	 * @param end
	 * @return boolean
	 */
	public boolean checkDate(LocalDate start, LocalDate end) {
		if (! dateList.isEmpty()) {
			int i = 0;
			while(i < dateList.size()) {
				int j = i + 1;
				if (start.isAfter(dateList.get(i)) && start.isBefore(dateList.get(j))) return false;
				if (end.isAfter(dateList.get(i)) && end.isBefore(dateList.get(j))) return false;
				if (start.isEqual(dateList.get(i)) || start.isEqual(dateList.get(j))) return false;
				if (end.isEqual(dateList.get(i)) || end.isEqual(dateList.get(j))) return false;
				if (dateList.get(i).isAfter(start) && dateList.get(j).isBefore(end)) return false;
				i = j + 1;
			}
		}
		return true;
	}
	/**
	 * Add start and end date
	 * to dateList
	 * @param start
	 * @param end
	 */
	public void addDate(LocalDate start, LocalDate end) {
		dateList.add(start);
		dateList.add(end);
	}
	/**
	 * Remove start and end date
	 * to dateList
	 * @param start
	 * @param end
	 */
	public void delDate(LocalDate start, LocalDate end) {
		Iterator<LocalDate> it = dateList.iterator();
		while (it.hasNext()) {
			LocalDate temp = it.next();
			if (start.isEqual(temp) || end.isEqual(temp)) it.remove();
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
		Room otherObj = (Room) obj;
		return this.name.equals(otherObj.name) 
				&& this.size.equals(otherObj.size);
	}
}

package staff.test;

import java.time.LocalDate;

import staff.Lecturer;
import staff.StaffMember;

public class StaffTest {
	public void printStaffDetails(StaffMember staff) {
		System.out.println(staff.toString());
	}
	
	public static void main(String[] args) {
        // Add your code
		LocalDate d1 = LocalDate.of(2018,01,01);
		LocalDate d2 = LocalDate.of(2019,01,01);
		
		StaffMember s1 = new StaffMember("Ling Ling", 5000.05, d1, d2);
		StaffMember s2 = new StaffMember("Abu", 3000.20, d1, d2);
		
		Lecturer l1 = new Lecturer("Ling Ling", 10000, d1, d2, "UNSW", 'A');
		Lecturer l2 = new Lecturer("Ling Ling", 10000, d1, d2, "UNSW", 'A');
		
		StaffTest test = new StaffTest();
		System.out.println("---Printing Staff(s)---");
		test.printStaffDetails(s1);
		test.printStaffDetails(s2);
		test.printStaffDetails(l1);
		
		System.out.println("---Equals Method---");
		if (s1.equals(s2)) System.out.println("s1 is equals to s2");
		else System.out.println("s1 is NOT equals to s2");
		
		if (s1.equals(l1)) System.out.println("s1 is equals to l1");
		else System.out.println("s1 is NOT equals to l1");
		
		if (l1.equals(s1)) System.out.println("l1 is equals to s1");
		else System.out.println("l1 is NOT equals to s1");
		
		/* reflective */
		if (l1.equals(l1)) System.out.println("l1 is equals to l1");
		else System.out.println("l1 is NOT equals to l1");
		
		/* symmetric */
		if (l1.equals(l2)) System.out.println("l1 is equals to l2");
		else System.out.println("l1 is NOT equals to l2");
		
		/* null check */
		if (l1.equals(null)) System.out.println("l1 is equals to null");
		else System.out.println("l1 is NOT equals to null");
    }
}

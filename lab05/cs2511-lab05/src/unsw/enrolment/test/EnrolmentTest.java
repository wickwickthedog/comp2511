package unsw.enrolment.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Grade;
import unsw.enrolment.Lecture;
import unsw.enrolment.LogSystem;
import unsw.enrolment.Marks;
import unsw.enrolment.Student;
import unsw.enrolment.Tutorial;

public class EnrolmentTest {

    public static void main(String[] args) {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // Create some sessions for the courses
        Lecture lecture1511 = new Lecture(comp1511Offering, "Rex Vowels", DayOfWeek.TUESDAY, LocalTime.of(12, 0),LocalTime.of(14, 0), "Andrew Taylor");
        Lecture lecture1531 = new Lecture(comp1531Offering, "CLB 5", DayOfWeek.MONDAY, LocalTime.of(9, 0),LocalTime.of(11, 0), "Aarthi Natarajan");
        Lecture lecture2521 = new Lecture(comp2521Offering, "Clancy Auditorium", DayOfWeek.FRIDAY, LocalTime.of(15, 0),LocalTime.of(17, 0), "Ashesh Mahidadia");

        Tutorial tute1531 = new Tutorial(comp1531Offering, "Quad 1041", DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(19,0), "Hugh Chan");

        // Create a student
        Student student = new Student("z5555555");

        // Enrol the student in COMP1511 for T1 (this should succeed)
        Enrolment comp1511Enrolment = comp1511Offering.enrol(student, lecture1511);

        if (comp1511Enrolment != null)
            System.out.println("Enrolled in COMP1511");

        // Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        Enrolment comp1531Enrolment = comp1531Offering.enrol(student, lecture1531, tute1531);

        if (comp1531Enrolment == null)
            System.out.println("Can't enrol in COMP1531");

        // Give the student a passing grade for COMP1511
        Marks comp1511Grade = new Marks("Assessments");
        // Assign marks for comp1511
        
        // DONE Give the student a passing mark for assignment 1
        Grade comp1511Ass1 = new Grade("Ass1", 20);
        comp1511Grade.addassessments(comp1511Ass1);
        
        // DONE Give the student a passing mark for milestone 1 of assignment 2
        Marks comp1511Ass2 = new Marks("Ass2");
        Grade ass2_milestone1 = new Grade("milestone1", 20);
        Grade ass2_milestone2 = new Grade("milestone2", 20);
        comp1511Ass2.addassessments(ass2_milestone1);
        
        // DONE Give the student a passing mark for milestone 2 of assignment 2

        comp1511Ass2.addassessments(ass2_milestone2);
        comp1511Grade.addassessments(comp1511Ass2);
        // DONE Give the student an assignment 2 mark which is the average of
        // milestone 1 and 2
        // DONE Give the student a prac mark which is the sum of assignment 1
        // and 2
//        System.out.println("Prac Mark = " + comp1511Grade.getPracMarks());
        // DONE Give the student a passing exam mark.
        Grade comp1511Finals = new Grade("FinalExam", 40);
        comp1511Grade.addassessments(comp1511Finals);
        comp1511Enrolment.setGrade(comp1511Grade);
        if (comp1511Enrolment.hasPassed()) {
        	System.out.println(student.getZID() + " passed COMP1511 with " + comp1511Enrolment.getMarks() + "!");
        }
        // Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        Enrolment comp2521Enrolment = comp2521Offering.enrol(student, lecture2521);
        if (comp2521Enrolment == null) System.out.println("Fail to Enrol");
        // LogSystem
        LogSystem logSys = new LogSystem();
        comp1511Enrolment.addObserver(logSys);
        // Try to create 2 same log or append 
        System.out.println(logSys.createLog(comp1511Enrolment, comp1511Grade));
        System.out.println(logSys.createLog(comp1511Enrolment, comp1511Grade));
        // update log

        Grade ass2_milestone3 = new Grade("milestone3", 50);
        comp1511Ass2.addassessments(ass2_milestone3);
//        System.out.println("New Prac Mark = " + comp1511Grade.getPracMarks());
        System.out.println(logSys.updateLog(comp1511Enrolment, comp1511Grade));
        
        if (comp2521Enrolment != null)
            System.out.println("Enrolled in COMP2521");
    }
}

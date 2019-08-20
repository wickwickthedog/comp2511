package unsw.enrolment.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import unsw.enrolment.Course;
import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.Grade;
import unsw.enrolment.Lecture;
import unsw.enrolment.Session;
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
        // DONE Create some sessions for the courses
        Lecture comp1511Lecture = new Lecture("Science Theater", DayOfWeek.MONDAY, LocalTime.of(9,00), LocalTime.of(12,00), "Ling Ling");
        comp1511Offering.addSession(comp1511Lecture);
        Tutorial comp1511Tut1 = new Tutorial("CSE lab 1", DayOfWeek.MONDAY, LocalTime.of(12,00), LocalTime.of(13,00), "Ling Ling");
        comp1511Offering.addSession(comp1511Tut1);
        Tutorial comp1511Tut2 = new Tutorial("CSE lab 1", DayOfWeek.TUESDAY, LocalTime.of(12,00), LocalTime.of(13,00), "Ling Ling");
        comp1511Offering.addSession(comp1511Tut2);
        
        Lecture comp1531Lecture = new Lecture("Law Theater", DayOfWeek.TUESDAY, LocalTime.of(9,00), LocalTime.of(12,00), "Ling Ling");
        comp1531Offering.addSession(comp1531Lecture);
        Lecture comp2521Lecture = new Lecture("Science Theater", DayOfWeek.WEDNESDAY, LocalTime.of(9,00), LocalTime.of(12,00), "Ling Ling");
        comp2521Offering.addSession(comp2521Lecture);
        // DONE Create a student
        Student student = new Student("z5555555");
        // DONE Enrol the student in COMP1511 for T1 (this should succeed)
        Enrolment e1 = new Enrolment(comp1511Offering, student);
        student.addEnrolments(e1);
        // DONE Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        comp1531Offering.addEnrolment(student);
        
        // DONE Give the student a passing grade for COMP1511
        e1.setGrade(new Grade(50));
        // DONE Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        comp2521Offering.addEnrolment(student);

        // display student enrolment
        System.out.println("-----Display-----");
        System.out.println(student.toString());
        
    }
}

# Lab 03

## Due: Week 3, Sunday, 5:00 pm

## Value: 1 mark

## Aim

* Understand the OO principles abstraction, encapsulation and inheritance
* Become familiar with the Java API

## Setup

An individual repository for this lab has been created for you here (replace z5555555 with your own zID):

https://gitlab.cse.unsw.edu.au/z5555555/19T2-cs2511-lab03

## Exercise

Recall the university enrolment system from the lectures. The requirements for this system were as follows:

* Students enrol in courses that are offered in particular semesters
* Students receive grades (pass, fail, etc.) for courses in particular semesters
* Courses may have pre-requisites (other courses) and must have credit point values
* For a student to enrol in a course, s/he must have passed all prerequisite courses
* Course offerings are broken down into multiple sessions (lectures, tutorials and labs)
* Sessions in a course offering for a particular semester have an allocated room and timeslot
* If a student enrols in a course, s/he must also enrol in some sessions of that course

Using what was developed in the lecture as a basis, draw a complete UML diagram including all fields, methods and relationships. In deciding on the methods for the classes, you may wish to consider the principle of least knowledge (Law of Demeter).

In this repo is skeleton code for basic classes and a test class with a series of `TODO`s describing a simple use case. Complete the test class by implementing the necessary components of the enrolment system. You can change the code in any way you feel is necessary.

After finishing the implementation, make sure your diagram is still consistent with the code. If you had to update your diagram, consider why.

## Submission

Make sure that all your work has been pushed to GitLab then submit it with:

```bash
$ 2511 submit lab03
```

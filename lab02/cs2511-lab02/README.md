# Lab 02

## Due: Week 2, Sunday, 5:00 pm

## Value: 1 mark

## Aim

* Understand the OO principles abstraction, encapsulation and inheritance
* Become familiar with the Java API

## Setup

An individual repository for this lab has been created for you here (replace z5555555 with your own zID):

https://gitlab.cse.unsw.edu.au/z5555555/19T2-cs2511-lab02

## Exercise

Modify the class StaffMember (inside package `staff`) so that it satisfies the following requirements:

* Attributes to store the staff member’s name, salary, hire date, and end date.
* Appropriate constructors, getters, setters, and other methods you think are necessary.
* Overridden `toString()` and `equals(..)` methods. Use what you learnt from the tutorial, but make sure you understand what these methods are doing.
* Javadoc for all non-overriding methods and constructors.

Create a sub-class of `StaffMember` called `Lecturer` in the same package with the following requirements:

* An attribute to store the school (e.g. CSE) that the lecturer belongs to
* An attribute that stores the academic status of the lecturer (e.g A for an Associate Lecturer, B  for a Lecturer, and C for a Senior Lecturer)
* Appropriate getters and setters.
* An overriding `toString()` method that includes the school name and academic level.
* An overriding `equals(...)` method.
* Javadoc for all non-overriding methods and constructors.

Create a class `StaffTest` in the package in the package `staff.test` to test the above classes. It should contain:

* A method `printStaffDetails(...)` that takes a `StaffMember` as a parameter and invokes the `toString()` method on it to print the details of the staff.
* A `main(...)` method that:

  * Creates a `StaffMember` with a name and salary of your choosing.
  * Creates an instance of `Lecturer` with a name, salary, school and academic status of your choosing.
  * Calls `printStaffDetails(...)` twice with each of the above as arguments.
  * Tests the `equals(..)` method of the two classes. Use [the documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-) for `Object.equals(...)` as a guide for what you should test.

## Submission

Make sure that all your work has been pushed to GitLab then submit it with:

```bash
$ 2511 submit lab02
```

## Prologue

With any leftover time in your lab, look at the specification for assignment 1 and try to come to a concrete understanding of the requirements. You may even try to write down an initial set of CRC cards.

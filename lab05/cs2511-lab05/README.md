# Lab 05

## Due: Week 6, Sunday, 5:00 pm

**NOTE**: You have two weeks to do this lab

## Value: 2 marks

## Aim

* Learn how to find useful classes and methods in the Java API
* Become familiar with design patterns - composite and observer

## Setup

An individual repository for this lab has been created for you here (replace z5555555 with your own zID):

https://gitlab.cse.unsw.edu.au/z5555555/19T2-cs2511-lab05

## Preparation

The composite and observer patterns you need for this lab will be covered in the week 5 lectures. For the composite pattern, you may also find [this video](https://www.soln.io/course/B1RYiLjNX?s=HkKJqGGdQ) helpful.

## Composite

Recall the enrolment system from week 3. For this lab, some additional requirements have been introduced.

* Students are assigned marks for the assessments they do while undertaking a course.
* Marks are either entered in manually or calculated automatically based on submarks.
* Each mark has an associated maximum mark obtainable
* Calculated marks are either a sum or an average.

To give an example, a student may have a "prac" mark which is the sum of an "ass1" and an "ass2" mark. The "ass1" mark was entered in manually, but the "ass2" mark is the average of "milestone1", "milestone2" and "milestone3", all of which were entered in manually.

A solution to the enrolment example from week 3 is included in this lab. Modify the `Enrolment` class such that it stores the student's marks. Use the composite pattern to model the relationship between marks. To simplify matters, you can assume that:

* The final mark for a course is just the sum of the marks for the top-level assessments of that course.
* Calculated marks can't be created based on marks that haven't been entered yet.
* A mark can't be used directly in more than one calculation.
* Marks are whole numbers not fractional.

You should keep in mind that students may be assigned marks that are initially not part of any calculated mark but which subsequently are placed into one. If you can, try to keep your use of the composite pattern contained entirely within the classes that make up the pattern and `Enrolment`. See if you can provide a simple interface for entering in marks in `Enrolment`.

Complete the `TODO`s in `EnrolmentTest` to show how your solution works.

Commit and push your changes for this first part of the lab before moving on to the next.

## Observer

As marks are important and sensitive data, it is desirable that any changes to marks are logged in an external system. Ideally, this logging would be separated from the code that stores and calculates the marks. Use the observer pattern to realise this. Concretely:

* A log file will be written for each enrolment.
* That log file will be named in the format 'course-term-student'. e.g. 'COMP1511-19T1-z5555555'.
* Each line of the log file will contain the current date and time, the assessment, and the new mark for that assessment, in a format of your choosing.

After making the appropriate class a subclass of `java.util.Observable`, attach observers in `Enrolment` that listen for changes to the marks. Upon observing such a change, the observers should write a new line to the log for that enrolment.

**HINT:** Make sure that you're appending to the file, not overwriting it.

## Submission

Make sure that all your work has been pushed to GitLab then submit it with:

```bash
$ 2511 submit lab05
```

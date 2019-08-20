# Lab 09

## Due: Week 10, Sunday, 5:00 pm

**NOTE**: You have two weeks to do this lab

## Value: 2 marks

## Aim

* Implement a class given a contract.
* Gain experience with Java generics.

## Setup

An individual repository for this lab has been created for you here (replace z5555555 with your own zID):

https://gitlab.cse.unsw.edu.au/z5555555/19T2-cs2511-lab09

## Preamble

This lab will be entirely automarked. Some tests have been provided, but the tests used for automarking will be more exhaustive. Partial marks are available (see below). It's important that you carefully read through the specification and the provided code to ensure you have a complete understanding of what is required.

In addition to being worth lab marks, this exercise is also similar in style to a question you will need to answer in the exam, so it is good practice. That question will also be entirely automarked, but shorter than this exercise.

## Specification

In the provided code, the `unsw.collections.Set` interface represents a generic set. Note that this is distinct to the standard `java.util.Set`. Also provided is a partially complete implementation of this interface, `unsw.collections.ArrayListSet`. In addition to implementing the interface methods, this class also needs to implement the `iterator()` method from `Iterable` and the `equals(...)` method. Your task is to complete it without making any changes to the `Set` interface and ensuring it is consistent with the documentation and contract.

What follow is a list of what needs to be done, in rough order of difficulty with the corresponding mark weighting.

* `subsetOf(...)` - *0.25 marks*
* `iterator()` - *0.25 marks*
* `union(...)` - *0.25 marks*
* `intersection(...)` - *0.25 marks*
* `equals(...)` - *1 mark*

Note that the automarking will assume the `add(...)`, `remove(...)`, `contains(...)`, and `size()` methods all work, which they do in the code given. You shouldn't need to change these methods, but if you do, you may find unexpected automarking failures when you get the results back.

## Hints

* The `iterator()` method can be implemented in one line.
* For `equals(...)`, you will need to use `instanceof` and wildcards (`?`) in the body of the method.
* Some basic tests have been given, but you should write your own to ensure your implementation is correct.

## Submission

Make sure that all your work has been pushed to GitLab then submit it with:

```bash
$ 2511 submit lab09
```

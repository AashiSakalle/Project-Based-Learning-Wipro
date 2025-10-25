package com.mile1.service;

import com.mile1.bean.Student;
import com.mile1.exception.*;

public class StudentReport {

    // Method to validate the student object before grading
    public String validate(Student s) throws NullStudentObjectException, NullNameException, NullMarksArrayException {
        // If Object passes as parameter itself is null, then, throw the NullStudentObjectException.
        if (s == null) {
            throw new NullStudentObjectException();
        }

        // Else do the following: Check whether there is any null data in the given object.
        // We need to look for null inside the object.

        // 1) If Name is null, then throw the NullNameException.
        if (s.getName() == null) {
            throw new NullNameException();
        }

        // 2) Else If marks array is null then throw the NullMarksArrayException.
        if (s.getMarks() == null) {
            throw new NullMarksArrayException();
        }

        // 3) If all data is valid, return "VALID".
        return "VALID";
    }

    // Method to calculate and return the grade
    // Assumption: Only valid objects are passed to this method.
    public String findGrades(Student studentObject) {
        // Get the marks from the given object studentObject.
        int[] marks = studentObject.getMarks();
        int sum = 0;

        // If any one of the marks is less than 35 then grade is "F";
        for (int mark : marks) {
            if (mark < 35) {
                return "F";
            }
            sum += mark;
        }

        // Find the Sum of all the marks.
        // Grading logic based on sum:
        if (sum < 150) {
            return "C";
        } else if (sum < 200) {
            return "B";
        } else if (sum < 250) {
            return "A";
        } else { // sum >= 250
            return "A+";
        }
    }
}
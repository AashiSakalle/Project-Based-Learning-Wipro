package com.mile1.service;

import com.mile1.bean.Student;
import com.mile1.exception.*;

public class StudentReport {

    public String validate(Student s) throws NullStudentObjectException, NullNameException, NullMarksArrayException {
        // If Object passes as parameter itself is null
        if (s == null) {
            throw new NullStudentObjectException();
        }

        // 1) If Name is null
        if (s.getName() == null) {
            throw new NullNameException();
        }

        // 2) Else If marks array is null
        if (s.getMarks() == null) {
            throw new NullMarksArrayException();
        }

        // 3) If all data is valid
        return "VALID";
    }

    public String findGrades(Student studentObject) {
        int[] marks = studentObject.getMarks();
        int sum = 0;

        // If any one of the marks is less than 35 then grade is "F"
        for (int mark : marks) {
            if (mark < 35) {
                return "F";
            }
            sum += mark;
        }

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
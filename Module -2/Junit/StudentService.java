package com.mile1.service;

import com.mile1.bean.Student;

public class StudentService {

    public int findNumberOfNullMarksArray(Student s[]) {
        int count = 0;
        
        if (s != null) {
            for (Student student : s) {
                // Check if the individual object is not null, then check marks array
                if (student != null) {
                    if (student.getMarks() == null) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int findNumberOfNullName(Student s[]) {
        int count = 0;
        
        if (s != null) {
            for (Student student : s) {
                if (student != null) {
                    if (student.getName() == null) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int findNumberOfNullObjects(Student s[]) {
        int count = 0;
        
        if (s != null) {
            for (Student student : s) {
                if (student == null) {
                    count++;
                }
            }
        }
        return count;
    }
}
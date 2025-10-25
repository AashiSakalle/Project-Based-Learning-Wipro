public class StudentService {
    
}
package com.mile1.service;

import com.mile1.bean.Student;

public class StudentService {

    // Method to count objects where the marks array is null
    public int findNumberOfNullMarksArray(Student s[]) {
        // Let C=0;
        int count = 0;
        
        // Check whether the s is not null (prevents NullPointerException on array itself)
        if (s != null) {
            // For all the objects in the s array:
            for (Student student : s) {
                // Check if the individual object is not null, then check marks array
                if (student != null) {
                    // Check whether the marks array is null. If so, increase C by 1.
                    if (student.getMarks() == null) {
                        count++;
                    }
                }
            }
        }
        // Return latest Count value;
        return count;
    }

    // Method to count objects where the name is null
    public int findNumberOfNullName(Student s[]) {
        // Code like above method, referring to findNumberOfNullMarksArray
        int count = 0;
        
        if (s != null) {
            for (Student student : s) {
                if (student != null) {
                    // Check whether the name is null.
                    if (student.getName() == null) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // Method to count the number of null objects
    public int findNumberOfNullObjects(Student s[]) {
        // Let C=0;
        int count = 0;
        
        // Check whether the s is not null.
        if (s != null) {
            // For all the objects in the s array, if the individual object is null, then increase C by 1.
            for (Student student : s) {
                if (student == null) {
                    count++;
                }
            }
        }
        // Return latest Count value;
        return count;
    }
}
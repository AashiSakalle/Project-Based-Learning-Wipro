package com.mile1.main;

import com.mile1.bean.Student;
import com.mile1.exception.*;
import com.mile1.service.*;

public class StudentMain {

    // Variables
    static Student data[] = new Student[4];
    
    // StudentMain() Constructor
    public StudentMain() {
        // Initialize the array with empty Student objects (as requested, though not strictly needed for this logic)
        for (int i = 0; i < data.length; i++) {
            data[i] = new Student();
        }

        // Initialize the objects
        data[0] = new Student("Sekar", new int[] {85, 75, 95}, null); // Valid, sum=255
        data[1] = new Student(null, new int[] {11, 22, 33}, null); // Name null
        data[2] = null; // Object null
        data[3] = new Student("Manoj", null, null); // Marks array null
    }

    public static void main(String[] args) {
        // Initialize the data array using the constructor
        new StudentMain(); 
        
        // Create StudentReport and StudentService Objects
        StudentReport studentReport = new StudentReport();
        StudentService studentService = new StudentService();
        
        System.out.println("--- Validation and Grade Calculation ---");
        
        // 1) Call the validate method for all objects available in data array.
        // 2) If any exception arises during validation, catch the exception and call the printStackTrace() method on that object.
        // 3) If validate method returns "VALID", then call the findGrades method & print the result returned by findGrades method.
        for (int i = 0; i < data.length; i++) {
            Student s = data[i];
            
            try {
                String validationResult = studentReport.validate(s);
                
                if (validationResult.equals("VALID")) {
                    String grade = studentReport.findGrades(s);
                    s.setGrade(grade); // Set the grade back to the object
                    System.out.printf("S%d: Name: %-6s - Marks: %s - Grade: %s%n", 
                                      i, s.getName(), java.util.Arrays.toString(s.getMarks()), grade);
                }
            } catch (NullStudentObjectException | NullNameException | NullMarksArrayException e) {
                System.out.printf("S%d: Exception caught for employee: %s%n", i, (s == null ? "NULL OBJECT" : s.getName()));
                // The requirement is to call printStackTrace() on the exception object.
                e.printStackTrace(); 
            } catch (Exception e) {
                // Catch any other unexpected exceptions
                e.printStackTrace();
            }
        }
        
        System.out.println("\n--- Counting Methods ---");
        
        // Call the findNumberOfNullMarksArray(data) method and print the result.
        int nullMarksCount = studentService.findNumberOfNullMarksArray(data);
        System.out.println("Number of students with Null Marks Array: " + nullMarksCount); 
        
        // Call the findNumberOfNullName(data) method and print the result.
        int nullNameCount = studentService.findNumberOfNullName(data);
        System.out.println("Number of students with Null Name: " + nullNameCount);
        
        // Call the findNumberOfNullObjects(data) method and print the result.
        int nullObjectCount = studentService.findNumberOfNullObjects(data);
        System.out.println("Number of Null Student Objects: " + nullObjectCount);
    }
}
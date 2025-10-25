package com.mile1.main;

import com.mile1.bean.Student;
import com.mile1.exception.*;
import com.mile1.service.*;

public class StudentMain {

    static Student data[] = new Student[4];
    
    public StudentMain() {
        // Initialize the objects as per the requirements
        data[0] = new Student("Sekar", new int[] {85, 75, 95}, null); 
        data[1] = new Student(null, new int[] {11, 22, 33}, null); 
        data[2] = null; 
        data[3] = new Student("Manoj", null, null); 
    }

    public static void main(String[] args) {
        // Initialize the data array using the constructor
        new StudentMain(); 
        
        StudentReport studentReport = new StudentReport();
        StudentService studentService = new StudentService();
        
        System.out.println("--- Validation and Grade Calculation ---");
        
        for (int i = 0; i < data.length; i++) {
            Student s = data[i];
            
            try {
                String validationResult = studentReport.validate(s);
                
                if (validationResult.equals("VALID")) {
                    String grade = studentReport.findGrades(s);
                    s.setGrade(grade); 
                    System.out.printf("S%d: Name: %-6s - Marks: %s - Grade: %s%n", 
                                      i, s.getName(), java.util.Arrays.toString(s.getMarks()), grade);
                }
            } catch (NullStudentObjectException | NullNameException | NullMarksArrayException e) {
                // Catch exception and call printStackTrace()
                System.out.printf("S%d: Exception caught for object: %s%n", i, (s == null ? "NULL OBJECT" : s.getName()));
                e.printStackTrace(); 
            }
        }
        
        System.out.println("\n--- Counting Methods ---");
        
        // Call and print results of the counting methods
        System.out.println("Number of students with Null Marks Array: " + studentService.findNumberOfNullMarksArray(data)); 
        System.out.println("Number of students with Null Name: " + studentService.findNumberOfNullName(data));
        System.out.println("Number of Null Student Objects: " + studentService.findNumberOfNullObjects(data));
    }
}
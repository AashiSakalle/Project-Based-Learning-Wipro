public class Student {
    
}
package com.mile1.bean;

public class Student {
    // Instance variables
    private String name;
    private int[] marks;
    private String grade;

    // Constructors
    public Student() {
        // Auto-generated default constructor
    }

    public Student(String name, int[] marks, String grade) {
        this.name = name;
        this.marks = marks;
        this.grade = grade;
    }

    // Getters and Setters (Auto-generated)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
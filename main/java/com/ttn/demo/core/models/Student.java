package com.ttn.demo.core.models;
public class Student {
    private final int id;
    private final String name;
    private final int marks;
    private final int age;

    public Student(int id, String name, int marks, int age) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getMarks() { return marks; }
    public int getAge() { return age; }
}


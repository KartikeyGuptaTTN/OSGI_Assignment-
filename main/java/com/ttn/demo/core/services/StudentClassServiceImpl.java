package com.ttn.demo.core.services;

import com.ttn.demo.core.models.Student;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component(service = StudentClassService.class)
public class StudentClassServiceImpl implements StudentClassService {
    private final List<Student> studentList = new ArrayList<>();

    public StudentClassServiceImpl() {
        studentList.add(new Student(1, "Ramesh", 85, 26));
        studentList.add(new Student(2, "Suresh", 35, 16));
        studentList.add(new Student(3, "Kohli", 20, 20));
    }

    @Override
    public String addStudent(Student student) {
        studentList.add(student);
        return "Student added: " + student.getName();
    }

    @Override
    public String deleteStudent(int id) {
        return studentList.removeIf(s -> s.getId() == id) ? "Student removed successfully" : "Student not found";
    }

    @Override
    public boolean isStudentPassed(int id) {
        return studentList.stream().anyMatch(s -> s.getId() == id && s.getMarks() >= 40);
    }

    @Override
    public Student getStudent(int id) {
        return studentList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList);
    }
}

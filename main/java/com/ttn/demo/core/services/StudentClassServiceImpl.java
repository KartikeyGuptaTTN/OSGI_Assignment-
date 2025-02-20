package com.ttn.demo.core.services;

import com.ttn.demo.core.models.Student;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Component(service = StudentClassService.class)
public class StudentClassServiceImpl implements StudentClassService {
    private final List<Student> studentList = new ArrayList<>();

    public StudentClassServiceImpl() {
        studentList.add(new Student(1, "Ramesh", 85, 26));
        studentList.add(new Student(2, "Suresh", 35, 16));
        studentList.add(new Student(3, "Kohli", 20, 20));
        studentList.add(new Student(4, "Virat", 90, 22));
        studentList.add(new Student(5, "Rohit", 79, 21));
        studentList.add(new Student(6, "Sharma", 10, 21));
        studentList.add(new Student(7, "Hardik", 15, 24));
        studentList.add(new Student(8, "Pandya", 45, 25));
        studentList.add(new Student(9, "Jasprit", 80, 26));
        studentList.add(new Student(10, "Bumrah", 23, 23));
        studentList.add(new Student(11, "Ravi", 30, 26));

    }
    @Reference
    private ClassConfigurationService classConfigService;

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
        int passingmarks = classConfigService.getPassingMarks();
        return studentList.stream().anyMatch(s -> s.getId() == id && s.getMarks() >= passingmarks);
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

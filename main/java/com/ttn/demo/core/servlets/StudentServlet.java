package com.ttn.demo.core.servlets;

import com.ttn.demo.core.models.Student;
import com.ttn.demo.core.services.StudentClassService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/student",
                "sling.servlet.methods=GET"
        }
)
public class StudentServlet extends SlingAllMethodsServlet {

    @Reference
    private StudentClassService studentService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application");
        response.setCharacterEncoding("UTF-8");

        // Ensure the service is available
        if (studentService == null) {
            response.getWriter().write("{\"error\": \"Student service is not available\"}");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            response.getWriter().write("{\"error\": \"Action parameter is required\"}");
            return;
        }

        try {
            switch (action.toLowerCase()) {
                case "add":
                    Student student = new Student(
                            Integer.parseInt(request.getParameter("id")),
                            request.getParameter("name"),
                            Integer.parseInt(request.getParameter("marks")),
                            Integer.parseInt(request.getParameter("age"))
                    );
                    response.getWriter().write("{\"message\": \"" + studentService.addStudent(student) + "\"}");
                    break;

                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    response.getWriter().write("{\"message\": \"" + studentService.deleteStudent(deleteId) + "\"}");
                    break;

                case "ispassed":
                    int passId = Integer.parseInt(request.getParameter("id"));
                    response.getWriter().write("{\"result\": \"" + (studentService.isStudentPassed(passId) ? "Student Passed" : "Student Failed") + "\"}");
                    break;

                case "get":
                    int getId = Integer.parseInt(request.getParameter("id"));
                    Student retrievedStudent = studentService.getStudent(getId);
                    response.getWriter().write(retrievedStudent != null ? "{\"name\": \"" + retrievedStudent.getName() + "\"}" : "{\"error\": \"Student not found\"}");
                    break;

                case "getall":
                    List<Student> students = studentService.getAllStudents();
                    String studentNames = students.stream().map(Student::getName).collect(Collectors.joining(", "));
                    response.getWriter().write("{\"students\": \"" + studentNames + "\"}");
                    break;

                default:
                    response.getWriter().write("{\"error\": \"Invalid action\"}");
                    break;
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("{\"error\": \"Invalid numeric parameter\"}");
        }
    }
}

package com.student.demo.controller;

import java.util.Map;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.demo.model.Admin;
import com.student.demo.model.Student;
import com.student.demo.model.Tutor1;
import com.student.demo.model.Hod;
import com.student.demo.repository.AdminRepo;
import com.student.demo.repository.StudentRepository;
import com.student.demo.repository.TutorRepo;
import com.student.demo.repository.HodRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private TutorRepo tutorRepo;

    @Autowired
    private AdminRepo adminrepo;

    @Autowired
    private HodRepository hodRepo;

    // A common login API
    @PostMapping("/leaveform")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        // Check tutor login
        Optional<Tutor1> tutor = tutorRepo.findByUsername(username);
        if (tutor.isPresent() && tutor.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("role", tutor.get().getRole()));
        }

        // Check student login
        Optional<Student> student = studentRepo.findByUsername(username);
        if (student.isPresent() && student.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("role", student.get().getRole()));
        }

        Optional<Admin> admin = adminrepo.findByUsername(username);
        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("role", admin.get().getRole()));
        }

        // Check HOD login
        Optional<Hod> hod = hodRepo.findByUsername(username);
        if (hod.isPresent() && hod.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of("role", hod.get().getRole()));
        }

        // Invalid credentials
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Missing student endpoints
    @PostMapping("/students/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        student.setRole("student");
        studentRepo.save(student);
        return ResponseEntity.ok("Student added successfully");
    }

    @GetMapping("/students/all")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // Missing tutor endpoints
    @PostMapping("/tutor/add")
    public ResponseEntity<String> addTutor(@RequestBody Tutor1 tutor) {
        tutor.setRole("tutor");
        tutorRepo.save(tutor);
        return ResponseEntity.ok("Tutor added successfully");
    }

    @GetMapping("/tutor/by-department/{dept}")
    public List<Tutor1> getTutorsByDepartment(@PathVariable String dept) {
        // We'd ideally have findByDepartment, but we can do it via stream for now
        return tutorRepo.findAll().stream().filter(t -> dept.equalsIgnoreCase(t.getDepartment())).toList();
    }
}

package com.student.demo.security;

import com.student.demo.model.Admin;
import com.student.demo.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.student.demo.model.Student;
import com.student.demo.model.Tutor1;
import com.student.demo.repository.StudentRepository;
import com.student.demo.repository.TutorRepo;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private AdminRepo adminRepo;
    
    @Autowired
    private StudentRepository studentRepo;
    
    @Autowired
    private TutorRepo tutorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if any admin account already exists
        if (adminRepo.count() == 0) {
            Admin defaultAdmin = new Admin();
            defaultAdmin.setUsername("admin@school.edu");
            // Hash the password for security setup
            defaultAdmin.setPassword(passwordEncoder.encode("AdminPass123!"));
            defaultAdmin.setRole("admin");
            adminRepo.save(defaultAdmin);
        }
        
        if (studentRepo.count() == 0) {
            Student defaultStudent = new Student();
            defaultStudent.setUsername("student@school.edu");
            defaultStudent.setPassword(passwordEncoder.encode("StudentPass123!"));
            defaultStudent.setRole("student");
            defaultStudent.setDepartment("CS");
            defaultStudent.setYear("3");
            studentRepo.save(defaultStudent);
        }
        
        if (tutorRepo.count() == 0) {
            Tutor1 defaultTutor = new Tutor1();
            defaultTutor.setUsername("tutor@school.edu");
            defaultTutor.setPassword(passwordEncoder.encode("TutorPass123!"));
            defaultTutor.setRole("tutor");
            defaultTutor.setDepartment("CS");
            tutorRepo.save(defaultTutor);
        }
    }
}

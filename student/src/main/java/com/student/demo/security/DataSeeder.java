package com.student.demo.security;

import com.student.demo.model.Admin;
import com.student.demo.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if any admin account already exists
        if (adminRepo.count() == 0) {
            Admin defaultAdmin = new Admin();
            defaultAdmin.setUsername("admin");
            // Hash the password for security setup
            defaultAdmin.setPassword(passwordEncoder.encode("admin123"));
            defaultAdmin.setRole("admin");

            adminRepo.save(defaultAdmin);
            System.out.println("====== DEFAULT ADMIN ACCOUNT CREATED ======");
            System.out.println("Username: admin");
            System.out.println("Password: admin123");
            System.out.println("Please login and restrict this application!");
            System.out.println("===========================================");
        } else {
            System.out.println("Admin system already initialized. Skipping seeding.");
        }
    }
}

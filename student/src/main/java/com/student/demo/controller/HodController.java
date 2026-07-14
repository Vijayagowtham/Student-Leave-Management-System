package com.student.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.demo.model.Hod;
import com.student.demo.repository.HodRepository;

@RestController
@RequestMapping("/hodform")
public class HodController {

    @Autowired
    private HodRepository hodRepo;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @PostMapping("/addhod")
    public ResponseEntity<String> addHod(@RequestBody Hod hod) {
        hod.setRole("hod"); // enforce role
        if (hod.getPassword() != null) {
            hod.setPassword(passwordEncoder.encode(hod.getPassword()));
        }
        hodRepo.save(hod);
        return ResponseEntity.ok("HOD added successfully");
    }

    @GetMapping("/viewhod")
    public List<Hod> getAllHods() {
        return hodRepo.findAll();
    }

    @PutMapping("/updatehod/{id}")
    public ResponseEntity<Hod> updateHod(@PathVariable Long id, @RequestBody Hod updatedHod) {
        Optional<Hod> existing = hodRepo.findById(id);
        if (existing.isPresent()) {
            Hod hod = existing.get();
            hod.setName(updatedHod.getName());
            hod.setUsername(updatedHod.getUsername());
            hod.setPassword(updatedHod.getPassword());
            hod.setDepartment(updatedHod.getDepartment());
            hodRepo.save(hod);
            return ResponseEntity.ok(hod);
        }
        return ResponseEntity.notFound().build();
    }
}

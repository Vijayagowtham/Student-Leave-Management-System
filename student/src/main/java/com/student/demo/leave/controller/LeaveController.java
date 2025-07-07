package com.student.demo.leave.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.student.demo.leave.model.LeaveRequest;
import com.student.demo.leave.service.LeaveRequestService;

@RestController
@RequestMapping("/leaveform")
@CrossOrigin(origins = "*")
public class LeaveController {

    @Autowired
    private LeaveRequestService service;

    // Insert Leave Request
    @PostMapping("/ins")
    public void insert(@RequestBody LeaveRequest request) {
        service.insertLeave(request);
    }

    // Update status (approve, reject, forward)
    @PutMapping("/upd/{id}")
    public void update(@PathVariable Long id, @RequestParam String action) {
        service.updateLeaveStatus(id, action);
    }

    // Get all leave requests
    @GetMapping("/get")
    public List<LeaveRequest> select() {
        return service.getAllLeaves();
    }

    // Delete leave request
    @DeleteMapping("/del/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteLeave(id);
    }
 // Get only approved requests
    @GetMapping("/approved")
    public List<LeaveRequest> getApproved() {
        return service.getByStatus("APPROVED_BY_TUTOR");
    }


    // Get only pending requests (for tutor)
    @GetMapping("/pending")
    public List<LeaveRequest> getPending() {
        return service.getByStatus("PENDING");
    }
}

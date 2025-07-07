package com.student.demo.leave.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.demo.leave.model.LeaveRequest;
import com.student.demo.leave.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository repo;

    public void insertLeave(LeaveRequest req) {
        repo.save(req);
    }

    public void updateLeaveStatus(Long id, String action) {
        Optional<LeaveRequest> optional = repo.findById(id);
        if (optional.isPresent()) {
            LeaveRequest request = optional.get();
            switch (action.toLowerCase()) {
                case "approve":
                    request.setStatus("APPROVED_BY_TUTOR");
                    break;
                case "forward":
                    request.setStatus("FORWARDED_TO_HOD");
                    break;
                case "reject":
                    request.setStatus("REJECTED");
                    break;
            }
            repo.save(request);
        }
    }

    public List<LeaveRequest> getAllLeaves() {
        return repo.findAll();
    }

    public String deleteLeave(Long id) {
        repo.deleteById(id);
        return "Deleted";
    }

    public List<LeaveRequest> getByStatus(String status) {
        return repo.findByStatus(status);
    }
}


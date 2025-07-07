package com.student.demo.leave.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.student.demo.leave.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatus(String status);
}


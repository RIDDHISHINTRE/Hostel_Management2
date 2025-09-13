package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentRoute{

    @Autowired
    private StudentService studentService;

    // ✅ Get profile
    @GetMapping("/{stuId}/profile")
    public ResponseEntity<Student> getProfile(@PathVariable Long stuId) {
        return ResponseEntity.ok(studentService.getProfile(stuId));
    }

    // ✅ Update profile (all fields required except feeStatus)
    @PutMapping("/{stuId}/profile")
    public ResponseEntity<Student> updateProfile(
            @PathVariable Long stuId,
            @RequestBody Student updatedStudent) {

        Student updated = studentService.updateProfile(stuId, updatedStudent);
        return ResponseEntity.ok(updated);
    }
}
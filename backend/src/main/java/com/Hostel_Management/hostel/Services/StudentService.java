package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.User;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepo userRepository;

    @Transactional
    public Student updateProfile(Long stuId, Student updatedData) {
        User user = userRepository.findById(stuId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + stuId));

        Student student = studentRepository.findById(stuId)
                .orElseGet(() -> {
                    Student s = new Student();
                    s.setStuId(user.getUserId());
                    s.setUser(user);
                    return s;
                });

        // update fields
        student.setContact(updatedData.getContact());
        student.setGuardianContact(updatedData.getGuardianContact());
        student.setDept(updatedData.getDept());
        student.setAddress(updatedData.getAddress());
        student.setYear(updatedData.getYear());
        student.setFeeStatus(false); // default for now

        return studentRepository.save(student); // Hibernate tracks correctly
    }

    public Student getProfile(Long stuId) {
        return studentRepository.findById(stuId)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + stuId));
    }
}

package com.abednego.smartIntern.service;

import com.abednego.smartIntern.model.Student;
import com.abednego.smartIntern.dto.StudentRegistrationDto;
import com.abednego.smartIntern.dto.StudentUpdateDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student save(StudentRegistrationDto dto);
    Optional<Student> findById(Long id);
    Optional<Student> findByEmail(String email);
    List<Student> findAll();
    Student update(Long id, StudentUpdateDto updatedStudent);
    void delete(Long id);
}

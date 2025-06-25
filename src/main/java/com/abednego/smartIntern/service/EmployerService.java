package com.abednego.smartIntern.service;

import com.abednego.smartIntern.model.Employer;
import com.abednego.smartIntern.dto.EmployerRegistrationDto;
import com.abednego.smartIntern.dto.EmployerUpdateDto;

import java.util.List;
import java.util.Optional;

public interface EmployerService {
    Employer save(EmployerRegistrationDto dto);
    Optional<Employer> findById(Long id);
    Optional<Employer> findByEmail(String email);
    List<Employer> findAll();
    Employer update(Long id, EmployerUpdateDto updatedEmployer);
    void delete(Long id);
}

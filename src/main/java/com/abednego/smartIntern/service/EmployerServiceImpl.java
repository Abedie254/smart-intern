package com.abednego.smartIntern.service.impl;

import com.abednego.smartIntern.dto.EmployerUpdateDto;
import com.abednego.smartIntern.dto.EmployerRegistrationDto;
import com.abednego.smartIntern.exception.ResourceNotFoundException;
import com.abednego.smartIntern.model.Employer;
import com.abednego.smartIntern.model.Role;
import com.abednego.smartIntern.repository.EmployerRepository;
import com.abednego.smartIntern.service.EmployerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;
    private final PasswordEncoder passwordEncoder; // inject encoder

    @Override
    public Employer save(EmployerRegistrationDto dto) {
        log.info("Saving new employer: {}", dto.getEmail());

        Employer employer = new Employer();
        employer.setFirstName(dto.getFirstName());
        employer.setLastName(dto.getLastName());
        employer.setEmail(dto.getEmail());
        employer.setPassword(passwordEncoder.encode(dto.getPassword())); // 🔐 hash
        employer.setPhone(dto.getPhone());
        employer.setCompanyName(dto.getCompanyName());
        employer.setCompanyWebsite(dto.getCompanyWebsite());
        employer.setContactPerson(dto.getContactPerson());
        employer.setIndustry(dto.getIndustry());
        employer.setLocation(dto.getLocation());
        employer.setRole(Role.EMPLOYER); // 👔 set role

        return employerRepository.save(employer);
    }

    @Override
    public Optional<Employer> findById(Long id) {
        log.info("Fetching employer by ID: {}", id);
        return employerRepository.findById(id);
    }

    @Override
    public Optional<Employer> findByEmail(String email) {
        log.info("Fetching employer by email: {}", email);
        return employerRepository.findByEmail(email);
    }

    @Override
    public List<Employer> findAll() {
        log.info("Fetching all employers");
        return employerRepository.findAll();
    }

    @Override
    public Employer update(Long id, EmployerUpdateDto updatedEmployer) {
        log.info("Updating employer with ID: {}", id);
        Employer existingEmployer = employerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found with ID: " + id));

        if(updatedEmployer.getFirstName() != null)
            existingEmployer.setFirstName(updatedEmployer.getFirstName());

        if(updatedEmployer.getLastName() != null)
            existingEmployer.setLastName(updatedEmployer.getLastName());

        if(existingEmployer.getPhone() != null)
            existingEmployer.setPhone(updatedEmployer.getPhone());

        if(existingEmployer.getCompanyName() != null)
            existingEmployer.setCompanyName(updatedEmployer.getCompanyName());

        if(existingEmployer.getEmail() != null)
            existingEmployer.setEmail(updatedEmployer.getEmail());

        if(existingEmployer.getContactPerson() != null)
            existingEmployer.setContactPerson(updatedEmployer.getContactPerson());

        if(existingEmployer.getLocation() != null)
            existingEmployer.setLocation(updatedEmployer.getLocation());

        return employerRepository.save(existingEmployer);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting employer with ID: {}", id);
        if (!employerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employer not found with ID: " + id);
        }
        employerRepository.deleteById(id);
    }
}

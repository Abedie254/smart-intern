package com.abednego.smartIntern.controller;

import com.abednego.smartIntern.dto.EmployerUpdateDto;
import com.abednego.smartIntern.dto.EmployerRegistrationDto;
import com.abednego.smartIntern.exception.ResourceNotFoundException;
import com.abednego.smartIntern.model.Employer;
import com.abednego.smartIntern.service.EmployerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    @PostMapping
    public ResponseEntity<Employer> createEmployer(@Valid @RequestBody EmployerRegistrationDto employerDto) {
        log.info("Creating employer: {}", employerDto.getEmail());
        Employer createdEmployer = employerService.save(employerDto);
        return ResponseEntity.ok(createdEmployer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        log.info("Fetching employer by ID: {}", id);
        return employerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found with ID: " + id));
    }

    @GetMapping("/email")
    public ResponseEntity<Employer> getEmployerByEmail(@RequestParam("value") String email) {
        log.info("Fetching employer by email: {}", email);
        return employerService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found with email: " + email));
    }

    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        log.info("Fetching all employers");
        return ResponseEntity.ok(employerService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable Long id, @Valid @RequestBody EmployerUpdateDto updatedEmployer) {
        log.info("Updating employer with ID: {}", id);
        Employer existingEmployer = employerService.update(id, updatedEmployer);
        return ResponseEntity.ok(existingEmployer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        log.info("Deleting employer with ID: {}", id);
        employerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

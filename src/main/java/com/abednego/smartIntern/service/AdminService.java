package com.abednego.smartIntern.service;

import com.abednego.smartIntern.dto.AdminRegistrationDto;
import com.abednego.smartIntern.model.Admin;
import com.abednego.smartIntern.dto.AdminUpdateDto;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    Admin save(AdminRegistrationDto dto);
    Optional<Admin> findById(Long id);
    Optional<Admin> findByEmail(String email);
    List<Admin> findAll();
    Admin update(Long id, AdminUpdateDto updatedAdmin);
    void delete(Long id);
}

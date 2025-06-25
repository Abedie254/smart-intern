package com.abednego.smartIntern.service;

import com.abednego.smartIntern.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    User update(Long id, User updatedUser);
    void delete(Long id);
}

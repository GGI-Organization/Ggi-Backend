package com.ggi.service.interfaces;

import com.ggi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Page<User> getAll(Pageable pageable);
    Optional<User> getById(Long id);
    User create(User user);
    User update(Long id, User user);
    ResponseEntity<?> delete(Long id);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}

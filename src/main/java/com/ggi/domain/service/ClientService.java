package com.ggi.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface ClientService extends UserDetailsService {
    Page<User> getAll(Pageable pageable);
    User getById(Long id);
    User create(User user);
    User update(Long id, User userRequest);
    ResponseEntity<?> delete(Long id);
}

package com.ggi.service.implement;

import com.ggi.model.User;
import com.ggi.repository.UserRepository;
import com.ggi.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existOtherWithEmail(Long id, String email) {
        var count = userRepository.findAll().stream().filter(user -> user.getEmail().equalsIgnoreCase(email) && !user.getId().equals(id)).count();
        return count > 0;
    }


    @Override
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(Long id, User user) {
        var getUser = userRepository.findById(id).get();
        if (!user.getPassword().isEmpty()) {
            getUser.setPassword(encoder.encode(user.getPassword()));
        }
        getUser.setFullname(user.getFullname());
        getUser.setEmail(user.getEmail());
        userRepository.save(getUser);
        return getUser;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }


    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }
}

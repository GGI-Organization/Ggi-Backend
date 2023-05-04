package com.ggi.controller;

import com.ggi.model.User;
import com.ggi.payload.request.DefaultPageable;
import com.ggi.payload.request.ProfileReq;
import com.ggi.payload.response.DefaultRes;
import com.ggi.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        var res = new DefaultRes<>();
        try {
            Pageable pageable = new DefaultPageable();
            var users = userService.getAll(pageable);
            res = new DefaultRes<>("", false);
            res.setResult(users);
            return ResponseEntity.ok().body(res);
        }catch(Exception e){
            res = new DefaultRes<>(e.getMessage(),true);
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PutMapping("/id")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> edit(@PathVariable(value = "id") Long id, @Valid @RequestBody ProfileReq profileReq) {
        var res = new DefaultRes<>();
        try {
            var user = new User("", profileReq.getEmail(), "");
            var userSave = userService.update(id, user);
            res = new DefaultRes<>("", false);
            res.setResult(userSave);
            return ResponseEntity.ok().body(res);
        }catch(Exception e){
            res = new DefaultRes<>(e.getMessage(),true);
            return ResponseEntity.badRequest().body(res);
        }
    }

    @GetMapping("/id")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        var res = new DefaultRes<>();
        try {
            var user = userService.getById(id);
            if (user.isEmpty()){
                res = new DefaultRes<>("Usuario no existe", true);
                return ResponseEntity.status(400).body(res);
            }
            res = new DefaultRes<>("", false);
            res.setResult(user);
            return ResponseEntity.ok().body(res);
        }catch(Exception e){
            res = new DefaultRes<>(e.getMessage(),true);
            return ResponseEntity.badRequest().body(res);
        }
    }

}

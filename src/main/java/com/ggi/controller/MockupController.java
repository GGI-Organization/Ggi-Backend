package com.ggi.controller;

import com.ggi.payload.request.DefaultPageable;
import com.ggi.payload.request.DiagramReq;
import com.ggi.payload.request.MockupReq;
import com.ggi.payload.response.DefaultRes;
import com.ggi.repository.UserRepository;
import com.ggi.security.jwt.JwtUtils;
import com.ggi.service.interfaces.DiagramBPMNService;
import com.ggi.service.interfaces.MockupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mockups")
public class MockupController {
    @Autowired
    private MockupService mockupService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token) {
        var res = new DefaultRes<>();
        try {
            var username = jwtUtils.getUserNameFromJwtToken(token.split(" ")[1]);
            var user = userRepository.findByUsername(username);
            Pageable pageable = new DefaultPageable();
            var diagramsBPMN = mockupService.getAll(pageable, user.get().getId());
            res = new DefaultRes<>("", false);
            res.setResult(diagramsBPMN);
            return ResponseEntity.ok().body(res);
        }catch(Exception e){
            res = new DefaultRes<>(e.getMessage(),true);
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> add(Authentication authentication, @Valid @RequestBody MockupReq mockupReq) {
        var res = new DefaultRes<>();
        try {
            var newMockups = mockupService.create(mockupReq);
            res = new DefaultRes<>("Mockups agregado correctamente", false);
            res.setResult(newMockups);
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
            var mockup = mockupService.getById(id);
            if (mockup.isEmpty()){
                res = new DefaultRes<>("Mockup no existe", true);
                return ResponseEntity.status(400).body(res);
            }
            res = new DefaultRes<>("", false);
            res.setResult(mockup);
            return ResponseEntity.ok().body(res);
        }catch(Exception e){
            res = new DefaultRes<>(e.getMessage(),true);
            return ResponseEntity.badRequest().body(res);
        }
    }

    @DeleteMapping("/id")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        var res = new DefaultRes<>();
        try {
            var mockup = mockupService.getById(id);
            if (mockup.isEmpty()){
                res = new DefaultRes<>("Mockup no existe", true);
                return ResponseEntity.status(400).body(res);
            }
            mockupService.delete(id);
            res = new DefaultRes<>("Mockup eliminado correctamente", false);
            res.setResult(mockup);
            return ResponseEntity.ok().body(res);
        }catch(Exception e){
            res = new DefaultRes<>(e.getMessage(),true);
            return ResponseEntity.badRequest().body(res);
        }
    }
}

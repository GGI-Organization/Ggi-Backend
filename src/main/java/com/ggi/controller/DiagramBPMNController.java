package com.ggi.controller;

import com.ggi.model.User;
import com.ggi.payload.request.DefaultPageable;
import com.ggi.payload.request.DiagramReq;
import com.ggi.payload.request.ProfileReq;
import com.ggi.payload.response.DefaultRes;
import com.ggi.repository.UserRepository;
import com.ggi.security.jwt.JwtUtils;
import com.ggi.service.interfaces.DiagramBPMNService;
import com.ggi.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/diagrams-bpmn")
public class DiagramBPMNController {

    @Autowired
    private DiagramBPMNService diagramBPMNService;
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
            var diagramsBPMN = diagramBPMNService.getAll(pageable, user.get().getId());
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
    public ResponseEntity<?> add(Authentication authentication,@Valid @RequestBody DiagramReq diagramReq) {
        var res = new DefaultRes<>();
        try {
            var newDiagramBPMN = diagramBPMNService.create(diagramReq);
            res = new DefaultRes<>("Diagrama agregado correctamente", false);
            res.setResult(newDiagramBPMN);
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
            var diagramBPMN = diagramBPMNService.getById(id);
            if (diagramBPMN.isEmpty()){
                res = new DefaultRes<>("Diagrama no existe", true);
                return ResponseEntity.status(400).body(res);
            }
            res = new DefaultRes<>("", false);
            res.setResult(diagramBPMN);
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
            var diagramBPMN = diagramBPMNService.getById(id);
            if (diagramBPMN.isEmpty()){
                res = new DefaultRes<>("Diagrama no existe", true);
                return ResponseEntity.status(400).body(res);
            }
            diagramBPMNService.delete(id);
            res = new DefaultRes<>("Diagrama eliminado correctamente", false);
            res.setResult(diagramBPMN);
            return ResponseEntity.ok().body(res);
        }catch(Exception e){
            res = new DefaultRes<>(e.getMessage(),true);
            return ResponseEntity.badRequest().body(res);
        }
    }
}

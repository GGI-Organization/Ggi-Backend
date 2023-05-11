package com.ggi.controller;

import com.ggi.model.Notification;
import com.ggi.payload.request.DefaultPageable;
import com.ggi.payload.request.DiagramReq;
import com.ggi.payload.request.NotificationReq;
import com.ggi.payload.response.DefaultRes;
import com.ggi.repository.UserRepository;
import com.ggi.security.jwt.JwtUtils;
import com.ggi.service.interfaces.DiagramBPMNService;
import com.ggi.service.interfaces.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token) {
        var res = new DefaultRes<>();
        try {
            Pageable pageable = new DefaultPageable();
            var notifications = notificationService.getAll(pageable);
            res = new DefaultRes<>("", false);
            res.setResult(notifications);
            return ResponseEntity.status(200).body(res);
        } catch (HttpMessageNotWritableException e) {
            System.out.println("error bpmn " + e.getMessage());
            res = new DefaultRes<>(e.getMessage(), true);
            return ResponseEntity.status(500).body(res);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> add(Authentication authentication, @Valid @RequestBody NotificationReq notificationReq) {
        var res = new DefaultRes<>();
        try {
            var newNotification = notificationService.create(new Notification(notificationReq.getDescription()));
            res = new DefaultRes<>("Notificación agregado correctamente", false);
            res.setResult(newNotification);
            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            res = new DefaultRes<>(e.getMessage(), true);
            return ResponseEntity.badRequest().body(res);
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        var res = new DefaultRes<>();
        try {
            var notification = notificationService.getById(id);
            if (notification.isEmpty()) {
                res = new DefaultRes<>("Notificación no existe", true);
                return ResponseEntity.status(400).body(res);
            }
            res = new DefaultRes<>("", false);
            res.setResult(notification);
            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            res = new DefaultRes<>(e.getMessage(), true);
            return ResponseEntity.badRequest().body(res);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        var res = new DefaultRes<>();
        try {
            var notification = notificationService.getById(id);
            if (notification.isEmpty()) {
                res = new DefaultRes<>("Notificación no existe", true);
                return ResponseEntity.status(400).body(res);
            }
            notificationService.delete(id);
            res = new DefaultRes<>("Notificación eliminado correctamente", false);
            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            res = new DefaultRes<>(e.getMessage(), true);
            return ResponseEntity.badRequest().body(res);
        }
    }
}
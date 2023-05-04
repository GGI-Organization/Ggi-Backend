package com.ggi.service.interfaces;

import com.ggi.model.DiagramBPMN;
import com.ggi.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface NotificationService {
    Page<Notification> getAll(Pageable pageable);

    Optional<Notification> getById(Long id);

    Notification create(Notification notification);

    //Notification update(Long id, Notification notification);
    ResponseEntity<?> delete(Long id);
}

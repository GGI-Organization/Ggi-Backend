package com.ggi.service.implement;

import com.ggi.model.Notification;
import com.ggi.repository.NotificationRepository;
import com.ggi.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Page<Notification> getAll(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    @Override
    public Optional<Notification> getById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification create(Notification notification) {
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        notificationRepository.deleteById(id);
        return null;
    }
}

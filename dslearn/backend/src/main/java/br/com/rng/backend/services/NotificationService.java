package br.com.rng.backend.services;

import br.com.rng.backend.dtos.NotificationDTO;
import br.com.rng.backend.entities.Notification;
import br.com.rng.backend.entities.User;
import br.com.rng.backend.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public Page<NotificationDTO> notificationsForCurrentUser(Pageable pageable) {
        User user = authService.authenticated();

        Page<Notification> page = notificationRepository.findByUser(user, pageable);

        return page.map(notification -> new NotificationDTO(notification));
    }
}
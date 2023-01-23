package br.com.rng.backend.resources;

import br.com.rng.backend.dtos.NotificationDTO;
import br.com.rng.backend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationResources {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Page<NotificationDTO>> notificationsForCurrentUser(Pageable pageable) {

        Page<NotificationDTO> page = notificationService.notificationsForCurrentUser(pageable);

        return ResponseEntity.ok(page);
    }
}
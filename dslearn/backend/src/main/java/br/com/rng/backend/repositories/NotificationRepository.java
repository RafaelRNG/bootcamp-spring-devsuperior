package br.com.rng.backend.repositories;

import br.com.rng.backend.entities.Lesson;
import br.com.rng.backend.entities.Notification;
import br.com.rng.backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findByUser(User user, Pageable pageable);
}
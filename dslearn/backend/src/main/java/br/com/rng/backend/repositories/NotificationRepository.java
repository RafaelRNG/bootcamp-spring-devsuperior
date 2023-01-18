package br.com.rng.backend.repositories;

import br.com.rng.backend.entities.Lesson;
import br.com.rng.backend.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> { }
package br.com.rng.backend.repositories;

import br.com.rng.backend.entities.Notification;
import br.com.rng.backend.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> { }
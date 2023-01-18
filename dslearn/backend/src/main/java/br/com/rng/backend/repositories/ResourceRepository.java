package br.com.rng.backend.repositories;

import br.com.rng.backend.entities.Notification;
import br.com.rng.backend.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> { }
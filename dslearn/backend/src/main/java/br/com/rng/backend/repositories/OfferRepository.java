package br.com.rng.backend.repositories;

import br.com.rng.backend.entities.Notification;
import br.com.rng.backend.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> { }
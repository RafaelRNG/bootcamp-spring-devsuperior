package br.com.rng.backend.repositories;

import br.com.rng.backend.entities.Notification;
import br.com.rng.backend.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> { }
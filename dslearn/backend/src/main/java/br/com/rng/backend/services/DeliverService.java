package br.com.rng.backend.services;

import br.com.rng.backend.dtos.DeliverRevisionDTO;
import br.com.rng.backend.entities.Deliver;
import br.com.rng.backend.repositories.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliverService {

    @Autowired
    private DeliverRepository deliverRepository;

    @Transactional(readOnly = false)
    public void saveRevision(Long id, DeliverRevisionDTO dto) {
        Deliver deliver = deliverRepository.getReferenceById(id);
        deliver.setStatus(dto.getStatus());
        deliver.setFeedback(dto.getFeedback());
        deliver.setCorrectCount(dto.getCorrectCount());

        deliverRepository.save(deliver);
    }
}
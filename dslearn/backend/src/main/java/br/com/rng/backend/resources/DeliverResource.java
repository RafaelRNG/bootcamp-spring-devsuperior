package br.com.rng.backend.resources;

import br.com.rng.backend.dtos.DeliverRevisionDTO;
import br.com.rng.backend.services.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
public class DeliverResource {

    @Autowired
    private DeliverService deliverService;

    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> saveRevision(@PathVariable Long id, @RequestBody DeliverRevisionDTO dto) {
       deliverService.saveRevision(id, dto);

       return ResponseEntity.noContent().build();
    }
}
package br.com.rng.client.resources;

import br.com.rng.client.dtos.ClientDTO;
import br.com.rng.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                   @RequestParam(name = "orderBy", defaultValue = "name") String orderBy,
                                                   @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<ClientDTO> clientDTOPage = clientService.findAll(pageRequest);

        return ResponseEntity.ok(clientDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.findById(id);

        return ResponseEntity.ok(clientDTO);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO) {
        ClientDTO saveClientDTO = clientService.save(clientDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(saveClientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        ClientDTO cliDTO = clientService.update(id, clientDTO);

        return ResponseEntity.ok(cliDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clientService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
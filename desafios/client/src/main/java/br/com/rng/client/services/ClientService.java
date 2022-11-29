package br.com.rng.client.services;

import br.com.rng.client.dtos.ClientDTO;
import br.com.rng.client.entities.Client;
import br.com.rng.client.repositories.ClientRepository;
import br.com.rng.client.services.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);

        return clients.map(client -> new ClientDTO(client));
    }

    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Unable to search for element"));

        return new ClientDTO(client);
    }
}
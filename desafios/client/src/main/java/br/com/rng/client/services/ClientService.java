package br.com.rng.client.services;

import br.com.rng.client.dtos.ClientDTO;
import br.com.rng.client.entities.Client;
import br.com.rng.client.repositories.ClientRepository;
import br.com.rng.client.services.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);

        return clients.map(client -> new ClientDTO(client));
    }

    @Transactional
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Unable to search for element"));

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO save(ClientDTO clientDTO) {
        Client client  = new Client();
        copyClient(clientDTO, client);
        client = clientRepository.save(client);

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        try {
            Client client = clientRepository.getReferenceById(id);
            copyClient(clientDTO, client);
            client = clientRepository.save(client);

            return new ClientDTO(client);
        } catch(EntityNotFoundException e) {
            throw new ObjectNotFound("Unable to search for element");
        }
    }

    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ObjectNotFound("Object not found!");
        }
    }

    private void copyClient(ClientDTO clientDTO, Client client) {

        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
    }
}
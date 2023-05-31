package com.example.vehiclesrent.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.vehiclesrent.model.Client;
import com.example.vehiclesrent.repository.ClientRepository;
import org.springframework.stereotype.Service;

// TODO A classe service deve ser anotada com @Service para o Spring gerenciar e conseguir injetar ela em outras classes.
@Service
public class ClientService implements ICrudService<Client>{

    // TODO Recomendo nomear a variável como clientRepository por uma questão de semântica.
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void delete(UUID uuid) {
        Optional<Client> record = clientRepository.findById(uuid);

        // TODO A classe Optinal oferece o método ifPresent que recebe um Consumer como parâmetro,
        //  então tu pode chamar direto o método delete do clientRepository.
        record.ifPresent(clientRepository::delete);
    }

    @Override
    public List<Client> getAll() {
        // TODO Como não tem lógica no getAll, pode retornar direto o resultado do findAll.
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getById(UUID uuid) {
        return clientRepository.findById(uuid);
    }

    @Override
    public Client save(Client object) {
        return clientRepository.save(object);
    }

    
}

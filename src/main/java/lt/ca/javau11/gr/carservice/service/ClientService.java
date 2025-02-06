package lt.ca.javau11.gr.carservice.service;

import lt.ca.javau11.gr.carservice.dto.ClientDto;
import lt.ca.javau11.gr.carservice.entity.ClientEntity;
import lt.ca.javau11.gr.carservice.repository.ClientRepository;
import lt.ca.javau11.gr.carservice.repository.UserRepository;
import lt.ca.javau11.gr.carservice.util.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);


    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDto createClient(ClientDto cDto){

        ClientEntity clientEntityBeforeSave = clientMapper.toClientEntity(cDto);
        ClientEntity clientEntityAfterSave = clientRepository.save(clientEntityBeforeSave);

        return clientMapper.toClientDto(clientEntityAfterSave);

    }

    public List<ClientDto> getAllClients(){

        List<ClientEntity> clients = clientRepository.findAll();

        return clients.stream()
                .map(clientMapper::toClientDto)
                .toList();

    }

    public Optional<ClientDto> getClientById (Long id){
        Optional<ClientEntity> client = clientRepository.findById(id);

        return client.map(clientMapper::toClientDto);

    }

    public Optional<ClientDto> updateClient(Long id, ClientDto cDto) {

        if (clientRepository.existsById(id)) {
            ClientEntity clientEntityBeforeSave = clientMapper.toClientEntity(cDto);
            clientEntityBeforeSave.setId(id);

            ClientEntity clientEntityAfterSave = clientRepository.save(clientEntityBeforeSave);
            return Optional.of(clientMapper.toClientDto(clientEntityAfterSave));

        } else {
            return Optional.empty();
        }
    }

    public void deleteClient(Long id) {

        clientRepository.deleteById(id);

    }
}



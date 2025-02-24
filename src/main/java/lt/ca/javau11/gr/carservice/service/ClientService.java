package lt.ca.javau11.gr.carservice.service;

import lt.ca.javau11.gr.carservice.dto.ClientDto;
import lt.ca.javau11.gr.carservice.entity.ClientEntity;
import lt.ca.javau11.gr.carservice.entity.UserEntity;
import lt.ca.javau11.gr.carservice.exception.ResourceNotFoundException;
import lt.ca.javau11.gr.carservice.repository.ClientRepository;
import lt.ca.javau11.gr.carservice.repository.UserRepository;
import lt.ca.javau11.gr.carservice.util.ClientMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, UserRepository userRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toClientDto)
                .toList();
    }

    public List<ClientDto> getClientsByUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        
        return clientRepository.findByUser(user).stream()
                .map(clientMapper::toClientDto)
                .toList();
    }

    public Optional<ClientDto> getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toClientDto);
    }

    public ClientDto createClient(ClientDto clientDto) {
        UserEntity user = userRepository.findById(clientDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + clientDto.getUserId()));

        // Check if email or phone already exists
        if (clientRepository.existsByEmail(clientDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (clientRepository.existsByPhoneNumber(clientDto.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        ClientEntity client = clientMapper.toClientEntity(clientDto, user);
        return clientMapper.toClientDto(clientRepository.save(client));
    }

    public Optional<ClientDto> updateClient(Long id, ClientDto clientDto) {
        if (!clientRepository.existsById(id)) {
            return Optional.empty();
        }

        UserEntity user = userRepository.findById(clientDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + clientDto.getUserId()));

        // Check if email or phone already exists for other clients
        Optional<ClientEntity> existingByEmail = clientRepository.findByEmail(clientDto.getEmail());
        if (existingByEmail.isPresent() && !existingByEmail.get().getId().equals(id)) {
            throw new IllegalArgumentException("Email already exists");
        }

        Optional<ClientEntity> existingByPhone = clientRepository.findByPhoneNumber(clientDto.getPhoneNumber());
        if (existingByPhone.isPresent() && !existingByPhone.get().getId().equals(id)) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        ClientEntity client = clientMapper.toClientEntity(clientDto, user);
        client.setId(id);
        return Optional.of(clientMapper.toClientDto(clientRepository.save(client)));
    }

    public void deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        }
    }
}

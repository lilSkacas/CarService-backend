package lt.ca.javau11.gr.carservice.service;

import lt.ca.javau11.gr.carservice.dto.VehicleDto;
import lt.ca.javau11.gr.carservice.entity.ClientEntity;
import lt.ca.javau11.gr.carservice.entity.VehicleEntity;
import lt.ca.javau11.gr.carservice.exception.ResourceNotFoundException;
import lt.ca.javau11.gr.carservice.repository.ClientRepository;
import lt.ca.javau11.gr.carservice.repository.VehicleRepository;
import lt.ca.javau11.gr.carservice.util.VehicleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleService(VehicleRepository vehicleRepository, 
                         ClientRepository clientRepository,
                         VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
        this.vehicleMapper = vehicleMapper;
    }

    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicleMapper::toVehicleDto)
                .toList();
    }

    public List<VehicleDto> getVehiclesByClient(Long clientId) {
        ClientEntity client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        
        return vehicleRepository.findByClient(client).stream()
                .map(vehicleMapper::toVehicleDto)
                .toList();
    }

    public Optional<VehicleDto> getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .map(vehicleMapper::toVehicleDto);
    }

    public Optional<VehicleDto> getVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate)
                .map(vehicleMapper::toVehicleDto);
    }

    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        ClientEntity client = clientRepository.findById(vehicleDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + vehicleDto.getClientId()));

        // Check if license plate already exists
        if (vehicleRepository.existsByLicensePlate(vehicleDto.getLicensePlate())) {
            throw new IllegalArgumentException("License plate already exists");
        }

        VehicleEntity vehicle = vehicleMapper.toVehicleEntity(vehicleDto, client);
        return vehicleMapper.toVehicleDto(vehicleRepository.save(vehicle));
    }

    public Optional<VehicleDto> updateVehicle(Long id, VehicleDto vehicleDto) {
        if (!vehicleRepository.existsById(id)) {
            return Optional.empty();
        }

        ClientEntity client = clientRepository.findById(vehicleDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + vehicleDto.getClientId()));

        // Check if license plate already exists for other vehicles
        Optional<VehicleEntity> existingByPlate = vehicleRepository.findByLicensePlate(vehicleDto.getLicensePlate());
        if (existingByPlate.isPresent() && !existingByPlate.get().getId().equals(id)) {
            throw new IllegalArgumentException("License plate already exists");
        }

        VehicleEntity vehicle = vehicleMapper.toVehicleEntity(vehicleDto, client);
        vehicle.setId(id);
        return Optional.of(vehicleMapper.toVehicleDto(vehicleRepository.save(vehicle)));
    }

    public void deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
        }
    }
}

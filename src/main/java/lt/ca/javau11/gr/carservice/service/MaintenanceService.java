package lt.ca.javau11.gr.carservice.service;

import lt.ca.javau11.gr.carservice.dto.MaintenanceDto;
import lt.ca.javau11.gr.carservice.entity.MaintenanceEntity;
import lt.ca.javau11.gr.carservice.entity.VehicleEntity;
import lt.ca.javau11.gr.carservice.exception.ResourceNotFoundException;
import lt.ca.javau11.gr.carservice.repository.MaintenanceRepository;
import lt.ca.javau11.gr.carservice.repository.VehicleRepository;
import lt.ca.javau11.gr.carservice.util.MaintenanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;
    private final MaintenanceMapper maintenanceMapper;
    private static final Logger logger = LoggerFactory.getLogger(MaintenanceService.class);

    public MaintenanceService(MaintenanceRepository maintenanceRepository,
                              VehicleRepository vehicleRepository,
                              MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleRepository = vehicleRepository;
        this.maintenanceMapper = maintenanceMapper;
    }

    public List<MaintenanceDto> getAllMaintenance() {
        List<MaintenanceEntity> maintenance = maintenanceRepository.findAll();

        return maintenance.stream()
                .map(maintenanceMapper::toMaintenanceDto)
                .toList();
    }

    public List<MaintenanceDto> getMaintenanceByVehicle(Long vehicleId) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + vehicleId));

        return maintenanceRepository.findByVehicle(vehicle).stream()
                .map(maintenanceMapper::toMaintenanceDto)
                .toList();
    }

    public List<MaintenanceDto> getMaintenanceByVehicleAndDateRange(Long vehicleId, LocalDate startDate, LocalDate endDate) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + vehicleId));

        return maintenanceRepository.findByVehicleAndDateBetween(vehicle, startDate, endDate).stream()
                .map(maintenanceMapper::toMaintenanceDto)
                .toList();
    }

    public Optional<MaintenanceDto> getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id)
                .map(maintenanceMapper::toMaintenanceDto);
    }

    public MaintenanceDto createMaintenance(MaintenanceDto maintenanceDto) {
        VehicleEntity vehicle = vehicleRepository.findById(maintenanceDto.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + maintenanceDto.getVehicleId()));

        MaintenanceEntity maintenance = maintenanceMapper.toMaintenanceEntity(maintenanceDto, vehicle);
        return maintenanceMapper.toMaintenanceDto(maintenanceRepository.save(maintenance));
    }

    public Optional<MaintenanceDto> updateMaintenance(Long id, MaintenanceDto maintenanceDto) {
        if (!maintenanceRepository.existsById(id)) {
            return Optional.empty();
        }

        VehicleEntity vehicle = vehicleRepository.findById(maintenanceDto.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + maintenanceDto.getVehicleId()));

        MaintenanceEntity maintenance = maintenanceMapper.toMaintenanceEntity(maintenanceDto, vehicle);
        maintenance.setId(id);
        return Optional.of(maintenanceMapper.toMaintenanceDto(maintenanceRepository.save(maintenance)));
    }

    public void deleteMaintenance(Long id) {
        if (maintenanceRepository.existsById(id)) {
            maintenanceRepository.deleteById(id);
        }
    }
}

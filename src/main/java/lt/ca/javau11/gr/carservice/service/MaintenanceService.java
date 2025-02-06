package lt.ca.javau11.gr.carservice.service;

import lt.ca.javau11.gr.carservice.dto.MaintenanceDto;
import lt.ca.javau11.gr.carservice.entity.MaintenanceEntity;
import lt.ca.javau11.gr.carservice.repository.MaintenanceRepository;
import lt.ca.javau11.gr.carservice.util.MaintenanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;
    private static final Logger logger = LoggerFactory.getLogger(MaintenanceService.class);


    public MaintenanceService(MaintenanceRepository serviceRepository, MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = serviceRepository;
        this.maintenanceMapper = maintenanceMapper;
    }

    public MaintenanceDto createMaintenance(MaintenanceDto mDto) {

        MaintenanceEntity maintenanceEntityBeforeSave = maintenanceMapper.toMaintenanceEntity(mDto);

        MaintenanceEntity maintenanceEntityAfterSave = maintenanceRepository.save(maintenanceEntityBeforeSave);

        return maintenanceMapper.toMaintenanceDto(maintenanceEntityAfterSave);

    }

    public List<MaintenanceDto> getAllMaintenance() {
        List<MaintenanceEntity> maintenance = maintenanceRepository.findAll();

        return maintenance.stream()
                .map(maintenanceMapper::toMaintenanceDto)
                .toList();
    }

    public Optional<MaintenanceDto> getMaintenanceById(Long id) {
        Optional<MaintenanceEntity> maintenance = maintenanceRepository.findById(id);

        return maintenance.map(maintenanceMapper::toMaintenanceDto);

    }

    public Optional<MaintenanceDto> updateMaintenance (Long id, MaintenanceDto mDto){

        if (maintenanceRepository.existsById(id)) {
                MaintenanceEntity maintenanceEntityBeforeSave = maintenanceMapper.toMaintenanceEntity(mDto);
                maintenanceEntityBeforeSave.setId(id);


                MaintenanceEntity maintenanceEntityAfterSave = maintenanceRepository.save(maintenanceEntityBeforeSave);
                return Optional.of(maintenanceMapper.toMaintenanceDto(maintenanceEntityAfterSave));

         } else {
                return Optional.empty();
            }
        }

        public void deleteMaintenance (Long id){

            maintenanceRepository.deleteById(id);

        }
    }



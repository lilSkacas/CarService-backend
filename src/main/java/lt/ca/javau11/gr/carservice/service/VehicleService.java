package lt.ca.javau11.gr.carservice.service;

import lt.ca.javau11.gr.carservice.dto.VehicleDto;
import lt.ca.javau11.gr.carservice.entity.VehicleEntity;
import lt.ca.javau11.gr.carservice.repository.VehicleRepository;
import lt.ca.javau11.gr.carservice.util.VehicleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);


    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }



    public VehicleDto createVehicle(VehicleDto vehicleDto) {

        VehicleEntity vehicleEntityBeforeSave = vehicleMapper.toVehicleEntity(vehicleDto);
        VehicleEntity vehicleEntityAfterSave = vehicleRepository.save(vehicleEntityBeforeSave);

        return vehicleMapper.toVehicleDto(vehicleEntityAfterSave);
    }

    public List<VehicleDto> getAllVehicles(){
        List<VehicleEntity> vehicle = vehicleRepository.findAll();

        return vehicle.stream()
                .map(vehicleMapper::toVehicleDto)
                .toList();
    }

    public Optional<VehicleDto> getVehicleById(Long id) {
        Optional<VehicleEntity> vehicle = vehicleRepository.findById(id);

        return vehicle.map(vehicleMapper::toVehicleDto);
    }

    public Optional<VehicleDto> updateVehicle(Long id, VehicleDto vehicleDto ){

        if( vehicleRepository.existsById(id) ) {
            VehicleEntity vehicleEntityBeforeSave = vehicleMapper.toVehicleEntity(vehicleDto);
            vehicleEntityBeforeSave.setId(id);

            VehicleEntity vehicleEntityAfterSave = vehicleRepository.save(vehicleEntityBeforeSave);
            return Optional.of( vehicleMapper.toVehicleDto(vehicleEntityAfterSave));

        } else {
            return Optional.empty();
        }

    }

    public void deleteVehicle(Long id) {

        vehicleRepository.deleteById(id);

    }


}

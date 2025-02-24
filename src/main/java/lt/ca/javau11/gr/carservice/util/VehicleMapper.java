package lt.ca.javau11.gr.carservice.util;

import lt.ca.javau11.gr.carservice.dto.VehicleDto;
import lt.ca.javau11.gr.carservice.entity.ClientEntity;
import lt.ca.javau11.gr.carservice.entity.VehicleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMapper {
    
    public VehicleEntity toVehicleEntity(VehicleDto dto, ClientEntity client) {
        VehicleEntity entity = new VehicleEntity();
        entity.setId(dto.getId());
        entity.setLicensePlate(dto.getLicensePlate());
        entity.setMake(dto.getMake());
        entity.setModel(dto.getModel());
        entity.setYear(dto.getYear());
        entity.setEngine(dto.getEngine());
        entity.setFuel(dto.getFuel());
        entity.setTransmission(dto.getTransmission());
        entity.setWheelDrive(dto.getWheelDrive());
        entity.setBody(dto.getBody());
        entity.setClient(client);
        return entity;
    }

    public VehicleDto toVehicleDto(VehicleEntity entity) {
        VehicleDto dto = new VehicleDto();
        dto.setId(entity.getId());
        dto.setLicensePlate(entity.getLicensePlate());
        dto.setMake(entity.getMake());
        dto.setModel(entity.getModel());
        dto.setYear(entity.getYear());
        dto.setEngine(entity.getEngine());
        dto.setFuel(entity.getFuel());
        dto.setTransmission(entity.getTransmission());
        dto.setWheelDrive(entity.getWheelDrive());
        dto.setBody(entity.getBody());
        if (entity.getClient() != null) {
            dto.setClientId(entity.getClient().getId());
        }
        return dto;
    }
}

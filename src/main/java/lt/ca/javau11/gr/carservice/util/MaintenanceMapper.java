package lt.ca.javau11.gr.carservice.util;

import lt.ca.javau11.gr.carservice.dto.MaintenanceDto;
import lt.ca.javau11.gr.carservice.entity.MaintenanceEntity;
import lt.ca.javau11.gr.carservice.entity.VehicleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceMapper {

    public MaintenanceEntity toMaintenanceEntity(MaintenanceDto dto, VehicleEntity vehicle) {
        MaintenanceEntity entity = new MaintenanceEntity();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setDate(dto.getDate());
        entity.setVehicle(vehicle);
        return entity;
    }

    public MaintenanceDto toMaintenanceDto(MaintenanceEntity entity) {
        MaintenanceDto dto = new MaintenanceDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setDate(entity.getDate());
        if (entity.getVehicle() != null) {
            dto.setVehicleId(entity.getVehicle().getId());
        }
        return dto;
    }
}

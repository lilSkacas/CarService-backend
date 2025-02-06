package lt.ca.javau11.gr.carservice.util;

import lt.ca.javau11.gr.carservice.dto.MaintenanceDto;
import lt.ca.javau11.gr.carservice.entity.MaintenanceEntity;
import org.springframework.stereotype.Component;


@Component
public class MaintenanceMapper {

    public MaintenanceEntity toMaintenanceEntity(MaintenanceDto mDto) {

        return new MaintenanceEntity(
                mDto.getId(),
                mDto.getDate(),
                mDto.getDescription(),
                mDto.getPrice());

    }

    public MaintenanceDto toMaintenanceDto(MaintenanceEntity mEntity) {

        return new MaintenanceDto(
                mEntity.getId(),
                mEntity.getDate(),
                mEntity.getDescription(),
                mEntity.getPrice());

    }
}


package lt.ca.javau11.gr.carservice.util;

import lt.ca.javau11.gr.carservice.dto.VehicleDto;
import lt.ca.javau11.gr.carservice.entity.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleEntity toVehicleEntity(VehicleDto vDto) {

        return new VehicleEntity(vDto.getId(), vDto.getLicensePlate(),
                vDto.getMake(),
                vDto.getModel(),
                vDto.getYear(),
                vDto.getEngine(),
                vDto.getFuel(),
                vDto.getTransmission(),
                vDto.getWheelDrive(),
                vDto.getBody());



    }

    public VehicleDto toVehicleDto(VehicleEntity vEntity) {
        return new VehicleDto(
                vEntity.getId(),
                vEntity.getLicensePlate(),
                vEntity.getMake(),
                vEntity.getModel(),
                vEntity.getYear(),
                vEntity.getEngine(),
                vEntity.getFuel(),
                vEntity.getTransmission(),
                vEntity.getWheelDrive(),
                vEntity.getBody());

    }
}

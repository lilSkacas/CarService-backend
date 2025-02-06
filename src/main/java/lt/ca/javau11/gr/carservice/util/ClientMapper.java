package lt.ca.javau11.gr.carservice.util;

import lt.ca.javau11.gr.carservice.dto.ClientDto;
import lt.ca.javau11.gr.carservice.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientEntity toClientEntity(ClientDto cDto){

        return new ClientEntity(cDto.getId(),
                                cDto.getFirstName(),
                                cDto.getLastName(),
                                cDto.getEmail(),
                                cDto.getPhoneNumber());

    }

    public ClientDto toClientDto(ClientEntity cEntity){

        return new ClientDto(cEntity.getId(),
                                cEntity.getFirstName(),
                                cEntity.getLastName(),
                                cEntity.getEmail(),
                                cEntity.getPhoneNumber());

    }
}

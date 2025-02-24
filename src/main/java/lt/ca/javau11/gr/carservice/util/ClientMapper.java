package lt.ca.javau11.gr.carservice.util;

import lt.ca.javau11.gr.carservice.dto.ClientDto;
import lt.ca.javau11.gr.carservice.entity.ClientEntity;
import lt.ca.javau11.gr.carservice.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class ClientMapper {

    public ClientEntity toClientEntity(ClientDto dto, UserEntity user) {
        ClientEntity entity = new ClientEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setUser(user);
        return entity;
    }

    public ClientDto toClientDto(ClientEntity entity) {
        ClientDto dto = new ClientDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        return dto;
    }
}

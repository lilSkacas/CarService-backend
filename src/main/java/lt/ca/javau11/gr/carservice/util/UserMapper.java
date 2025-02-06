package lt.ca.javau11.gr.carservice.util;

import lt.ca.javau11.gr.carservice.dto.UserDto;
import lt.ca.javau11.gr.carservice.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toUserEntity(UserDto dto) {

        return new UserEntity(dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getPhoneNumber());

    }

    public UserDto toUserDto(UserEntity entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getPassword(),
                entity.getRoles());

    }

}

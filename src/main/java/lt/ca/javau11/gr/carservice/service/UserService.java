package lt.ca.javau11.gr.carservice.service;

import lt.ca.javau11.gr.carservice.dto.UserDto;
import lt.ca.javau11.gr.carservice.entity.UserEntity;
import lt.ca.javau11.gr.carservice.repository.UserRepository;
import lt.ca.javau11.gr.carservice.util.UserMapper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService  {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<UserDto> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDto);
    }

    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntityBeforeSave = userMapper.toUserEntity(userDto);
        UserEntity userEntityAfterSave = userRepository.save(userEntityBeforeSave);
        return userMapper.toUserDto(userEntityAfterSave);
    }

    public List<UserDto> getAllUsers(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public Optional<UserDto> getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(userMapper::toUserDto);
    }

    public Optional<UserDto> updateUser(Long id, UserDto userDto ){
        if(userRepository.existsById(id)) {
            UserEntity userEntity = userMapper.toUserEntity(userDto);
            userEntity.setId(id);
            UserEntity userEntityAfter = userRepository.save(userEntity);   
            return Optional.of(userMapper.toUserDto(userEntityAfter));
        } else {
            return Optional.empty();
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        logger.info("Loaded :" + user.toString());
        return userMapper.toUserDto(user);
    }
}

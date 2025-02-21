package lt.ca.javau11.gr.carservice.service;

import lt.ca.javau11.gr.carservice.entity.UserEntity;
import lt.ca.javau11.gr.carservice.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getProfile(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity updateProfile(String username, UserEntity updatedUser) {
        return userRepository.findByUsername(username)
                .map(existingUser -> {
                    existingUser.setUsername(updatedUser.getUsername());
                    existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String getAuthenticatedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
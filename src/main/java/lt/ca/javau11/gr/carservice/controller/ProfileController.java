package lt.ca.javau11.gr.carservice.controller;

import lt.ca.javau11.gr.carservice.entity.UserEntity;
import lt.ca.javau11.gr.carservice.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@CrossOrigin(origins = "http://localhost:5173") // Allow frontend access
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/carservice/user/profile/{username}")
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {
        Optional<UserEntity> user = profileService.getProfile(username);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @PutMapping("/carservice/user/profile/{username}")
    public ResponseEntity<?> updateProfile(@PathVariable String username, @RequestBody UserEntity updatedUser) {

        try {
            UserEntity updatedProfile = profileService.updateProfile(username, updatedUser);
            return ResponseEntity.ok(updatedProfile);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

package lt.ca.javau11.gr.carservice.controller;

import lt.ca.javau11.gr.carservice.dto.UserDto;

import lt.ca.javau11.gr.carservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
public class UserController {

    private final UserService userService;


    public  UserController(UserService userService) {
                this.userService = userService;
    }


    @PostMapping("/carservice/user/create")
    public ResponseEntity<UserDto>  createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/carservice/user/get/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/carservice/user/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){

        Optional<UserDto> userInBox = userService.getUserById(id);
        return userInBox
                .map( ResponseEntity::ok )
                .orElseGet( () -> ResponseEntity.notFound().build());
    }


    @PutMapping("/carservice/user/update/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserDto userDto){
        Optional<UserDto> userInBox = userService.updateUser(id, userDto);

        return userInBox
                .map( ResponseEntity::ok )
                .orElseGet( () -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("carservice/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/carservice/user/profile")
    public ResponseEntity<UserDto> getCurrentUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/carservice/user/profile")
    public ResponseEntity<UserDto> updateCurrentUserProfile(@RequestBody UserDto userDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.getUserByUsername(username)
                .map(currentUser -> {
                    userDto.setId(currentUser.getId());
                    return userService.updateUser(currentUser.getId(), userDto)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/carservice/user/profile")
    public ResponseEntity<Void> deleteCurrentUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.getUserByUsername(username)
                .map(user -> {
                    userService.deleteUser(user.getId());
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/carservice/auth/logout")
    public ResponseEntity<Void> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }
}

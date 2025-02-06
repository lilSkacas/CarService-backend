package lt.ca.javau11.gr.carservice.service;

import lt.ca.javau11.gr.carservice.dto.UserDto;
import lt.ca.javau11.gr.carservice.entity.RoleEntity;
import lt.ca.javau11.gr.carservice.entity.UserEntity;
import lt.ca.javau11.gr.carservice.enums.ERole;
import lt.ca.javau11.gr.carservice.repository.RoleRepository;
import lt.ca.javau11.gr.carservice.repository.UserRepository;
import lt.ca.javau11.gr.carservice.request.LoginRequest;
import lt.ca.javau11.gr.carservice.request.SignupRequest;
import lt.ca.javau11.gr.carservice.response.JwtResponse;
import lt.ca.javau11.gr.carservice.response.MessageResponse;
import lt.ca.javau11.gr.carservice.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthService (
                        AuthenticationManager authenticationManager,
                        UserRepository userRepository,
                        RoleRepository roleRepository,
                        PasswordEncoder encoder,
                        JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;


        logger.info("JwtUtils injected: " + (jwtUtils != null));
    }



    public JwtResponse authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDto userDetails = (UserDto) authentication.getPrincipal();
        logger.info("Before: " + userDetails.toString());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

    public MessageResponse registerUser(SignupRequest signUpRequest) {

        checkUserExists(signUpRequest);


        UserEntity user = createNewUser(signUpRequest);
        Set<RoleEntity> roles = getInitialRoles(signUpRequest);

        user.setRoles(roles);

        logger.info("Before: " + user.toString());
        user = userRepository.save(user);
        logger.info("After: " + user.toString());

        return new MessageResponse("User registered successfully!");
    }



    private UserEntity createNewUser(SignupRequest signUpRequest) {
        UserEntity user = new UserEntity(
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber());
        logger.info(user.toString());
        return user;
    }



    private Set<RoleEntity> getInitialRoles(SignupRequest signUpRequest) {
        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            for (String role : strRoles) {
                RoleEntity resolvedRole = roleRepository.findByName(ERole.valueOf("ROLE_" + role.toUpperCase()))
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(resolvedRole);
            }
        }
        return roles;
    }



    private void checkUserExists(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Username is already in use!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Email is already in use!");
        }
    }
}

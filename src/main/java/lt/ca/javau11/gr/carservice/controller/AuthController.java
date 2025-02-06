package lt.ca.javau11.gr.carservice.controller;

import lt.ca.javau11.gr.carservice.request.LoginRequest;
import lt.ca.javau11.gr.carservice.request.SignupRequest;
import lt.ca.javau11.gr.carservice.response.JwtResponse;
import lt.ca.javau11.gr.carservice.response.MessageResponse;
import lt.ca.javau11.gr.carservice.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;


@CrossOrigin
@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/carservice/auth/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/carservice/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        logger.info("Trying to signup \n" + signUpRequest);

        try {
            MessageResponse response = authService.registerUser(signUpRequest);
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {

            return ResponseEntity.status(e.getStatusCode())
                    .body(new MessageResponse(e.getReason()));
        }
    }
}
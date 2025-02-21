package lt.ca.javau11.gr.carservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class RoleController {

    @GetMapping("/carservice/get/mod")
    public ResponseEntity<String> getModePage(){
        return new ResponseEntity<>("Moderator page is accessible", HttpStatus.OK);
    }

    @GetMapping("/carservice/get/user")
    public ResponseEntity<String> getUserPage(){
        return new ResponseEntity<>("User page is accessible", HttpStatus.OK);
    }

    @GetMapping("/carservice/get/admin")
    public ResponseEntity<String> getAdminPage(){
        return new ResponseEntity<>("Admin page is accessible", HttpStatus.OK);
    }

    @GetMapping("/carservice/get/all")
    public ResponseEntity<String> getHomePage(){
        return new ResponseEntity<>("Home page is accessible", HttpStatus.OK);
    }
}


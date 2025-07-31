package example.kafkauser.controller;

import example.kafkauser.model.Jwt.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class ControllerHome {

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login() {

        return ResponseEntity.status(HttpStatus.OK).body("123");
    }

    @GetMapping("")
    public ResponseEntity<?> home() {
        return ResponseEntity.status(HttpStatus.OK).body("home /");
    }

}

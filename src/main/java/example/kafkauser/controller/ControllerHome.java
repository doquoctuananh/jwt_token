package example.kafkauser.controller;

import example.kafkauser.model.CustomUserDetail;
import example.kafkauser.model.Jwt.JwtUntil;
import example.kafkauser.model.Jwt.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class ControllerHome {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUntil jwtUntil;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword())
        );
        CustomUserDetail customUserDetail = (CustomUserDetail) auth.getPrincipal();
        String username = customUserDetail.getUsername();
        String role = customUserDetail.getUser().getRole().getRoleName();
        String token = jwtUntil.generateToken(username, role);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @GetMapping("/")
    public ResponseEntity<?> home() {
        return ResponseEntity.status(HttpStatus.OK).body("home 123");
    }

}

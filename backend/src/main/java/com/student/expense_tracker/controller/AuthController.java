package com.student.expense_tracker.controller;

import com.student.expense_tracker.model.User;
import com.student.expense_tracker.repository.UserRepository;
import com.student.expense_tracker.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private JWTUtil jwtUtil;
    @Autowired private PasswordEncoder encoder;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.badRequest()
                    .body(Map.of("error","Username already exists!"));
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message","User registered successfully!"));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User dbUser=userRepository.findByUsername(user.getUsername()).orElse(null);
        if(dbUser==null || !encoder.matches(user.getPassword(), dbUser.getPassword())){
            return ResponseEntity.status(401).body(Map.of("error","Invalid username or password!"));
        }
        String token=jwtUtil.generateToken(dbUser.getUsername());
        return ResponseEntity.ok(Map.of("token",token));
    }
}
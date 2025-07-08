package com.example.DataTabledb.service;

import com.example.DataTabledb.repository.UserRepository;
import com.example.DataTabledb.model.User;
import com.example.DataTabledb.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public String signup(String email, String password) {
        if (userRepo.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(password);
        userRepo.save(new User(email, hashedPassword));
        return "User registered successfully";
    }

    public String login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtils.generateToken(user.getEmail());
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}

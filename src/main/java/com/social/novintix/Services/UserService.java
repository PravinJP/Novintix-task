package com.social.novintix.Services;


import com.social.novintix.Config.JwtUtils;
import com.social.novintix.dto.AuthResponse;
import com.social.novintix.dto.LoginRequest;
import com.social.novintix.dto.RegisterRequest;
import com.social.novintix.model.User;
import com.social.novintix.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;


    public UserService(UserRepository repo, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public User register(RegisterRequest data) {
        User u = new User();
        u.setUsername(data.getUsername());
        u.setEmailId(data.getEmail());
        u.setDisplayName(data.getName());
        u.setHashedPassword(encoder.encode(data.getPassword()));

        return repo.save(u);
    }

    public AuthResponse login(LoginRequest req) {


        User found = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (!encoder.matches(req.getPassword(), found.getHashedPassword())) {
            throw new RuntimeException("Invalid password");
        }


        String token = jwtUtils.createToken(found.getUsername());


        return new AuthResponse(token, "Login successful");
    }
}

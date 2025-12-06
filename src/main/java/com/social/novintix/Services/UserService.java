package com.social.novintix.Services;


import com.social.novintix.dto.LoginRequest;
import com.social.novintix.dto.RegisterRequest;
import com.social.novintix.model.User;
import com.social.novintix.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(RegisterRequest data) {
        User u = new User();
        u.setUsername(data.getUsername());
        u.setEmailId(data.getEmail());
        u.setDisplayName(data.getName());
        u.setHashedPassword(encoder.encode(data.getPassword()));

        return repo.save(u);
    }

    public User login(LoginRequest req) {
        User found = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("No such user"));

        if (!encoder.matches(req.getPassword(), found.getHashedPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return found;
    }
}

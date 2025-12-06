package com.social.novintix.Controller;



import com.social.novintix.Config.JwtUtils;
import com.social.novintix.Services.UserService;
import com.social.novintix.dto.AuthResponse;
import com.social.novintix.dto.LoginRequest;
import com.social.novintix.dto.RegisterRequest;
import com.social.novintix.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        User saved = userService.register(req);
        return ResponseEntity.ok("User registered: " + saved.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        AuthResponse response = userService.login(req);
        return ResponseEntity.ok(response);
    }

}


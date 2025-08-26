package com.stevenmadeit.financetracker.user;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // Creates a user
    @Transactional
    public UserResponse create(UserRegistrationRequest req) {
        String email = req.email().toLowerCase().trim();

        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        String hashed = encoder.encode(req.password());

        User entity = new User(req.name(), email, hashed);
        User saved = repo.save(entity);

        return toResponse(saved);
    }

    // Read a user by id and return a safe DTO
    @Transactional(readOnly = true)
    public UserResponse get(UUID id){
        User user = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return toResponse(user);
    }

    private UserResponse toResponse(User u) {
        return new UserResponse(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getCreatedAt()
        );
    }
}

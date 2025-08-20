package ru.filche.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.filche.test.dto.LoginRequest;
import ru.filche.test.dto.LoginResponse;
import ru.filche.test.entity.BankUser;
import ru.filche.test.repository.BankUserRepository;
import ru.filche.test.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final BankUserRepository bankUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        BankUser bankUser = bankUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), bankUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(bankUser.getUsername(), bankUser.getRole().getAuthorities());
        return new LoginResponse(token, bankUser.getRole().name());
    }
}

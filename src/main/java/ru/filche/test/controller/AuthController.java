package ru.filche.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.filche.test.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
}

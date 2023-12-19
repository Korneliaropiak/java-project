package com.lab.library.controller;

import com.lab.library.domain.User;
import com.lab.library.domain.UserDto;
import com.lab.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthRestController {
    private final UserService userService;
    public AuthRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public UserDto showRegistrationForm() {
        return new UserDto();
    }

    @PostMapping("/register/save")
    public String registration(@Valid @RequestBody UserDto userDto,
                               BindingResult result) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            return "registration failed";
        }
        userService.saveUser(userDto);
        return "registration successful";
    }

    @GetMapping("/users")
    public List<UserDto> users() {
        return userService.findAllUsers();
    }
}


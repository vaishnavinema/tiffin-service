package com.tiffinservice.tiffin_service.controller;

import com.tiffinservice.tiffin_service.model.User;
import com.tiffinservice.tiffin_service.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }
}
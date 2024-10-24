package com.townsq.api.controllers;

import com.townsq.api.domain.user.UserEditDTO;
import com.townsq.api.repositories.UserRepository;
import com.townsq.api.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUser(@RequestHeader(name = "Authorization") String token, Authentication authentication) {
        return userService.getUser(token.replace("Bearer ", ""), authentication);
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> editUser(@RequestBody @Valid UserEditDTO data,
                                      @PathVariable("id") Long id) {
        return userService.editUser(data, id);
    }
}

package com.townsq.api.services;

import com.townsq.api.config.security.TokenService;
import com.townsq.api.domain.user.*;
import com.townsq.api.repositories.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    public ResponseEntity createUser(RegisterDTO data) {
        if(userRepository.findByUsername(data.username()) != null)
            return ResponseEntity.badRequest().body(new UserEditDTOResponse("Username already taken!"));

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.role());

        ReturnRegisterDTO returnDTO = new ReturnRegisterDTO(data.username());

        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnDTO);
    }

    public ResponseEntity getUser(String bearer, Authentication authentication) {
        var username = tokenService.validateToken(bearer);
        UserDetails optionalUser = userRepository.findByUsername(username);
        if(optionalUser != null) {
            User user = (User) authentication.getPrincipal();
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getRole());
            return ResponseEntity.ok().body(userDTO);
        }

        return null;
    }

    public ResponseEntity<?> editUser(@Valid UserEditDTO data, Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User newUser = new User(id, data.username(), data.role(), user.get().getPassword());
            userRepository.save(newUser);
            UserEditDTOResponse userEditDTOResponse = new UserEditDTOResponse("User edited with success!");
            return ResponseEntity.ok().body(userEditDTOResponse);
        }else {
            UserEditDTOResponse userEditDTOResponse = new UserEditDTOResponse("User not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userEditDTOResponse);
        }
    }
}

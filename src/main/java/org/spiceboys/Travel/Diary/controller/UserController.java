package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.dto.UserDTO;
import org.spiceboys.Travel.Diary.service.UserService;
import org.spiceboys.Travel.Diary.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        UserDTO createdUserDTO = new UserDTO(createdUser.getUserId(), createdUser.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> fetchUserByUsername(@PathVariable String username) {
        User fetchedUser = userService.getUserByUsername(username);
        UserDTO fetchedUserDTO = new UserDTO(fetchedUser.getUserId(), fetchedUser.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(fetchedUserDTO);
    }
}

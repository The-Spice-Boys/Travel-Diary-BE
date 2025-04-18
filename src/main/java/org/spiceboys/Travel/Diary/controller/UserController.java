package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.dto.UserDTO;
import org.spiceboys.Travel.Diary.dto.PublicUserDTO;
import org.spiceboys.Travel.Diary.service.UserService;
import org.spiceboys.Travel.Diary.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<PublicUserDTO> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        PublicUserDTO createdUserDTO = new PublicUserDTO(
                createdUser.getUserId(),
                createdUser.getUsername(),
                createdUser.getBio(),
                createdUser.getProfilePicUrl(),
                createdUser.getIsPrivate()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> fetchUserByUsername(@PathVariable String username) {
        UserDTO fetchedUser = userService.getUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(fetchedUser);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<UserDTO> fetchUserByUserId(@PathVariable Long userId) {
        UserDTO fetchedUser = userService.getUserByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(fetchedUser);
    }

}

package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.dto.UserDTO;
import org.spiceboys.Travel.Diary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    @PatchMapping("/userId/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId,
                                              @RequestBody Map<String, Object> updatedFields) {
        UserDTO updatedUser = userService.updateUser(userId, updatedFields);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}

package org.spiceboys.Travel.Diary.controller;

import org.spiceboys.Travel.Diary.dto.PatchUserDTO;
import org.spiceboys.Travel.Diary.dto.UserDTO;
import org.spiceboys.Travel.Diary.service.UserService;
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

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> fetchUserByUsername(@PathVariable String username) {
        UserDTO fetchedUser = userService.getUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(fetchedUser);
    }

    @PatchMapping("/username/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody PatchUserDTO patchUserDTO) {
        UserDTO updatedUser = userService.updateUser(username, patchUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}

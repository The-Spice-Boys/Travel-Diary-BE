package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.dto.*;
import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.User;
import org.spiceboys.Travel.Diary.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new ContentNotFoundException("User with username " + " not found"));
        return createUserDTO(user);
    }

    public UserDTO getUserByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ContentNotFoundException("User with id " + userId + " not found"));
        return createUserDTO(user);
    }

    public UserDTO updateUser(Long userId, Map<String, Object> updates) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ContentNotFoundException("User with id " + userId + " not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "username":
                    user.setUsername((String) value);
                    break;
                case "firstName":
                    user.setFirstName((String) value);
                    break;
                case "lastName":
                    user.setLastName((String) value);
                    break;
                case "email":
                    user.setEmail((String) value);
                    break;
                case "password":
                    user.setPassword((String) value);
                    break;
                case "bio":
                    user.setBio((String) value);
                    break;
                case "profilePicUrl":
                    user.setProfilePicUrl((String) value);
                    break;
                case "private":
                    user.setIsPrivate(Boolean.parseBoolean((String) value));
            }
        });

        User updatedUser = userRepository.save(user);
        return createAuthorisedUserDTO(updatedUser);
    }

    private UserDTO createUserDTO(User user) {
        return user.getIsPrivate()
                ? new PrivateUserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    true)
                : new PublicUserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getBio(),
                    user.getProfilePicUrl(),
                    user.getIsPrivate());
    }

    private UserDTO createAuthorisedUserDTO(User user) {
        return new AuthorisedUserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getBio(),
                user.getProfilePicUrl(),
                user.getIsPrivate(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

}

